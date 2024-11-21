package org.hipeday.sphere.core.handler.support;

import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.handler.AbstractFunctionLifecycleHandler;
import org.hipeday.sphere.core.logging.SphereLogger;

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
    public void onError() {
        // do nothing
    }

    @Override
    public void onComplete() {
        // do nothing
    }

}
