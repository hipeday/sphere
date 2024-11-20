package org.hipeday.sphere.core.config;

/**
 * Sphere 属性文件配置
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SphereProperties {

    public String getProperty(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }

}
