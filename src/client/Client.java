package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import gameLogic.Game;

public class Client {

	private static final int PORT = 5555;
	private Socket serverConnection;
	private static int clientID;
	private long seed;
	private static PrintWriter serverWriter;
	private static Scanner serverReader;
	public Client(String serverIp,Game game) {
		//TODO Add error checking for bad ip
		//Potentially have user put in port
				
		this.serverConnection = null;
		serverWriter = null;
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
			Thread messageSender = new Thread(new MessageSender());
			Thread serverListener = new Thread(new ServerListener(this.serverConnection, clientID,serverWriter,serverReader));
			messageSender.start();
			serverListener.start();
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
	public long getSeed() {
		return this.seed;
	}
	public static void sendMessage(String msg) {
		JSONObject json = new JSONObject();
		try {
			json.put("type", "msg");
			json.put("id", clientID);
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
	public static void main(String args []) {
		Client client = new Client("localhost", null);
	}
	
}
class MessageSender implements Runnable{
	
	@Override
	public void run() {
		String msg = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Type a message and hit enter to send to your friends!");
		while(true) {
			try {
				msg = in.nextLine();
				System.out.println();
				Client.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
class ServerListener implements Runnable{
	private Socket serverConnection;
	private int clientID;
	private PrintWriter serverWriter;
	private Scanner serverReader;
	public ServerListener(Socket socket,int clientID,PrintWriter serverWriter, Scanner serverReader) {
		this.serverConnection = socket;
		this.clientID = clientID;
		this.serverWriter = serverWriter;
		this.serverReader = serverReader;
	}
	@Override
	public void run() {
		String msg = "";
		while(true) {
			try {
				JSONObject response = new JSONObject(serverReader.nextLine());
				if(response.has("type") && response.getString("type").equals("msg")) {
					msg = response.getString("msg");
					System.out.println("\nClient " + response.getInt("id") + ": " + msg);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
