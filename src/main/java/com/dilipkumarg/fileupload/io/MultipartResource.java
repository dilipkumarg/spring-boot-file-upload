package com.dilipkumarg.fileupload.io;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dilip Kumar
 * @since 26/3/16
 */
public class MultipartResource implements Resource {

    private final MultipartFile multipartFile;

    public MultipartResource(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String getName() {
        return multipartFile.getOriginalFilename();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.multipartFile.getInputStream();
    }

    @Override
    public boolean exists() {
        return true;
    }

}
