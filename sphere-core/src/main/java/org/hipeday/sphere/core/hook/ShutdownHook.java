package org.hipeday.sphere.core.hook;

import org.hipeday.sphere.core.network.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 退出回调
 *
 * @author jixiangup
 * @since 1.1.0.20
 */
public class ShutdownHook implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ShutdownHook.class);
    private List<Client> clients;

    public void addClient(Client client) {
        if (clients == null) {
            clients = new ArrayList<>();
        }
        clients.add(client);
    }

    @Override
    public void run() {
        if (log.isInfoEnabled()) {
            log.info("Sphere shutdown hook is running...");
        }
        if (clients == null) {
            return;
        }
        clients.forEach(Client::close);
    }
}
