package control;

public class PlayerInfo {
	
	private int number;
	private String IPadres;
	private String hostName;

	public PlayerInfo(int number, String IPadres, String hostName)
	{
		this.setNumber(number);
		this.setIPadres(IPadres);
		this.setHostName(hostName);
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

}
