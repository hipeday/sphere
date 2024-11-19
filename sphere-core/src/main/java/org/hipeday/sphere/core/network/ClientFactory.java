package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.config.SphereConfiguration;

/**
 * 网络客户端工厂
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
@FunctionalInterface
public interface ClientFactory<T> {

    /**
     * 创建客户端
     *
     * @return 客户端
     */
    Client createClient(SphereClientConfig<T> config, SphereConfiguration configuration);

}
