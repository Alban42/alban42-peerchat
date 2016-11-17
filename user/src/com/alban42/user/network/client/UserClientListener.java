package com.alban42.user.network.client;

import com.alban42.network.client.NetworkClient;
import com.alban42.network.client.listener.NetworkClientListener;
import com.alban42.network.register.objects.packet.Packet;

import java.sql.Connection;

/**
 * Created by alban on 02/08/2016.
 *
 * @author alban
 */
public class UserClientListener extends NetworkClientListener {

    /**
     * Constructor.
     *
     * @param network The parent of the listener.
     */
    public UserClientListener(final NetworkClient network) {
        super(network);
    }

    @Override
    protected void execute(final Connection connection, final Packet response) {

    }
}
