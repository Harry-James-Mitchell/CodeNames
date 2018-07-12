package server;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ServerUI extends JFrame {
	
	public ServerUI(String IP, int port) {
		
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			ServerUI mm = new ServerUI("null", 0);
            mm.setVisible(true);
        });
	}

}
