package semsdn.devices;

/**
 * 实例类，将设备对应于对象，便于封装为JSON，或者便于访问，存储等
 * @author ssj
 *
 */
public class Host {
	
	String ip;
	String mac;
	String sId;
	int spid;
	long lastSeen;
	
	public Host() {
		super();
	}

	public Host(String ip, String mac, String sId, int spid, long lastSeen) {
		super();
		this.ip = ip;
		this.mac = mac;
		this.sId = sId;
		this.spid = spid;
		this.lastSeen = lastSeen;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public int getSpid() {
		return spid;
	}

	public void setSpid(int spid) {
		this.spid = spid;
	}

	public long getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(long lastSeen) {
		this.lastSeen = lastSeen;
	}

	@Override
	public String toString() {
		return "Host [ip=" + ip + ", mac=" + mac + ", sId=" + sId + ", spid=" + spid + ", lastSeen=" + lastSeen + "]";
	}
	
	
	
	
	
	
}
