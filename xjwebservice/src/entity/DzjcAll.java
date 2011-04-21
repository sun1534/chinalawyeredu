package entity;

import java.sql.Timestamp;

/**
 * DzjcAll entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DzjcAll implements java.io.Serializable {

	// Fields

	private int id;
	private String hphm;
	private short hpzl;
	private Timestamp dzjcsj;
	private String dzjcdd;
	private String wzxx;
	private String dq;

	// Constructors

	/** default constructor */
	public DzjcAll() {
	}

	/** minimal constructor */
	public DzjcAll(int id) {
		this.id = id;
	}

	/** full constructor */
	public DzjcAll(int id, String hphm, short hpzl, Timestamp dzjcsj, String dzjcdd, String wzxx, String dq) {
		this.id = id;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.dzjcsj = dzjcsj;
		this.dzjcdd = dzjcdd;
		this.wzxx = wzxx;
		this.dq = dq;
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

}