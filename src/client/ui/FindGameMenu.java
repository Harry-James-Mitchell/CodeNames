package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

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
		setTitle("Main Menu");
		setSize(width, height);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JButton toMainMenu = new JButton("Main Menu");
        JButton setSeed = new JButton("Start Game");
        JTextField seed = new JTextField("Enter Room Code Here");
        
        String[] args = new String[0];
        toMainMenu.addActionListener((ActionEvent event) -> {
        	MainMenu.main(args);
        	leavePage();
        });
        
        setSeed.addActionListener((ActionEvent event) -> {
        	String roomCode = seed.getText();
        	if(roomCode == null) {
        		JOptionPane.showMessageDialog(null, "");
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
