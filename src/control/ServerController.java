package control;

import java.util.ArrayList;

public class ServerController {
	
	private Thread server;
	private int players = 0;
	private ArrayList<PlayerInfo> playerinfo;

	public ServerController(int maxPlayers)
	{
		this.playerinfo = new ArrayList<>();
		this.server = new Thread(new Server(this, maxPlayers));
		server.start();
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
		return playerinfo.get(number);
	}
	
	public void addPlayer(String IPadres, String host)
	{
		playerinfo.add(new PlayerInfo(players,IPadres, host));
	}
}
