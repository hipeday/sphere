package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.annotation.SphereFunction;
import org.hipeday.sphere.core.interceptor.Interceptor;
import org.hipeday.sphere.core.interceptor.InterceptorChain;
import org.hipeday.sphere.core.interceptor.InterceptorFactory;
import org.hipeday.sphere.core.listener.ListenerChain;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.reflection.Function;
import org.hipeday.sphere.core.registry.support.InterceptorChainRegistry;
import org.hipeday.sphere.core.registry.support.InterceptorRegistry;
import org.hipeday.sphere.core.registry.support.ListenerChainRegistry;
import org.hipeday.sphere.core.session.Session;
import org.hipeday.sphere.core.util.ArrayUtils;
import org.hipeday.sphere.core.util.ClassUtils;
import org.hipeday.sphere.core.util.CollectionUtils;
import org.hipeday.sphere.core.util.MethodUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象 Sphere 上下文
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractSphereContext implements SphereContext {

    protected final Client client;
    protected final Function<?> function;
    protected final InterceptorChainRegistry interceptorChainRegistry;
    protected final InterceptorRegistry interceptorRegistry;
    protected final ListenerChainRegistry listenerChainRegistry;
    protected final InterceptorFactory interceptorFactory;

    public AbstractSphereContext(Client client, Function<?> function) {
        this.client = client;
        this.function = function;
        ApplicationContext applicationContext = ApplicationContext.getApplicationContext();
        interceptorChainRegistry = applicationContext.getInterceptorChainRegistry();
        listenerChainRegistry = applicationContext.getListenerChainRegistry();
        interceptorRegistry = applicationContext.getInterceptorRegistry();
        interceptorFactory = applicationContext.getInterceptorFactory();

        // 创建拦截器调用链
        createInterceptorChain();

        // 创建监听器调用链路
        createListenerChain();

    }

    /**
     * 创建拦截器调用链
     */
    protected void createInterceptorChain() {
        // 获取全局拦截器 暂时还没有配置以后再说 先留口子

        // 获取接口声明拦截器
        SphereClient sphereClient = ClassUtils.getAnnotation(SphereClient.class, function.getInterfaceClass());
        List<Interceptor> interfaceInterceptors = parseInterfaceInterceptor(sphereClient);

        // 获取函数拦截器
        List<Interceptor> functionInterceptors = parseFunctionInterceptor(MethodUtils.getAnnotation(SphereFunction.class, function.getMethod()));

        // 所有的拦截器
        List<Interceptor> interceptors = CollectionUtils.mergeToList(interfaceInterceptors, functionInterceptors);

        // 创建拦截器调用链
        InterceptorChain interceptorChain = interceptorChainRegistry.computeIfUnregister(client.clientId(), clientId -> new InterceptorChain(interceptors));
    }

    /**
     * 解析 {@linkplain SphereFunction Sphere 函数} 声明拦截器
     *
     * @param sphereFunction 接口声明的 {@linkplain SphereFunction Sphere 函数} 注解
     *
     * @return 解析到的拦截器列表
     */
    protected List<Interceptor> parseFunctionInterceptor(SphereFunction sphereFunction) {
        if (null == sphereFunction) {
            return null;
        }
        return getInterceptors(sphereFunction.interceptors());
    }

    /**
     * 解析接口声明拦截器
     *
     * @param sphereClient 接口声明的 {@linkplain SphereClient SphereClient} 注解
     *
     * @return 解析到的拦截器列表
     */
    protected List<Interceptor> parseInterfaceInterceptor(SphereClient sphereClient) {
        if (null == sphereClient) {
            return null;
        }
        return getInterceptors(sphereClient.interceptors());
    }

    private List<Interceptor> getInterceptors(Class<? extends Interceptor>[] interceptorsClass) {
        List<Interceptor> interceptors = null;
        if (ArrayUtils.isNotEmpty(interceptorsClass)) {
            for (Class<? extends Interceptor> interceptor : interceptorsClass) {
                Interceptor interceptorInstance = interceptorFactory.getInterceptor(interceptor);
                if (CollectionUtils.isEmpty(interceptors)) {
                    interceptors = new ArrayList<>();
                }
                interceptors.add(interceptorInstance);
            }
        }
        return interceptors;
    }

    /**
     * 创建监听器调用链
     */
    protected void createListenerChain() {

    }

    @Override
    public Client getClient() {
        return this.client;
    }

    @Override
    public Session getSession() {
        return this.client.getSession();
    }

    @Override
    public Function<?> getFunction() {
        return this.function;
    }

    @Override
    public InterceptorChain getInterceptorChain() {
        return interceptorChainRegistry.getInstance(client.clientId());
    }

    @Override
    public ListenerChain getListenerChain() {
        return null;
    }

    @Override
    public Object getPayload() {
        return function.getPayload();
    }

}
