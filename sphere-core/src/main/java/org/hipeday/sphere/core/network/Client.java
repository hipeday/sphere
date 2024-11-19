package org.hipeday.sphere.core.network;

import org.hipeday.sphere.core.context.SphereContext;

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
     * 写数据并且刷新缓冲区
     */
    void writeAndFlush(Object msg);

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
