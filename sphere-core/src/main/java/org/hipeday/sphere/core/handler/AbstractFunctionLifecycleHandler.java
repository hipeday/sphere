package org.hipeday.sphere.core.handler;

import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.interceptor.InterceptorChain;
import org.hipeday.sphere.core.reflection.Function;

/**
 * 抽象 {@linkplain org.hipeday.sphere.core.reflection.Function 函数生命周期} 处理器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public abstract class AbstractFunctionLifecycleHandler implements FunctionLifecycleHandler {

    /**
     * 被调用函数
     */
    protected final Function<?> function;

    /**
     * Sphere 上下文
     */
    protected final SphereContext context;

    /**
     * 拦截器调用链
     */
    protected final InterceptorChain interceptorChain;


    public AbstractFunctionLifecycleHandler(SphereContext context) {
        this.context = context;
        this.function = context.getFunction();
        this.interceptorChain = context.getInterceptorChain();
    }

    /**
     * 在生命周期内调用拦截器
     */
    public Object handle() {
        try {
            // 执行之前确认网络客户端连接状态
            if (!context.getClient().isConnected()) {
                context.getClient().connect();
            }
            Object before = onBefore();
            Object result = invoke(before);
            onSuccess();
            return result;
        } catch (Exception e) {
            onError();
        } finally {
            onComplete();
        }
        return null;
    }
}
