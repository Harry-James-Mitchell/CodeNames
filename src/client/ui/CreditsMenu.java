package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditsMenu extends JFrame{

	private final int WIDTH = 450;
	private final int HEIGHT = 500;
	private final int PIC_WIDTH = 150;
	private final int PIC_HEIGHT = 150;
	
	//Harry: lead dev
	//Jerry: lead backend dev
	//Josh: lead frontend dev
	//Noah: lead artist
	
	
	public CreditsMenu()
	{
		initUI();
	}
	
	public void initUI()
	{
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("Credits");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("pics/Frame.png");
        setIconImage(icon.getImage());
		
        ImageIcon JerryFace = new ImageIcon("pics/JerryFace.png");
        ImageIcon JoshFace = new ImageIcon("pics/Josh_Face.png");
        ImageIcon HarryFace = new ImageIcon("pics/Dab.png");
        ImageIcon NoahFace = new ImageIcon("pics/NoahMeme.png");
		
		JLabel jer = new JLabel("Jeremiah Brusegaard");
		jer.setLocation(40, 225);
		jer.setSize(150, 20);
		JLabel JerryPic = new JLabel(JerryFace);
		JerryPic.setSize(PIC_WIDTH, PIC_HEIGHT);
		JerryPic.setLocation(25, 235);
		
		JLabel jos = new JLabel("Josh Brenneman");
		jos.setLocation(265, 10);
		jos.setSize(150, 20);
		JLabel JoshPic = new JLabel(JoshFace);
		JoshPic.setSize(PIC_WIDTH, PIC_HEIGHT);
		JoshPic.setLocation(235, 40);
		
		JLabel har = new JLabel("Harry Mitchell");
		har.setLocation(65, 10);
		har.setSize(150, 20);
		JLabel HarryPic = new JLabel(HarryFace);
		HarryPic.setSize(PIC_WIDTH, PIC_HEIGHT);
		HarryPic.setLocation(25, 40);
		
		JLabel noa = new JLabel("Noah Mitchell");
		noa.setLocation(265, 225);
		noa.setSize(150, 20);
		JLabel NoahPic = new JLabel(NoahFace);
		NoahPic.setSize(PIC_WIDTH, PIC_HEIGHT);
		NoahPic.setLocation(235, 235);
		
		JButton mainBtn = new JButton("Main Menu");
		mainBtn.addActionListener((ActionEvent event) -> {
			MainMenu.main(null);
			leavePage();
		});		
		mainBtn.setLocation(150, 400);
		mainBtn.setSize(100, 50);
		
		displayPanel.add(jer);
		displayPanel.add(jos);
		displayPanel.add(har);
		displayPanel.add(noa);
		displayPanel.add(mainBtn);
		displayPanel.add(HarryPic);
		displayPanel.add(JoshPic);
		displayPanel.add(JerryPic);
		displayPanel.add(NoahPic);
		
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
