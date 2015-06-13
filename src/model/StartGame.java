package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.ClientController;
import control.Controlmanager;
import control.ServerController;
import control.StateController;

public class StartGame extends AbstractModel{
	
	private int players;
	private Controlmanager control;

	public StartGame(Controlmanager control)
	{
		this.control = control;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Impact", Font.BOLD + Font.ITALIC, 100));
		g2.drawString("Create Game", 0, 100);
		g2.setFont(new Font("Impact", Font.BOLD, 20));
		g2.drawString("maximum players: " + 2, 0, 200);
		g2.drawString("Remco's Pong Game", 0, 220);
		g2.drawString("Waiting for players", 0, 240);
		g2.drawString(players + " players connected", 0, 260);
		for(int i = 1; i <= players; i++)
		{
			if(control.getServer().getInfo(i-1) != null)
			{
				g2.drawString("Player " + i + " " + control.getServer().getInfo(i-1).getIPadres() + " " + control.getServer().getInfo(i-1).getHostName(), 0, 260 + 20*i);
			}
		}
		if(players == 2)
		{
			g2.drawString("Press Enter to start the game", 0, 400);
		}
	}

	@Override
	public void update() {
		if(control.getServer() != null)
		{
			players = control.getServer().getPlayers();
		}
	}
	
	public void startServer()
	{
		this.control.getServer().startServer(2);
		this.control.setServer(true);
		this.control.setClient(new ClientController());
	}

	@Override
	public void init(int x, int y, boolean server) {
		startServer();
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
			control.getState().switchState(0, false);
			break;
		case KeyEvent.VK_ENTER:
			if(players == 2)
			{
				control.getServer().startGame();
				control.getState().switchState(1, true);
			}
			break;
		}
		
	}

}
