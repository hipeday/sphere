package org.hipeday.sphere.core.network;

/**
 * 网络客户端工厂
 *
 * @author jixiangup
 * @since 1.0.0
 */
@FunctionalInterface
public interface ClientFactory {

    /**
     * 创建客户端
     *
     * @return 客户端
     */
    Client getClient(NetworkClientConfig<?> config);

}
