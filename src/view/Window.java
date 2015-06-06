package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.AbstractModel;
import model.Game;
import model.Menu;

public class Window {
	
	public Window()
	{
		JFrame frame = new JFrame("pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new GamePanel();
		panel.setBackground(Color.black);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}

class GamePanel extends JPanel implements ActionListener 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    //private ArrayList<Shape> players = new ArrayList<Shape>();
	private ArrayList<AbstractModel> models = new ArrayList<>();
	private Timer timer;
	private int abstractModel;
	
	/* Constructor */
	public GamePanel()
	{
		setPreferredSize(new Dimension(600,600));
		//this.players = players;
		models.add(new Menu());
		models.add(new Game());
		this.timer = new Timer(1000/120, this);
		timer.start();
		this.abstractModel = 1; //set model
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		models.get(abstractModel).draw(g2);
//		g2.setColor(Color.white);
//		for(Shape S: players)
//		{
//			g2.fill(S);
//		}
		
		// Draw body 1 en body 2
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		models.get(abstractModel).update();
	}
	
	
}