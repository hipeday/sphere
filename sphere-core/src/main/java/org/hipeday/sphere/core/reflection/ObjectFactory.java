package org.hipeday.sphere.core.reflection;

/**
 * Sphere 对象工厂
 * <p>适用于 Sphere 相关接口(飞请求客户端接口)和回调函数的工厂接口</p>
 * <p>当这些累没有实力的情况下，会先实例化并缓存下来，以后再取回通过缓存获取对象</p>
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public interface ObjectFactory {

    /**
     * 获取对象
     * @param clazz 对象类型
     * @param <T> 对象接口烦类型
     * @return 对象实例
     */
    <T> T getObject(Class<T> clazz);

    /**
     * 注册构造对象接口
     *
     * @param clazz 对象类型
     * @param constructor 构造对象接口
     */
    void registerConstructor(Class<?> clazz, ObjectConstructor<?> constructor);

    /**
     * 注册对象
     *
     * @param clazz 对象类型
     * @param object 对象实例
     */
    void registerObject(Class<?> clazz, Object object);

}
