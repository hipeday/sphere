package org.hipeday.sphere.core.listener;

import org.hipeday.sphere.core.context.SphereContext;

/**
 * 监听器
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public interface Listener {

    void listener(SphereContext context, byte[] message);

}
