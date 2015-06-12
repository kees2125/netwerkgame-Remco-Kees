package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

	private Socket socket;
	private ClientController clientController;
	private DataInputStream in;
	private DataOutputStream out;
	
	public Client(ClientController clientController)
	{
		this.clientController = clientController;
	}

	@Override
	public void run() {
		try {
			this.socket = new Socket("localhost", 8000);
			clientController.setInetadres(socket.getInetAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("connected to server");
		try {
			this.in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
