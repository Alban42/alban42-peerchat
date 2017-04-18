package com.alban42.user.network.server;

import com.alban42.common.PeerRegister;
import com.alban42.network.register.ClassRegister;
import com.alban42.network.server.NetworkServer;
import com.alban42.network.server.listener.NetworkServerListener;

/**
 * Created by alban on 17/07/2016.
 *
 * @author alban
 */
public class UserServer {

    private NetworkServer server;

    public UserServer(final int tcpPort) {
        final NetworkServerListener listener = new UserServerListener();
        final ClassRegister register = new PeerRegister();

        server = new NetworkServer(tcpPort, tcpPort + 1, listener, register);

        server.start();

    }

    public void stop() {
        server.stop();
    }
}
