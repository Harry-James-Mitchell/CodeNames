package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Server {

	private static final int PORT = 5555;
	private static ServerSocket server;
	private static HashMap<Integer, Socket> clientMap;
	private static ArrayList<Thread> clientThreads;
	public static void main(String args[]) {
		try {
			server = new ServerSocket(PORT);
			String systemipaddress = "";
	        URL url_name = new URL("http://bot.whatismyipaddress.com");
	 
	        BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
	        systemipaddress = sc.readLine().trim();
	    
			System.out.println("Starting server .... listening on " + systemipaddress + ":" + PORT);
			
		}catch(Exception e) {
			System.err.println("Could not start server on port " + PORT);
			System.exit(0);
		}
		clientMap = new HashMap<>(25);
		clientThreads = new ArrayList<>();
		ServerUI.main(null);
		while(true) {
			Socket clientSocket = null;
			try {
				clientSocket = server.accept();
				int clientID = genClientID();
				clientMap.put(clientID, clientSocket);
				System.out.println("Client " + clientID + " " +  clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " connected");
				Thread client = new Thread(new ClientHandler(clientSocket, clientID));
				clientThreads.add(client);
				client.start();
			}catch (Exception e) {
				if(clientSocket != null) {
					System.err.println("Client "  + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " unable to connect");
				}else {
					System.err.println("Client unable to connect... null connection");
				}
			}
		}
	}
	private static Integer genClientID() {
		Integer id = 0;
		Random random = new Random();
		while(clientMap.containsKey(id)) {
			id = random.nextInt(99999);
		}
		return id;
	}
}
class ClientHandler implements Runnable{

	public ClientHandler(Socket socket, int clientId) {
		
	}
	@Override
	public void run() {
		
	}
	
}
