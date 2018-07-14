package gameLogic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Client;
import client.ui.MainMenu;

public class Game extends JFrame {
	
	private Client server;
	
	private static final long serialVersionUID = 1L;
	private final int width = 1000;
	private final int height = 700;
	private static long seed;
	private final int NUMBER_OF_CARDS = 25;
	private final int RED_CARDS = 9;
	private final int BLUE_CARDS = 8;
	private final int DEATH_CARDS = 1;
	private int currentTurn = 0;
	private JLabel turnName = new JLabel();
	private JLabel redTurn;
	private JLabel blueTurn;
	private int redScore;
	private int blueScore;
	private JLabel redP;
	private JLabel blueP;
	
	
	public Game(String ip){
		
		initUI();
		server = new Client(ip);
	}
	
	private void newGame() {
		currentTurn = 0;
		turnName.setText("RED's Turn");
		redScore = RED_CARDS;
		blueScore = BLUE_CARDS;
	}
	
	private void initUI() {
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(null);
		setTitle("CodeNames");
		setSize(width, height);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setLayout(null);
        
        this.newGame();
        File file = new File("textFiles/Words.txt");//This text file contains all of the words used in the game
        Random rand = new Random(seed); //We use the room code as the seed so everyone has the same board.
        
        ArrayList<String> words = new ArrayList<String>(); //This arraylist will contain all of the words used in the game
        try {
        	Scanner sc = new Scanner(file);
        	while(sc.hasNextLine()) {
        		words.add(sc.nextLine());
        	}
        	sc.close();
        } catch(FileNotFoundException e) {
        	JOptionPane.showMessageDialog(null, "You are missing the Words.txt file and\nwill now be taken back to the main menu.");
        	this.leavePage();
        	String[] args = null;
        	MainMenu.main(args);
        }
        
        JButton endTurn = new JButton("End Turn");
        endTurn.setLocation(450, 25);
        endTurn.setSize(100, 30);
        
        ImageIcon redSpy = new ImageIcon("pics/RedSpy.png");
        JButton redSM = new JButton();
        redSM.setIcon(redSpy);
        redSM.setLocation(10, 200);
        redSM.setSize(100, 300);

        ImageIcon blueSpy = new ImageIcon("pics/BlueSpy.png");
        JButton blueSM = new JButton();
        blueSM.setIcon(blueSpy);
        blueSM.setLocation(875, 200);
        blueSM.setSize(100, 300);
 
        ImageIcon pointer = new ImageIcon("pics/TurnCursor.png");
        redTurn = new JLabel();
        redTurn.setIcon(pointer);
        redTurn.setLocation(45, 150);
        redTurn.setSize(100, 50);
        
        blueTurn = new JLabel();
        blueTurn.setIcon(pointer);
        blueTurn.setLocation(910, 150);
        blueTurn.setSize(100, 50);
        blueTurn.setVisible(false);
        
        //assign a word to every button and keep track of them using the codeNameCard object
        JButton[] cardButtons = new JButton[NUMBER_OF_CARDS];
        CodeNameCard[] cnc = new CodeNameCard[NUMBER_OF_CARDS];
        ArrayList<Integer> locations = new ArrayList<Integer>();
        int c =-1;
        for(int i=0; i<NUMBER_OF_CARDS; i++){
        	if(i%5 ==0) c++;
        	int index = rand.nextInt(words.size());
        	String nextWord = words.get(index);
        	words.remove(index);
        	cardButtons[i] = new JButton(nextWord);
        	cardButtons[i].setSize(125, 25);
        	cardButtons[i].setLocation((i%5*125) + 175, (c*100) + 100);
        	cnc[i] = new CodeNameCard(nextWord, "WHITE", i);
        	locations.add(i);
        }
        
        redP = new JLabel(redScore+"");
        redP.setLocation(60, 510);
        redP.setSize(20, 20);
        blueP = new JLabel(blueScore+"");
        blueP.setSize(20, 20);
        blueP.setLocation(925, 510);
        
        //Assign all of red's cards
        for(int i=0; i<RED_CARDS; i++) {
        	int r = rand.nextInt(locations.size());
        	int index = locations.get(r);
        	cnc[index].setColor("RED");
        	locations.remove(r);
        }
        
        //Assign all of blue's cards
        for(int i=0; i<BLUE_CARDS; i++) {
        	int r = rand.nextInt(locations.size());
        	int index = locations.get(r);
        	cnc[index].setColor("BLUE");
        	locations.remove(r);
        }
        
        //Assign all the black cards
        for(int i=0; i<DEATH_CARDS; i++) {
        	int r = rand.nextInt(locations.size());
        	int index = locations.get(r);
        	cnc[index].setColor("BLACK");
        	locations.remove(r);
        }
    
        //The following buttons are the 25 cards in the game.
        cardButtons[0].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[0].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[0].setBackground(color);
        		this.updateTurn(cnc[0].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[1].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[1].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[1].setBackground(color);
        		this.updateTurn(cnc[1].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[2].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[2].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[2].setBackground(color);
        		this.updateTurn(cnc[2].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[3].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[3].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[3].setBackground(color);
        		this.updateTurn(cnc[3].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[4].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[0].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[4].setBackground(color);
        		this.updateTurn(cnc[4].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[5].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[5].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[5].setBackground(color);
        		this.updateTurn(cnc[5].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[6].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[6].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[6].setBackground(color);
        		this.updateTurn(cnc[6].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[7].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[7].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[7].setBackground(color);
        		this.updateTurn(cnc[7].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[8].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[8].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[8].setBackground(color);
        		this.updateTurn(cnc[8].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[9].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[9].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[9].setBackground(color);
        		this.updateTurn(cnc[9].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[10].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[10].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[10].setBackground(color);
        		this.updateTurn(cnc[10].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[11].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[11].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[11].setBackground(color);
        		this.updateTurn(cnc[11].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[12].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[12].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[12].setBackground(color);
        		this.updateTurn(cnc[12].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[13].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[13].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[13].setBackground(color);
        		this.updateTurn(cnc[13].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[14].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[14].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[14].setBackground(color);
        		this.updateTurn(cnc[14].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[15].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[15].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[15].setBackground(color);
        		this.updateTurn(cnc[15].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[16].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[16].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[16].setBackground(color);
        		this.updateTurn(cnc[16].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[17].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[17].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[17].setBackground(color);
        		this.updateTurn(cnc[17].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[18].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[18].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[18].setBackground(color);
        		this.updateTurn(cnc[18].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[19].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[19].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[19].setBackground(color);
        		this.updateTurn(cnc[19].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[20].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[20].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[20].setBackground(color);
        		this.updateTurn(cnc[20].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[21].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[21].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[21].setBackground(color);
        		this.updateTurn(cnc[21].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[22].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[22].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[22].setBackground(color);
        		this.updateTurn(cnc[22].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[23].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[23].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[23].setBackground(color);
        		this.updateTurn(cnc[23].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        cardButtons[24].addActionListener((ActionEvent event) -> {
        	try {
    			Field field = Class.forName("java.awt.Color").getField(cnc[24].getColor());
        		Color color = (Color)field.get(null);
        		cardButtons[24].setBackground(color);
        		this.updateTurn(cnc[24].getColor());
    		} catch (Exception e) {
    			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
    		}
        });
        
        JButton spyM = new JButton("SpyMaster");
        spyM.addActionListener((ActionEvent event) -> {
        	for(int i=0; i< NUMBER_OF_CARDS; i++) {
        		try {
        			Field field = Class.forName("java.awt.Color").getField(cnc[i].getColor());
            		Color color = (Color)field.get(null);
            		cardButtons[i].setBackground(color);
        		} catch (Exception e) {
        			JOptionPane.showMessageDialog(null, "Unable to fetch colors. :(");
        			break;
        		}
        	}
        });
        
        redSM.addActionListener((ActionEvent event) -> {
        	spyM.doClick();
        });
        blueSM.addActionListener((ActionEvent event) -> {
        	spyM.doClick();
        });
        endTurn.addActionListener((ActionEvent event)->{
        	ChangeTurn(0);
        });
        
        displayPanel.add(turnName);
        displayPanel.add(redSM);
        displayPanel.add(blueSM);
        displayPanel.add(redTurn);
        displayPanel.add(blueTurn);
        displayPanel.add(endTurn);
        displayPanel.add(redP);
        displayPanel.add(blueP);
        //displayPanel.add(spyM);
        for(int i=0;i<NUMBER_OF_CARDS; i++) {
        	displayPanel.add(cardButtons[i]);
        }
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	//This method takes in the color of the clicked button and determines if the turn should be updated
	private void updateTurn(String C) {
		if(C.equals("BLACK")) {
			this.endGame(C);
		}
		else if(C.equals("WHITE")) {
			this.ChangeTurn(currentTurn);
			String team = this.getTeam(currentTurn);
			JOptionPane.showMessageDialog(null, "Your team picked a white square.\nIt is now "+team+"'s turn");
		}
		else if(C.equals("RED")) {
			redScore--;
			if(currentTurn == 1) {
				JOptionPane.showMessageDialog(null, "You clicked the enemy team's square.\n It is now their turn.");
				this.ChangeTurn(currentTurn);
			}
			redP.setText(redScore+"");
			if(redScore == 0) {
				this.endGame(C);
			}
		}
		else if(C.equals("BLUE")) {
			blueScore--;
			if(currentTurn == 0) {
				JOptionPane.showMessageDialog(null, "You clicked the enemy team's square.\n It is now their turn.");
				this.ChangeTurn(currentTurn);
			}
			blueP.setText(blueScore+"");
			if(blueScore == 0) {
				this.endGame(C);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "ERROR, the button you clicked was not assigned a color. :(");
			this.leavePage();
			String[] args = new String[0];
			MainMenu.main(args);
		}
	}
	
	private void ChangeTurn(int t) {
		currentTurn = 1-currentTurn;
		if(currentTurn == 0) {
			this.turnName.setText("RED's Turn");
			redTurn.setVisible(true);
			blueTurn.setVisible(false);
		} else {
			this.turnName.setText("BLUE's Turn");
			redTurn.setVisible(false);
			blueTurn.setVisible(true);
		}
		repaint();
	}
	
	private String getTeam(int t) {
		if(t == 0) {
			return "RED";
		}
		return "Blue";
	}
	
	private void endGame(String c) {
		if(c.equals("BLACK")) {
			ImageIcon death = new ImageIcon("pic/Death.png");
			if(currentTurn == 1) {
				JOptionPane.showMessageDialog(
	                    null,
	                    "You picked the death card and die.\n The RED team wins",
	                    "SAD", JOptionPane.INFORMATION_MESSAGE,
	                    death);
			} else {
				JOptionPane.showMessageDialog(
	                    null,
	                    "You picked the death card and die.\n The BLUE team wins",
	                    "SAD", JOptionPane.INFORMATION_MESSAGE,
	                    death);
			}
		}
		else if(c.equals("BLUE")) {
			ImageIcon blue = new ImageIcon("pic/BlueSpy");
			JOptionPane.showMessageDialog(
                    null,
                    "BLUE team found all of their words and wins the game!",
                    "Good Job", JOptionPane.INFORMATION_MESSAGE,
                    blue);
		} 
		else if(c.equals("RED")) {
			ImageIcon red = new ImageIcon("pic/RedSpy");
			JOptionPane.showMessageDialog(
                    null,
                    "RED team found all of their words and wins the game!",
                    "Good Job", JOptionPane.INFORMATION_MESSAGE,
                    red);
		} 
		else {
			JOptionPane.showMessageDialog(null, "An error occured when the game was sent to a winning state without a winner.\n Taking you back to the main menu");
			
		}
		this.leavePage();
		String[] args = new String[0];
		MainMenu.main(args);
	}
	
	private void leavePage() {
		this.removeAll();
		this.setVisible(false);
	}
	
	public long getSeed() {
		return this.seed;
	}
	
	public static void main(String[] args, String ip) {
		seed = Long.parseLong(ip);
		EventQueue.invokeLater(() -> {
            Game mm = new Game(ip);
            mm.setVisible(true);
        });
	}

}
