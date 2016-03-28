package com.dilipkumarg.fileupload.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dilipkumarg.fileupload.io.FileSystemException;
import com.dilipkumarg.fileupload.io.FileSystemResource;
import com.dilipkumarg.fileupload.io.PathInformation;
import com.dilipkumarg.fileupload.io.Resource;


/**
 * @author Dilip Kumar
 * @since 21/3/16
 */
@Service
public class LocalFileRepository implements FileRepository, InitializingBean {

    @Value("${app.properties.storage.basePath}")
    private File baseDir;

    @Override
    public PathInformation save(final Resource resource, final PathInformation pathInformation) {
        File rootDir = baseDir;
        if (pathInformation.getDirectoryPath().isPresent()) {
            rootDir = new File(baseDir, pathInformation.getDirectoryPath().get());
            if (!rootDir.mkdirs()) {
                throw new FileSystemException("Error while creating directories for, Directory:" + rootDir);
            }
        }

        try {
            Files.copy(resource.getInputStream(), new File(rootDir, pathInformation.getFileName()).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileSystemException("Error while saving file", e);
        }

        return pathInformation;
    }

    @Override
    public PathInformation save(final Resource resource) {
        return save(resource, new PathInformation(resource.getName()));
    }

    @Override
    public boolean exists(final String filePath) {
        return new File(baseDir, filePath).exists();
    }

    @Override
    public Resource getFileResource(final String filePath) {
        return new FileSystemResource(new File(baseDir, filePath));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!baseDir.exists()) {
            if (!baseDir.mkdirs()) {
                throw new FileSystemException("Error while creating uploads directory: " + baseDir);
            }
        }
    }
}
