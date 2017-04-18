package com.alban42.user.network.client;

import java.io.IOException;
import java.util.List;
import java.util.function.BooleanSupplier;

import com.alban42.common.PeerRegister;
import com.alban42.common.StatusPacket;
import com.alban42.common.User;
import com.alban42.network.client.NetworkClient;
import com.alban42.network.client.listener.NetworkClientListener;
import com.alban42.network.register.ClassRegister;
import com.alban42.network.register.objects.ICaller;
import com.alban42.network.register.objects.message.Message;
import com.alban42.network.register.objects.packet.Packet;
import com.alban42.properties.Properties42;
import com.alban42.user.properties.UserProperties;
import com.esotericsoftware.minlog.Log;

/**
 * Created by alban on 02/08/2016.
 *
 * @author alban
 */
public class UserClient implements ICaller {

	protected String TAG = UserClient.class.getName();

	private List<User> connectedUsers;
	private boolean statusReceived = false;
	private NetworkClient client;
	private NetworkClientListener listener;
	private ClassRegister register;
	private final User currentUser;

	public UserClient(final User currentUser) {
		this.currentUser = currentUser;
	}

	private NetworkClient getNetwork() {
		if (client == null) {
			client = new NetworkClient();
			listener = new UserClientListener(client);
			register = new PeerRegister();
		}

		return client;
	}

	private void connectToServer() throws IOException {
		final String serverHost = Properties42.getProperty(UserProperties.SERVER_HOST);
		final Integer tcpServerPort = Integer.valueOf(Properties42.getProperty(UserProperties.SERVER_PORT));
		final Integer udpServerPort = Integer.valueOf(Properties42.getProperty(UserProperties.SERVER_PORT_UDP));
		getNetwork().connect(serverHost, tcpServerPort, udpServerPort, register, listener);

		wait(() -> getNetwork().isConnected(), "connect to server");
	}

	private void connectToUser(User user) throws IOException {
		getNetwork().connect(user.host, user.tcpPort, register, listener);

		wait(() -> getNetwork().isConnected(), "connect to user");
	}

	private void disconnect() {
		if (client.isConnected()) {
			client.disconnect();
		}
	}

	public void sendMessage(User user, String message) throws IOException {
		connectToUser(user);

		final Message messagePacket = new Message();
		messagePacket.message = message;
		getNetwork().sendPacketTCP(messagePacket);

		disconnect();
	}

	public List<User> getConnectedUsers() throws IOException {
		connectToServer();

		final StatusPacket statusPacket = new StatusPacket();
		statusPacket.user = currentUser;
		getNetwork().sendAndWaitPacketTCP(statusPacket, this);

		wait(() -> isStatusReceived(), "get connected users");

		disconnect();

		return connectedUsers;
	}

	private Boolean isStatusReceived() {
		return statusReceived;
	}

	private void wait(BooleanSupplier isWaitDone, String name) {
		Log.info(TAG, "Waiting to " + name + " ...");
		while (!isWaitDone.getAsBoolean()) {
			try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				e.printStackTrace();
				Log.error(TAG, "Unable to " + name + " ...");
			}
		}
		Log.info(TAG, name + " !!");
	}

	@Override
	public void responseReceived(final Packet response) {
		if (response instanceof StatusPacket) {
			Log.info(TAG, "Response received by server");
			connectedUsers = ((StatusPacket) response).connectedUsers;
			statusReceived = true;
		}
	}
}
