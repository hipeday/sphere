package org.hipeday.sphere.core.util;

import java.util.Objects;

/**
 * 数组的工具类
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class ArrayUtils {

    public static boolean isEmpty(Object[] array) {
        return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static boolean contains(Object[] array, Object contains) {
        if (isEmpty(array)) {
            return false;
        }
        for (Object o : array) {
            if (Objects.nonNull(o) && o.equals(contains)) {
                return true;
            }
        }
        return false;
    }

    public static void reserve(Object[] arr) {
        org.apache.commons.lang3.ArrayUtils.reverse(arr);
    }

}
