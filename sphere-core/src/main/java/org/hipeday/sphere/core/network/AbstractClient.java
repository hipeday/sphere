package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.assertion.Assert;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.interceptor.Interceptor;
import org.hipeday.sphere.core.interceptor.InterceptorChain;

/**
 * 抽象网络客户端实现
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractClient implements Client {

    protected final NetworkClientConfig<?> config;
    protected final Class<?> interfaceClass;
    protected InterceptorChain interceptorChain;
    protected SphereContext context;

    public AbstractClient(NetworkClientConfig<?> config) {
        this.config = config;
        this.interfaceClass = config.interfaceClass();
        init();
    }

    public void init() {
        // 初始化
        initInterceptor();
    }

    public SphereClient getSphereClient() {
        SphereClient sphereClient = interfaceClass.getAnnotation(SphereClient.class);
        Assert.notNull(sphereClient, "The interface class does not use @SphereClient annotation");
        return sphereClient;
    }

    public void initInterceptor() {
        // 初始化拦截器
        SphereClient sphereClient = getSphereClient();
        Class<? extends Interceptor>[] interceptors = sphereClient.interceptors();
        interceptorChain = new InterceptorChain(interceptors);
    }

    @Override
    public SphereContext getContext() {
        return context;
    }

    @Override
    public NetworkClientConfig<?> getConfig() {
        return config;
    }

    public void setContext(SphereContext context) {
        this.context = context;
    }

}
