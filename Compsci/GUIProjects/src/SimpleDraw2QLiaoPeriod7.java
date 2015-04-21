import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/* Name: qin Liao
 * Period: 7 
 * This lab was probably one of the most difficult labs we have had all year.
 * I think this is partly due to me misunderstanding the directions. At first
 * I was c
 *
 */
 


public class SimpleDraw2QLiaoPeriod7 {

	public static void main(String[] args) {

		MyGUI gui = new MyGUI();
	}
}

class MyGUI implements ActionListener, MouseMotionListener, MouseListener{

	// Attributes
	Color color = Color.RED;
	MyDrawingPanel drawingPanel;
	Desktop desktop;
	Color grid[][] = new Color[20][20];

	MyGUI() {

		// Create Java Window
		JFrame window = new JFrame("SimpleDraw");
		window.setBounds(100, 100, 445, 600);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create GUI elements

		// JPanel to draw in
		drawingPanel = new MyDrawingPanel();
		drawingPanel.setBounds(20, 20, 400, 400);
		drawingPanel.setBorder(BorderFactory.createEtchedBorder());

		// JButton
		JButton button = new JButton("Reset");
		button.setBounds(190, 510, 75, 20);

		// JRadioButtons
		JRadioButton radioButton1 = new JRadioButton("Red", true);
		JRadioButton radioButton2 = new JRadioButton("Green");
		JRadioButton radioButton3 = new JRadioButton("Custom");
		
		radioButton1.setBounds(50, 75, 100, 20);
		radioButton2.setBounds(50, 100, 100, 20);
		radioButton3.setBounds(50, 125, 100, 20);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioButton1);
		radioGroup.add(radioButton2);
		radioGroup.add(radioButton3);		

		// Add GUI elements to the Java window's ContentPane
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JPanel colorPanel = new JPanel();
		colorPanel.setBorder(BorderFactory.createTitledBorder("Drawing Color"));

		colorPanel.setBounds(120, 425, 200, 70);
		colorPanel.add(radioButton1);
		colorPanel.add(radioButton2);
		colorPanel.add(radioButton3);

		mainPanel.add(drawingPanel);

		mainPanel.add(colorPanel);
		mainPanel.add(button);

		window.getContentPane().add(mainPanel);
		
		drawingPanel.addMouseMotionListener(this);
		drawingPanel.addMouseListener(this);
		
		radioButton1.addActionListener(this);
		radioButton2.addActionListener(this);
		radioButton3.addActionListener(this);

		button.addActionListener(this);

		//Add a new menu to my program 
		JMenuBar menuBar = new JMenuBar();		
				
		//Make Jmenus
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		//Step 3 make jmenu items
		JMenuItem menuOpen = new JMenuItem("open", 'o');
		JMenuItem menuSave = new JMenuItem("save", 's');
		JMenuItem menuClear = new JMenuItem("clear", 'c');
		
		//add the items to the menu
		fileMenu.add(menuOpen);
		fileMenu.add(menuSave);
		editMenu.add(menuClear);
		
		menuOpen.addActionListener(this);
		menuSave.addActionListener(this);
		menuClear.addActionListener(this);
		
		window.setJMenuBar(menuBar);
		
		//initialize grids
		initializeGrids();

		// Let there be light
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		System.out.println("Action -> " + e.getActionCommand());

