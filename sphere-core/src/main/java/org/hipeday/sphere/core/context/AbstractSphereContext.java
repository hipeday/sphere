package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.annotation.SphereFunction;
import org.hipeday.sphere.core.interceptor.Interceptor;
import org.hipeday.sphere.core.interceptor.InterceptorChain;
import org.hipeday.sphere.core.interceptor.InterceptorFactory;
import org.hipeday.sphere.core.listener.Listener;
import org.hipeday.sphere.core.listener.ListenerChain;
import org.hipeday.sphere.core.listener.ListenerFactory;
import org.hipeday.sphere.core.listener.support.DefaultConnectedListener;
import org.hipeday.sphere.core.listener.support.DefaultHeartbeatListener;
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
import java.util.stream.Stream;

/**
 * 抽象 Sphere 上下文
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractSphereContext implements SphereContext {

    protected static final List<Class<? extends Listener>> DEFAULT_LISTENERS = Stream.of(DefaultConnectedListener.class, DefaultHeartbeatListener.class).toList();

    protected final Client client;
    protected final Function<?> function;
    protected final InterceptorChainRegistry interceptorChainRegistry;
    protected final InterceptorRegistry interceptorRegistry;
    protected final ListenerChainRegistry listenerChainRegistry;
    protected final InterceptorFactory interceptorFactory;
    protected final ListenerFactory listenerFactory;

    public AbstractSphereContext(Client client, Function<?> function) {
        this.client = client;
        this.function = function;
        ApplicationContext applicationContext = ApplicationContext.getApplicationContext();
        interceptorChainRegistry = applicationContext.getInterceptorChainRegistry();
        listenerChainRegistry = applicationContext.getListenerChainRegistry();
        interceptorRegistry = applicationContext.getInterceptorRegistry();
        interceptorFactory = applicationContext.getInterceptorFactory();
        listenerFactory = applicationContext.getListenerFactory();

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

        // 去除重复的拦截器
        if (CollectionUtils.isNotEmpty(interceptors)) {
            interceptors = interceptors.stream().distinct().toList();
        }

        // 创建拦截器调用链
        final List<Interceptor> finalInterceptors = interceptors;
        InterceptorChain interceptorChain = interceptorChainRegistry.computeIfUnregister(client.clientId(), clientId -> new InterceptorChain(finalInterceptors));
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
     * 解析接口声明监听器
     *
     * @param sphereClient 接口声明的 {@linkplain SphereClient SphereClient} 注解
     *
     * @return 解析到的监听器列表
     */
    protected List<Listener> parseInterfaceListeners(SphereClient sphereClient) {
        if (null == sphereClient) {
            return null;
        }
        return getListeners(sphereClient.listeners());
    }

    private List<Listener> getListeners(Class<? extends Listener>[] listenersClass) {
        List<Listener> listeners = null;
        if (ArrayUtils.isNotEmpty(listenersClass)) {
            for (Class<? extends Listener> listener : listenersClass) {
                Listener listenerInstance = listenerFactory.getListener(listener);
                if (CollectionUtils.isEmpty(listeners)) {
                    listeners = new ArrayList<>();
                }
                listeners.add(listenerInstance);
            }
        }
        return listeners;
    }

    /**
     * 创建监听器调用链
     */
    protected void createListenerChain() {
        // 添加默认监听器
        List<? extends Listener> defaultListeners = parseDefaultListener();

        // 获取全局监听器 暂时还没有配置以后再说 先留口子

        // 解析接口声明监听器
        List<Listener> interfaceListeners = parseInterfaceListeners(ClassUtils.getAnnotation(SphereClient.class, function.getInterfaceClass()));

        // 解析函数监听器
        List<Listener> functionListeners = parseFunctionListeners(MethodUtils.getAnnotation(SphereFunction.class, function.getMethod()));

        // 所有的监听器
        List<Listener> listeners = CollectionUtils.mergeToList(interfaceListeners, functionListeners);

        if (CollectionUtils.isEmpty(listeners)) {
            listeners = new ArrayList<>();
        }
        listeners.addAll(0, defaultListeners);

        // 去除重复的监听器
        listeners = listeners.stream().distinct().toList();

        // 创建监听器调用链
        final List<Listener> finalListeners = listeners;
        ListenerChain listenerChain = listenerChainRegistry.computeIfUnregister(client.clientId(), clientId -> new ListenerChain(finalListeners));
    }

    private List<? extends Listener> parseDefaultListener() {
        return DEFAULT_LISTENERS.stream().map(listenerFactory::getListener).toList();
    }

    private List<Listener> parseFunctionListeners(SphereFunction annotation) {
        if (null == annotation) {
            return null;
        }
        return getListeners(annotation.listeners());
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
        return listenerChainRegistry.getInstance(client.clientId());
    }

    @Override
    public Object getPayload() {
        return function.getPayload();
    }

}
