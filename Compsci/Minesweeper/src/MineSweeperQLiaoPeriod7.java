/*Name: Qin Liao



 * Period: 7
 * Time Spent: 2 Hours
 * Reflection: 
 * 
 * Graphics:
 * I spent a decent amount of time polishing my minewsweeper. I've always liked pokemon
 * so I decided to go with a pokemon themed version. I thought it would be cool to
 * evolve the pokemon. So what I did was as the user hits a certain number of flags right,
 * the pokemon will evolve-- first from pichu to pikachu to raichu. Next, I also incoroporated
 * a logo, made general UI improvements by playing with colors, and adding a soundTrack.
 * I had some sort of trouble adding the logo. I learned to extend a class and calling
 * the paintComponent(duh... but I tottally forgot for a bit).
 * 
 *  AutoPlay:
 *  I tried to work on autoplay for fun. My strategy for autoplay:
 *  1) Pick a random spot first
 *  2) Scan the rows and columns, If there is a spot on the map such that the number
 *  of neighboring mines equals the number of available spaces left just flag that one
 *
 *  3) Pick a random spot again  and repeat. Alert the user every time you do 
 *  a spot.
 *  4) Methods include
 *  	i. countNearByUnclicked(). This returns the number of unclicked spaces
 *  	ii. countNearByMines(). This returns the number of mines nearby
 *  	iii. Flag all nearbyMines(). if the condition is met that the number
 *  of unclicked spaces equals the number of mines nearby, just flag all the nearby mines.
 *  
 *  
 * 
 * 
 * 
 */


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.Timer;



public class MineSweeperQLiaoPeriod7 implements ActionListener, MouseListener, MouseMotionListener{
	// Attributes
	Color color = Color.RED;
	MyDrawingPanel drawingPanel;
	Desktop desktop;
	Color grid[][] = new Color[20][20];
	String state[][] = new String[20][20];
	JButton button3;
	JSlider slider;
	int generation = 0;
	JFrame window;
	int minesLeftCounter = 0;
	BufferedImage image;
	JLabel minesLable;
	JLabel timeLable;
	int timeLeft = 600; //10 minuts to complete the challenge
	Timer timer;	
	// Used for MIDI files
	File midiFile;
	Sequencer sequencer;
	
	private String currentPokemon = "/Pichu.png";
	
	MineSweeperQLiaoPeriod7() {
		// Create Java Window		
		window = new JFrame("MineSweeper");
		window.setBounds(200, 200, 600, 700);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanel to draw in
		drawingPanel = new MyDrawingPanel();
		drawingPanel.setBounds(100, 130, 400, 400);
		drawingPanel.setBorder(BorderFactory.createEtchedBorder());			
		
		//Time Elapsed Box
		JPanel timeElapsed = new JPanel();
		timeElapsed.setBounds(100, 530, 150, 100);
		timeElapsed.setBorder(BorderFactory.createTitledBorder("Time Left"));
		timeLable = new JLabel("" + timeLeft);
		timeElapsed.add(timeLable);

		//Mines Left
		JPanel minesLeft = new JPanel();
		minesLeft.setBounds(250, 530, 150, 100);
		minesLeft.setBorder(BorderFactory.createTitledBorder("Battles Left"));
		minesLable = new JLabel("" + minesLeftCounter);
		minesLeft.add(minesLable);
		
		//Auto Play
		JButton autoPlay = new JButton("AUTOPLAY");
		autoPlay.setBounds(400, 570, 100, 50 );
		
		//Pokemon lLogo
		pokemonPanel pokemonLogo = new pokemonPanel();
		pokemonLogo.setBounds(100, 10, 400, 100);		

					
		// Add GUI elements to the Java window's ContentPane
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
				
		
		mainPanel.add(pokemonLogo);
		mainPanel.add(drawingPanel);
		mainPanel.add(timeElapsed);
		mainPanel.add(minesLeft);
		mainPanel.add(autoPlay);
		Color orange = new Color(255, 204, 0);
		mainPanel.setBackground(Color.orange);
		
		window.getContentPane().add(mainPanel);
		
		
		//add listeners
		drawingPanel.addMouseMotionListener(this);
		drawingPanel.addMouseListener(this);
			
		//Add a new menu to my program 
		JMenuBar menuBar = new JMenuBar();		
				
		//Make Jmenus
		JMenu gameMenu = new JMenu("Game");
		JMenu optionsMenu = new JMenu("Options");
		JMenu helpMenu = new JMenu("Help"); 
		
		menuBar.add(gameMenu);
		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);
		
		
		//Step 3 make jmenu items
		JMenuItem menuNewGame = new JMenuItem("New Game");
		JMenuItem menuExit = new JMenuItem("Exit");
		JMenuItem menuTotalMines = new JMenuItem("Total Mines");
		JMenuItem menuAbout = new JMenuItem("About");
		JMenuItem menuHowToPlay= new JMenuItem("How To Play");
						
