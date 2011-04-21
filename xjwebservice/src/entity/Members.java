package entity;

import java.sql.Timestamp;

/**
 * Members entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Members implements java.io.Serializable {

	// Fields

	private int id;
	private String telNumber;
	private String service;
	private String jiashizheng;
	private String chepai;
	private int chepaiType=1;

	private Timestamp regTime;
	private Timestamp quitTime;
	private Timestamp returnTime;
	//1是ok0是暂停
	private int active=1;
	private int sentPos=0;
	private Timestamp lastSentTime;
	private int score=0;
	private String remark;
	private int dzFlag=0;
	private String banner;
	//违章次数
	private int wzCount=0;
	private Timestamp wzLastSentTime;
//	mtflag=1时表示无违章里不下发短信
	private int mtFlag=0;
	private int isfree=0;
	private int ismisc=0;
	private int x=0;
	private int mtFlag1=1;

	// Constructors

	/** default constructor */
	public Members() {
	}

	/** minimal constructor */
	public Members(int id, String telNumber, String service, Timestamp regTime, int active, int sentPos,
			int score, int dzFlag) {
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
	public Members(int id, String telNumber, String service, String jiashizheng, String chepai, int chepaiType,
			Timestamp regTime, Timestamp quitTime, Timestamp returnTime, int active, int sentPos, Timestamp lastSentTime,
			int score, String remark, int dzFlag, String banner, int wzCount, Timestamp wzLastSentTime,
			int mtFlag, int isfree, int ismisc, int x, int mtFlag1) {
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public int getChepaiType() {
		return this.chepaiType;
	}

	public void setChepaiType(int chepaiType) {
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

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getSentPos() {
		return this.sentPos;
	}

	public void setSentPos(int sentPos) {
		this.sentPos = sentPos;
	}

	public Timestamp getLastSentTime() {
		return this.lastSentTime;
	}

	public void setLastSentTime(Timestamp lastSentTime) {
		this.lastSentTime = lastSentTime;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDzFlag() {
		return this.dzFlag;
	}

	public void setDzFlag(int dzFlag) {
		this.dzFlag = dzFlag;
	}

	public String getBanner() {
		return this.banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public int getWzCount() {
		return this.wzCount;
	}

	public void setWzCount(int wzCount) {
		this.wzCount = wzCount;
	}

	public Timestamp getWzLastSentTime() {
		return this.wzLastSentTime;
	}

	public void setWzLastSentTime(Timestamp wzLastSentTime) {
		this.wzLastSentTime = wzLastSentTime;
	}

	public int getMtFlag() {
		return this.mtFlag;
	}

	public void setMtFlag(int mtFlag) {
		this.mtFlag = mtFlag;
	}

	public int getIsfree() {
		return this.isfree;
	}

	public void setIsfree(int isfree) {
		this.isfree = isfree;
	}

	public int getIsmisc() {
		return this.ismisc;
	}

	public void setIsmisc(int ismisc) {
		this.ismisc = ismisc;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getMtFlag1() {
		return this.mtFlag1;
	}

	public void setMtFlag1(int mtFlag1) {
		this.mtFlag1 = mtFlag1;
	}

}