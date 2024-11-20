package org.hipeday.sphere.core.reflection;

import org.hipeday.sphere.core.annotation.ClientProtocol;
import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.assertion.Assert;
import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.network.ClientFactories;
import org.hipeday.sphere.core.network.InetAddress;
import org.hipeday.sphere.core.network.SphereClientConfig;
import org.hipeday.sphere.core.proxy.InterfaceProxyHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Sphere 方法实例
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SphereMethod<T> {

    private final InterfaceProxyHandler<T> proxyHandler;
    private final SphereConfiguration configuration;
    private final Class<T> interfaceClass;
    private final Method method;
    private final ClientProtocol protocol;
    private String clientId;
    private InetAddress inetAddress;
    private Object payload;
    private String heartbeat;

    public SphereMethod(InterfaceProxyHandler<T> proxyHandler, SphereConfiguration configuration, Method method, Class<T> interfaceClass) {
        this.proxyHandler = proxyHandler;
        this.configuration = configuration;
        this.method = method;
        this.interfaceClass = interfaceClass;
        SphereClient sphereClient = getSphereClient();
        this.protocol = sphereClient.protocol();
    }

    public SphereClient getSphereClient() {
        SphereClient sphereClient = interfaceClass.getAnnotation(SphereClient.class);
        Assert.notNull(sphereClient, "The interface class does not use @SphereClient annotation");
        return sphereClient;
    }

    /**
     * 调用方法
     *
     * @param args 参数
     * @return 返回值
     */
    public Object invoke(final Object... args) {

        // 参数准备
        parameterPreparation(args);

        // 1. 判断当前Client是否已经创建了连接 如果没有则创建连接否则从连接池获取
        Client client = configuration.getNetworkClientCache().get(clientId);
        if (client == null) {
            SphereClientConfig<T> clientConfig = new SphereClientConfig<>(clientId, protocol, inetAddress, interfaceClass, heartbeat);
            client = ClientFactories.getClientFactory(protocol).createClient(clientConfig, configuration);
        }

        // 判断是否要缓存网络客户端
        boolean networkClientCacheEnabled = configuration.isNetworkClientCacheEnabled();
        if (networkClientCacheEnabled) {
            SphereConfiguration.cacheNetworkClient(clientId, client);
        }

        // 判断当前客户端是否已经连接 如果没有连接则连接
        if (!client.isConnected()) {
            client.connect();
        }

        // 发送消息 这里的消息内容要重新获取 使用 @Command 去获取
        Object data = client.getInterceptorChain().onBeforeExecute(client.getContext(), this.payload);
        client.writeAndFlush(data);
        return null;
    }

    public void parameterPreparation(final Object ... args) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Assert.notEmpty(parameterAnnotations, "The parameter does not use @ClientId to identify the client's unique identifier");
        String address = null;
        Integer port = null;
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                switch (annotation.annotationType().getName()) {
                    case "org.hipeday.sphere.core.annotation.ClientId":
                        clientId = (String) args[i];
                        break;
                    case "org.hipeday.sphere.core.annotation.ClientAddress":
                        address = args[i].toString();
                        break;
                    case "org.hipeday.sphere.core.annotation.ClientPort":
                        port = Integer.parseInt(args[i].toString());
                        break;
                    case "org.hipeday.sphere.core.annotation.Payload":
                        payload = args[i];
                        break;
                    case "org.hipeday.sphere.core.annotation.Heartbeat":
                        heartbeat = args[i].toString();
                        break;
                }
            }
        }
        if (protocol == ClientProtocol.TCP) {
            Assert.hasText(address, "The parameter does not use @ClientAddress to identify the client's address");
            Assert.notNull(port, "The parameter does not use @ClientPort to identify the client's port");
            inetAddress = new InetAddress(address, port);
        }

        Assert.hasText(clientId, "The parameter does not use @ClientId to identify the client's unique identifier");

        Assert.notNull(payload, "The parameter does not use @Payload to identify the client's payload");
    }

}