		//add the items to the menu
		gameMenu.add(menuNewGame);
		gameMenu.add(menuExit);
		optionsMenu.add(menuTotalMines);
		helpMenu.add(menuAbout);
		helpMenu.add(menuHowToPlay);
		
		
		menuNewGame.addActionListener(this);
		menuExit.addActionListener(this);
		menuTotalMines.addActionListener(this);
		menuAbout.addActionListener(this);
		menuHowToPlay.addActionListener(this);
		autoPlay.addActionListener(this);
		
		//add stuff to the windows
		window.setJMenuBar(menuBar);		
		
		//initialize the mines
		initializeState();
		minesLeftCounter = initializeMines();	
		checkState();
		
		// Let there be light
		window.setVisible(true);
				
		//revealAllMines(); 
		minesLable.setText("" + minesLeftCounter);
		
		/*
		  //start the timer		  ;
		  int delay = 1000; //milliseconds every second
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		         timeLeft--;
		         timeLable.setText("" + timeLeft);
		         
		         if(timeLeft == 0){
		        	 JOptionPane.showMessageDialog(null, "GameOver!");	
		        	 
		        	 newGame();
		         }
		      }
		  };
		 new Timer(delay, taskPerformer).start();
		 
		 */
		
		JOptionPane.showMessageDialog(null, "Team Rocket has caught your pokemon!"
				+ "Battle them by flagging them! " + "Do not step into "
						+ "their territory without flagging them first!");
		JOptionPane.showMessageDialog(null, "As you go through more battles, your pokemon"
				+ " will evolve!");
		
