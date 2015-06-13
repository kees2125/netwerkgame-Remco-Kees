package control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	
	private ServerSocket serversocket;
	private ServerController serverController;
	private int maxPlayers;
	
	public Server(ServerController serverController, int maxPlayers) {
		this.serverController = serverController;
		this.maxPlayers = maxPlayers;
	}

	public void run()
	{
		try {
			this.serversocket = new ServerSocket(8000);
			System.out.println("started server");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO fix probleem met een client die de verbinding verliest.
		while(true)
		{
			try {
				Socket socket = serversocket.accept();
				System.out.println("player joined");
				serverController.playerJoined();
				Thread thread = new Thread(new ClientThread(socket, serverController));
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
