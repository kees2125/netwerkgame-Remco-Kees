package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
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
	private boolean isrunning = false;

	public ClientThread(Socket socket, ServerController server)
	{
		this.socket = socket;
		this.server = server;
		try {
			this.socket.setTcpNoDelay(true);
			in = new DataInputStream(this.socket.getInputStream());
			out = new DataOutputStream(this.socket.getOutputStream());
			this.server.addPlayer(this.socket.getInetAddress().getHostAddress(), this.socket.getInetAddress().getHostName());
			out.writeInt(this.server.getPlayers());
			this.timer = new Timer(1000/60, this);
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
		int x = 0;
		int y = 0;
			if(server.gameIsStarted())
			{
				try {
					out.writeBoolean(true);
					isrunning = true;
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
		
		
		try {
			out.writeInt(server.getPlayers());
			out.writeDouble(server.getBall().getX());
			out.writeDouble(server.getBall().getY());
			out.writeDouble(server.getInfo(0).getPosition().getX());
			out.writeDouble(server.getInfo(0).getPosition().getY());
			out.writeInt(server.getInfo(0).getScore());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(server.getInfo(1) != null)
		{
			try {
				 x = in.readInt();
				 y  = in.readInt();
				 System.out.println(x +":"+ y);
				 if(x != 0)
				server.getInfo(1).setPosition(new Point2D.Float(x, y));
				out.writeInt(server.getInfo(1).getScore());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				out.writeInt(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
