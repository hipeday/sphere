package org.hipeday.sphere.core.interceptor;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.util.ArrayUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * 拦截器调用链
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class InterceptorChain implements Interceptor {

    private final List<Interceptor> interceptors;
    private int index = 0;

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

    /**
     * 当客户端激活时调用
     */
    @Override
    public void activated(SphereContext context) {
        if (index < interceptors.size()) {
            interceptors.get(index++).activated(context);
        }
        index = 0;
    }

    @Override
    public Object onBeforeExecute(SphereContext context, Object payload) {
        if (index < interceptors.size()) {
            return interceptors.get(index++).onBeforeExecute(context, payload);
        }
        index = 0;
        return payload;
    }
}
