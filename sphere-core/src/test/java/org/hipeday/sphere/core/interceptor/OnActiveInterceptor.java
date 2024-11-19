package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.context.Session;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.network.SphereClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接成功之后的拦截器
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class OnActiveInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(OnActiveInterceptor.class);

    @Override
    public void activated(SphereContext context) {
        SphereClientConfig<?> config = context.getClient().getConfig();
        log.info("客户端 ' {} ' 连接成功之后的拦截器", config.clientId());
    }
}
