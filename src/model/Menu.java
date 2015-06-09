package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Menu extends AbstractModel{
	
	private int menuItem;
	private JFrame frame;
	
	public Menu(JFrame frame)
	{
		this.menuItem = 1;
		this.frame = frame;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Arial", Font.BOLD, 50));
		g2.drawString("Pong Extreme", 10, 50);
		if(menuItem == 1)
		{
			g2.setColor(Color.GRAY);
		}
		else
		{
			g2.setColor(Color.WHITE);
		}
		g2.setFont(new Font("Impact", Font.BOLD, 96));
		g2.drawString("Create Game", frame.getWidth()/2-300, 250);
		if(menuItem == 2)
		{
			g2.setColor(Color.GRAY);
		}
		else
		{
			g2.setColor(Color.WHITE);
		}
		g2.drawString("Join Game", frame.getWidth()/2-250, 350);
		if(menuItem == 3)
		{
			g2.setColor(Color.GRAY);
		}
		else
		{
			g2.setColor(Color.WHITE);
		}
		g2.drawString("HighScores", frame.getWidth()/2-280, 450);
		if(menuItem == 4)
		{
			g2.setColor(Color.GRAY);
		}
		else
		{
			g2.setColor(Color.WHITE);
		}
		g2.drawString("Exit", frame.getWidth()/2-100, 550);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			if(menuItem > 1)
			{
				menuItem--;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(menuItem < 4)
			{
				menuItem++;
			}
			break;
		case KeyEvent.VK_ENTER:
		switch(menuItem)
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			frame.dispose();
			break;
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
