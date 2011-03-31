package entity;

import java.sql.Timestamp;

/**
 * Members entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Members implements java.io.Serializable {

	// Fields

	private Integer id;
	private String telNumber;
	private String service;
	private String jiashizheng;
	private String chepai;
	private Integer chepaiType=1;

	private Timestamp regTime;
	private Timestamp quitTime;
	private Timestamp returnTime;
	//1是ok0是暂停
	private Integer active=1;
	private Integer sentPos=0;
	private Timestamp lastSentTime;
	private Integer score=0;
	private String remark;
	private Integer dzFlag=0;
	private String banner;
	//违章次数
	private Integer wzCount=0;
	private Timestamp wzLastSentTime;
//	mtflag=1时表示无违章里不下发短信
	private Integer mtFlag=0;
	private Integer isfree=0;
	private Integer ismisc=0;
	private Integer x=0;
	private Integer mtFlag1=1;

	// Constructors

	/** default constructor */
	public Members() {
	}

	/** minimal constructor */
	public Members(Integer id, String telNumber, String service, Timestamp regTime, Integer active, Integer sentPos,
			Integer score, Integer dzFlag) {
		this.id = id;
		this.telNumber = telNumber;
		this.service = service;
		this.regTime = regTime;
		this.active = active;
		this.sentPos = sentPos;
		this.score = score;
		this.dzFlag = dzFlag;
	}

	/** full constructor */
	public Members(Integer id, String telNumber, String service, String jiashizheng, String chepai, Integer chepaiType,
			Timestamp regTime, Timestamp quitTime, Timestamp returnTime, Integer active, Integer sentPos, Timestamp lastSentTime,
			Integer score, String remark, Integer dzFlag, String banner, Integer wzCount, Timestamp wzLastSentTime,
			Integer mtFlag, Integer isfree, Integer ismisc, Integer x, Integer mtFlag1) {
		this.id = id;
		this.telNumber = telNumber;
		this.service = service;
		this.jiashizheng = jiashizheng;
		this.chepai = chepai;
		this.chepaiType = chepaiType;
		this.regTime = regTime;
		this.quitTime = quitTime;
		this.returnTime = returnTime;
		this.active = active;
		this.sentPos = sentPos;
		this.lastSentTime = lastSentTime;
		this.score = score;
		this.remark = remark;
		this.dzFlag = dzFlag;
		this.banner = banner;
		this.wzCount = wzCount;
		this.wzLastSentTime = wzLastSentTime;
		this.mtFlag = mtFlag;
		this.isfree = isfree;
		this.ismisc = ismisc;
		this.x = x;
		this.mtFlag1 = mtFlag1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelNumber() {
		return this.telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getJiashizheng() {
		return this.jiashizheng;
	}

	public void setJiashizheng(String jiashizheng) {
		this.jiashizheng = jiashizheng;
	}

	public String getChepai() {
		return this.chepai;
	}

	public void setChepai(String chepai) {
		this.chepai = chepai;
	}

	public Integer getChepaiType() {
		return this.chepaiType;
	}

	public void setChepaiType(Integer chepaiType) {
		this.chepaiType = chepaiType;
	}

	public Timestamp getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public Timestamp getQuitTime() {
		return this.quitTime;
	}

	public void setQuitTime(Timestamp quitTime) {
		this.quitTime = quitTime;
	}

	public Timestamp getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}

	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getSentPos() {
		return this.sentPos;
	}

	public void setSentPos(Integer sentPos) {
		this.sentPos = sentPos;
	}

	public Timestamp getLastSentTime() {
		return this.lastSentTime;
	}

	public void setLastSentTime(Timestamp lastSentTime) {
		this.lastSentTime = lastSentTime;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDzFlag() {
		return this.dzFlag;
	}

	public void setDzFlag(Integer dzFlag) {
		this.dzFlag = dzFlag;
	}

	public String getBanner() {
		return this.banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Integer getWzCount() {
		return this.wzCount;
	}

	public void setWzCount(Integer wzCount) {
		this.wzCount = wzCount;
	}

	public Timestamp getWzLastSentTime() {
		return this.wzLastSentTime;
	}

	public void setWzLastSentTime(Timestamp wzLastSentTime) {
		this.wzLastSentTime = wzLastSentTime;
	}

	public Integer getMtFlag() {
		return this.mtFlag;
	}

	public void setMtFlag(Integer mtFlag) {
		this.mtFlag = mtFlag;
	}

	public Integer getIsfree() {
		return this.isfree;
	}

	public void setIsfree(Integer isfree) {
		this.isfree = isfree;
	}

	public Integer getIsmisc() {
		return this.ismisc;
	}

	public void setIsmisc(Integer ismisc) {
		this.ismisc = ismisc;
	}

	public Integer getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getMtFlag1() {
		return this.mtFlag1;
	}

	public void setMtFlag1(Integer mtFlag1) {
		this.mtFlag1 = mtFlag1;
	}

}