package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.context.SphereContext;
import org.hipeday.sphere.core.registry.AbstractRegistry;

/**
 * Sphere 函数上下文注册表名称 key为 {@linkplain org.hipeday.sphere.core.network.Client#clientId() 网络客户端ID}
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class SphereContextRegistry extends AbstractRegistry<String, SphereContext> {
    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.SPHERE_CONTEXT_REGISTRY_NAME;
    }
}
