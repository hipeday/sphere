package org.hipeday.sphere.core.listener.support;

import org.hipeday.sphere.core.annotation.ListenerHandler;
import org.hipeday.sphere.core.annotation.Order;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.listener.Listener;
import org.hipeday.sphere.core.logging.SphereLogger;

/**
 * 默认连接成功监听器
 *
 * @author jixiangup
 * @since 1.0.0
 */
@ListenerHandler
@Order(Integer.MIN_VALUE)
public class DefaultConnectedListener implements Listener {

    private static final SphereLogger log = SphereLogger.getLogger(DefaultConnectedListener.class);

    @Override
    public void activated(SphereContext context) {
        log.debug("{} activated", context.getClient().clientId());
    }

}
