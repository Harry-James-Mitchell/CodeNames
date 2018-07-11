package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;

public class Server {

	private static final int PORT = 5555;
	private static ServerSocket server;
	private static HashMap<Integer, Socket> clientMap;
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
		}
		clientMap = new HashMap<>();
		while(true) {
			Socket clientSocket = null;
			try {
				clientSocket = server.accept();
				clientMap.put(genClientID(), clientSocket);
				System.out.println("Client "  + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " connected");
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
		
		return 0;
	}
}
class ClientHandler implements Runnable{

	public ClientHandler(Socket socket, int clientId) {
		
	}
	@Override
	public void run() {
		
	}
	
}
