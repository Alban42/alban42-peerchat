package com.alban42.user.network.server;

import com.alban42.network.register.objects.message.Message;
import com.alban42.network.register.objects.packet.Packet;
import com.alban42.network.server.listener.NetworkServerListener;
import com.esotericsoftware.kryonet.Connection;

/**
 * Created by alban on 17/07/2016.
 *
 * @author alban
 */
public class UserServerListener extends NetworkServerListener {

    /**
     * Constructor.
     */
    public UserServerListener() {
        super();
    }

    @Override
    protected void executeWhenReceived(final Connection connection, final Packet packet) {
        if (packet instanceof Message) {
            final Message message = (Message) packet;
            System.out.println(message.message);
        }
    }
}
