package control;

public class ClientController {
	
	private Thread client;
	
	public ClientController()
	{
		this.client = new Thread(new Client(this));
		client.start();
	}

}
