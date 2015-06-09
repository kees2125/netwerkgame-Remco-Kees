package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.time.temporal.ValueRange;

import control.GameManager;

public class Game extends AbstractModel{
	private GameManager gm;
	private int speed = 5;
	private int playerNmr;
	boolean right,left;
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawString("Game", 10, 10);
		gm.draw(g2);
		
	}

	@Override
	public void update() {
		gm.Update();
		if(left)
			gm.getPlayer(playerNmr).move(-speed);
		if(right)
			gm.getPlayer(playerNmr).move(speed);
	}

	@Override
	
	public void init(int thisPlayer,int totalPlayers) {
		playerNmr = thisPlayer;
		gm = new GameManager(totalPlayers);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			right = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		left = false;
		right = false;
	}

}
