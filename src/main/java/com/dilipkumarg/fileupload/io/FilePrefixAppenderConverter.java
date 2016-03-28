package com.dilipkumarg.fileupload.io;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Dilip Kumar
 * @since 21/3/16
 */
@Converter
@Component
public class FilePrefixAppenderConverter implements AttributeConverter<String, String>, InitializingBean {

    private static String prefix;

    @Value("${app.properties.storage.staticFilesPrefix}")
    private String staticFilesPrefix;

    @Override
    public String convertToDatabaseColumn(final String attribute) {
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(final String dbData) {
        return StringUtils.isNotBlank(dbData) ? PathUtils.combinePaths(prefix, dbData) : null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isNotBlank(staticFilesPrefix) && !staticFilesPrefix.startsWith("${")) {
            prefix = staticFilesPrefix;
        }
    }
}
