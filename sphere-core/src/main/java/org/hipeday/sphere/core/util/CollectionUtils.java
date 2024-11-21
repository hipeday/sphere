package org.hipeday.sphere.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    /**
     * 将两个集合合并为一个 {@linkplain List list}
     * @param c1 集合1
     * @param c2 集合2
     * @return 合并之后的 {@linkplain List list}
     * @param <T> 集合中的值类型
     */
    public static <T> List<T> mergeToList(Collection<T> c1, Collection<T> c2) {
        if (isEmpty(c1) && isEmpty(c2)) {
            return null;
        }

        List<T> list;
        if (isEmpty(c1)) {
            return new ArrayList<>(c2);
        } else if (isEmpty(c2)) {
            return new ArrayList<>(c1);
        }

        list = new ArrayList<>(c1.size() + c2.size());
        list.addAll(c1);
        list.addAll(c2);

        return list;
    }

}
