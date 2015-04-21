/* Name; Qin Liao
 * Period; 7
 * Date: Monday February 20th
 * Reflection: This lab was a very good refersher for my skills.
 * I spent most of my break having fun and procrastinating, so I really
 * struggled with doing this lab because I forgot everything!
 * Nonetheless, I completed in a fashion that was satisfactory. The
 * part that I struggled the most with was actually placing the first
 * white block on the left panel of size 150, 200. I tried using
 * set preferred size(new dimesnsion(150, 200)) However that did not work.
 * The I tried to set Max size to no avail either. Then i tried to use
 * a wrapper Jpanel of size (150,200). Then putting the white panel
 * into the "wrapper" jpanel(This would theoreticcally work because 
 * one element in the border layout will fill up the whole "wrapper" Jpanel)
 * Finally, out of a whim, I tried using setPreferredSize, setMaximumSize
 * and set MinimumSize at the same time-- this worked! I was very happy
 * and very lucky. Another thing I struggled with was the last panel in which
 * I had to draw the elemnts. I was very confused on whether I had to override
 * the paintComponent and how graphcis worked. Eventually I read up on it,
 * watched a youtube video, and realized you can use getGraphics to draw with it.
 * 
 * 
 */



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;

public class ClasworkGUI implements MouseListener{
	JSlider radiusSlider;
	JFrame window;
	JPanel rightPanel;
	
	void createGUI() {

		// Create a basic Java window frame
		window = new JFrame("My Window Title");

		// Decide what to do when the user closes the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//window gridlayout
		window.setLayout(new GridLayout(1,3));

		// Get the screen dimensions
		int screenWidth = (int) (window.getToolkit().getScreenSize().getWidth());
		int screenHeight = (int) (window.getToolkit().getScreenSize().getHeight());
		// Our window size
		int frameWidth = 800;
		int frameHeight = 600;
		// Centers the JFrame, regardless of screen resolution
		window.setBounds(screenWidth / 2 - frameWidth / 2, screenHeight / 2	- frameHeight / 2, frameWidth, frameHeight);
		
		// Decide whether to allow users to resize the window
		window.setResizable(true);
		
		// make the jframes
//		JPanel firstWrapper = new JPanel();
//		firstWrapper.setMaximumSize(new Dimension(200, 150));
//		firstWrapper.add(topPanel);
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		Color lightred = new Color(219, 88, 88);
		leftPanel.setBackground(lightred);
		leftPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		
		// WORK ON THE LEFT PANLE		
		//top frame make sure to put some oborders on that
		JPanel topPanel = new JPanel();	
		topPanel.setPreferredSize(new Dimension(200, 150));
		topPanel.setMinimumSize(new Dimension(200, 150));
		topPanel.setMaximumSize(new Dimension(200, 150));
			
		//topPanel.setBorder()
		topPanel.setBackground(Color.WHITE);
		Color gray = new Color(190, 190, 190);
		topPanel.setBorder(BorderFactory.createLineBorder(gray, 5));
		leftPanel.add(topPanel);
		
		//three buttons
		JPanel ButtonFrame = new JPanel();
		ButtonFrame.setBackground(null);
		ButtonFrame.setLayout(new BoxLayout(ButtonFrame, BoxLayout.X_AXIS));
		JButton Bob = new JButton("Bob");
		JButton Jon = new JButton("Jon");
		JButton Save = new JButton("Save");
		ButtonFrame.add(Bob);
		ButtonFrame.add(Jon);
		ButtonFrame.add(Save);
		leftPanel.add(ButtonFrame);
		
		//add the slider
		radiusSlider = new JSlider(JSlider.VERTICAL, 0, 100, 25);
		radiusSlider.setPaintLabels(true);
		radiusSlider.setMajorTickSpacing(10);
		radiusSlider.setMinorTickSpacing(2);
		radiusSlider.setPaintTicks(true);
		radiusSlider.setSnapToTicks(true);
		leftPanel.add(radiusSlider);
		
		//MOVE ON THE THE MIDDLE PANEL
		JPanel middlePanel = new JPanel();
		Color lightGreen = new Color(229, 255, 204);
		middlePanel.setBackground(lightGreen);
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
		
		JPanel panelInMiddle = new JPanel();
		JPanel wrapper = new JPanel();
		wrapper.setMaximumSize(new Dimension(200, 300));
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));		
		wrapper.setBackground(Color.white);
		
		
		panelInMiddle.setPreferredSize(new Dimension(200, 300));
		panelInMiddle.setBackground(Color.yellow);
		panelInMiddle.setBorder(BorderFactory.createTitledBorder("BlackBoard"));
		
		wrapper.add(Box.createHorizontalGlue());
		wrapper.add(panelInMiddle);
		wrapper.add(Box.createHorizontalGlue());
		
		middlePanel.add(Box.createVerticalGlue());
		middlePanel.add(wrapper);
		middlePanel.add(Box.createVerticalGlue());
		
		
		//MOVE ON TO THE RIGH PANEL
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		Color lightblue = new Color(204, 229, 255);
		rightPanel.setBackground(lightblue);
		rightPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		rightPanel.addMouseListener(this);
		
		
		window.add(leftPanel);
		window.add(middlePanel);
		window.add(rightPanel);		
		window.setVisible(true);


	}

	@Override
	public void mouseClicked(MouseEvent e) {
			int button = e.getModifiersEx();
			if(button == 1024){
				drawSquare(e.getX(), e.getY());		
				System.out.println("wet");
				
			}
			else if(button == 4096){
				drawCircle(e.getX(), e.getY());
				System.out.println("wet");
			}
	}

	public void mousePressed(MouseEvent e) {
		int button = e.getModifiersEx();
		if(button == 1024){
			drawSquare(e.getX(), e.getY());		
			System.out.println("wet");
			
		}
		else if(button == 4096){
			drawCircle(e.getX(), e.getY());
			System.out.println("wet");
		}
	}
	
	private void drawSquare(int x, int y) {
		Graphics g = rightPanel.getGraphics();
			g.setColor(Color.white);
			g.fillRect(x, y, radiusSlider.getValue(), radiusSlider.getValue());
			
    }
	
	private void drawCircle(int x, int y) {
		Graphics g = rightPanel.getGraphics();
		g.setColor(Color.white);
		g.fillOval(x, y, radiusSlider.getValue(), radiusSlider.getValue());
		
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}


	public void mouseReleased(MouseEvent arg0) {
	}
}
