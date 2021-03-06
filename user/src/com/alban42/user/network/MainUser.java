package com.alban42.user.network;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.alban42.common.User;
import com.alban42.user.network.client.UserClient;
import com.alban42.user.network.server.UserServer;

/**
 * Created by alban on 17/07/2016.
 *
 * @author alban
 */
public class MainUser {

	public static void main(String[] args) {
		try {
			final Scanner scanIn = new Scanner(System.in);

			System.out.print("tcpPort : ");
			final Integer tcpPort = Integer.valueOf(scanIn.nextLine());

			final UserServer server = new UserServer(tcpPort);

			System.out.print("Pseudo : ");
			final String pseudo = scanIn.nextLine();
			final User currentUser = new User();
			currentUser.pseudo = pseudo;
			currentUser.host = "localhost";
			currentUser.tcpPort = tcpPort;
			final UserClient client = new UserClient(currentUser);

			List<User> connectedUsers = getUsers(client);

			boolean stop = false;
			String input;
			String[] split;
			while (!stop) {
				System.out.print("Send message : user#,message");
				input = scanIn.nextLine();

				if (input.equals("stop")) {
					stop = true;
				} else if (input.equals("list")) {
					connectedUsers = getUsers(client);
				} else {
					split = input.split(",");
					client.sendMessage(connectedUsers.get(Integer.valueOf(split[0]) - 1), split[1]);
				}
			}

			server.stop();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private static List<User> getUsers(final UserClient client) throws IOException {
		final List<User> connectedUsers = client.getConnectedUsers();
		if (connectedUsers != null) {
			int count = 1;
			for (final User connectedUser : connectedUsers) {
				System.out.println(count + " : " + connectedUser.pseudo);
				count++;
			}
		}
		return connectedUsers;
	}
}
