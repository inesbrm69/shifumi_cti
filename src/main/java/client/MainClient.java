package client;

import java.util.Scanner;

public class MainClient {
	/** * construct a new client * @param args */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
		} else {
			Scanner scPseudo = new Scanner(System.in);
			System.out.print("Votre pseudo >> ");
			String pseudo = scPseudo.nextLine();
			String address = args[0];
			Integer port = Integer.parseInt(args[1]);
			Client c = new Client(address, port, pseudo);
		}
	}

	private static void printUsage() {
		System.out.println("java client.Client <address> <port>");
		System.out.println("\t<address>: server's ip address");
		System.out.println("\t<port>: server's port");
	}
}
