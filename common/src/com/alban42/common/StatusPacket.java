package com.alban42.common;

import com.alban42.network.register.objects.packet.Packet;

import java.util.List;

/**
 * Created by alban on 02/08/2016.
 *
 * @author alban
 */
public class StatusPacket extends Packet {

    /**
     * The {@link User} asking for the server status.
     */
    public User user;

    /**
     * The list of {@link User}s actually connected to the server.
     */
    public List<User> connectedUsers;
}
