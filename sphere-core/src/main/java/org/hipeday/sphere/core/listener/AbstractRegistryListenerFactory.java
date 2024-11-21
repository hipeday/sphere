package org.hipeday.sphere.core.listener;

import org.hipeday.sphere.core.context.ApplicationContext;
import org.hipeday.sphere.core.registry.support.ListenerRegistry;

/**
 * 抽象注册表监听器工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractRegistryListenerFactory implements ListenerFactory {

    protected final ListenerRegistry listenerRegistry;

    public AbstractRegistryListenerFactory() {
        ApplicationContext applicationContext = ApplicationContext.getApplicationContext();
        listenerRegistry = applicationContext.getListenerRegistry();
    }

}
