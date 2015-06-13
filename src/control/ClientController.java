package control;

import java.net.InetAddress;

public class ClientController {
	
	private Thread client;
	private boolean connected, startServer = false;
	private InetAddress Inetadres = null;
	private int player;
	
	public ClientController()
	{
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
	
	public void startServer()
	{
		this.startServer = true;
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

}
