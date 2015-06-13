package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.ClientController;
import control.Controlmanager;
import control.StateController;

public class JoinGame extends AbstractModel{
	
	private String serverAdres;
	private boolean connected;
	private Controlmanager control;

	public JoinGame(Controlmanager control)
	{
		this.control = control;
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
			g2.drawString("Joined the server as player " + control.getClient().getPlayer() + ", waiting for the game to start.", 0, 120);
			g2.drawString("IPadres: " + serverAdres, 0, 140);
		}
	}

	@Override
	public void update() {
		if(control.getClient().getInetadres() != null)
		{
			this.serverAdres = control.getClient().getInetadres().getHostAddress();
		}
		if(control.getClient().isStarted())
		{
			control.getState().switchState(1, false);
		}
		
	}

	@Override
	public void init(int x, int y, boolean server) {
		control.setClient(new ClientController());
		control.setClient(true);
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
			control.getState().switchState(0, false);
			break;
		}
	}
}
