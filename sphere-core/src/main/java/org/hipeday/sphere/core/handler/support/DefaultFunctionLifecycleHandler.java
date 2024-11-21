package org.hipeday.sphere.core.handler.support;

import io.netty.channel.AbstractChannel;
import io.netty.channel.ConnectTimeoutException;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.exception.SphereRuntimeException;
import org.hipeday.sphere.core.handler.AbstractFunctionLifecycleHandler;
import org.hipeday.sphere.core.logging.SphereLogger;

import java.net.ConnectException;

/**
 * 默认 {@linkplain org.hipeday.sphere.core.reflection.Function 函数生命周期} 处理器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class DefaultFunctionLifecycleHandler extends AbstractFunctionLifecycleHandler {

    private static final SphereLogger log = SphereLogger.getLogger(DefaultFunctionLifecycleHandler.class);

    public DefaultFunctionLifecycleHandler(SphereContext context) {
        super(context);
    }

    @Override
    public Object onBefore() {
        Object beforeExecute = interceptorChain.onBeforeExecute(context);
        // 覆盖现在的负载
        context.getFunction().setPayload(beforeExecute);
        return beforeExecute;
    }

    @Override
    public Object invoke(Object command) {
        log.debug("Sphere invoke function payload is {}", command);
        context.getClient().writeAndFlush(command);
        return null;
    }

    @Override
    public void onSuccess() {
        // do nothing
    }

    @Override
    public void onError(Throwable th) {
        // 如果是连接异常 触发连接失败监听器（并且不会再调用拦截器）否则应该全部走拦截器的异常处理逻辑
        if (th instanceof ConnectException e) {
            org.hipeday.sphere.core.network.ConnectException connectException;

            if (e instanceof ConnectTimeoutException connectTimeoutException) {
                connectException = new org.hipeday.sphere.core.network.ConnectTimeoutException(connectTimeoutException.getMessage(), connectTimeoutException);
            } else {
                connectException = new org.hipeday.sphere.core.network.ConnectException(e.getMessage(), e);
            }
            listenerChain.onError(context, connectException);
            // 如果没有异常拦截器则抛出异常
            throw connectException;
        }
        interceptorChain.onError(context, th);
    }

    @Override
    public void onComplete() {
        // do nothing
    }

}
