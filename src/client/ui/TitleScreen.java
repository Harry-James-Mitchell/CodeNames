package client.ui;

import java.awt.EventQueue;

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
        
        ImageIcon backGround = new ImageIcon("pics/TitleImage.png");
        JButton start = new JButton("Start");
        
        JLabel bg = new JLabel(backGround);
        bg.setLocation(0, 0);
        bg.setSize(WIDTH, HEIGHT);
        
        
        displayPanel.add(bg);
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			TitleScreen mm = new TitleScreen();
            mm.setVisible(true);
        });
	}
}
