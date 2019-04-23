package com.newer.mall.common.utils;

public class ImageUtil {
	
	private static String[] IMAGE_FILE_EXT = new String[] {"bmp", "jpg", "jpeg", "png", "gif"};

    public static String IMAGE_DIR = "G:/image/temp/";

    public static String IMAGE_DOMAIN = "http://192.168.43.29:80/upload/";

    public static boolean isImageAllowed(String imageExt) {
        for (String ext : IMAGE_FILE_EXT) {
            if (ext.equals(imageExt)) {
                return true;
            }
        }
        return false;
    }

}
