package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.annotation.ClientProtocol;

/**
 * Sphere 客户端配置
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public record SphereClientConfig<T>(
        String clientId,
        ClientProtocol protocol,
        InetAddress serverAddress,
        Class<T> interfaceClass
) {

}
