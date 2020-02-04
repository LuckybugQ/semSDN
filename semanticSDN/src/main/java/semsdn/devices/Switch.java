package semsdn.devices;

/**
 * 实例类，将设备对应于对象，便于封装为JSON，或者便于访问，存储等
 * @author ssj
 *
 */
public class Switch {

	String switchId;
	String ip;
	String openFlowVersion;
	long connectedSince;
	
	public Switch(String switchId, String ip, String openFlowVersion, long connectedSince) {
		super();
		this.switchId = switchId;
		this.ip = ip;
		this.openFlowVersion = openFlowVersion;
		this.connectedSince = connectedSince;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOpenFlowVersion() {
		return openFlowVersion;
	}

	public void setOpenFlowVersion(String openFlowVersion) {
		this.openFlowVersion = openFlowVersion;
	}

	public long getConnectedSince() {
		return connectedSince;
	}

	public void setConnectedSince(long connectedSince) {
		this.connectedSince = connectedSince;
	}

	@Override
	public String toString() {
		return "Switch [switchId=" + switchId + ", ip=" + ip + ", openFlowVersion=" + openFlowVersion
				+ ", connectedSince=" + connectedSince + "]";
	}
	
	
	
	
	
	
}
