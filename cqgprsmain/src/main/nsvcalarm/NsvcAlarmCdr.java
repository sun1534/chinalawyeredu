/**
 * 
 */
package main.nsvcalarm;

/**
 * @author 华锋
 * Nov 4, 2009-10:03:12 PM
 *
 */
public class NsvcAlarmCdr {
//	clearAlarm;	pcmE1T1AlarmIndicationSignal;	communications;	major;	2009-12-27 14:08:26;	Interface 3.2.2.1 PCM port 8 has detected alarm indication signal AIS.;	{2121522833};	autoclear;

	private String pcm;
	private String communication;
	private String qos;
	private int alarmdate;
	private String reason;
	private String timestamp;
	private String gbindex;
	private String sgsnid;
	/**
	 * @return the pcm
	 */
	public String getPcm() {
		return pcm;
	}
	/**
	 * @param pcm the pcm to set
	 */
	public void setPcm(String pcm) {
		this.pcm = pcm;
	}
	/**
	 * @return the communication
	 */
	public String getCommunication() {
		return communication;
	}
	/**
	 * @param communication the communication to set
	 */
	public void setCommunication(String communication) {
		this.communication = communication;
	}
	/**
	 * @return the date
	 */
	public int getAlarmdate() {
		return alarmdate;
	}
	/**
	 * @param date the date to set
	 */
	public void setAlarmdate(int date) {
		this.alarmdate = date;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the gbindex
	 */
	public String getGbindex() {
		return gbindex;
	}
	/**
	 * @param gbindex the gbindex to set
	 */
	public void setGbindex(String gbindex) {
		this.gbindex = gbindex;
	}
	/**
	 * @return the qos
	 */
	public String getQos() {
		return qos;
	}
	/**
	 * @param qos the qos to set
	 */
	public void setQos(String qos) {
		this.qos = qos;
	}
	/**
	 * @return the sgsnid
	 */
	public String getSgsnid() {
		return sgsnid;
	}
	/**
	 * @param sgsnid the sgsnid to set
	 */
	public void setSgsnid(String sgsnid) {
		this.sgsnid = sgsnid;
	}
	
	
}
