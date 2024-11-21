package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.callback.OnBeforeInvoke;
import org.hipeday.sphere.core.context.SphereContext;

/**
 * 拦截器接口
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface Interceptor extends OnBeforeInvoke {

    @Override
    default Object onBeforeExecute(SphereContext context) {
        // do nothing
        return context.getPayload();
    }
}
