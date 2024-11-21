package org.hipeday.sphere.core.callback;

import org.hipeday.sphere.core.context.SphereContext;

/**
 * 异常回调
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface OnError {

    /**
     * 异常回调
     *
     * @param context 上下文
     *
     * @param th 异常
     */
    void onError(SphereContext context, Throwable th);

}
