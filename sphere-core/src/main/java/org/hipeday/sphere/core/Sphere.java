package org.hipeday.sphere.core;

import org.hipeday.sphere.core.config.SphereConfiguration;

/**
 * Sphere 启动调用客户端
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public abstract class Sphere {

    /**
     * 获取配置
     *
     * @return 配置
     */
    public static SphereConfiguration config() {
        return SphereConfiguration.configuration();
    }

    /**
     * 创建客户端
     *
     * @param clazz 客户端接口
     * @return 客户端实例
     * @param <T> 客户端类型
     */
    public static <T> T client(Class<T> clazz) {
        return Sphere.config().createClient(clazz);
    }


}
