package client;

import java.net.Socket;
import java.util.Scanner;

public class Client {

	private static final int PORT = 8080;
	private static Socket serverConnection;
	public static void main(String args[]) {
		
		//TODO Add error checking for bad ip
		//Potentially have user put in port
		
		System.out.print("Please enter ip of server: ");
		Scanner in = new Scanner(System.in);
		String serverIp = in.nextLine();
		in.close();
		serverConnection = null;
		try {
			serverConnection = new Socket(serverIp, PORT);
			
		}catch (Exception e) {
			System.err.println("Unable to resolve " + serverIp + ":" + PORT);
			e.printStackTrace();
		}
	}
}
