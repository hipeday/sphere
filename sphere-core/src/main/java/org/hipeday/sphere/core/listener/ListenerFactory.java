package org.hipeday.sphere.core.listener;

/**
 * {@linkplain Listener 监听器} 工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface ListenerFactory {

    <T extends Listener> T getListener(Class<T> clazz);

}
