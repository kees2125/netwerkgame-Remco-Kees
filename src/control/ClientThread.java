package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread implements Runnable{

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ServerController server;

	public ClientThread(Socket socket, ServerController server)
	{
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		try {
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.addPlayer(socket.getInetAddress().getHostAddress(), socket.getInetAddress().getHostName());
	}

}
