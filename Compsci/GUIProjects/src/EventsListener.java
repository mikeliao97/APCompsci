import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
public class EventsListener implements ActionListener, ChangeListener, MouseListener {
	JFrame 
	
	public static void main(String[] args){
		
		JButton button = new JButton();
		JButton button2 = new JButton();
		button.addActionListener(this);
		button2.adActionListener(this);
		
		slider.addChangeListener(this);
	}

	/*Necessary for Action Listener*/
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			System.out.println("Don't push me around!");
		}
		else if (e.getSource() == button2){
			System.out.prntln("Please push me around");
		}
		else if(e.getActionCommand().equals("Don't Push me !")){ //if its equal to a string
			
		}
		
	}
	public void ChangeListener(ChangeEvent e){
		if(e.getSource() == mySlider){
			System.out.println(slider.getValue());
			
		}
	}
	//private inner class
	private class MyJPanel extends JPanel {
		//private inner classes can get slider, which is an attribute
	  g.drawLine(0,0, slider.getValue() * 10)	
	}	

}
*/