		 timer = new Timer(1000, new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					 timeLeft--;
			         timeLable.setText("" + timeLeft);
			         
			         if(timeLeft == 0){
			        	 JOptionPane.showMessageDialog(null, "GameOver!");	
			        	 timer.stop();
			        	 newGame();
			         }									
				}
			  });
		timer.start();
		
		//Do the Midi File
		/*
		midiFile = new File("/PokemonThemeSong.mid");
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.setSequence(MidiSystem.getSequence(midiFile));
			sequencer.open();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}
		catch(MidiUnavailableException mue) {
			System.out.println("Midi device unavailable!" + mue.getMessage()); }
		catch(InvalidMidiDataException imde) {
			System.out.println("Invalid Midi data!" + imde.getMessage()); }
		catch(IOException ioe) {
			System.out.println("I/O Error!" + ioe.getMessage()); }
		sequencer.start();

		*/
		

	}
	public void initializeState(){
		for(int a = 0; a < state.length; a++){
			for (int b = 0; b < state.length; b++) {
				state[a][b] = "notClickedYet";
			}
		}
	}
	
	public void checkState(){
		for(int a = 0; a < state.length; a++){
			for(int b = 0; b < state.length; b++){
				if(state[a][b].equals("mine")){
					System.out.println("Mine at: " + a + " " + b);
					
				}
				
			}
		}
	}
	
	
	public int initializeMines(){
		int num = 50; // have 50 mines
		
		for(int a = 0; a < num; a ++){
				Random leftNum = new Random();
				Random rightNum = new Random();
				int leftNumber = leftNum.nextInt(20);
				int rightNumber = rightNum.nextInt(20);
				state[leftNumber][rightNumber]= "mine";					
		}
		return num;
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		int num = arg0.getModifiersEx();
		System.out.println(arg0.getModifiersEx());
		
						
		//if its a left click then do recursion
		if(num == 1024){
			System.out.println("X:" + arg0.getX()/20);
			System.out.println("Y:" + arg0.getY()/20);			
			if(state[arg0.getX()/20][arg0.getY()/20] == "mine"){
				drawSquare("mine", arg0.getX()/20 * 20, arg0.getY()/20 * 20);
				JOptionPane.showMessageDialog(null, "Team Rocket Win!");				
				newGame();
			}
			else{
				//check Neighbors
				int neighbors = checkNeighbors(arg0.getX()/20, arg0.getY()/20);	
				System.out.println("neighbors OG" + neighbors);				
				if (neighbors == 0) {
					drawSquare("clear", arg0.getX()/20 * 20, arg0.getY()/20 * 20);
					state[arg0.getX()/20][arg0.getY()/20] = "clickedAndClear";
					runMineSweep(arg0.getX()/20, arg0.getY()/20);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), arg0.getX()/20 * 20, arg0.getY()/20 * 20 );
					state[arg0.getX()/20][arg0.getY()/20] = "numbered";
				}
			}
		}
		
		//else right click is a flag
		//we will use command click which is 1280
		else if(num == 1280){
			//if the number of minesLeft equals 0,then the game wins			
			//get the code to put a flag on this 
			drawSquare("flag", arg0.getX()/20 * 20, arg0.getY()/20 * 20 );
			state[arg0.getX()/20][arg0.getY()/20] = "flag";
							
			if(state[arg0.getX()/20][arg0.getY()/20] == "mine"){
				minesLeftCounter--;
				minesLable.setText("" + minesLeftCounter);
			}
			//change the pokemon based on the number of mines left
			if(minesLeftCounter == 48){
				JOptionPane.showMessageDialog(null, "Your pokemon Evolved! It's a Pikachu");
				currentPokemon = "/Pikachu.png";
			}
			if(minesLeftCounter == 45){
				JOptionPane.showMessageDialog(null, "Your pokemon Evolved! It's a Raichu");
				currentPokemon = "/raichu.png";
			}
			//if the the minesLeftCounter equals 0 the game is won
			if(minesLeftCounter == 0){
				JOptionPane.showMessageDialog(null, "You Won! Congrats!");
			}
						
		}
	}
	
	private void newGame() {
		drawingPanel.repaint();
		//initialize the mines
		initializeState();
		minesLeftCounter = initializeMines();	
		checkState();
		minesLable.setText("" + minesLeftCounter);
		currentPokemon = "/Pichu.png";
		//reset the timer
		timeLeft = 600;
		timer = new Timer(1000, new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					 timeLeft--;
			         timeLable.setText("" + timeLeft);
			         
			         if(timeLeft == 0){
			        	 JOptionPane.showMessageDialog(null, "GameOver!");	
			        	 timer.stop();
			        	 newGame();
			         }									
				}
			  });
		timer.start();
	}
	
	//Auto Play Algorithm
	
	public void autoPlay(){
		do{
			JOptionPane.showMessageDialog(null, "AutoPlay Intiatied");
			//Pick a random spot make sure its not a mine though
			boolean conditionsNotMet = true;
			Random generator = new Random();
			int left = generator.nextInt(20);
			int right = generator.nextInt(20);
			while(conditionsNotMet){
				conditionsNotMet = false;				
				if(state[left][right] == "mine"){
					conditionsNotMet = true;
				};
				left = generator.nextInt(20);
				right = generator.nextInt(20);
			}
			
			// target the notClickedYet
			int neighbors = checkNeighbors(left, right);	
			System.out.println("neighbors OG" + neighbors);				
			if (neighbors == 0) {
				drawSquare("clear", left * 20, right * 20);
				state[left][right] = "clickedAndClear";
				runMineSweep(left, right);
			}
			else{
				//put the number on the board
				drawSquare(Integer.toString(neighbors), left * 20, right * 20);
				state[left][right] = "numbered";
			}
			
			//this should open up some spots
			//check the neighbor of each spot. If the neighbor equals
			int regions = 0;
			for (int a = 0; a < state.length; a++) {
				for (int b = 0; b < state.length; b++) {
					//find the number of unClicked spots
					//find the Mines 
					//if they are equal then just flag them
					int unclicked = checkUnclicked(a, b);
					int nearbyMines = checkNeighbors(a, b);
					if(unclicked == nearbyMines){
						flagAllUnclicked(a, b);
						regions++;
					}
				}
			}
			
			//Now alert the user what happened
			JOptionPane.showMessageDialog(null, "Step Complete. Flagged " + regions + " Regions");					
			
			
		} while(minesLeftCounter > 0);
		
	}
	
	
	//This method flags all spots that are unclicked
	private void flagAllUnclicked(int x, int y) {
		// TODO Auto-generated method stub
		int tempRow = x - 1;
		int tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol * 20);
				}				
			}
		}
		// top 
		tempRow = x;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol * 20);
				}				
			}
		}
		// top right corner
		tempRow = x + 1;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol);
				}				
			}
		}
		// right
		tempRow = x + 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol * 20);
				}				
			}
		}
		// bottom right
		tempRow = x + 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol * 20);
				}				
			}
		}
		// bottom 
		tempRow = x;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol * 20);
				}				
			}
		}
		// bottom left
		tempRow = x - 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol * 20);
				}				
			}
		}
		// left
		tempRow = x - 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					state[tempRow][tempCol] = "flagged";
					drawSquare("flag", tempRow * 20, tempCol * 20);
				}				
			}
		}
		
		
	}
	private int checkUnclicked(int x, int y) {
		// TODO Auto-generated method stub
		int neighbors = 0;
		// just have a bunch of if statements to simplify things
		//top left
		int tempRow = x - 1;
		int tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		// top 
		tempRow = x;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		// top right corner
		tempRow = x + 1;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		// right
		tempRow = x + 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		// bottom right
		tempRow = x + 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		// bottom 
		tempRow = x;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		// bottom left
		tempRow = x - 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		// left
		tempRow = x - 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("notClickedYet")) {
					neighbors++;
				}				
			}
		}
		
		return neighbors;
		
	}
	private void runMineSweep(int x, int y) {
		// Start from the top
		//This is assuming that this spot has no neighbors that are mines
		int tempRow = x;
		int tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					drawSquare("clear", tempRow * 20, tempCol * 20);
					state[tempRow][tempCol] = "clickedAndClear";
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
					state[tempRow][tempCol] = "numbered";
				}
				
			}
		}
		// top right cornercorner
		tempRow = x + 1;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					drawSquare("clear", tempRow * 20, tempCol * 20);
					state[tempRow][tempCol] = "clickedAndClear";
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
					state[tempRow][tempCol] = "numbered";
				}
				
			}
		}
		// right corner
		tempRow = x + 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					drawSquare("clear", tempRow * 20, tempCol * 20);
					state[tempRow][tempCol] = "clickedAndClear";
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					state[tempRow][tempCol] = "numbered";
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
				}
				
			}
		}
		// bottom right corner
		tempRow = x + 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					state[tempRow][tempCol] = "clickedAndClear";
					drawSquare("clear", tempRow * 20, tempCol * 20);					
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
					state[tempRow][tempCol] = "numbered";
				}
				
			}
		}
		// bottom
		tempRow = x;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					state[tempRow][tempCol] = "clickedAndClear";
					drawSquare("clear", tempRow * 20, tempCol * 20);					
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
					state[tempRow][tempCol] = "numbered";
				}
				
			}
		}
		// bottom left
		tempRow = x - 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					drawSquare("clear", tempRow * 20, tempCol * 20);
					state[tempRow][tempCol] = "clickedAndClear";
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
					state[tempRow][tempCol] = "numbered";
				}
				
			}
		}
		// left
		tempRow = x - 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					drawSquare("clear", tempRow * 20, tempCol * 20);
					state[tempRow][tempCol] = "clickedAndClear";
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
					state[tempRow][tempCol] = "numbered";
				}
				
			}
		}
		// top left
		tempRow = x - 1;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				//Check the number of neighbors at thsi point
				int neighbors = checkNeighbors(tempRow, tempCol);
				System.out.println("neighbors" + neighbors);
				if (neighbors == 0 && state[tempRow][tempCol] == "notClickedYet") {
					//if there is no neighbors
					drawSquare("clear", tempRow * 20, tempCol * 20);
					state[tempRow][tempCol] = "clickedAndClear";
					//if there is no neighbors go to recursion
					runMineSweep(tempRow, tempCol);
				}
				else{
					//put the number on the board
					drawSquare(Integer.toString(neighbors), tempRow  * 20, tempCol * 20);
					state[tempRow][tempCol] = "numbered";
				}
				
			}
		}
	}
	private void revealAllMines() {
		// TODO Auto-generated method stub		
		Graphics g = drawingPanel.getGraphics();
		for(int a = 0; a < 20; a++){
			for (int b = 0; b < 20; b++) {
				if(state[a][b] == "mine"){
					try {
						image = ImageIO.read(getClass().getResourceAsStream("/TeamRocket.png"));
					} catch (Exception e) {
						System.out.println("Didn't catch it!");
						
					}		
					g.drawImage(image, a * 20, b*20, 20, 20, Color.gray, null); 							
					
					//draw the borders
					g.setColor(Color.LIGHT_GRAY);
					g.drawRect(a*20, b* 20, 20, 20);					
				}
			}
		}
	}

	public void drawSquare(String state, int x, int y){
		Graphics g = drawingPanel.getGraphics();		
		if(state.equals("number")){
			//draw the number
		}
		else if(state.equals("flag")){
			//draw the flag
			/*
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/pink-flag-md.png"));
			} catch (Exception e) {
				System.out.println("Didn't catch it!");
				
			}
			*/
			
			g.drawImage(image, x, y, 20, 20, Color.gray, null); 
			
			//draw the borders
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		else if(state.equals("mine")){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/TeamRocket.png"));
			} catch (Exception e) {
				System.out.println("Didn't catch it!");
				
			}			
			g.drawImage(image, x, y, 20, 20, Color.gray, null); 
			
			//draw the borders
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		
		//draw the number
		else if(state.equals("1")){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Minesweeper_1.png"));
			} catch (Exception e) {
				System.out.println("Didn't catch it!");
				
			}			
			g.drawImage(image, x, y, 20, 20, Color.gray, null); 
			
			//draw the borders
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		else if(state.equals("2")){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Minesweeper_2.png"));
			} catch (Exception e) {
				System.out.println("Didn't catch it!");
				
			}			
			g.drawImage(image, x, y, 20, 20, Color.gray, null); 
			
			//draw the borders
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		else if(state.equals("3")){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Minesweeper_3.png"));
			} catch (Exception e) {
				System.out.println("Didn't catch it!");
				
			}			
			g.drawImage(image, x, y, 20, 20, Color.gray, null); 
			
			//draw the borders
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		else if(state.equals("4")){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Minesweeper_4.png"));
			} catch (Exception e) {
				System.out.println("Didn't catch it!");
				
			}			
			g.drawImage(image, x, y, 20, 20, Color.gray, null); 
			
			//draw the borders
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		else if(state.equals("5")){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Minesweeper_5.png"));
			} catch (Exception e) {
				System.out.println("Didn't catch it!");
				
			}			
			g.drawImage(image, x, y, 20, 20, Color.gray, null); 
			
			//draw the borders
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		
		else if(state.equals("clear")){
			//draw the cleared square
			try {
				image = ImageIO.read(getClass().getResourceAsStream(currentPokemon ));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				
			}
			g.drawImage(image, x, y, 20, 20, Color.gray, null);
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(x, y, 20, 20);
		}
		
		
	}
	
	private int checkNeighbors(int x, int y) {
		int neighbors = 0;
		// just have a bunch of if statements to simplify things
		//top left
		int tempRow = x - 1;
		int tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		// top 
		tempRow = x;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		// top right corner
		tempRow = x + 1;
		tempCol = y - 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		// right
		tempRow = x + 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		// bottom right
		tempRow = x + 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		// bottom 
		tempRow = x;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		// bottom left
		tempRow = x - 1;
		tempCol = y + 1;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		// left
		tempRow = x - 1;
		tempCol = y;
		if (tempRow >= 0 && tempRow < 20) {
			if (tempCol >= 0 && tempCol < 20) {
				if (state[tempRow][tempCol].equals("mine")) {
					neighbors++;
				}				
			}
		}
		
		return neighbors;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("New Game")){
			newGame();
		}
		//Total Mines
		if(e.getActionCommand().equals("Total Mines")){
			JOptionPane.showMessageDialog(null, minesLeftCounter);
		}
		//Exit
		if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}
		//About
		if(e.getActionCommand().equals("About")){
			try {
				JEditorPane aboutContent = new JEditorPane(new URL("file:About.html"));
				JScrollPane aboutPane = new JScrollPane(aboutContent);
				JOptionPane.showMessageDialog(null, aboutPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
			
		//How to Play
		if(e.getActionCommand().equals("How To Play")){
			try {
				JEditorPane howToPlayContent = new JEditorPane(new URL("file:howToPlay.html"));
				JScrollPane howToPlayPane = new JScrollPane(howToPlayContent);
				JOptionPane.showMessageDialog(null, howToPlayPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}			
		}
		if(e.getActionCommand().equals("AUTOPLAY")){
			autoPlay();
		}
		
		
	}
	
	private class MyDrawingPanel extends JPanel {

		// Not required, but gets rid of the serialVersionUID warning. Google
		// it, if desired.
		static final long serialVersionUID = 1234567890L;

		public void paintComponent(Graphics g) {
			
			g.setColor(Color.black);
			g.fillRect(2, 2, this.getWidth() - 2, this.getHeight() - 2);

			g.setColor(Color.lightGray);
			for (int x = 0; x < this.getWidth(); x += 20)
				g.drawLine(x, 0, x, this.getHeight());

			for (int y = 0; y < this.getHeight(); y += 20)
				g.drawLine(0, y, this.getWidth(), y);			 			
			
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class pokemonPanel extends JPanel{

    private BufferedImage image;

    public pokemonPanel() {
       try {                          
          image = ImageIO.read(getClass().getResourceAsStream("/PokemonLogo.png"));		  
       } catch (IOException ex) {
    	   System.out.println(ex.getMessage());
		
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 400, 100, Color.CYAN, null); // see javadoc for more info on the parameters            
    }

}