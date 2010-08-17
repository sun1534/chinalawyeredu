/**
 * 
 */
package main.readerrors;

import java.sql.Timestamp;

/**
 * @author 华锋
 * Nov 4, 2009-10:03:12 PM
 *
 */
public class HWChrLog {

	private long time;
	private String msisdn;
	private String imsi;
	private String imei;
	private String mobiletype;
	private String flowid;
	private String trigreason;
	private String lazytime;
	private String outreason;
	private String innerreason;
	private String apn;
	private String allowapn;
	private String cellid;
	private String sgsnid;
	private String filesgsn;
	private String ggsn;
	private String oldcellid;
	private String oldsgsnid;
	private String kuang;
	private String cao;
	private int thehour;
	private String thedate;
	/**
	 * @return the thehour
	 */
	public int getThehour() {
		return thehour;
	}
	/**
	 * @param thehour the thehour to set
	 */
	public void setThehour(int thehour) {
		this.thehour = thehour;
	}
	/**
	 * @return the thedate
	 */
	public String getThedate() {
		return thedate;
	}
	/**
	 * @param thedate the thedate to set
	 */
	public void setThedate(String thedate) {
		this.thedate = thedate;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}
	/**
	 * @param msisdn the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the mobiletype
	 */
	public String getMobiletype() {
		return mobiletype;
	}
	/**
	 * @param mobiletype the mobiletype to set
	 */
	public void setMobiletype(String mobiletype) {
		this.mobiletype = mobiletype;
	}
	/**
	 * @return the flowid
	 */
	public String getFlowid() {
		return flowid;
	}
	/**
	 * @param flowid the flowid to set
	 */
	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}
	/**
	 * @return the trigreason
	 */
	public String getTrigreason() {
		return trigreason;
	}
	/**
	 * @param trigreason the trigreason to set
	 */
	public void setTrigreason(String trigreason) {
		this.trigreason = trigreason;
	}
	/**
	 * @return the lazytime
	 */
	public String getLazytime() {
		return lazytime;
	}
	/**
	 * @param lazytime the lazytime to set
	 */
	public void setLazytime(String lazytime) {
		this.lazytime = lazytime;
	}
	/**
	 * @return the outreason
	 */
	public String getOutreason() {
		return outreason;
	}
	/**
	 * @param outreason the outreason to set
	 */
	public void setOutreason(String outreason) {
		this.outreason = outreason;
	}
	/**
	 * @return the innerreason
	 */
	public String getInnerreason() {
		return innerreason;
	}
	/**
	 * @param innerreason the innerreason to set
	 */
	public void setInnerreason(String innerreason) {
		this.innerreason = innerreason;
	}
	/**
	 * @return the apn
	 */
	public String getApn() {
		return apn;
	}
	/**
	 * @param apn the apn to set
	 */
	public void setApn(String apn) {
		this.apn = apn;
	}
	/**
	 * @return the allowapn
	 */
	public String getAllowapn() {
		return allowapn;
	}
	/**
	 * @param allowapn the allowapn to set
	 */
	public void setAllowapn(String allowapn) {
		this.allowapn = allowapn;
	}
	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}
	

	
	/**
	 * @param cellid the cellid to set
	 */
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	/**
	 * @return the sgsn
	 */
	public String getSgsnid() {
		return sgsnid;
	}
	/**
	 * @param sgsn the sgsn to set
	 */
	public void setSgsnid(String sgsn) {
		this.sgsnid = sgsn;
	}
	/**
	 * @return the ggsn
	 */
	public String getGgsn() {
		return ggsn;
	}
	/**
	 * @param ggsn the ggsn to set
	 */
	public void setGgsn(String ggsn) {
		this.ggsn = ggsn;
	}
	/**
	 * @return the oldcellid
	 */
	public String getOldcellid() {
		return oldcellid;
	}
	/**
	 * @param oldcellid the oldcellid to set
	 */
	public void setOldcellid(String oldcellid) {
		this.oldcellid = oldcellid;
	}
	/**
	 * @return the oldsgsn
	 */
	public String getOldsgsnid() {
		return oldsgsnid;
	}
	/**
	 * @param oldsgsn the oldsgsn to set
	 */
	public void setOldsgsnid(String oldsgsn) {
		this.oldsgsnid = oldsgsn;
	}
	/**
	 * @return the kuang
	 */
	public String getKuang() {
		return kuang;
	}
	/**
	 * @param kuang the kuang to set
	 */
	public void setKuang(String kuang) {
		this.kuang = kuang;
	}
	/**
	 * @return the cao
	 */
	public String getCao() {
		return cao;
	}
	/**
	 * @param cao the cao to set
	 */
	public void setCao(String cao) {
		this.cao = cao;
	}
	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}
	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	/**
	 * @return the filesgsn
	 */
	public String getFilesgsn() {
		return filesgsn;
	}
	/**
	 * @param filesgsn the filesgsn to set
	 */
	public void setFilesgsn(String filesgsn) {
		this.filesgsn = filesgsn;
	}
	
	
//	[ UTC 日期/时间  ] 2010-07-27 17:08:03
//	[ MSISDN         ] 8615215161033
//	[ IMSI           ] 460022151600264
//	[ IMEISV         ] 3585920104672887
//	[ 终端型号       ] 
//	[ 流程ID         ] MS_GPRS_DETACH
//	[ 触发原因       ] MS
//	[ 时延           ] 10
//	[ 外部原因       ] UNDEFINED
//	[ 内部原因       ] UNDEFINED
//	[ APN            ] 
//	[ 允许APN        ] 
//	[ 小区编码       ] 331D:01:4E68
//	[ 小区名称       ] 
//	[ SGSN           ] Test Sgsn 1
//	[ GGSN           ] 
//	[ 旧小区编码     ] 331D:01:4E68
//	[ 旧小区名称     ] 
//	[ 旧侧SGSN地址   ] 
//	[ 框号           ] 8
//	[ 槽号           ] 5
}
