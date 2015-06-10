package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	
	public Window()
	{
		JFrame frame = new JFrame("pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new GamePanel(frame);
		panel.setBackground(Color.black);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}

