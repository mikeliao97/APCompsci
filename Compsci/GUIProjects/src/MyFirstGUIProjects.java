import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;


public class MyFirstGUIProjects  {
	public static void main(String[] args){
		JFrame window = new JFrame("My First GUI. Cool!");
//		window.setSize(800, 600);
		int screenWidth = (int) window.getGraphicsConfiguration().getBounds().getWidth();
		int screenHeight = (int) window.getGraphicsConfiguration().getBounds().getHeight();
		window.setBounds(screenWidth/2 - 400, screenHeight/2 - 300, 800, 600);
		window.setResizable(true);
		window.getContentPane().setLayout(null);
		
		MyButton7 button = new MyButton7("Push me!");
		button.setBackground(Color.GREEN);
//		button.setPreferredSize(new Dimension(200, 100));
		button.setBounds(0, 0, 100, 100);
		window.getContentPane().add(button, BorderLayout.PAGE_START);
		
		JButton button2 = new JButton("Don't Push Me!");
		button2.setBounds(0, 200, 100, 50);
		window.add(button2);
		

		MyPanel7 panel = new MyPanel7("");
		panel.setBackground(Color.yellow);
		panel.setBounds(100, 100, 100, 100);
		panel.setSize(new Dimension(100, 100));
		panel.setPreferredSize(new Dimension(100, 100));
		window.add(panel);
		
		
		//Sliders
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		window.add(slider);
		
		//Labels
		JLabel label = new JLabel("Hello. I'm a label");
		JLabel vlable = new JLabel("Alpha");
		
		//top jpanel 
		JPanel topPanel = new JPanel();
		topPanel.add(label);
		topPanel.add(slider);
		window.add(topPanel, BorderLayout.PAGE_START);
		
		//add the main panel
		window.add(panel, BorderLayout.CENTER);
		
		//add the bottom panel
		JPanel botPanel = new JPanel();
		botPanel.add(button);
		botPanel.add(button2);
		window.add(botPanel, BorderLayout.PAGE_END);
		
		//right jPanel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		//rightPanel.add(vlabel);
		//rightPanel.add(vslider);
		window.add(rightPanel, BorderLayout.LINE_END);
		
		//JCOLOR CHOOSER
		
//		window.add(new JColorChooser(), BorderLayout.LINE_END);
		
		//adding bordres
		TitledBorder border = new TitledBorder("Pen Size");
		slider.setBorder(border);
		
		
		window.setVisible(true);
		
	}
}

class MyPanel7 extends JPanel {
	public MyPanel7(String string) {
		super();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();
		System.out.println(screenWidth);
		System.out.println(screenHeight);

		g.setColor(Color.pink);
		g.fillOval(10, 10, 80, 80);
		g.drawString("Wet", 30, 30);
	}
}


