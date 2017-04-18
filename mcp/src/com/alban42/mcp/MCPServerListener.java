package com.alban42.mcp;

import java.util.List;

import com.alban42.common.StatusPacket;
import com.alban42.common.User;
import com.alban42.network.register.objects.packet.Packet;
import com.alban42.network.server.listener.NetworkServerListener;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

/**
 * Created by alban on 02/08/2016.
 *
 * @author alban
 */
public class MCPServerListener extends NetworkServerListener {

	private final MCPServer mcpServer;

	public MCPServerListener(final MCPServer mcpServer) {
		this.mcpServer = mcpServer;
	}

	@Override
	protected void executeWhenReceived(final Connection connection, final Packet packet) {
		Log.info("SERVER", "Packet received : " + packet);
		if (packet instanceof StatusPacket) {
			final StatusPacket status = (StatusPacket) packet;

			// Send the connected user list to the sender
			final List<User> connectedUsers = mcpServer.getConnectedUsers();
			status.connectedUsers = connectedUsers;
			server.sendToTCP(connection.getID(), status);

			// Add the user to the connected user list of not already existing
			mcpServer.addUser(connection, status.user);
		}
	}

	@Override
	public void disconnected(final Connection connection) {
		super.disconnected(connection);

		// mcpServer.removeUser(connection);
	}
}
