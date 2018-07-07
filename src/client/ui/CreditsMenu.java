package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditsMenu extends JFrame{
	
	//todo to add jerry

	private int width = 400;
	private int height = 400;
	
	//Jerry: programming
	//Harry: programming
	//Josh: programming
	//Noah: art/programming
	
	
	public CreditsMenu()
	{
		initUI();
	}
	
	public void initUI()
	{
		
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("Credits");
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("pics/Frame.png");
        setIconImage(icon.getImage());
		
		
		JLabel jer = new JLabel("Jeremiah Brusegaard");
		jer.setLocation(50, 50);
		jer.setSize(150, 20);
		
		JLabel jos = new JLabel("Josh Brenneman");
		jos.setLocation(250, 50);
		jos.setSize(150, 20);
		
		JLabel har = new JLabel("Harry Mitchell");
		har.setLocation(50, 150);
		har.setSize(150, 20);
		
		JLabel noa = new JLabel("Noah Mitchell");
		noa.setLocation(250, 150);
		noa.setSize(150, 20);
		
		JButton mainBtn = new JButton("Main Menu");
		
		mainBtn.addActionListener((ActionEvent event) -> {
			MainMenu.main(null);
			leavePage();
		});
		
		
		jer.setLocation(50, 50);
		jer.setSize(150, 20);
		
		mainBtn.setLocation(150, 300);
		mainBtn.setSize(100, 50);
		
		displayPanel.add(jer);
		displayPanel.add(jos);
		displayPanel.add(har);
		displayPanel.add(noa);
		displayPanel.add(mainBtn);
		
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	private void leavePage() {
		this.removeAll();
		this.setVisible(false);
	}
	
	public static void main(String args[]) {
		
		EventQueue.invokeLater(() -> {
            CreditsMenu cm = new CreditsMenu();
            cm.setVisible(true);
        });
		
	}
}
