package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.listener.Listener;
import org.hipeday.sphere.core.registry.AbstractRegistry;

/**
 * {@linkplain Listener 监听器} 注册表
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ListenerRegistry extends AbstractRegistry<Class<? extends Listener>, Listener> {

    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.LISTENER_REGISTRY_NAME;
    }
}
