package gameLogic;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.ui.MainMenu;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int width = 700;
	private final int height = 700;
	private static long seed;
	private final int NUMBER_OF_CARDS = 25;
	private final int RED_CARDS = 9;
	private final int BLUE_CARDS = 8;
	private final int DEATH_CARDS = 1;
	
	public Game(){
		initUI();
	}
	
	private void newGame() {
		
	}
	
	private void initUI() {
		JPanel displayPanel = new JPanel();
		setTitle("CodeNames");
		setSize(width, height);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
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
        
        //assign a word to every button and keep track of them using the codeNameCard object
        JButton[] cardButtons = new JButton[NUMBER_OF_CARDS];
        CodeNameCard[] cnc = new CodeNameCard[NUMBER_OF_CARDS];
        ArrayList<Integer> locations = new ArrayList<Integer>();
        for(int i=0; i<NUMBER_OF_CARDS; i++){
        	int index = rand.nextInt(words.size());
        	String nextWord = words.get(index);
        	words.remove(index);
        	cardButtons[i] = new JButton(nextWord);
        	cnc[i] = new CodeNameCard(nextWord, "white", i);
        	locations.add(i);
        }
        
        //Assign all of red's cards
        for(int i=0; i<RED_CARDS; i++) {
        	int index = rand.nextInt(locations.size());
        	cnc[index].setColor("red");
        	locations.remove(index);
        }
        
        for(int i=0;i<NUMBER_OF_CARDS; i++) {
        	displayPanel.add(cardButtons[i]);
        }
        this.add(displayPanel);
        this.setVisible(true);
	}
	
	private void leavePage() {
		this.removeAll();
		this.setVisible(false);
	}
	
	public static void main(String[] args, String code) {
		seed = Long.parseLong(code);
		EventQueue.invokeLater(() -> {
            Game mm = new Game();
            mm.setVisible(true);
        });
	}

}
