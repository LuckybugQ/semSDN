package diameter_server.httputils;

public class Host {

	String ipv4;
	String mac;
	String swid;
	int    pid;
	long lastSeen;
	
	
	
	public Host(String ipv4, String mac, String swid, int pid, long lastSeen) {
		super();
		this.ipv4 = ipv4;
		this.mac = mac;
		this.swid = swid;
		this.pid = pid;
		this.lastSeen = lastSeen;
	}



	public String getIpv4() {
		return ipv4;
	}



	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}



	public String getMac() {
		return mac;
	}



	public void setMac(String mac) {
		this.mac = mac;
	}



	public String getSwid() {
		return swid;
	}



	public void setSwid(String swid) {
		this.swid = swid;
	}



	public int getPid() {
		return pid;
	}



	public void setPid(int pid) {
		this.pid = pid;
	}



	public long getLastSeen() {
		return lastSeen;
	}



	public void setLastSeen(long lastSeen) {
		this.lastSeen = lastSeen;
	}



	@Override
	public String toString() {
		return "Host [ipv4=" + ipv4 + ", mac=" + mac + ", swid=" + swid + ", pid=" + pid + ", lastSeen=" + lastSeen
				+ "]";
	}
	
	
	
	
}
