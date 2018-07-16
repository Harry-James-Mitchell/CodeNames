package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Client;
import gameLogic.Game;


public class FindGameMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final int width = 400;
	private final int height = 400;
	
	public FindGameMenu() {
		initUI();
	}
	
	public void initUI() {
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("Main Menu");
		setSize(width, height);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("pics/Frame.png");
        setIconImage(icon.getImage());
        
        JButton toMainMenu = new JButton("Main Menu");
        toMainMenu.setLocation(10, 10);
        toMainMenu.setSize(100, 20);
        
        JButton joinIP = new JButton("Start Game");
        joinIP.setLocation(110, 120);
        joinIP.setSize(150,75);
        
        JTextField ip = new JTextField("");
        ip.setLocation(110, 200);
        ip.setSize(150, 20);
        ip.setText(null);
        
        
        String[] args = new String[0];
        toMainMenu.addActionListener((ActionEvent event) -> {
        	MainMenu.main(args);
        	leavePage();
        });
        
        ip.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String roomCode = ip.getText();
		    	leavePage();
		    	Game.main(args, new Client(roomCode,null));
		    }
		});
        
        joinIP.addActionListener((ActionEvent event) -> {
        	String roomCode = ip.getText();
        	if(roomCode.isEmpty()) {
        		JOptionPane.showMessageDialog(null, "You must enter an ip to join a game!");
        	} else {
        		//TODO check bad IP
        		this.leavePage();
        		Game.main(args, new Client(roomCode,null));
        	}
        });
        
        displayPanel.add(toMainMenu);
        displayPanel.add(ip);
        displayPanel.add(joinIP);
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	private void leavePage() {
		this.removeAll();
		this.setVisible(false);
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
            FindGameMenu mm = new FindGameMenu();
            mm.setVisible(true);
        });
	}
}
