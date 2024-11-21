package org.hipeday.sphere.core.listener.support;

import org.hipeday.sphere.core.listener.AbstractRegistryListenerFactory;
import org.hipeday.sphere.core.listener.Listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 默认监听器工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class DefaultListenerFactory extends AbstractRegistryListenerFactory {

    public DefaultListenerFactory() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Listener> T getListener(Class<T> clazz) {
        return (T) listenerRegistry.computeIfUnregister(clazz, k -> {
            try {
                Constructor<T> constructor = clazz.getConstructor();
                return constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new IllegalArgumentException("Failed to create interceptor instance", e);
            }
        });
    }
}
