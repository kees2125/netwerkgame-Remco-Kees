package control;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ServerController {
	
	private Thread server;
	private int players = 0;
	private ArrayList<PlayerInfo> playerinfo;
	private boolean gameStarted = false;
	private Point2D ball;

	public ServerController()
	{
		this.playerinfo = new ArrayList<>();
		this.setBall(new Point2D.Double());
	}
	
	public void startServer(int maxPlayers)
	{
		this.server = new Thread(new Server(this, maxPlayers));
		server.start();
	}
	
	public void startGame()
	{
		this.gameStarted = true;
	}
	
	public boolean gameIsStarted()
	{
		return gameStarted;
	}
	
	public void playerJoined()
	{
		players++;
	}
	
	public int getPlayers()
	{
		return players;
	}
	
	public PlayerInfo getInfo(int number)
	{
		if(playerinfo.size() > number)
			return playerinfo.get(number);
		else
			return null;
	}
	
	public void addPlayer(String IPadres, String host)
	{
		playerinfo.add(new PlayerInfo(players, IPadres, host));
	}

	public Point2D getBall() {
		return ball;
	}

	public void setBall(Point2D ball) {
		this.ball = ball;
	}
}
