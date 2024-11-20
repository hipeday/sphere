package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.annotation.ClientProtocol;
import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.assertion.Assert;
import org.hipeday.sphere.core.listener.Listener;

import java.lang.reflect.InvocationTargetException;

/**
 * Sphere 客户端配置
 *
 * @author jixiangup
 * @since 1.0.0
 */
public record SphereClientConfig<T>(
        String clientId,
        ClientProtocol protocol,
        InetAddress serverAddress,
        Class<T> interfaceClass,
        String heartbeat
) {

    private static Listener heartbeatListener;

    public Listener getHeartbeatListener() {
        SphereClient sphereClient = getSphereClient();
        Class<? extends Listener> heartbeatListenerClass = sphereClient.heartbeatListener();

        try {
            heartbeatListener = heartbeatListenerClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException("The heartbeat listener class must have a default constructor");
        }
        return heartbeatListener;
    }

    public SphereClient getSphereClient() {
        SphereClient sphereClient = interfaceClass.getAnnotation(SphereClient.class);
        Assert.notNull(sphereClient, "The interface class does not use @SphereClient annotation");
        return sphereClient;
    }

}
