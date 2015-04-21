/* Name: Qin Liao

 * Date: Feb 16th 2-15
 * Time Spent: 3 hours 30 minutes.
 * ReflectioN: This lab was actually really difficult. I had to reserach
 * so much stuff. I am using a late pass to turn this lab in late. But
 * I had muhc trouble with this lab because of many things:
 * 1) repaint().
 * 2) the clearButton
 * 3) stroke in g2
 * 4) Listeners
 * 5) and many more problems that I ended up solving.
 * I was able to solve the problem of listeners and repaint by watching several
 * youtube videos. At first I thought it was necessary to use drawingArea.eComponet
 * to get g. And then draw with g. But soon I realized that one must override
 * the paint component otherwise, the changes will not be permanent. Eventually
 * I did this and my circle ended up working.Also, I used private classes
 * to extend the JPanel. At first I thought the private classes would
 * not be able to get the attributes, so I created getter methods in my
 * main class. I used the getter methods to get the radius, name, stroke,
 * etcetera and put it into my private class. However, this gave me a number
 * of problems. Eventually, I tried many different things, and by experimenting
 * I was able to find the right answer.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CircleDrawQLiaoPeriod7 implements ActionListener, ChangeListener {

	Color myColor = Color.red;
	int penSize;
	JButton drawButton;
	JButton clearButton;
	JRadioButton radioGreen;
	JRadioButton radioRed;
	JRadioButton radioYellow;
	JSlider radiusSlider;
	JLabel radiusLabel;
	JToggleButton lineThickness;
	MyJPanel drawingArea;
	JTextField field = new JTextField();
	MyTextArea fieldArea = new MyTextArea();
	int radius = 50;
	boolean isSelected = true;
	String name = "";
	 // Create a new Stroke style using a BasicStroke of width 3
	Stroke s = new BasicStroke(3);

	void createGUI() {
		// create my jframe window with three panels.
		JFrame window = new JFrame("My window");
		int screenWidth = (int) window.getGraphicsConfiguration().getBounds()
				.getWidth();
		int screenHeight = (int) window.getGraphicsConfiguration().getBounds()
				.getHeight();
		window.setBounds(screenWidth / 2 - 400, screenHeight / 2 - 300, 800,
				600);

		window.setLayout(new GridLayout(1, 3));

		// declare the three different panels
		JPanel panelLeft = new JPanel();
		JPanel panelMiddle = new JPanel();
		JPanel panelRight = new JPanel();

		// make is so that each of the different panels are box layout
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		panelMiddle.setLayout(new BoxLayout(panelMiddle, BoxLayout.Y_AXIS));
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

		// add the apnels to the window
		window.add(panelLeft);
		window.add(panelMiddle);
		window.add(panelRight);

		// add the thing that can change the pen's width
		// you can do the toggle button later
		lineThickness = new JToggleButton("Thin");

		// add lineThickness to mouse listener
		lineThickness.addActionListener(this);

		panelLeft.add(Box.createRigidArea(new Dimension(100, 100)));
		JPanel thickness = new JPanel();
		thickness.setBackground(Color.blue);
		thickness.setBorder(new TitledBorder("Thickness"));
		thickness.setMaximumSize(new Dimension(150, 100));
		thickness.add(lineThickness);
		lineThickness.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelLeft.add(thickness);
		panelLeft.add(Box.createRigidArea(new Dimension(30, 30)));

		// create my group of radio buttons;
		JPanel greenPanel = new JPanel();
		JPanel yellowPanel = new JPanel();
		JPanel redPanel = new JPanel();
		radioGreen = new JRadioButton();
		radioYellow = new JRadioButton();
		radioRed = new JRadioButton();
		JLabel green = new JLabel("green");
		JLabel red = new JLabel("red");
		JLabel yellow = new JLabel("yellow");
		greenPanel.add(radioGreen);
		greenPanel.add(green);
		yellowPanel.add(radioYellow);
		yellowPanel.add(yellow);
		redPanel.add(radioRed);
		redPanel.add(red);
		radioGreen.addActionListener(this);
		radioYellow.addActionListener(this);
		radioRed.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(radioGreen);
		group.add(radioYellow);
		group.add(radioRed);

		JPanel colorPanel = new JPanel();
		colorPanel.add(greenPanel);
		colorPanel.add(redPanel);
		colorPanel.add(yellowPanel);
		colorPanel.setBackground(Color.yellow);
		colorPanel.setBorder(new TitledBorder("Line Color"));
		panelLeft.add(colorPanel);
		panelLeft.add(Box.createVerticalGlue());

		// okauy for the final thing on the left panel I want to let the
		// user to set how big he wants the radius to be
		radiusSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
		radiusSlider.setPaintLabels(true);
		radiusSlider.setMajorTickSpacing(10);
		radiusSlider.setMinorTickSpacing(5);
		radiusSlider.setPaintTicks(true);
		radiusSlider.setSnapToTicks(true);
		radiusSlider.setBorder(new TitledBorder("Radius"));
		radiusSlider.addChangeListener(this);
		panelLeft.add(radiusSlider);

		// Now moving on to the left panel
		drawButton = new JButton("Draw");
		clearButton = new JButton("Clear");
		JPanel tempPanel2 = new JPanel();
		tempPanel2.setLayout(new FlowLayout());
		tempPanel2.add(drawButton);
		tempPanel2.add(clearButton);
		
		drawButton.addActionListener(this);
		clearButton.addActionListener(this);
		panelLeft.add(tempPanel2);

		// You have to create a place to draw the circle
		drawingArea = new MyJPanel();
		JPanel topFill = new JPanel();
		JPanel bottomFill = new JPanel();
		drawingArea.setPreferredSize(new Dimension(panelMiddle.getWidth(),
				panelMiddle.getWidth() + 100));
		drawingArea.setBackground(Color.white);
		panelMiddle.add(topFill);
		panelMiddle.add(drawingArea);
		panelMiddle.add(bottomFill);

		// okay finally moving on to the right apnel
		// this is the place to put the console window that displays 
		JPanel tempPanel = new JPanel();
		field = new JTextField("                       ");
		fieldArea = new MyTextArea();		
		fieldArea.setLineWrap(true);
		tempPanel.add(field);
		panelRight.add(Box.createRigidArea(new Dimension(100, 100)));
		panelRight.add(tempPanel);
		panelRight.add(fieldArea);
		panelRight.add(Box.createRigidArea(new Dimension(100, 100)));

		// Make the window visible
		window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == radioGreen) {
			myColor = Color.GREEN;
		} else if (e.getSource() == radioYellow) {
			myColor = Color.YELLOW;
		} else if (e.getSource() == radioRed) {
			myColor = Color.RED;
		} else if (e.getSource() == lineThickness) {			     	
			if (lineThickness.isSelected()) {
				lineThickness.setText("Thick");	
				s = new BasicStroke(12);				
			} else {
				lineThickness.setText("Thin");					
				s = new BasicStroke(3);
			}
		}
		else if(e.getSource() == drawButton){		
			drawingArea.repaint();			
			name = field.getText();
			fieldArea.repaint();
		}
		// The clear button does not work I do not know why
		else if(e.getSource() == clearButton){
			drawingArea.setBackground(Color.white);	
			System.out.println("Why isnt it white");
			
		}
		
	}	
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == radiusSlider) {
			System.out.println(radiusSlider.getValue());
			radius = radiusSlider.getValue();
			drawingArea.repaint();
		}	
	}
	
	// "inner class" for custom JPanel drawing
	//this is to draw the circle
	private class MyJPanel extends JPanel {
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(myColor);
			g2.setStroke(s);
			g2.drawOval(this.getHeight()/2 - radius/2, this.getWidth()/2 - radius/2 ,  radius, radius);
			System.out.println("My color: " + myColor);
		}
	}
	
	private class MyTextArea extends JTextArea {
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
			g2.drawString("Congratulations" + name , 10, 20);
			g2.drawString("You drew a circle radius " + radius, 10, 35); 
			g2.drawString("and Color of " + myColor + "!", 10, 50);

		}
	}

}
