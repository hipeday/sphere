package org.hipeday.sphere.core.handler;

/**
 * 生命周期处理器 在生命周期内调用拦截器
 *
 * @author jixiangup
 * @since 1.0.0
 */
@FunctionalInterface
public interface LifecycleHandler {

    /**
     * 在生命周期内调用拦截器
     */
    Object handle();

}
