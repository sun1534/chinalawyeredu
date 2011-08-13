package com.changpeng.lawcase.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.sxit.workflow.model.TwflAction;

/**
 * TlawLawcase entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawLawcase implements java.io.Serializable {

	// Fields

	private Long caseid;
	private Integer bankid;
	private String bank;
	private String bankbranch;
	private String bankadmin;
	private String theyear;
	private String thedate;
	private String requerytime;
	private String contractno;
	private long susongworkid=-1; //诉讼承办人
	private String susongworkname;
	private long zhixingworkid=-1; //执行承办人
	private String zhixingworkname;
	/**
	 * 状态,表示是否新增案件、存量案件、结案案件、异常案件等
	 */
	private int statusid=-1;
	private int isqisu=-1;
	/**
	 * 是否立案
	 */
	private int islian=-1;
	/**
	 * 是否缴费
	 */
	private int isjiaofei=-1;
	/**
	 * 是否有档案
	 */
	private int isdangan=-1;
	/**
	 * 是否进入登记表
	 */
	private int isdengji=-1;
	/**
	 * 不符合起诉条件/未立案/未缴费原因
	 */
	private String whynot;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 案件进度计划表
	 */
	private String planlogs;
	private Long createuserid=-1L;
	private String createusername;
	private Timestamp createtime;
	/**
	 * 每个节点处理完毕的时候更新这个modifytime
	 */
	private Timestamp modifytime=new java.sql.Timestamp(System.currentTimeMillis());
	/**
	 * 单个新增的还是批量上传导入的
	 * 0是单个新增
	 * 其他为对应的tlaw_files中的fileid
	 */
	private String createtype="-1";

	
    private TlawJiekuanren jiekuanren;
    private TlawStageDate stagedate;
    
    /**
     * 当前所处的节点
     */
    private int nodeid=-1;
    /**
     * 案件当前的处理人
     */
    private long  hotman=-1L;
    /**
     * 对应待办事务列表中的那个id
     */
    private int waitid=-1;
    /**
     * 案件进度备注列表
     */
    private Set<TlawPlanLogs> planlogslist = new HashSet<TlawPlanLogs>(0);

	// Constructors

	/** default constructor */
	public TlawLawcase() {
	}


	// Property accessors

	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	public Integer getBankid() {
		return this.bankid;
	}

	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankbranch() {
		return this.bankbranch;
	}

	public void setBankbranch(String bankbranch) {
		this.bankbranch = bankbranch;
	}

	public String getBankadmin() {
		return this.bankadmin;
	}

	public void setBankadmin(String bankadmin) {
		this.bankadmin = bankadmin;
	}

	public String getTheyear() {
		return this.theyear;
	}

	public void setTheyear(String theyear) {
		this.theyear = theyear;
	}

	public String getThedate() {
		return this.thedate;
	}

	public void setThedate(String thedate) {
		this.thedate = thedate;
	}

	public String getRequerytime() {
		return this.requerytime;
	}

	public void setRequerytime(String requerytime) {
		this.requerytime = requerytime;
	}

	public String getContractno() {
		return this.contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}



	/**
	 * @return the susongworkid
	 */
	public long getSusongworkid() {
		return susongworkid;
	}


	/**
	 * @param susongworkid the susongworkid to set
	 */
	public void setSusongworkid(long susongworkid) {
		this.susongworkid = susongworkid;
	}


	/**
	 * @return the susongworkname
	 */
	public String getSusongworkname() {
		return susongworkname;
	}


	/**
	 * @param susongworkname the susongworkname to set
	 */
	public void setSusongworkname(String susongworkname) {
		this.susongworkname = susongworkname;
	}


	/**
	 * @return the zhixingworkid
	 */
	public long getZhixingworkid() {
		return zhixingworkid;
	}


	/**
	 * @param zhixingworkid the zhixingworkid to set
	 */
	public void setZhixingworkid(long zhixingworkid) {
		this.zhixingworkid = zhixingworkid;
	}


	/**
	 * @return the zhixingworkname
	 */
	public String getZhixingworkname() {
		return zhixingworkname;
	}


	/**
	 * @param zhixingworkname the zhixingworkname to set
	 */
	public void setZhixingworkname(String zhixingworkname) {
		this.zhixingworkname = zhixingworkname;
	}


	public int getStatusid() {
		return this.statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getIslian() {
		return this.islian;
	}

	public void setIslian(int islian) {
		this.islian = islian;
	}

	public int getIsjiaofei() {
		return this.isjiaofei;
	}

	public void setIsjiaofei(int isjiaofei) {
		this.isjiaofei = isjiaofei;
	}

	public int getIsdangan() {
		return this.isdangan;
	}

	public void setIsdangan(int isdangan) {
		this.isdangan = isdangan;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPlanlogs() {
		return this.planlogs;
	}

	public void setPlanlogs(String planlogs) {
		this.planlogs = planlogs;
	}

	public Long getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Long createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateusername() {
		return this.createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getCreatetype() {
		return this.createtype;
	}

	public void setCreatetype(String createtype) {
		this.createtype = createtype;
	}


	/**
	 * @return the isdengji
	 */
	public int getIsdengji() {
		return isdengji;
	}


	/**
	 * @param isdengji the isdengji to set
	 */
	public void setIsdengji(int isdengji) {
		this.isdengji = isdengji;
	}


	/**
	 * @return the whynot
	 */
	public String getWhynot() {
		return whynot;
	}


	/**
	 * @param whynot the whynot to set
	 */
	public void setWhynot(String whynot) {
		this.whynot = whynot;
	}


	/**
	 * @return the jiekuanren
	 */
	public TlawJiekuanren getJiekuanren() {
		return jiekuanren;
	}


	/**
	 * @param jiekuanren the jiekuanren to set
	 */
	public void setJiekuanren(TlawJiekuanren jiekuanren) {
		this.jiekuanren = jiekuanren;
	}


	/**
	 * @return the isqisu
	 */
	public int getIsqisu() {
		return isqisu;
	}


	/**
	 * @param isqisu the isqisu to set
	 */
	public void setIsqisu(int isqisu) {
		this.isqisu = isqisu;
	}


	/**
	 * @return the nodeid
	 */
	public int getNodeid() {
		return nodeid;
	}


	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}


	/**
	 * @return the hotman
	 */
	public long getHotman() {
		return hotman;
	}


	/**
	 * @param hotman the hotman to set
	 */
	public void setHotman(long hotman) {
		this.hotman = hotman;
	}


	/**
	 * @return the waitid
	 */
	public int getWaitid() {
		return waitid;
	}


	/**
	 * @param waitid the waitid to set
	 */
	public void setWaitid(int waitid) {
		this.waitid = waitid;
	}


	/**
	 * @return the modifytime
	 */
	public Timestamp getModifytime() {
		return modifytime;
	}


	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}


	/**
	 * @return the stagedate
	 */
	public TlawStageDate getStagedate() {
		return stagedate;
	}


	/**
	 * @param stagedate the stagedate to set
	 */
	public void setStagedate(TlawStageDate stagedate) {
		this.stagedate = stagedate;
	}


	/**
	 * @return the planlogslist
	 */
	public Set<TlawPlanLogs> getPlanlogslist() {
		return planlogslist;
	}


	/**
	 * @param planlogslist the planlogslist to set
	 */
	public void setPlanlogslist(Set<TlawPlanLogs> planlogslist) {
		this.planlogslist = planlogslist;
	}

}