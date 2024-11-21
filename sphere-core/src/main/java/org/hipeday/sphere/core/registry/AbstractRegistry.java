package org.hipeday.sphere.core.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 抽象注册表实现
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractRegistry<K, V> implements Registry<K, V> {

    protected AbstractRegistry() {
    }

    /**
     * 注册表实例容器
     */
    protected final Map<K, V> registry = new ConcurrentHashMap<>();

    @Override
    public void register(K key, V obj) {
        if (registry.containsKey(key)) {
            throw new IllegalArgumentException(key + " already exists in the registry " + getRegistryName());
        }
        registry.put(key, obj);
    }

    @Override
    public V computeIfUnregister(K key, Function<? super K, ? extends V> mappingFunction) {
        return registry.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public void unregister(K key) {
        registry.remove(key);
    }

    @Override
    public V getInstance(K key) {
        return registry.get(key);
    }

    @Override
    public boolean isRegistered(K key) {
        return registry.containsKey(key);
    }
}
