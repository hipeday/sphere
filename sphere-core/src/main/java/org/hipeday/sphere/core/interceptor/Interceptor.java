package org.hipeday.sphere.core.interceptor;


import org.hipeday.sphere.core.callback.OnActive;
import org.hipeday.sphere.core.callback.OnCommandAnalysis;
import org.hipeday.sphere.core.context.SphereContext;

/**
 * 拦截器接口
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface Interceptor extends OnActive, OnCommandAnalysis {

    @Override
    default void activated(SphereContext context) {
        // do nothing
    }

    @Override
    default Object onBeforeExecute(SphereContext context, Object payload) {
        // do nothing
        return payload;
    }
}
