package semsdn.devices;

/**
 * 实例类，将设备对应于对象，便于封装为JSON，或者便于访问，存储等
 * @author ssj
 *
 */
public class Link {

	String src_sid;
	int src_pid;
	String dst_sid;
	int dst_pid;
	String type;
	String direction;
	long latency;
	
	public Link() {
		super();
	}
	
	public Link(String src_sid, int src_pid, String dst_sid, int dst_pid, String type, String direction, long latency) {
		super();
		this.src_sid = src_sid;
		this.src_pid = src_pid;
		this.dst_sid = dst_sid;
		this.dst_pid = dst_pid;
		this.type = type;
		this.direction = direction;
		this.latency = latency;
	}
	public String getSrc_sid() {
		return src_sid;
	}
	public void setSrc_sid(String src_sid) {
		this.src_sid = src_sid;
	}
	public int getSrc_pid() {
		return src_pid;
	}
	public void setSrc_pid(int src_pid) {
		this.src_pid = src_pid;
	}
	public String getDst_sid() {
		return dst_sid;
	}
	public void setDst_sid(String dst_sid) {
		this.dst_sid = dst_sid;
	}
	public int getDst_pid() {
		return dst_pid;
	}
	public void setDst_pid(int dst_pid) {
		this.dst_pid = dst_pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public long getLatency() {
		return latency;
	}
	public void setLatency(long latency) {
		this.latency = latency;
	}
	@Override
	public String toString() {
		return "Link [src_sid=" + src_sid + ", src_pid=" + src_pid + ", dst_sid=" + dst_sid + ", dst_pid=" + dst_pid
				+ ", type=" + type + ", direction=" + direction + ", latency=" + latency + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
