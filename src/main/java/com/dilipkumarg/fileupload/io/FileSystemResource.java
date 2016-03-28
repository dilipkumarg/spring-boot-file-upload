package com.dilipkumarg.fileupload.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dilip Kumar
 * @since 26/3/16
 */
public class FileSystemResource implements Resource {

    private final File file;

    public FileSystemResource(final File file) {
        this.file = file;
    }


    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public boolean exists() {
        return file.exists();
    }
}
