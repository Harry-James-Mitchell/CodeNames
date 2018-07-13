package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class Server {

	private static final int PORT = 5555;
	private static ServerSocket server;
	private static HashMap<Integer, Socket> clientMap;
	private static HashMap<Integer, Thread> clientThreads;
	public static void main(String args[]) {
		String systemipaddress = "";
		try {
			server = new ServerSocket(PORT);
			
	        URL url_name = new URL("http://bot.whatismyipaddress.com");
	 
	        BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
	        systemipaddress = sc.readLine().trim();
	    
			System.out.println("Starting server .... listening on " + systemipaddress + ":" + PORT);
			
		}catch(Exception e) {
			System.err.println("Could not start server on port " + PORT);
			System.exit(0);
		}
		clientMap = new HashMap<>(25);
		clientThreads = new HashMap<>(25);
		ServerUI.main(null,systemipaddress , PORT);
		while(true) {
			Socket clientSocket = null;
			try {
				clientSocket = server.accept();
				int clientID = genClientID();
				clientMap.put(clientID, clientSocket);
				System.out.println("Client " + clientID + " " +  clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " connected");
				sendClientID(clientSocket, clientID);
				Thread client = new Thread(new ClientJSONReciever(clientSocket, clientID));
				clientThreads.put(clientID, client);
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
	private static void sendClientID(Socket client,int clientID) {
		
		try {
			JSONObject json = new JSONObject();
			PrintWriter out = null;
			json.put("type", "idAssign");
			json.put("id", clientID);
			json.put("msg", JSONObject.NULL);
			out = new PrintWriter(new BufferedOutputStream(client.getOutputStream()));
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
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
	public static void sendJSON(Socket socket, JSONObject json) {
		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
			out.println(json.toString());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
class ClientJSONReciever implements Runnable{
	private Socket serverConnection;
	private int clientID;
	public ClientJSONReciever(Socket socket, int clientID) {
		this.serverConnection = socket;
		this.clientID = clientID;
	}
	@Override
	public void run() {
		System.out.println("Running client " + this.clientID);
		Scanner in = null;
		try {
			in = new Scanner(new BufferedInputStream(this.serverConnection.getInputStream()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		while(true) {
			
			try {
//				System.out.println(in.next());
				if(!in.hasNextLine()) continue;
				String jsonStr = in.nextLine();
				JSONObject json = new JSONObject(jsonStr);
				switch (json.getString("type")) {
				case "msg":
					System.out.println("Client " + json.getInt("id") + ": " + json.get(json.getString("type")));
					break;
				case "connectChk":
					json.put("msg", "PONG");
					Server.sendJSON(this.serverConnection, json);
					break;
				default:
					break;
				}
				
			} catch (NullPointerException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
				
			}
		}
	}
}
