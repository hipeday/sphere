package org.hipeday.sphere.core.listener;

import org.hipeday.sphere.core.callback.Disconnect;
import org.hipeday.sphere.core.callback.OnActive;
import org.hipeday.sphere.core.callback.OnError;
import org.hipeday.sphere.core.callback.OnHeartbeat;
import org.hipeday.sphere.core.callback.OnMessage;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.exception.SphereRuntimeException;

/**
 * 监听器
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface Listener extends OnActive, OnHeartbeat, OnMessage, OnError, Disconnect {

    @Override
    default void activated(SphereContext context) {
        // do nothing
    }

    @Override
    default void heartbeat(SphereContext sphereContext, byte[] heartbeatMetadata) {
        // do nothing
    }

    @Override
    default void message(SphereContext context, byte[] message) {
        // do nothing
    }

    /**
     * 异常回调钩子 默认实现 需要注意的是
     * 监听器的异常处理只会在触发
     * {@linkplain org.hipeday.sphere.core.network.ConnectException 连接异常}
     * 时触发 其他异常都会被
     * {@linkplain org.hipeday.sphere.core.interceptor.Interceptor#onError(SphereContext, Throwable) 拦截器异常钩子}
     * 处理并转换 {@linkplain SphereRuntimeException Sphere 运行时异常} 抛出
     *
     * @param context 上下文
     *
     * @param th 异常
     */
    @Override
    default void onError(SphereContext context, Throwable th) {
        // do nothing
    }

    /**
     * 连接断开回调
     *
     * @param context 上下文
     */
    @Override
    default void disconnected(SphereContext context) {
        // do nothing
    }

}
