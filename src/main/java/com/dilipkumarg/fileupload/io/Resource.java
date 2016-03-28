package com.dilipkumarg.fileupload.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dilip Kumar
 * @since 26/3/16
 */
public interface Resource {

    String getName();

    InputStream getInputStream() throws IOException;

    boolean exists();
}
