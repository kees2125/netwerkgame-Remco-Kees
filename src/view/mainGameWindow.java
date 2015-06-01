package view;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainGameWindow {
	
	public mainGameWindow(ArrayList<Shape> player)
	{
		JFrame frame = new JFrame("pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new Ex5Panel(player);
		panel.setBackground(Color.black);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}

class Ex5Panel extends JPanel 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ArrayList<Shape> players = new ArrayList<Shape>();
	
	
	/* Constructor */
	public Ex5Panel(ArrayList<Shape> players)
	{
		setPreferredSize( new Dimension(600,600));
		this.players = players;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		for(Shape S: players)
		{
			g2.fill(S);
		}
		
		// Draw body 1 en body 2
		
	}
	
	
}