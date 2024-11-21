package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.callback.OnBeforeInvoke;
import org.hipeday.sphere.core.callback.OnError;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.exception.SphereRuntimeException;

/**
 * 拦截器接口
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface Interceptor extends OnBeforeInvoke, OnError {

    @Override
    default Object onBeforeExecute(SphereContext context) {
        // do nothing
        return context.getPayload();
    }

    @Override
    default void onError(SphereContext context, Throwable th) {
        // do nothing
    }
}
