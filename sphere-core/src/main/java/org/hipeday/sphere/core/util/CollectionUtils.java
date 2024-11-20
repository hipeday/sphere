package org.hipeday.sphere.core.util;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     *
     * @param c 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> c) {
        return org.apache.commons.collections4.CollectionUtils.isEmpty(c);
    }

}
