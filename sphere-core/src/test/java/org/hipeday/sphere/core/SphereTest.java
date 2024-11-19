package org.hipeday.sphere.core;

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
        client.invoke("客户端ID", "191.168.2.5", 6800, "消息负载", "www.usr.cn");
        while (true) {

        }
    }
}