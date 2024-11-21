package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.registry.AbstractRegistry;

/**
 * 接口客户端实例注册表
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class InterfaceClientRegistry extends AbstractRegistry<String, Object> {
    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.INTERFACE_CLIENT_REGISTRY_NAME;
    }
}
