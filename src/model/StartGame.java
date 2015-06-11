package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import control.ServerController;
import control.StateController;

public class StartGame extends AbstractModel{
	
	private ServerController server;
	private StateController controller;
	private int players;

	public StartGame(StateController controller)
	{
		this.controller = controller;
		this.server = null;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Impact", Font.BOLD + Font.ITALIC, 100));
		g2.drawString("Create Game", 0, 100);
		g2.setFont(new Font("Impact", Font.BOLD, 20));
		g2.drawString(players + " players", 0, 200);
		g2.drawString("Remco's Pong Game", 0, 220);
		g2.drawString("Press Enter to continue", 0, 240);
	}

	@Override
	public void update() {
		players = server.getPlayers();
		
	}

	@Override
	public void init(int x, int y) {
		this.server = new ServerController();
		
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
