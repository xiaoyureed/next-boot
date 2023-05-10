package io.github.xiaoyureed.nextbootslim.core.util;

import org.apache.commons.io.FilenameUtils;

public final class FileNameUtils {
    private FileNameUtils() {}

    public static String getExtension(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }
}
