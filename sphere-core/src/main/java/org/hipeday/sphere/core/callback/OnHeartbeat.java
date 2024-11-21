package org.hipeday.sphere.core.callback;

import org.hipeday.sphere.core.context.SphereContext;

/**
 * 在触发心跳时的回调
 *
 * @author jixiangup
 * @since 1.0.0
 */
@FunctionalInterface
public interface OnHeartbeat {

    void heartbeat(SphereContext context, byte[] heartbeatMetadata);

}
