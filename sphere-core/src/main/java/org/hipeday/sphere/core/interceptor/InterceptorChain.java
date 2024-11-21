package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.network.Client;
import org.hipeday.sphere.core.util.ArrayUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 拦截器调用链
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class InterceptorChain implements Interceptor {

    /**
     * 拦截器调用链ID
     * <p>
     *     用于标识拦截器调用链
     *     拦截器调用脸id应该和 {@link Client#clientId()} 保持一致
     * </p>
     */
    private String id;

    private final List<Interceptor> interceptors;
    private int index = 0;

    public InterceptorChain() {
        interceptors = Collections.emptyList();
    }

    public InterceptorChain(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public InterceptorChain(Class<? extends Interceptor>[] interceptorClasses) {
        if (ArrayUtils.isEmpty(interceptorClasses)) {
            interceptors = new LinkedList<>();
        } else {
            interceptors = new LinkedList<>();
            for (Class<? extends Interceptor> interceptorClass : interceptorClasses) {
                try {
                    Constructor<? extends Interceptor> constructor = interceptorClass.getConstructor();
                    interceptors.add(constructor.newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new IllegalArgumentException("Failed to create interceptor instance", e);
                }
            }
        }
    }

    @Override
    public Object onBeforeExecute(SphereContext context) {
        if (index < interceptors.size()) {
            return interceptors.get(index++).onBeforeExecute(context);
        }
        return context.getPayload();
    }

    public String getId() {
        return id;
    }
}
