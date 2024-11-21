package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.listener.ListenerChain;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.registry.AbstractRegistry;

/**
 * {@linkplain org.hipeday.sphere.core.listener.ListenerChain 监听器调用链} 注册表 key使用 {@linkplain Client#clientId()} 作为唯一标识
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class ListenerChainRegistry extends AbstractRegistry<String, ListenerChain> {
    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.LISTENER_CHAIN_REGISTRY_NAME;
    }
}
