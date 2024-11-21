package org.hipeday.sphere.core.reflection;

import org.hipeday.sphere.core.annotation.ClientProtocol;
import org.hipeday.sphere.core.annotation.SphereClient;
import org.hipeday.sphere.core.assertion.Assert;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.context.SphereContextFactories;
import org.hipeday.sphere.core.context.SphereContextFactory;
import org.hipeday.sphere.core.handler.support.DefaultFunctionLifecycleHandler;
import org.hipeday.sphere.core.network.InetAddress;
import org.hipeday.sphere.core.network.NetworkClientConfig;
import org.hipeday.sphere.core.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Sphere 方法实例
 *
 * @author jixiangup
 * @since 1.0.0
 */
public final class Function<T> {

    private final Class<T> interfaceClass;
    private final Method method;
    private final ClientProtocol protocol;
    private String clientId;
    private InetAddress inetAddress;
    private Object payload;
    private String heartbeat;

    public Function(Method method, Class<T> interfaceClass) {
        this.method = method;
        this.interfaceClass = interfaceClass;
        SphereClient sphereClient = ClassUtils.getAnnotation(SphereClient.class, interfaceClass);
        this.protocol = sphereClient.protocol();
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

        // 创建上下文
        NetworkClientConfig<T> networkClientConfig = new NetworkClientConfig<>(clientId, protocol, inetAddress, interfaceClass, heartbeat);
        SphereContextFactory sphereContextFactory = SphereContextFactories.getSphereContextFactory(networkClientConfig, this);
        SphereContext context = sphereContextFactory.createContext();

        // 调用方法
        return new DefaultFunctionLifecycleHandler(context).handle();
    }

    private void parameterPreparation(final Object... args) {
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
                    case "org.hipeday.sphere.core.annotation.Command":
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

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Class<T> getInterfaceClass() {
        return interfaceClass;
    }

    public Method getMethod() {
        return method;
    }
}
