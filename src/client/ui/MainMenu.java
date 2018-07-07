package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JFrame {
	
	private int width = 400;
	private int height = 400;
	private static JPanel displayPanel;
	private static final long serialVersionUID = 1L;

	public MainMenu() {
		initUI();
	}
	
	public void initUI() {
		displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("Main Menu");
		setSize(width, height);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JButton startButton = new JButton("Join Game");
        startButton.setLocation(125, 40);
        startButton.setSize(150,50);
        
        JButton instructionsButton = new JButton("How to play");
        instructionsButton.setLocation(125, 120);
        instructionsButton.setSize(150,50);
        
        JButton creditsButton = new JButton("Credits");
        creditsButton.setLocation(125, 200);
        creditsButton.setSize(150,50);
        
        JButton quitButton = new JButton("Quit");
        quitButton.setLocation(125, 280);
        quitButton.setSize(150,50);
        
        
        String[] args = new String[0];
        startButton.addActionListener((ActionEvent event) -> {
        	FindGameMenu.main(args);
        	leavePage();
        });
        
        instructionsButton.addActionListener((ActionEvent event) -> {
        	HowToPlayMenu.main(args);
        	leavePage();
        });
        
        creditsButton.addActionListener((ActionEvent event) -> {
        	CreditsMenu.main(args);
        	leavePage();
        });
        
        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        displayPanel.add(startButton);
        displayPanel.add(instructionsButton);
        displayPanel.add(creditsButton);
        displayPanel.add(quitButton);
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	private void leavePage() {
		this.removeAll();
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            MainMenu mm = new MainMenu();
            mm.setVisible(true);
        });
	}

}
