package semsdn.devices;

/**
 * 实例类，将设备对应于对象，便于封装为JSON，或者便于访问，存储等
 * @author ssj
 *
 */
public class LinkPerformance {

	double latency;
	double dropRate; 
	String time; 
	double bandwidthUtil;
	public double getLatency() {
		return latency;
	}
	public void setLatency(double latency) {
		this.latency = latency;
	}
	public double getDropRate() {
		return dropRate;
	}
	public void setDropRate(double dropRate) {
		this.dropRate = dropRate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getBandwidthUtil() {
		return bandwidthUtil;
	}
	public void setBandwidthUtil(double bandwidthUtil) {
		this.bandwidthUtil = bandwidthUtil;
	}
	@Override
	public String toString() {
		return "LinkPerformance [latency=" + latency + ", dropRate=" + dropRate + ", time=" + time + ", bandwidthUtil="
				+ bandwidthUtil + "]";
	}
	
	
	
	
}
