package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.context.SphereContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接成功之后的拦截器
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class OnActivatedInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(OnActivatedInterceptor.class);

    @Override
    public void activated(SphereContext context) {
        log.info("连接成功之后的拦截器");
    }

}
