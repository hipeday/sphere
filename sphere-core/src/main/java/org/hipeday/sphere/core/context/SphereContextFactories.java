package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.context.support.TCPSphereContextFactory;
import org.hipeday.sphere.core.network.NetworkClientConfig;
import org.hipeday.sphere.core.reflection.Function;

/**
 * Sphere 上下文工厂对象获取
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SphereContextFactories {

    public static SphereContextFactory getSphereContextFactory(NetworkClientConfig<?> config, Function<?> function) {
        return switch (config.protocol()) {
            case TCP -> new TCPSphereContextFactory<>(config, function);
            case SERIAL_PORTS -> throw new UnsupportedOperationException("Serial ports not supported yet");
        };
    }


}
