package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

public class Client {

	private static final int PORT = 5555;
	private Socket serverConnection;
	private int clientID;
	private long seed;
	public Client(String serverIp) {
		//TODO Add error checking for bad ip
		//Potentially have user put in port
				
		this.serverConnection = null;
		try {
			this.serverConnection = new Socket(serverIp, PORT);
			System.out.println();
			System.out.println("Connected to " + serverIp + "!");
			this.clientID = -1;
			this.seed = -1;
			Scanner in = new Scanner(new BufferedInputStream(this.serverConnection.getInputStream()));
			JSONObject json = new JSONObject(in.nextLine());
			if(json.getString("type")!= null && json.getString("type").equals("idAssign")) {
				this.clientID = json.getInt("id");
			}
			json = new JSONObject(in.nextLine());
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
			Thread serverHandler = new Thread(new ServerJSONSender(this.serverConnection,this.clientID));
			Thread serverConnectionChecker = new Thread(new ServerConnectionStatus(this.serverConnection, this.clientID));
			serverConnectionChecker.start();
			serverHandler.start();
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
}
class ServerJSONSender implements Runnable{
	private Socket serverConnection;
	private int clientID;
	public ServerJSONSender(Socket socket,int clientID) {
		this.serverConnection = socket;
		this.clientID = clientID;
	}
	@Override
	public void run() {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print("Enter a message to the server:");
			sendMessage(in.nextLine());
		}
	}
	private void sendMessage(String msg) {
		JSONObject json = new JSONObject();
		try {
			json.put("type", "msg");
			json.put("id", this.clientID);
			json.put("msg", msg);
			
			PrintWriter out = new PrintWriter(new BufferedOutputStream(this.serverConnection.getOutputStream()));
			out.println(json.toString());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
class ServerConnectionStatus implements Runnable{
	private Socket serverConnection;
	private int clientID;
	public ServerConnectionStatus(Socket socket,int clientID) {
		this.serverConnection = socket;
		this.clientID = clientID;
	}
	@Override
	public void run() {
		while(true) {
			JSONObject json = new JSONObject();
			try {
				json.put("type", "connectChk");
				json.put("id", this.clientID);
				json.put("msg", "PING");
				
				PrintWriter out = new PrintWriter(new BufferedOutputStream(this.serverConnection.getOutputStream()));
				out.println(json.toString());
				out.flush();
				Scanner in = new Scanner(new BufferedInputStream(this.serverConnection.getInputStream()));
				JSONObject response = new JSONObject(in.nextLine());
				if(!response.getString("type").equals(json.getString("type")) || response.getInt("id") != json.getInt("id") || !response.getString("msg").equals("PONG")) {
					System.out.println("Connection closed... due to time out");
					this.serverConnection.close();
					System.exit(-1);
				}
//				System.out.println("\nPlayed Ping pong with server");
				Thread.sleep(60000);
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
