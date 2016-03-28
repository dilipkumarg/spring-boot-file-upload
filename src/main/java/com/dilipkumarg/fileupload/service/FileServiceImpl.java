package com.dilipkumarg.fileupload.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dilipkumarg.fileupload.io.PathInformation;
import com.dilipkumarg.fileupload.io.Resource;
import com.dilipkumarg.fileupload.repository.FileRepository;


/**
 * @author Dilip Kumar
 * @since 21/3/16
 */
@Service
public class FileServiceImpl implements FileService {

    public static final int FILE_NAME_LENGTH = 12;

    @Autowired
    private FileRepository repository;

    @Override
    public PathInformation save(final Resource resource, final PathInformation pathInformation) {
        return repository.save(resource, pathInformation);
    }

    @Override
    public PathInformation save(final Resource resource, final boolean overwrite) {

        String fileName = overwrite ? resource.getName() : getUniqueName(resource.getName());

        PathInformation pathInformation = new PathInformation(fileName);
        return save(resource, pathInformation);
    }

    @Override
    public Resource getFile(final String relativePath) {
        return repository.getFileResource(relativePath);
    }

    private String getUniqueName(String fileName) {
        String newName = fileName;
        if (repository.exists(newName)) {
            PathInformation pathInformation = new PathInformation(newName);
            newName = RandomStringUtils.randomAlphabetic(FILE_NAME_LENGTH) + pathInformation.getExtension();

            // checking recursively to find unique name
            newName = getUniqueName(newName);
        }
        return newName;
    }
}
