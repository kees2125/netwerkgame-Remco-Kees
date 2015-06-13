package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.ClientController;
import control.StateController;

public class JoinGame extends AbstractModel{
	
	private StateController controller;
	private ClientController client;
	private String serverAdres;
	private boolean connected;

	public JoinGame(StateController controller)
	{
		this.controller = controller;
		this.client = null;
		this.serverAdres = null;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Impact", Font.BOLD + Font.ITALIC, 100));
		g2.drawString("Join Game", 0, 100);
		g2.setFont(new Font("Impact", Font.BOLD, 20));
		if(connected)
		{
			g2.drawString("Joined the server as player " + client.getPlayer() + ", waiting for the game to start.", 0, 120);
			g2.drawString("IPadres: " + serverAdres, 0, 140);
		}
	}

	@Override
	public void update() {
		if(client.getInetadres() != null)
		{
			this.serverAdres = client.getInetadres().getHostAddress();
		}
		if(client.isStarted())
		{
			controller.switchState(1);
		}
		
	}

	@Override
	public void init(int x, int y) {
		this.client = new ClientController();
		this.connected = true;
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
		}
	}
}
