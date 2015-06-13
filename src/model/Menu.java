package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import control.Controlmanager;

public class Menu extends AbstractModel{
	
	private int menuItem;
	private JFrame frame;
	private Controlmanager control;
	
	public Menu(JFrame frame, Controlmanager control)
	{
		this.control = control;
		this.menuItem = 1;
		this.frame = frame;
	}

	@Override
	public void draw(Graphics2D g2) {
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Impact", Font.BOLD+Font.ITALIC, 96));
			g2.drawString("Pong Extreme", 0, 90);
			if(menuItem == 1)
			{
				g2.setColor(Color.GRAY);
			}
			else
			{
				g2.setColor(Color.WHITE);
			}
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
	}

	@Override
	public void init(int x, int y, boolean server) {
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
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_ENTER:
				switch(menuItem)
				{
				case 1:
					control.getState().switchState(2, false);
					break;
				case 2:
					control.getState().switchState(3, false);
					break;
				case 3:
					break;
				case 4:
					frame.dispose();
					System.exit(0);
					break;
				}
				break;
			}
	}
}
