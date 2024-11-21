package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.registry.AbstractRegistry;

/**
 * {@linkplain org.hipeday.sphere.core.network.Client 网络客户端} 注册表
 * 使用网络客户端ID作为key 使用 {@linkplain Client} 作为注册对象
 *
 * @see Client
 * @author jixiangup
 * @since 1.0.0
 */
public class NetworkClientRegistry extends AbstractRegistry<String, Client> {

    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.NETWORK_CLIENT_REGISTRY_NAME;
    }
}
