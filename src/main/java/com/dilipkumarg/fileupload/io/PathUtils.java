package com.dilipkumarg.fileupload.io;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Dilip Kumar
 * @since 26/3/16
 */
public class PathUtils {

    public static String getRootPath(String path) {
        final String rootPath = StringUtils.substringBeforeLast(path, "/");
        return (rootPath.length() == path.length()) ? "" : rootPath;
    }

    public static String getFileName(String path) {
        final String fileName = StringUtils.substringAfterLast(path, "/");
        return fileName.length() == 0 ? path : fileName;
    }

    public static String getFileNameWithoutExtension(String fileName) {
        return StringUtils.substringBeforeLast(fileName, ".").replace(" ", "");
    }

    public static String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, ".");
    }


    public static String combinePaths(String path1, String path2) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(path1)) {
            if (!path1.startsWith("/")) {
                sb.append("/");
            }

            if (path1.endsWith("/")) {
                path1 = path1.substring(0, path1.length() - 1);
            }

            sb.append(path1);
        }

        if (StringUtils.isNotBlank(path2)) {
            if (!path2.startsWith("/")) {
                sb.append("/");
            }

            sb.append(path2);
        }

        return sb.toString();
    }
}
