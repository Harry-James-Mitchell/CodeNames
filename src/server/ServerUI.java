package server;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String IP;
	private static int port;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	public ServerUI() {
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("CodeNames");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("pics/Frame.png");
        setIconImage(icon.getImage());
        
        JLabel IPLabel = new JLabel("Your server IP is: "+IP);
        JLabel PortLabel = new JLabel("Your server port is: "+port);
        IPLabel.setLocation(50, HEIGHT-300);
        PortLabel.setLocation(50, HEIGHT-200);
        IPLabel.setSize(100, 100);
        PortLabel.setSize(100, 100);
        
        displayPanel.add(IPLabel);
        displayPanel.add(PortLabel);
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	public static void main(String args[], String ip, int portNumber) {
		IP = ip;
		port = portNumber;
		EventQueue.invokeLater(() -> {
			ServerUI mm = new ServerUI();
            mm.setVisible(true);
        });
	}

}
