package control;

import java.net.InetAddress;

public class ClientController {
	
	private Thread client;
	private boolean connected = false;
	private InetAddress Inetadres = null;
	
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

	public InetAddress getInetadres() {
		return Inetadres;
	}

	public void setInetadres(InetAddress inetAddress) {
		Inetadres = inetAddress;
	}

}
