package org.hipeday.sphere.core.registry;

import java.util.function.Function;

/**
 * Sphere 系统注册表 用来保存一下系统信息
 *
 * @param <K> 注册对象的名称类型
 * @param <V> 注册对象的类型
 * @author jixiangup
 * @since 1.0.0
 */
public interface Registry<K, V> {

    /**
     * 注册对象到注册表
     *
     * @param key 注册实例对应的名称
     *             注意这里的 {@code key}
     *             不是注册表的名称 {@code getRegisterName()}
     *             而是注册表中注册的对象的名称
     *
     * @param obj  注册对象
     *             注意这里的 {@code obj}
     *             不是注册表的对象 {@code Register}
     *             而是注册表中注册的对象
     */
    void register(K key, V obj);

    /**
     * 计算如果 {@code key} 不存在注册表中 则调用 {@code mappingFunction} 将其注册到注册表中
     *
     * @param key key
     * @param mappingFunction 注册函数
     * @return 注册的实例
     */
    V computeIfUnregister(K key, Function<? super K, ? extends V> mappingFunction);

    /**
     * 取消注册
     *
     * @param key 注册实例对应的名称
     *             注意这里的 {@code key}
     *             不是注册表的名称 {@code getRegisterName()}
     *             而是注册表中注册的对象的名称
     */
    void unregister(K key);

    /**
     * 获取注册对象
     *
     * @param key 注册实例对应的名称
     * @return 注册对象
     */
    V getInstance(K key);

    /**
     * 是否注册 用来判断注册表名称 {@code key} 是否注册
     *
     * @param key 注册实例对应的名称
     *             注意这里的 {@code key}
     *             不是注册表的名称 {@code getRegisterName()}
     *             而是注册表中注册的对象的名称
     *
     * @return 是否注册
     */
    boolean isRegistered(K key);

    /**
     * 获取注册表名称
     *
     * @return 注册表名称
     */
    String getRegistryName();

}
