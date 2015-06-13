package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
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
		this.timer = new Timer(1000/60, this);
			timer.start();
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean running = false;
		try {
			running = in.readBoolean();
		} catch (IOException arg1) {
			arg1.printStackTrace();
			running = false;
		}
		clientController.setRunning(running);
		try {
			int players = in.readInt();
			clientController.setTotalPlayers(players);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		double x = 0;
		try {
			x = in.readDouble();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		double y = 0;
		try {
			y = in.readDouble();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		clientController.setBall(new Point2D.Double(x,y));
		double x1 = 0;
		double y1 = 0;
		try {
			x1 = in.readDouble();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			y1 = in.readDouble();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int score1 = 0;
		int score2 = 0;
		try {
			score1 = in.readInt();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			score2 = in.readInt();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		clientController.setScore(score1, score2);
		clientController.getPlayerInfo(0).setPosition(new Point2D.Double(x1,y1));
	}
}
