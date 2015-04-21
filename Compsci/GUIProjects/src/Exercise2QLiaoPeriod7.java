// import libraries
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise2QLiaoPeriod7 {

	public static void main(String[] args) {

		// Create a basic Java window frame
		JFrame window = new JFrame("My Window Title");

		// Decide what to do when the user closes the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Make this a gridlayout
		window.getContentPane().setLayout(new GridLayout(2, 4));

		int screenWidth = (int) window.getGraphicsConfiguration().getBounds()
				.getWidth();
		int screenHeight = (int) window.getGraphicsConfiguration().getBounds()
				.getHeight();
		window.setBounds(screenWidth / 2 - 400, screenHeight / 2 - 300, 800,
				600);

		// Prevent users from resizing the window
		window.setResizable(false);

		// Create GUI components.
		// For us, create a custom JPanel to draw on.
		// MySketchPad panel = new MySketchPad();

		// Add GUI components to the JFrame (window)
		// window.add(panel);

		MyButton1 myButton1 = new MyButton1("");
		MyButton2 myButton2 = new MyButton2("");
		MyButton3 myButton3 = new MyButton3("");
		MyButton4 myButton4 = new MyButton4("");
		MyButton5 myButton5 = new MyButton5("");
		MyButton6 myButton6 = new MyButton6("");
		MyButton7 myButton7 = new MyButton7("");
		MyButton8 myButton8 = new MyButton8("");

		window.add(myButton1);
		window.add(myButton2);
		window.add(myButton3);
		window.add(myButton4);
		window.add(myButton5);
		window.add(myButton6);
		window.add(myButton7);
		window.add(myButton8);

		// Make the window visible
		window.setVisible(true);
	}
}

/*
 * A JPanel is like a SketchPad in the sense that you can draw on it. It's more
 * powerful though, because it has more capabilities than a SketchPad, such as
 * the ability to add buttons and GUI elements. And you can add JPanels to
 * JPanels.
 */
class MySketchPad extends JPanel {

	public void paintComponent(Graphics g) {

		// Set the background color to white (do this yourself)

		// Ask our parent to paint itself
		super.paintComponent(g);

		// Next, cast the Graphics parameter back into what
		// it really is - a more powerful Graphics2D object.
		// Or, if you want, you can leave it as a Graphics
		// and only use Graphics class methods.
		Graphics2D g2 = (Graphics2D) g;

		// Finally, draw stuff
		g2.drawString("This is a rectangle", 10, 50);
		g2.draw3DRect(10, 50, 300, 100, true);
	}
}

class MyButton1 extends JButton {
	public MyButton1(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(a)", 90, 50);

		// draw the circle
		g.drawOval(90, 100, 25, 25);
		g.drawRect(80, 125, 50, 25);

	}
}

class MyButton2 extends JButton {
	public MyButton2(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(b)", 90, 50);

		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();

		g.fillRoundRect(screenWidth / 2 - 50, screenHeight / 2 - 50, 100, 100,
				50, 50);
		g.setColor(Color.white);
		g.fillOval((screenWidth / 2 - 50), screenHeight / 2, 50, 50);
		g.fillOval(screenWidth / 2, screenHeight / 2, 50, 50);
		g.fillOval(screenWidth / 2 - 50, screenHeight / 2 - 50, 50, 50);
		g.fillOval(screenWidth / 2, (screenHeight / 2 - 50), 50, 50);

	}
}

class MyButton3 extends JButton {
	public MyButton3(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(c)", 90, 50);

		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();
		screenWidth = screenWidth / 2;
		screenHeight = screenHeight / 2;
		g.drawString("wet", screenWidth, screenHeight);

		g.setColor(Color.black);

		int xpoints[] = { (screenWidth - 40), screenWidth, screenWidth + 40,
				screenWidth };
		int ypoints[] = { (screenHeight), screenHeight - 40, screenHeight,
				screenHeight + 40 };
		g.fillPolygon(xpoints, ypoints, 4);

	}
}

class MyButton4 extends JButton {
	public MyButton4(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(d)", 90, 50);

		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();
		screenWidth = screenWidth / 2;
		screenHeight = screenHeight / 2;

		g.drawOval(screenWidth - 80, screenHeight, 50, 50);
		g.drawOval(screenWidth - 70, screenHeight + 10, 30, 30);

		g.drawOval(screenWidth + 40, screenHeight, 50, 50);
		g.drawOval(screenWidth + 50, screenHeight + 10, 30, 30);

		g.drawLine(screenWidth - 50, screenHeight, screenWidth + 65,
				screenHeight);
		g.drawLine(screenWidth - 50, screenHeight + 50, screenWidth + 65,
				screenHeight + 50);

	}
}

class MyButton5 extends JButton {
	public MyButton5(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(e)", 90, 50);

		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();
		screenWidth = screenWidth / 2;
		screenHeight = screenHeight / 2;

		// draw the circle
		g.drawOval(screenWidth, screenHeight, 30, 30);
		g.drawOval(screenWidth, screenHeight - 20, 30, 30);
		g.drawOval(screenWidth, screenHeight + 20, 30, 30);

		// left and right circle
		g.drawOval(screenWidth - 20, screenHeight - 10, 30, 30);
		g.drawOval(screenWidth + 20, screenHeight - 10, 30, 30);
		g.drawOval(screenWidth - 20, screenHeight + 10, 30, 30);
		g.drawOval(screenWidth + 20, screenHeight + 10, 30, 30);

		g.drawString("Sunshine", screenWidth - 20, screenHeight + 70);
	}
}

class MyButton6 extends JButton {
	public MyButton6(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(f)", 90, 50);

		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();
		screenWidth = screenWidth / 2 - 30;
		screenHeight = screenHeight / 2 + 30;

		// Bigger White Circle
		g.setColor(Color.white);
		g.fillOval(screenWidth - 55, screenHeight - 95, 130, 130);

		// white circle
		g.setColor(Color.black);
		g.fillOval(screenWidth - 40, screenHeight - 80, 100, 100);

		// set the font for the J
		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		g.setColor(Color.white);
		g.drawString("J", screenWidth, screenHeight);

	}
}

class MyButton7 extends JButton {
	public MyButton7(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(g)", 90, 50);

		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();
		screenWidth = screenWidth / 2 - 30;
		screenHeight = screenHeight / 2 + 30;

		g.drawArc(screenWidth - 25, screenHeight - 100, 100, 100, 90, 270);

		g.drawLine(screenWidth + 25, screenHeight - 100, screenWidth + 25,
				screenHeight - 50);
		g.drawLine(screenWidth + 25, screenHeight - 50, screenWidth + 75,
				screenHeight - 50);

	}
}

class MyButton8 extends JButton {
	public MyButton8(String string) {
		super(string);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawString("(h)", 90, 50);

		int screenWidth = (int) g.getClipBounds().getWidth();
		int screenHeight = (int) g.getClipBounds().getHeight();
		screenWidth = screenWidth / 2 - 80;
		screenHeight = screenHeight / 2 - 70;

		g.drawOval(screenWidth, screenHeight, 150, 150);
		// draw the eyes
		g.drawOval(screenWidth + 30, screenHeight + 45, 15, 15);
		g.drawOval(screenWidth + 100, screenHeight + 45, 15, 15);

		g.drawArc(screenWidth + 30, screenHeight + 80, 90, 40, 190, 160);

	}
}
