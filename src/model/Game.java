package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import control.ClientController;
import control.Controlmanager;
import control.GameManager;
import control.ServerController;

public class Game extends AbstractModel{
	private Controlmanager control;
	private GameManager gm;
	private int speed = 5;
	private int playerNmr;
	boolean down,up;
	
	public Game(Controlmanager control)
	{
		this.control = control;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawString("Game", 10, 10);
		gm.draw(g2);
		
	}

	@Override
	public void update() {
		gm.update();
		if(up)
			gm.getPlayer(playerNmr).move(-speed);
		if(down)
			gm.getPlayer(playerNmr).move(speed);
	}

	@Override
	
	public void init(int thisPlayer,int totalPlayers, boolean server) {
		if(server)
		playerNmr = 1;
		else
		playerNmr =2;
		gm = new GameManager(control);
		
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
	
	public void setServer(ServerController server)
	{
		this.control.setServer(server);
	}
	
	public void setClient(ClientController client)
	{
		this.control.setClient(client);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		up = false;
		down = false;
	}

}
