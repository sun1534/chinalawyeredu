package entity;

import java.sql.Timestamp;

/**
 * DzjcAllHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DzjcAllHistory implements java.io.Serializable {

	// Fields

	private int id;
	private String hphm;
	private short hpzl;
	private Timestamp dzjcsj;
	private String dzjcdd;
	private String wzxx;
	private String dq;
	private Timestamp firstTime;
	private int isHandled;
	private int handleDays;
	private Timestamp handleTime;
	

	// Constructors

	/** default constructor */
	public DzjcAllHistory() {
	}

	/** minimal constructor */
	public DzjcAllHistory(int id) {
		this.id = id;
	}

	/** full constructor */
	public DzjcAllHistory(int id, String hphm, short hpzl, Timestamp dzjcsj, String dzjcdd, String wzxx, String dq,
			int isHandled, int handleDays, Timestamp handleTime) {
		this.id = id;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.dzjcsj = dzjcsj;
		this.dzjcdd = dzjcdd;
		this.wzxx = wzxx;
		this.dq = dq;
		this.isHandled = isHandled;
		this.handleDays = handleDays;
		this.handleTime = handleTime;
	}

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHphm() {
		return this.hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public short getHpzl() {
		return this.hpzl;
	}

	public void setHpzl(short hpzl) {
		this.hpzl = hpzl;
	}

	public Timestamp getDzjcsj() {
		return this.dzjcsj;
	}

	public void setDzjcsj(Timestamp dzjcsj) {
		this.dzjcsj = dzjcsj;
	}

	public String getDzjcdd() {
		return this.dzjcdd;
	}

	public void setDzjcdd(String dzjcdd) {
		this.dzjcdd = dzjcdd;
	}

	public String getWzxx() {
		return this.wzxx;
	}

	public void setWzxx(String wzxx) {
		this.wzxx = wzxx;
	}

	public String getDq() {
		return this.dq;
	}

	public void setDq(String dq) {
		this.dq = dq;
	}

	public int getIsHandled() {
		return this.isHandled;
	}

	public void setIsHandled(int isHandled) {
		this.isHandled = isHandled;
	}

	public int getHandleDays() {
		return this.handleDays;
	}

	public void setHandleDays(int handleDays) {
		this.handleDays = handleDays;
	}

	public Timestamp getHandleTime() {
		return this.handleTime;
	}

	public void setHandleTime(Timestamp handleTime) {
		this.handleTime = handleTime;
	}

	/**
	 * @return the firstTime
	 */
	public Timestamp getFirstTime() {
		return firstTime;
	}

	/**
	 * @param firstTime the firstTime to set
	 */
	public void setFirstTime(Timestamp firstTime) {
		this.firstTime = firstTime;
	}

}