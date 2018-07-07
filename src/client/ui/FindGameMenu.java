package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        
        JButton setSeed = new JButton("Start Game");
        setSeed.setLocation(110, 120);
        setSeed.setSize(150,75);
        
        JTextField seed = new JTextField("");
        seed.setLocation(110, 200);
        seed.setSize(150, 20);
        seed.setText(null);
        
        
        String[] args = new String[0];
        toMainMenu.addActionListener((ActionEvent event) -> {
        	MainMenu.main(args);
        	leavePage();
        });
        
        setSeed.addActionListener((ActionEvent event) -> {
        	String roomCode = seed.getText();
        	if(roomCode.isEmpty()) {
        		JOptionPane.showMessageDialog(null, "You must enter a room code to join a game!");
        	} else {
        		this.leavePage();
        		Game.main(args, roomCode);
        	}
        });
        
        displayPanel.add(toMainMenu);
        displayPanel.add(seed);
        displayPanel.add(setSeed);
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
