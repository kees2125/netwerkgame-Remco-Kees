package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.ClientController;
import control.ServerController;
import control.StateController;

public class StartGame extends AbstractModel{
	
	private ServerController server;
	private StateController controller;
	private ClientController client;
	private int players;
	private int maxPlayers = -1;
	private boolean waiting = false;

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
		g2.drawString("maximum players: " + maxPlayers, 0, 200);
		if(waiting)
		{
			g2.drawString("Remco's Pong Game", 0, 220);
			g2.drawString("Waiting for players", 0, 240);
			g2.drawString(players + " players connected", 0, 260);
			for(int i = 1; i <= players; i++)
			{
				if(server.getInfo(i-1) != null)
				{
					g2.drawString("Player " + i + " " + server.getInfo(i-1).getIPadres() + " " + server.getInfo(i-1).getHostName(), 0, 260 + 20*i);
				}
			}
			if(players == maxPlayers)
			{
				g2.drawString("Press Enter to start the game", 0, 400);
			}
		}
		else
		{
			g2.drawString("Press Enter to create a game", 0, 220);
		}
	}

	@Override
	public void update() {
		if(server != null)
		{
			players = server.getPlayers();
		}
	}
	
	public void startServer()
	{
		this.server = new ServerController(maxPlayers);
		this.client = new ClientController();
	}

	@Override
	public void init(int x, int y) {
		
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
			if(players == maxPlayers && waiting)
			{
				controller.switchState(1);
			}
			else if(maxPlayers > 1 && !waiting)
			{
				startServer();
				waiting = true;
			}
			break;
		case KeyEvent.VK_UP:
			if(maxPlayers < 0 && !waiting)
			{
				maxPlayers = 1;
			}
			else if(maxPlayers < 4 && !waiting)
			{
				maxPlayers ++;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(maxPlayers > 1 && !waiting)
			{
				maxPlayers --;
			}
		}
		
	}

}
