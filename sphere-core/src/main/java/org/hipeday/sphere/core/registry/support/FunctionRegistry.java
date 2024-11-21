package org.hipeday.sphere.core.registry.support;

import org.hipeday.sphere.core.constants.ApplicationContextConstants;
import org.hipeday.sphere.core.reflection.Function;
import org.hipeday.sphere.core.registry.AbstractRegistry;

import java.lang.reflect.Method;

/**
 * Sphere 函数注册表
 *
 * @author jixiangup
 * @since 1.0.0
 */
public class FunctionRegistry extends AbstractRegistry<Method, Function<?>> {
    @Override
    public String getRegistryName() {
        return ApplicationContextConstants.FUNCTION_REGISTRY_NAME;
    }
}
