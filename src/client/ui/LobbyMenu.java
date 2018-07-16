package client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LobbyMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 550;
	private final int HEIGHT = 500;
	private JTextArea chatBox;

	public LobbyMenu() {
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("Lobby Menu");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("pics/Frame.png");
        setIconImage(icon.getImage());
        
        JTextField messageBox = new JTextField();
        messageBox.setSize(200, 50);
        messageBox.setLocation(25, 25);
        
        JButton sendMessage = new JButton("Send");
        sendMessage.setSize(75,50);
        sendMessage.setLocation(240, 25);
        
        JButton readyUp = new JButton("Ready");
        readyUp.setSize(75, 50);
        readyUp.setLocation(325, 25);
        
        JButton mainMenu = new JButton();
        mainMenu.setSize(75,50);
        mainMenu.setLocation(415, 25);
        
        chatBox = new JTextArea();
        chatBox = new JTextArea(5,30);
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Calbri", Font.PLAIN, 14));
        chatBox.setLineWrap(true);
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(25, 100,365, 300 );
        scroll.getViewport().add(chatBox);
        
        displayPanel.add(readyUp);
        displayPanel.add(sendMessage);
        displayPanel.add(messageBox);
        displayPanel.add(scroll, BorderLayout.CENTER);
        this.add(displayPanel);
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
            LobbyMenu mm = new LobbyMenu();
            mm.setVisible(true);
        });
	}

}
