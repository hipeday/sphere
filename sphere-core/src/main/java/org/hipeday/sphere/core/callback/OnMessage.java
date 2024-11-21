package org.hipeday.sphere.core.callback;

import org.hipeday.sphere.core.context.SphereContext;

/**
 * 收到消息的回调
 *
 * @author jixiangup
 * @since 1.0.0
 */
@FunctionalInterface
public interface OnMessage {

    void message(SphereContext context, byte[] message);

}
