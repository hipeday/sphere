package org.hipeday.sphere.core.assertion;

import org.hipeday.sphere.core.util.ArrayUtils;
import org.hipeday.sphere.core.util.StringUtils;

/**
 * 断言工具
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class Assert {

    /**
     * 断言对象不为空
     *
     * @param object 对象
     * @param message 异常消息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言数组不为空
     *
     * @param arr 数组
     * @param message 异常消息
     */
    public static void notEmpty(Object[] arr, String message) {
        if (ArrayUtils.isEmpty(arr)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言字符串不为空
     *
     * @param text 字符串
     * @param message 异常消息
     */
    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

}
