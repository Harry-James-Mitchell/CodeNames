package server;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        
        JLabel IPPrefix = new JLabel("Your server IP is:");
        JLabel PortPrefix = new JLabel("Your server port is:");
        IPPrefix.setSize(125, 50);
        PortPrefix.setSize(125, 50);
        IPPrefix.setLocation(50, 50);
        PortPrefix.setLocation(50, 150);
        
        JTextField IPText = new JTextField(IP);
        JTextField PortText = new JTextField(""+port);
        IPText.setLocation(185, 50);
        PortText.setLocation(185, 150);
        IPText.setSize(100, 50);
        PortText.setSize(100, 50);
        IPText.setEditable(false);
        PortText.setEditable(false);
        
        displayPanel.add(IPPrefix);
        displayPanel.add(PortPrefix);
        displayPanel.add(IPText);
        displayPanel.add(PortText);
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
