package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.session.Session;
import org.hipeday.sphere.core.context.SphereContext;

/**
 * 网络客户端
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface Client {

    /**
     * 获取客户端ID
     *
     * @return 客户端ID
     */
    String clientId();

    /**
     * 获取配置
     *
     * @return 配置
     */
    NetworkClientConfig<?> getConfig();

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
