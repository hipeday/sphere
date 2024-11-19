package org.hipeday.sphere.core.client;

import org.hipeday.sphere.core.annotation.ClientAddress;
import org.hipeday.sphere.core.annotation.ClientId;
import org.hipeday.sphere.core.annotation.ClientPort;
import org.hipeday.sphere.core.annotation.ClientProtocol;
import org.hipeday.sphere.core.annotation.Heartbeat;
import org.hipeday.sphere.core.annotation.Payload;
import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.interceptor.OnActiveInterceptor;
import org.hipeday.sphere.core.listener.HeartbeatListener;

/**
 * 料架客户端
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
@SphereClient(
        protocol = ClientProtocol.TCP,
        interceptors = {OnActiveInterceptor.class},
        heartbeatListener = HeartbeatListener.class
)
public interface MaterialRackClient {

    void invoke(
            @ClientId String clientId,
            @ClientAddress String address,
            @ClientPort Integer port,
            @Payload String message,
            @Heartbeat String heartbeat
    );

}
