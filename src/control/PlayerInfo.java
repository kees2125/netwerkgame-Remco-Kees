package control;

import java.awt.geom.Point2D;

public class PlayerInfo {
	
	private int number;
	private String IPadres;
	private String hostName;
	private Point2D position;
	private int score;

	public PlayerInfo(int number, String IPadres, String hostName)
	{
		this.setNumber(number);
		this.setIPadres(IPadres);
		this.setHostName(hostName);
		this.setPosition(new Point2D.Double(0,0));
		this.score = 0;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getIPadres() {
		return IPadres;
	}

	public void setIPadres(String iPadres) {
		IPadres = iPadres;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
