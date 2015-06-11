package control;

public class ServerController {
	
	private Thread server;
	private int players = 0;

	public ServerController()
	{
		this.server = new Thread(new Server(this));
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
}
