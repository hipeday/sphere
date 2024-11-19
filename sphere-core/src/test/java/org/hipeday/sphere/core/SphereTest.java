package org.hipeday.sphere.core;

import org.apache.commons.logging.LogFactory;
import org.hipeday.sphere.core.client.MaterialRackClient;
import org.hipeday.sphere.core.config.SphereConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void config() {
        SphereConfiguration config = Sphere.config();
        assertNotNull(config);
    }

    @Test
    void client() {
        MaterialRackClient client = Sphere.client(MaterialRackClient.class);
        client.invoke("客户端ID", "191.168.2.5", 6800, null);
        while (true) {

        }
    }
}