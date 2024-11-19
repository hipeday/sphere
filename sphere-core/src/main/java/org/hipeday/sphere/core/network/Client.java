package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.context.Session;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.interceptor.InterceptorChain;

/**
 * 网络客户端
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public interface Client {

    /**
     * 获取配置
     *
     * @return 配置
     */
    SphereClientConfig<?> getConfig();

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    SphereContext getContext();

    /**
     * 连接
     */
    void connect();

    /**
     * 获取会话
     *
     * @return 会话
     */
    Session getSession();

    /**
     * 设置会话
     *
     * @param session 会话
     */
    void setSession(Session session);

    /**
     * 获取拦截器链
     *
     * @return 拦截器链
     */
    InterceptorChain getInterceptorChain();

    /**
     * 获取配置
     *
     * @return 配置
     */
    SphereConfiguration getConfiguration();

    /**
     * 写数据并且刷新缓冲区
     */
    void writeAndFlush(Object msg);

    /**
     * 写数据
     */
    void writeAndFlush(byte[] msg);

    /**
     * 当前网络客户端是否连接
     *
     * @return 是否连接
     */
    boolean isConnected();

    /**
     * 关闭连接
     */
    void close();

}
