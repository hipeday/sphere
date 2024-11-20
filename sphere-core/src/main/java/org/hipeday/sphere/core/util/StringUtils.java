package org.hipeday.sphere.core.util;

import java.util.Collection;

/**
 * 字符串工具类
 *
 * @author jixiangup
 * @since 1.0.0
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

    public static String[] toStringArray(Collection<String> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }
        String[] array = new String[collection.size()];
        return collection.toArray(array);
    }

}
