package org.hipeday.sphere.core.callback;

import org.hipeday.sphere.core.context.SphereContext;

/**
 * 连接断开回调
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface Disconnect {

    /**
     * 连接断开回调
     */
    void disconnected(SphereContext context);

}
