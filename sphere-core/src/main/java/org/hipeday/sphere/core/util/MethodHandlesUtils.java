package org.hipeday.sphere.core.util;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

/**
 * 函数处理器工具类
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class MethodHandlesUtils {

    private final static Method PRIVATE_LOOKUP_IN;

    static {
        try {
            PRIVATE_LOOKUP_IN = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取函数处理器
     *
     * @param callerClass 调用者类
     * @return 函数处理器
     */
    public static MethodHandles.Lookup lookup(Class<?> callerClass) {
        try {
            return (MethodHandles.Lookup) PRIVATE_LOOKUP_IN.invoke(MethodHandles.class, callerClass, MethodHandles.lookup());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
