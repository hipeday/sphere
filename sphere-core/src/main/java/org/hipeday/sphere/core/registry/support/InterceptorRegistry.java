package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.interceptor.Interceptor;
import org.hipeday.sphere.core.registry.AbstractRegistry;

/**
 * {@linkplain org.hipeday.sphere.core.interceptor.Interceptor 拦截器} 注册表
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class InterceptorRegistry extends AbstractRegistry<Class<? extends Interceptor>, Interceptor> {

    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.INTERCEPTOR_REGISTRY_NAME;
    }
}
