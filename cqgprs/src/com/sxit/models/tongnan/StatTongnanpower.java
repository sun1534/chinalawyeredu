package com.sxit.models.tongnan;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import com.sxit.common.util.NumberUtil;


/**
 * StatTongnanpower entity. @author MyEclipse Persistence Tools
 */

public class StatTongnanpower  implements java.io.Serializable {


    // Fields    

     private int id;
     private String month;
     private String cellName;
     private String lacCell;
     private double zhongdanrijun;
     private double powerrijun;
     private int zaipin;
     private double huawuliangrijun;
     private double therate;
     private Timestamp createtime;
     private Timestamp updatetime;
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
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the cellName
	 */
	public String getCellName() {
		return cellName;
	}
	/**
	 * @param cellName the cellName to set
	 */
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	/**
	 * @return the lacCell
	 */
	public String getLacCell() {
		return lacCell;
	}
	/**
	 * @param lacCell the lacCell to set
	 */
	public void setLacCell(String lacCell) {
		this.lacCell = lacCell;
	}
	/**
	 * @return the zhongdanrijun
	 */
	public double getZhongdanrijun() {
		return zhongdanrijun;
	}
	/**
	 * @param zhongdanrijun the zhongdanrijun to set
	 */
	public void setZhongdanrijun(double zhongdanrijun) {
		this.zhongdanrijun = zhongdanrijun;
	}
	/**
	 * @return the powerrijun
	 */
	public double getPowerrijun() {
		return powerrijun;
	}
	/**
	 * @param powerrijun the powerrijun to set
	 */
	public void setPowerrijun(double powerrijun) {
		this.powerrijun = powerrijun;
	}
	/**
	 * @return the zaipin
	 */
	public int getZaipin() {
		return zaipin;
	}
	/**
	 * @param zaipin the zaipin to set
	 */
	public void setZaipin(int zaipin) {
		this.zaipin = zaipin;
	}
	/**
	 * @return the huawuliangrijun
	 */
	public double getHuawuliangrijun() {
		return huawuliangrijun;
	}
	/**
	 * @param huawuliangrijun the huawuliangrijun to set
	 */
	public void setHuawuliangrijun(double huawuliangrijun) {
		this.huawuliangrijun = huawuliangrijun;
	}
	/**
	 * @return the therate
	 */
	public double getTherate() {
//		return therate;
		return Float.parseFloat(nf.format(zhongdanrijun/powerrijun));
	}
	
	public String getTheratePercent(){

		return nff.format(zhongdanrijun/powerrijun);
	}
	
	/**
	 * @param therate the therate to set
	 */
	public void setTherate(double therate) {
		this.therate = therate;
//		this.therate = Float.parseFloat(NumberUtil.toMoney(zhongdanrijun/powerrijun));
	}
	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the updatetime
	 */
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	private final static DecimalFormat nf = new DecimalFormat("#####0.0000");
	
	private final static DecimalFormat nff = new DecimalFormat("#####0.00%");

    
}