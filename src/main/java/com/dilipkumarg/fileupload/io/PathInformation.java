package com.dilipkumarg.fileupload.io;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Dilip Kumar
 * @since 26/3/16
 */
public class PathInformation {

    private Optional<String> directoryPath;
    private String fileNameWithoutExtension;
    private String extension;

    public PathInformation(final String directoryPath, final String fileName) {
        this.directoryPath = StringUtils.isNotBlank(directoryPath) ? Optional.of(directoryPath) : Optional.empty();
        this.fileNameWithoutExtension = PathUtils.getFileNameWithoutExtension(fileName);
        this.extension = "." + PathUtils.getExtension(fileName);
    }

    public PathInformation(final String filePath) {
        this(PathUtils.getRootPath(filePath), PathUtils.getFileName(filePath));
    }

    public Optional<String> getDirectoryPath() {
        return directoryPath;
    }

    public String getFileNameWithoutExtension() {
        return fileNameWithoutExtension;
    }

    public String getExtension() {
        return extension;
    }

    public String getFileName() {
        return fileNameWithoutExtension + extension;
    }

    public String getCompletePath() {
        return directoryPath.isPresent() ? PathUtils.combinePaths(directoryPath.get(), getFileName()) : getFileName();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("directoryPath", directoryPath)
                .append("fileNameWithoutExtension", fileNameWithoutExtension)
                .append("extension", extension)
                .toString();
    }
}
