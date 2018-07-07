package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen extends JFrame {
	
	private final int WIDTH = 450;
	private final int HEIGHT = 450;
	
	public TitleScreen() {
		this.initUI();
	}
	
	public void initUI() {
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("CodeNames");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("pics/Frame.png");
        setIconImage(icon.getImage());
        
        ImageIcon backGround = new ImageIcon("pics/TitleIamge.png");
        
        JButton start = new JButton("Start");
        start.setLocation(175, 250);
        start.setSize(100,100);
        start.addActionListener((ActionEvent event)->{
        	MainMenu.main(null);
        	leavePage();
        });
        
        
        JLabel bg = new JLabel();
        bg.setIcon(backGround);
        bg.setLocation(0, 0);
        bg.setSize(WIDTH, HEIGHT);
        
        displayPanel.add(bg);
        displayPanel.add(start);
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	private void leavePage() {
		this.removeAll();
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			TitleScreen mm = new TitleScreen();
            mm.setVisible(true);
        });
	}
}
