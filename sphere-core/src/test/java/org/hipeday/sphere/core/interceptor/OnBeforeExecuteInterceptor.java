package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.context.SphereContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接成功之后的拦截器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class OnBeforeExecuteInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(OnBeforeExecuteInterceptor.class);

    @Override
    public Object onBeforeExecute(SphereContext context) {
        log.debug("On onBeforeExecute interceptor");
        return Interceptor.super.onBeforeExecute(context);
    }
}
