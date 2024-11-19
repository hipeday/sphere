package org.hipeday.sphere.core.util;

/**
 * 字符串工具类
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param text 字符串
     * @return 是否为空
     */
    public static boolean hasText(String text) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(text);
    }

}
