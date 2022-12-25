package com.geektcp.common.spring.util;

import com.geektcp.common.spring.context.SpringContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import java.io.FileNotFoundException;
import java.text.MessageFormat;

/**
 * @author tanghaiyang on 2017/11/07.
 */
public class ResourceUtils {

    public static String getActiveProfile() {
        Environment env = SpringContext.getEnv();
        String activeProfile = env.getActiveProfiles()[0];
        return MessageFormat.format("application-{}.properties", activeProfile);
    }

    public static String getPath(String resourceLocation) {
        try {
            return org.springframework.util.ResourceUtils
                    .getURL("classpath:" + resourceLocation).getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getJarPath() {
        String path = getResourcePath();
        if (path.contains("/target/")) {
            path = StringUtils.substringBefore(path, "target/");
            return path + "target/";
        } else if (path.endsWith("/lib/")) {
            return StringUtils.substringBefore(path, "lib/");
        }
        return path;
    }

    public static String getPath() {
        String path = getResourcePath();
        if (path.contains("/target/")) {
            if (path.contains("/test-classes/")) {
                return path.replace("test-classes", "classes");
            }
        } else {
            return path.replace("/lib/", "/conf/");
        }
        return path;
    }

    private static String getResourcePath() {
        String path = ResourceUtils.class.getResource("/").getPath();
        return path;
    }

    public static String getResourcePath(String resourceLocation) {
        String path = ResourceUtils.class.getResource(resourceLocation).getPath();
        return path;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(Resource.getPath("mysql.sql"));
        System.out.println(ResourceUtils.getPath());
        System.out.println(ResourceUtils.getJarPath());
        System.out.println(ResourceUtils.getResourcePath());
    }
}
