package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Menu extends AbstractModel{

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawString("Menu", 10, 10);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
