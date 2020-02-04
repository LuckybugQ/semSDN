package semsdn.devices;

/**
 * 实例类，将设备对应于对象，便于封装为JSON，或者便于访问，存储等
 * @author ssj
 *
 */
public class PortPerformance {

	private String time;  
	private String switchId; 
	private String portId; 
	double  bitsPerSecondRx; 
	double  bitsPerSecondTx; 
	double  rxBytes; 
	double  txBytes;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSwitchId() {
		return switchId;
	}
	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}
	public String getPortId() {
		return portId;
	}
	public double getBitsPerSecondRx() {
		return bitsPerSecondRx;
	}
	public void setBitsPerSecondRx(double bitsPerSecondRx) {
		this.bitsPerSecondRx = bitsPerSecondRx;
	}
	public double getBitsPerSecondTx() {
		return bitsPerSecondTx;
	}
	public void setBitsPerSecondTx(double bitsPerSecondTx) {
		this.bitsPerSecondTx = bitsPerSecondTx;
	}
	public double getRxBytes() {
		return rxBytes;
	}
	public void setRxBytes(double rxBytes) {
		this.rxBytes = rxBytes;
	}
	public double getTxBytes() {
		return txBytes;
	}
	public void setTxBytes(double txBytes) {
		this.txBytes = txBytes;
	}
	@Override
	public String toString() {
		return "PortPerformance [time=" + time + ", switchId=" + switchId + ", portId=" + portId + ", bitsPerSecondRx="
				+ bitsPerSecondRx + ", bitsPerSecondTx=" + bitsPerSecondTx + ", rxBytes=" + rxBytes + ", txBytes="
				+ txBytes + "]";
	}
	
	
	
	
	
	
}
