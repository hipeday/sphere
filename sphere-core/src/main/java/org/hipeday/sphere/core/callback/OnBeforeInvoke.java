package org.hipeday.sphere.core.callback;

import org.hipeday.sphere.core.context.SphereContext;

/**
 * 在执行之前回调
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface OnBeforeInvoke {

    /**
     * 在执行之前回调
     *
     * @param context 上下文
     * @return 回调结果
     */
    Object onBeforeExecute(SphereContext context);
}
