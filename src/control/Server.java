package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	
	private ServerSocket serversocket;
	private Socket clientsocket;
	private ServerController serverController;
	private DataInputStream in;
	private DataOutputStream out;
	
	public Server(ServerController serverController) {
		this.serverController = serverController;
	}

	public void run()
	{
		try {
			this.serversocket = new ServerSocket(8000);
			System.out.println("started server");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.clientsocket = serversocket.accept();
			System.out.println("player joined");
			serverController.playerJoined();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.in = new DataInputStream(clientsocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.out = new DataOutputStream(clientsocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.writeDouble(1.555);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(in.readDouble());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
