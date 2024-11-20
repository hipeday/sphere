package org.hipeday.sphere.core.context;

import org.hipeday.sphere.core.config.SphereConfiguration;
import org.hipeday.sphere.core.network.Client;

/**
 * Sphere上下文对象
 *
 * @author jixiangup
 * @since 1.0.0
 */
public interface SphereContext {

    Client getClient();

    Session getSession();

    SphereConfiguration getConfiguration();

}
