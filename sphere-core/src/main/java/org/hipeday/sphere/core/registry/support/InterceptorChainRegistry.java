package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.interceptor.InterceptorChain;
import org.hipeday.sphere.core.registry.AbstractRegistry;

/**
 * {@linkplain org.hipeday.sphere.core.interceptor.InterceptorChain 拦截器调用链} 注册表
 * key: 拦截器调用链ID {@link InterceptorChain#getId()}
 *
 * @see InterceptorChain
 * @author jixiangup
 * @since 1.0.0
 */
public class InterceptorChainRegistry extends AbstractRegistry<String, InterceptorChain> {

    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.INTERCEPTOR_CHAIN_REGISTRY_NAME;
    }

}
