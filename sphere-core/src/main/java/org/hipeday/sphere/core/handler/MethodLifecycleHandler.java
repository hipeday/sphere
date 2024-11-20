package org.hipeday.sphere.core.handler;

/**
 * 函数生命周期
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface MethodLifecycleHandler extends LifecycleHandler {

    /**
     * 方法调用前的处理逻辑
     */
    void onBefore();

    /**
     * 方法执行期间的处理逻辑（可进行环绕逻辑）
     *
     * @return 方法返回值
     */
    Object invoke();

    /**
     * 方法成功执行后的处理逻辑
     */
    void onSuccess();

    /**
     * 方法抛出异常时的处理逻辑
     */
    void onError();

    /**
     * 方法完成后的清理逻辑（无论成功或失败）
     */
    void onComplete();
    /**
     * 在生命周期内调用拦截器
     */
    default Object handle() {
        try {
            onBefore();
            Object result = invoke();
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
