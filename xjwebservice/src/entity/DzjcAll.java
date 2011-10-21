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
	private String wzxw;
	private String diqu;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the hphm
	 */
	public String getHphm() {
		return hphm;
	}
	/**
	 * @param hphm the hphm to set
	 */
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	/**
	 * @return the hpzl
	 */
	public short getHpzl() {
		return hpzl;
	}
	/**
	 * @param hpzl the hpzl to set
	 */
	public void setHpzl(short hpzl) {
		this.hpzl = hpzl;
	}
	/**
	 * @return the dzjcsj
	 */
	public Timestamp getDzjcsj() {
		return dzjcsj;
	}
	/**
	 * @param dzjcsj the dzjcsj to set
	 */
	public void setDzjcsj(Timestamp dzjcsj) {
		this.dzjcsj = dzjcsj;
	}
	/**
	 * @return the dzjcdd
	 */
	public String getDzjcdd() {
		return dzjcdd;
	}
	/**
	 * @param dzjcdd the dzjcdd to set
	 */
	public void setDzjcdd(String dzjcdd) {
		this.dzjcdd = dzjcdd;
	}
	/**
	 * @return the wzxw
	 */
	public String getWzxw() {
		return wzxw;
	}
	/**
	 * @param wzxw the wzxw to set
	 */
	public void setWzxw(String wzxw) {
		this.wzxw = wzxw;
	}
	/**
	 * @return the diqu
	 */
	public String getDiqu() {
		return diqu;
	}
	/**
	 * @param diqu the diqu to set
	 */
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}

	
}