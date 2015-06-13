package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.Timer;

public class Client implements Runnable, ActionListener{

	private Socket socket;
	private ClientController clientController;
	private DataInputStream in;
	private DataOutputStream out;
	private Timer timer;
	
	public Client(ClientController clientController)
	{
		this.clientController = clientController;
	}

	@Override
	public void run() {
		try {
			this.socket = new Socket("localhost", 8000);	
			clientController.setInetadres(socket.getInetAddress());
			System.out.println("connected to server");
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			int number = in.readInt();
			System.out.println(number);
			clientController.setPlayer(number);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clientController.setInetadres(socket.getInetAddress());
		this.timer = new Timer(1000/120, this);
			timer.start();
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(in.readBoolean());
			{
				clientController.startServer();
			}
		} catch (IOException arg1) {
			arg1.printStackTrace();
		}
	}
	}
