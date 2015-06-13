package control;

import view.GamePanel;

public class Controlmanager {
	
	private ClientController client;
	private ServerController server;
	private StateController state;
	private boolean isServer;
	private boolean isClient;
	
	public Controlmanager(GamePanel game)
	{
		this.client = null;
		this.setServer(new ServerController());
		this.setState(new StateController(game));
	}

	public StateController getState() {
		return state;
	}

	public void setState(StateController state) {
		this.state = state;
	}

	public ServerController getServer() {
		return server;
	}

	public void setServer(ServerController server) {
		this.server = server;
	}

	public ClientController getClient() {
		return client;
	}

	public void setClient(ClientController client) {
		this.client = client;
	}

	public boolean isServer() {
		return isServer;
	}

	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}

	public boolean isClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
}
