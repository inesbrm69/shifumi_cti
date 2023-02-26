package server;

import common.Map;

import java.io.IOException;

public class MainServer {

	/** * creates a new server * @param args */
	public static void main(String[] args) {
		if (args.length != 1) {
			printUsage();
		} else {
			Integer port = new Integer(args[0]);
			Map map = new Map();
			Server server = new Server(port);
			server.setMap(map);
		}
	}

	private static void printUsage() {
		System.out.println("java server.Server <port>");
		System.out.println("\t<port>: server's port");
	}
}
