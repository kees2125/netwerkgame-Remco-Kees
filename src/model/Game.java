package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.GameManager;

public class Game extends AbstractModel{
	private GameManager gm;
	private int speed = 5;
	private int playerNmr;
	boolean down,up;
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawString("Game", 10, 10);
		gm.draw(g2);
		
	}

	@Override
	public void update() {
		gm.Update();
		if(up)
			gm.getPlayer(playerNmr).move(-speed);
		if(down)
			gm.getPlayer(playerNmr).move(speed);
	}

	@Override
	
	public void init(int thisPlayer,int totalPlayers) {
		playerNmr = thisPlayer;
		gm = new GameManager(totalPlayers);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		up = false;
		down = false;
	}

}
