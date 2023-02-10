package server;

public class MainServer {

	/** * creates a new server * @param args */
	public static void main(String[] args) {
		if (args.length != 1) {
			printUsage();
		} else {
            System.out.println(args[0]);
			Integer port = Integer.parseInt(args[0]);
			Server server = new Server(port);
		}
	}

	private static void printUsage() {
		System.out.println("java server.Server <port>");
		System.out.println("\t<port>: server's port");
	}
}