		if (e.getActionCommand() != null) {

			if (e.getActionCommand().equals("Red"))
				color = Color.RED;
			else if (e.getActionCommand().equals("Green"))
				color = Color.GREEN;
			else if (e.getActionCommand().equals("Custom"))
				//JColor Chooser
				color = JColorChooser.showDialog(null, "Color Picker", color.red);

			if (e.getActionCommand().equals("Reset")) {
				clearDraw();
			}
			if (e.getActionCommand().equals("open")) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File file = fc.getSelectedFile();

				Scanner in;

				//alright this might be sorta difficult
				try {					
					int numCol = 0;
					int numRow = 0;
					in = new Scanner(file);			
					
					//get the first line for the column and width side					
					String firstLine = in.nextLine();					
					StringTokenizer t = new StringTokenizer(firstLine);
					String firstToken = t.nextToken();
					if(firstToken == "p3" || firstToken.contains("#")){
						//move on to the next line
						numRow = in.nextInt();
						numCol = in.nextInt();
					}
					else{
						//otherwise its the next token
						numRow = Integer.parseInt(firstToken);
						numCol = Integer.parseInt(t.nextToken());
					}
					//cool now that you have the rows and columns
					Color[][] gridColor = new Color[numRow][numCol];
					
					
					for (int a = 0; a < numRow; a++) {
						for (int b = 0; b < numCol; b++) {
							Color tempColor = new Color(in.nextInt(), in.nextInt(), in.nextInt());
							gridColor[a][b] = tempColor;
						}
					}
					
					//allright now draw the thing paste code
					Graphics g = drawingPanel.getGraphics();
					
					//figure out how big the thing is
					//so in total drawing panel is 400 by 400
					//that means each block is 400/numRows 400/numColumns
								
					int blockWidth = 400/numRow;
					int blockHeight = 400/numCol;
					
					for (int a = 0; a < numRow ; a++) {
						for (int b = 0; b < numCol; b++) {
							Color tempColor = gridColor[a][b];
							g.setColor(tempColor);
							g.fillRect((a * blockWidth), (b * blockHeight),blockWidth,blockHeight);							
						}
					}
					
					g.setColor(Color.lightGray);
					for (int x = 0; x < 400; x += numRow)
						g.drawLine(x, 0, x, 400);

					for (int y = 0; y < 400; y += numCol)
						g.drawLine(0, y, 400, y);
					
					/*
					//so while theres more stuff to red
					while(in.hasNextLine()){
						//get the current line
						String currentLine = in.nextLine();
						
						//put it in stringTokenizer
						StringTokenizer tokenizer = new StringTokenizer(currentLine);
						
						for (int a = 0; a < (tokenizer.countTokens())/3 ; a++) {
							int red = Integer.parseInt(tokenizer.nextToken());
							int green = Integer.parseInt(tokenizer.nextToken());
							int blue = Integer.parseInt(tokenizer.nextToken());
							Color tempColor = new Color(red, green, blue);
							
							gridColor
							
						}
						
						
					}*/						

				
				} catch (Exception e1) {
					e1.printStackTrace();				
				}

			}						
			}
			if(e.getActionCommand().equals("save")){
				try {
					//allright for this 
					//lets just assume there are 20 rows and 20 columns
					JFileChooser chooser = new JFileChooser();
					// chooser.showSaveDialog(null);
					//File file = chooser.getSelectedFile();
					
					File file = new File("output.ppm");
					
					FileWriter writer = new FileWriter(file);
					writer = new FileWriter(file);
					
					//ok this is not too hard
					//i have a grid[][] that stores my colors
					//write the number of rows and columsn
					String escape = "\r\n";
					
					//this is the first line
					writer.write("20 20" + escape);
					
					for (int a = 0; a < 20; a++) {
						for (int b = 0; b < 20; b++) {
							Color tempColor = grid[a][b];
							writer.write(tempColor.getRed() + " " +
							tempColor.getGreen() + " " + tempColor.getBlue() +
							"   ");
							
						}
						writer.write(escape);
					}
					writer.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(e.getActionCommand().equals("clear")){
				clearDraw();
			}	

		}		


	public void clearDraw() {
		drawingPanel.repaint();
		drawingPanel.paintComponent(drawingPanel.getGraphics());
	}

	private class MyDrawingPanel extends JPanel {

		// Not required, but gets rid of the serialVersionUID warning. Google
		// it, if desired.
		static final long serialVersionUID = 1234567890L;

		public void paintComponent(Graphics g) {

			g.setColor(Color.white);
			g.fillRect(2, 2, this.getWidth() - 2, this.getHeight() - 2);

			g.setColor(Color.lightGray);
			for (int x = 0; x < this.getWidth(); x += 20)
				g.drawLine(x, 0, x, this.getHeight());

			for (int y = 0; y < this.getHeight(); y += 20)
				g.drawLine(0, y, this.getWidth(), y);
			
			//paint component should run the update grids
			
			for (int a = 0; a < grid.length; a++) {
				for (int b = 0; b < grid.length; b++) {
					Color tempColor = grid[a][b];
					g.setColor(tempColor);
					g.fillRect((a * 20), (b * 20), 20, 20);							
				}
			}
			
			g.setColor(Color.lightGray);
			for (int x = 0; x < this.getWidth(); x += 20)
				g.drawLine(x, 0, x, this.getHeight());

			for (int y = 0; y < this.getHeight(); y += 20)
				g.drawLine(0, y, this.getWidth(), y);
		}
	}

	//use this method to draw little squares or erase squares
	
	public void drawSquare(boolean state, int x, int y){
		Graphics g = drawingPanel.getGraphics();
		if(state == true){		
			g.setColor(color);
			g.fillRect((x/20 * 20), (y/20 * 20), 20, 20);
			//draw a gray rectangle
			g.setColor(Color.lightGray);
			g.drawRect(x/20 * 20, y/20 * 20, 20, 20);
		}
		//erase stuff
		else if(state == false){		
			g.setColor(Color.white);
			g.fillRect((x/20 * 20), (y/20 * 20), 20, 20);
			
			//draw a gray rectangle
			g.setColor(Color.lightGray);
			g.drawRect(x/20 * 20, y/20 * 20, 20, 20);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {		
		int button = e.getModifiersEx();	
		System.out.println(button);
		if(button == 1024){
			drawSquare(true, e.getX(), e.getY());
			updateGrids(e.getX(), e.getY());
		}
		else if(button == 4096){
			drawSquare(false, e.getX(), e.getY());
			updateGrids(e.getX(), e.getY());
		}
	}	
	

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int button = e.getModifiersEx();
		if(button == 1024){
			drawSquare(true, e.getX(), e.getY());					
		}
		else if(button == 4096){
			drawSquare(false, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered");
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getModifiersEx();
		if(button == 1024){
			drawSquare(true, e.getX(), e.getY());					
		}
		else if(button == 4096){
			drawSquare(false, e.getX(), e.getY());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/* You have to save the position of the file grids
	 */
	
	public void initializeGrids(){
		for (int a = 0; a < 20 ; a++) {
			for (int b = 0; b < 20; b++) {
				Color white = new Color(255, 255, 255);
				grid[a][b]= white;				
			}
		}
		
	}
		
	//This methods sets the color of the grids
	public void updateGrids(int x, int y){
		//passed in x and y is the position of the pouse
		//the grid has 0 to 20
		//so say the passed in coordinates have
		int xPos = x/20;
		int yPos = y/20;
		grid[xPos][yPos] = color;
	}
	
}