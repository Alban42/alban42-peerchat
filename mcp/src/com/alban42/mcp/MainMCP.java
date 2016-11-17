package com.alban42.mcp;

import java.util.Scanner;

/**
 * Created by alban on 17/07/2016.
 *
 * @author alban
 */
public class MainMCP {

    public static void main(String[] args) {
        final Scanner scanIn = new Scanner(System.in);
        final MCPServer server = new MCPServer();

        boolean stop = false;
        String input;
        System.out.print("Server started on port 27960, tape 'stop' to stop it.");

        while (!stop) {
            input = scanIn.nextLine();
            if (input.equals("stop")) {
                stop = true;
            }
        }

        server.stop();
    }
}
