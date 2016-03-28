package com.dilipkumarg.fileupload.repository;


import com.dilipkumarg.fileupload.io.PathInformation;
import com.dilipkumarg.fileupload.io.Resource;

/**
 * @author Dilip Kumar
 * @since 21/3/16
 */
public interface FileRepository {

    PathInformation save(Resource resource, PathInformation pathInformation);

    PathInformation save(Resource resource);

    boolean exists(String filePath);

    Resource getFileResource(String filePath);

}
