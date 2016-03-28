package com.dilipkumarg.fileupload.io;

/**
 * @author Dilip Kumar
 * @since 26/3/16
 */
public class FileSystemException extends RuntimeException {

    public FileSystemException(final String message) {
        super(message);
    }

    public FileSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FileSystemException(final Throwable cause) {
        super(cause);
    }
}
