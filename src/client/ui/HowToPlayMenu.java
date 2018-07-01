package client.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HowToPlayMenu extends JFrame {
	
	private final int width = 450;
	private final int height = 400;
	private static JPanel displayPanel;
	private final int pages = 10;
	
	public HowToPlayMenu() {
		initUI();
	}
	
	public void initUI() {
		
		displayPanel = new JPanel();
		setTitle("How to play");
		setSize(width, height);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        String[] howTo = new String[]{"<html>","<html>","<html>","<html>","<html>","<html>","<html>","<html>","<html>","<html>"};
        int i = 0;
        File rules = new File("textFiles/rules.txt");
        try {
        	BufferedReader br = new BufferedReader(new FileReader(rules));
        	for(i=0; i<pages; i++) {
        		boolean flag = false;
            	for(int j=0; j<10; j++) {
            		String line = br.readLine();
            		if(line == null) {
            			flag = true;
            			break;
        			}
            		howTo[i] += line+"<br>";
            	}
            	howTo[i]+="</html>";
            	if(flag) {break;}
            }
        } catch(IOException E) {
        	JOptionPane.showMessageDialog(null, "The rules.txt file has been does not exist!\nReturning to main menu!");
        	this.leavePage();
        }
        int MaxPages = i;
        int current = 0;
        System.out.println("I:"+i+"/nMaxPages:"+MaxPages);
        System.out.println(howTo[0]);
        
        
        
        ImageIcon next = new ImageIcon("pics/NextArrow.png");
        ImageIcon prev = new ImageIcon("pics/PrevArrow.png");
        
        JButton mainButton = new JButton("Main Menu");
        JLabel instructions = new JLabel(howTo[0]);
        JButton nextDialog = new JButton(next);
        JButton prevDialog = new JButton(prev);
        
        
        mainButton.addActionListener((ActionEvent event) -> {
        	leavePage();
        });
        
        nextDialog.addActionListener((ActionEvent event) -> {
        	leavePage();
        });
        
        prevDialog.addActionListener((ActionEvent event) -> {
        	if(current != 0) {
        		
        	}
        });
        
        displayPanel.add(mainButton);
        displayPanel.add(instructions);
        displayPanel.add(nextDialog);
        displayPanel.add(prevDialog);
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	private void leavePage() {
		String[] args = new String[0];
		this.removeAll();
		this.setVisible(false);
		MainMenu.main(args);
	}
	
	private void updateButtons(int max, int current) {
		
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			HowToPlayMenu mm = new HowToPlayMenu();
            mm.setVisible(true);
        });
	}
}
