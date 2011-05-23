package com.changpeng.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Diaochatype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Diaochatype implements java.io.Serializable {

	// Fields

	private Integer typeid;
	private Diaocha diaocha;
	private String typename;
	
	private Set<Diaochawenti> diaochawentis = new HashSet<Diaochawenti>(0);
	
	// Constructors

	/** default constructor */
	public Diaochatype() {
	}

	/** full constructor */
	public Diaochatype(Diaocha diaocha, String typename) {
		this.diaocha = diaocha;
		this.typename = typename;
	}

	// Property accessors

	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Diaocha getDiaocha() {
		return this.diaocha;
	}

	public void setDiaocha(Diaocha diaocha) {
		this.diaocha = diaocha;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public Set<Diaochawenti> getDiaochawentis() {
		return diaochawentis;
	}

	public void setDiaochawentis(Set<Diaochawenti> diaochawentis) {
		this.diaochawentis = diaochawentis;
	}

}