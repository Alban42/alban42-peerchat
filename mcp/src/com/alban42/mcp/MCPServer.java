package com.alban42.mcp;

import com.alban42.common.PeerRegister;
import com.alban42.common.User;
import com.alban42.network.register.ClassRegister;
import com.alban42.network.server.NetworkServer;
import com.alban42.network.server.listener.NetworkServerListener;
import com.esotericsoftware.kryonet.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by alban on 17/07/2016.
 *
 * @author alban
 */
public class MCPServer {

    private NetworkServer server;

    private Map<Integer, User> connectedUsers;

    public MCPServer() {
        connectedUsers = new HashMap<>(0);

        final NetworkServerListener listener = new MCPServerListener(this);
        final ClassRegister register = new PeerRegister();

        server = new NetworkServer(null, listener, register);

        server.start();
    }

    public void stop() {
        server.stop();
    }

    public synchronized List<User> getConnectedUsers() {
        return connectedUsers.values().stream().collect(Collectors.toList());
    }

    public void removeUser(final Connection connection) {
        connectedUsers.remove(connection.getID());
    }
}
