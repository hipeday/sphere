package org.hipeday.sphere.core.callback;

import org.hipeday.sphere.core.context.SphereContext;

/**
 * 已激活 创建连接成功之后的回调钩子
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
@FunctionalInterface
public interface Activated extends Callback {

    void activated(SphereContext context);

}
