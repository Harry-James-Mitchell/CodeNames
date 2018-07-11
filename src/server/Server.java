package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;

public class Server {

	private static final int PORT = 8080;
	private static ServerSocket server;
	private static HashMap<Integer, Socket> clientMap;
	public static void main(String args[]) {
		try {
			server = new ServerSocket(PORT);
			String systemipaddress = "";
	        URL url_name = new URL("http://bot.whatismyipaddress.com");
	 
	        BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
	        systemipaddress = sc.readLine().trim();
//	        System.out.println("Public IP Address: " + systemipaddress +"\n");
	    
			System.out.println("Starting server .... listening on " + systemipaddress + ":" + PORT);
			
		}catch(Exception e) {
			System.err.println("Could not start server on port " + PORT);
		}
		clientMap = new HashMap<>();
		while(true) {
			
			try {
				Socket clientSocket = server.accept();
				clientMap.put(genClientID(), clientSocket);
			}catch (Exception e) {
				System.err.println("Client ");
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
