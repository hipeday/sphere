package org.hipeday.sphere.core.reflection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jixiangup
 * @since 1.1.0.20
 */
public class DefaultObjectFactory implements ObjectFactory {

    private final Map<Class<?>, Object> SPHERE_OBJECT_CACHE = new ConcurrentHashMap<>();

    /**
     * 框架中各种对象的构造函数
     */
    private final Map<Class<?>, ObjectConstructor<?>> CONSTRUCTOR_CACHE = new ConcurrentHashMap<>();

    /**
     * 从缓存获取接口对象
     *
     * @param clazz 接口类型
     * @param <T> 接口类型
     * @return 接口对象
     */
    protected <T> T getObjectFromCache(Class<T> clazz) {
        return (T) SPHERE_OBJECT_CACHE.get(clazz);
    }

    @Override
    public <T> T getObject(Class<T> clazz) {
        if (clazz == null) {
            return null;
        }

        Object obj = SPHERE_OBJECT_CACHE.get(clazz);
        if (obj != null) {
            return (T) obj;
        }

        final ObjectConstructor<?> constructor = CONSTRUCTOR_CACHE.get(clazz);
        if (constructor != null) {
            obj = constructor.construct();
            if (obj != null) {
                SPHERE_OBJECT_CACHE.put(clazz, obj);
            }
        }

        return (T) obj;
    }

    @Override
    public void registerConstructor(Class<?> clazz, ObjectConstructor<?> constructor) {
        CONSTRUCTOR_CACHE.put(clazz, constructor);
    }

    @Override
    public void registerObject(Class<?> clazz, Object object) {
        SPHERE_OBJECT_CACHE.put(clazz, object);
    }
}
