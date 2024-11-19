package org.hipeday.sphere.core.network;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InetAddressTest {

    @Test
    void getDefaultHost() {
        InetAddress inetAddress = new InetAddress(6800);
        String host = inetAddress.getDefaultHost();
        System.out.println(host);
    }
}