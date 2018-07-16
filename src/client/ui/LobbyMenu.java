package client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Client;
import gameLogic.Game;

public class LobbyMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 550;
	private final int HEIGHT = 500;
	private JTextArea chatBox;

	public LobbyMenu() {
		this.initUI();
	}
	
	public void initUI() {
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
        messageBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	sendMessage(messageBox.getText());
		    }
		});
        
        JButton sendMessage = new JButton("Send");
        sendMessage.setSize(75,50);
        sendMessage.setLocation(240, 25);
        sendMessage.addActionListener((ActionEvent event) ->{
        	this.sendMessage(messageBox.getText());
        });
        
        JButton readyUp = new JButton("Ready");
        readyUp.setSize(75, 50);
        readyUp.setLocation(325, 25);
        readyUp.addActionListener((ActionEvent event) ->{
        	//TODO send a json object to the server telling it that the player is ready
        });
        
        JButton mainMenu = new JButton("leave");
        mainMenu.setSize(75,50);
        mainMenu.setLocation(410, 25);
        mainMenu.addActionListener((ActionEvent event) -> {
        	String[] args = new String[0];
        	MainMenu.main(args);
        	leavePage();
        });
        
        chatBox = new JTextArea();
        chatBox = new JTextArea(5,30);
		chatBox.setEditable(false);
		chatBox.setFont(new Font("Calbri", Font.PLAIN, 14));
        chatBox.setLineWrap(true);
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(25, 100,365, 300 );
        scroll.getViewport().add(chatBox);
        
        displayPanel.add(mainMenu);
        displayPanel.add(readyUp);
        displayPanel.add(sendMessage);
        displayPanel.add(messageBox);
        displayPanel.add(scroll, BorderLayout.CENTER);
        this.add(displayPanel);
	}
	
	private void sendMessage(String message) {
		//TODO send a json object to the server containing the message so all clients can be updated with the message
	}
	
	private void leavePage() {
		this.removeAll();
		this.setVisible(false);
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
            LobbyMenu mm = new LobbyMenu();
            mm.setVisible(true);
        });
	}

}
