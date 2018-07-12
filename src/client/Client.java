package client;

import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class Client {

	private static final int PORT = 5555;
	private Socket serverConnection;
	
	public Client(String serverIp) {
		//TODO Add error checking for bad ip
		//Potentially have user put in port
				
//		System.out.print("Please enter ip of server: ");
//		Scanner in = new Scanner(System.in);
//		String serverIp = in.nextLine();
//		in.close();
		this.serverConnection = null;
		try {
			this.serverConnection = new Socket(serverIp, PORT);
			System.out.println();
			System.out.println("Connected to " + serverIp + "!");
			Thread serverHandler = new Thread(new ServerHandler(this.serverConnection));
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
		while(true);
	}
}
class ServerHandler implements Runnable{
	private Socket serverConnection;
	public ServerHandler(Socket socket) {
		this.serverConnection = socket;
	}
	@Override
	public void run() {
		sendMessage("testing");
		while(true) {
			
		}
	}
	private void sendMessage(String msg) {
		JSONObject json = new JSONObject();
		try {
			json.put("type", "MSG");
			json.put("msg", msg);
			OutputStreamWriter out = new OutputStreamWriter(this.serverConnection.getOutputStream(), StandardCharsets.UTF_8);
			out.write(json.toString());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
