package org.hipeday.sphere.core.client;

import org.hipeday.sphere.core.annotation.ClientAddress;
import org.hipeday.sphere.core.annotation.ClientId;
import org.hipeday.sphere.core.annotation.ClientPort;
import org.hipeday.sphere.core.annotation.ClientProtocol;
import org.hipeday.sphere.core.annotation.Heartbeat;
import org.hipeday.sphere.core.annotation.Command;
import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.annotation.SphereFunction;
import org.hipeday.sphere.core.interceptor.OnActiveInterceptor;
import org.hipeday.sphere.core.listener.support.DefaultConnectedListener;
import org.hipeday.sphere.core.listener.support.DefaultHeartbeatListener;

/**
 * 料架客户端
 *
 * @author jixiangup
 * @since 1.0.0
 */
@SphereClient(
        protocol = ClientProtocol.TCP,
        interceptors = {OnActiveInterceptor.class},
        listeners = {DefaultConnectedListener.class}
)
public interface MaterialRackClient {

    @SphereFunction(listeners = {DefaultHeartbeatListener.class})
    void invoke(
            @ClientId String clientId,
            @ClientAddress String address,
            @ClientPort Integer port,
            @Command String message,
            @Heartbeat String heartbeat
    );

}
