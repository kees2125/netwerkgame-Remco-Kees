package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.Timer;

public class ClientThread implements Runnable, ActionListener{

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ServerController server;
	private Timer timer;

	public ClientThread(Socket socket, ServerController server)
	{
		this.socket = socket;
		this.server = server;
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			server.addPlayer(socket.getInetAddress().getHostAddress(), socket.getInetAddress().getHostName());
			out.writeInt(server.getPlayers());
			this.timer = new Timer(1000/120, this);
			timer.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(server.isStarted())
		{
			try {
				out.writeBoolean(true);
				System.out.println("started");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				out.writeBoolean(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
