package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.interceptor.InterceptorChain;
import org.hipeday.sphere.core.listener.ListenerChain;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.reflection.Function;
import org.hipeday.sphere.core.session.Session;

/**
 * Sphere 上下文对象
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface SphereContext {

    Client getClient();

    Session getSession();

    Function<?> getFunction();

    InterceptorChain getInterceptorChain();

    ListenerChain getListenerChain();

    /**
     * 获取当前函数调用负载(新参参数值)
     *
     * @return 负载
     */
    Object getPayload();
}
