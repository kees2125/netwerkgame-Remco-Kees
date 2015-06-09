package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.time.temporal.ValueRange;

import control.GameManager;

public class Game extends AbstractModel{
	private GameManager gm;
	private int playerNmr;
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawString("Game", 10, 10);
		gm.draw(g2);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		gm.Update();
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
			gm.getPlayer(playerNmr).move(-5);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			gm.getPlayer(playerNmr).move(5);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
