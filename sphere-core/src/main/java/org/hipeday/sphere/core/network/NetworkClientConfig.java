package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.annotation.ClientProtocol;

/**
 * Sphere 网络客户端配置
 *
 * @author jixiangup
 * @since 1.0.0
 */
public record NetworkClientConfig<T>(
        String clientId,
        ClientProtocol protocol,
        InetAddress serverAddress,
        Class<T> interfaceClass,
        String heartbeat
) {

}
