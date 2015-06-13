package control;

import java.awt.geom.Point2D;
import java.net.InetAddress;
import java.util.ArrayList;

public class ClientController {
	
	private Thread client;
	private boolean connected, startServer = false;
	private InetAddress Inetadres = null;
	private int player, totalPlayers = 0;
	private Point2D ball;
	private ArrayList<PlayerInfo> locations;
	
	public ClientController()
	{
		this.locations = new ArrayList<>();
		this.client = new Thread(new Client(this));
		client.start();
	}
	
	public void setConnected(boolean connected)
	{
		this.connected = connected;
	}
	
	public boolean isConnected()
	{
		return connected;
	}
	
	public void setRunning(boolean running)
	{
		this.startServer = running;
	}
	
	public boolean isStarted()
	{
		return startServer;
	}

	public InetAddress getInetadres() {
		return Inetadres;
	}

	public void setInetadres(InetAddress inetAddress) {
		Inetadres = inetAddress;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getTotalPlayers() {
		return totalPlayers;
	}

	public void setTotalPlayers(int totalPlayers) {
		this.totalPlayers = totalPlayers;
		for(int i = 0; i < totalPlayers; i++)
		{
			locations.add(new PlayerInfo(i+1,null,null));
		}
	}

	public Point2D getBall() {
		return ball;
	}

	public void setBall(Point2D ball) {
		this.ball = ball;
	}

	public PlayerInfo getPlayerInfo(int player) {
		return locations.get(player);
	}
	
	public void setScore(int score1, int score2)
	{
		locations.get(0).setScore(score1);
		if(locations.size() > 1)
		{
			locations.get(1).setScore(score2);
		}
	}
}
