package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import control.StateController;

public class Menu extends AbstractModel{
	
	private int menuItem, window;
	private JFrame frame;
	private JoinGame join;
	private StartGame start;
	private StateController controller;
	
	public Menu(JFrame frame, StateController controller)
	{
		this.controller = controller;
		this.menuItem = 1;
		this.window = 0;
		this.frame = frame;
		this.join = new JoinGame(controller);
		this.start = new StartGame(controller);
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		switch(window)
		{
		case 0:
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
			break;
		case 1:
			start.draw(g2);
			break;
		case 2:
			join.draw(g2);
			break;
		}
		
	}

	@Override
	public void update() {
		switch(window)
		{
		case 1:
			start.update();
			break;
		case 2:
			join.update();
			break;
		}
	}
	
	public void switchState(int window)
	{
		this.window = window;
	}

	@Override
	public void init(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(window)
		{
		case 0:
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
		case 1:
			start.keyPressed(e);
			break;
		case 2:
			join.keyPressed(e);
			break;
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(window)
		{
		case 0:
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_ENTER:
				switch(menuItem)
				{
				case 1:
					start.init(0, 0);
					window = 1;
					break;
				case 2:
					join.init(0, 0);
					window = 2;
					break;
				case 3:
					break;
				case 4:
					frame.dispose();
					break;
				}
				break;
			}
			break;
		case 1:
			start.keyReleased(e);
			break;
		case 2:
			join.keyReleased(e);
			break;
		}
	}
}
