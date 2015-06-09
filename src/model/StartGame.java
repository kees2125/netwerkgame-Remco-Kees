package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.StateController;

public class StartGame extends AbstractModel{
	
	private StateController controller;

	public StartGame(StateController controller)
	{
		this.controller = controller;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Impact", Font.BOLD + Font.ITALIC, 100));
		g2.drawString("Create Game", 0, 100);
		g2.setFont(new Font("Impact", Font.BOLD, 20));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_ESCAPE:
			controller.switchState(0);
			break;
		case KeyEvent.VK_ENTER:
			controller.switchState(1);
			break;
		}
	}

}
