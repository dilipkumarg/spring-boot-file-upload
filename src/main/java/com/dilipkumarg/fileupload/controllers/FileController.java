package com.dilipkumarg.fileupload.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dilipkumarg.fileupload.io.MultipartResource;
import com.dilipkumarg.fileupload.io.PathInformation;
import com.dilipkumarg.fileupload.io.Resource;
import com.dilipkumarg.fileupload.service.FileService;


/**
 * @author Dilip Kumar
 * @since 26/3/16
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/{filePath:.+}")
    public void viewFile(
            @PathVariable(value = "filePath") String filePath, HttpServletResponse response) throws IOException {
        final Resource resource = fileService.getFile(filePath);

        if (resource.exists()) {
            String contentType = URLConnection.guessContentTypeFromName(resource.getName());

            if (StringUtils.isBlank(contentType)) {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            response.setContentType(contentType);

            IOUtils.copy(resource.getInputStream(), response.getOutputStream());
            response.flushBuffer();
        } else {
            throw new FileNotFoundException("No File found with given name:" + filePath);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            final PathInformation savedFileInfo = fileService.save(new MultipartResource(file));

            return savedFileInfo.getCompletePath();
        }
        throw new RuntimeException("No file selected");
    }


}
