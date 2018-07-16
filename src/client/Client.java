package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {

	private static final int PORT = 5555;
	private Socket serverConnection;
	private int clientID;
	private long seed;
	private PrintWriter serverWriter;
	private Scanner serverReader;
	public Client(String serverIp) {
		//TODO Add error checking for bad ip
		//Potentially have user put in port
				
		this.serverConnection = null;
		this.serverWriter = null;
		try {
			this.serverConnection = new Socket(serverIp, PORT);
			serverWriter = new PrintWriter(new BufferedOutputStream(this.serverConnection.getOutputStream()));
			serverReader = new Scanner(new BufferedInputStream(this.serverConnection.getInputStream()));
			System.out.println();
			System.out.println("Connected to " + serverIp + "!");
			this.clientID = -1;
			this.seed = -1;
			
			JSONObject json = new JSONObject(serverReader.nextLine());
			if(json.getString("type")!= null && json.getString("type").equals("idAssign")) {
				this.clientID = json.getInt("id");
				if(this.clientID == -1) {
					//TODO Send error to client that the server is full
					System.out.println("Server is full");
					this.serverConnection.close();
					System.exit(-1);
				}
			}
			json = new JSONObject(serverReader.nextLine());
			if(json.getString("type")!= null && json.getString("type").equals("seedAssign")) {
				this.seed = json.getLong("seed");
			}
			if(clientID == -1) {
				System.err.println("Unable to retrieve id from server");
				this.serverConnection.close();
				
				System.exit(-1);
			}
			if(seed == -1) {
				System.err.println("Unable to retrieve seed from server");
				this.serverConnection.close();
				
				System.exit(-1);
			}
			System.out.println("Seed is " + seed);
			Thread serverConnectionChecker = new Thread(new ServerConnectionStatus(this.serverConnection, this.clientID, this.serverWriter,this.serverReader));
			serverConnectionChecker.start();
		}catch (Exception e) {
			System.err.println("Unable to resolve " + serverIp + ":" + PORT);
			e.printStackTrace();
		}
		
	}
	public Socket getSocket() {
		return this.serverConnection;
	}
	//TODO check server is still connected
	public boolean checkConnection() {
		return true;
	}
	public static void main(String args[]) {
		Client client = new Client("localhost");
	}
	public long getSeed() {
		return this.seed;
	}
	public void sendMessage(String msg) {
		JSONObject json = new JSONObject();
		try {
			json.put("type", "msg");
			json.put("id", this.clientID);
			json.put("msg", msg);
			
			serverWriter.println(json.toString());
			serverWriter.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JSONObject sendJSON(JSONObject json) {
		serverWriter.println(json.toString());
		serverWriter.flush();
		try {
			return new JSONObject(serverReader.nextLine());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}
class ServerConnectionStatus implements Runnable{
	private Socket serverConnection;
	private int clientID;
	private PrintWriter serverWriter;
	private Scanner serverReader;
	public ServerConnectionStatus(Socket socket,int clientID,PrintWriter serverWriter, Scanner serverReader) {
		this.serverConnection = socket;
		this.clientID = clientID;
		this.serverWriter = serverWriter;
		this.serverReader = serverReader;
	}
	@Override
	public void run() {
		while(true) {
			JSONObject json = new JSONObject();
			try {
				json.put("type", "connectChk");
				json.put("id", this.clientID);
				json.put("msg", "PING");
				
				serverWriter.println(json.toString());
				serverWriter.flush();
				JSONObject response = new JSONObject(serverReader.nextLine());
				if(!response.getString("type").equals(json.getString("type")) || response.getInt("id") != json.getInt("id") || !response.getString("msg").equals("PONG")) {
					System.out.println("Connection closed... due to time out");
					this.serverConnection.close();
					System.exit(-1);
				}
//				System.out.println("\nPlayed Ping pong with server");
				Thread.sleep(20000);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Connection closed... due to time out");
				try {
					this.serverConnection.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(-1);
			}
		}
	}
	
}
