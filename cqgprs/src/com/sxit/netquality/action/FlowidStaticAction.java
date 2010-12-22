/**
 * HwchrQueryAction.java
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.ChrQueryService;

/**
 * @author 华锋
 * Jul 29, 20108:41:24 PM
 *
 */
public class FlowidStaticAction extends AbstractListAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String statdate;
	private String stattime="";
	private int statflag;// 1按天0按小时
	
	
	
	
	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		
		boolean stattdateset=true;
		if (this.statdate == null || this.statdate.equals("")) {
			this.statdate = df.format(new java.util.Date()); // 默认当天
			stattdateset=false;
		}
		
		if(stattime==null||stattime.equals("")){
			Calendar c=Calendar.getInstance();
			c.setTimeInMillis(c.getTimeInMillis()-60*60*1000);
			int minute=c.get(Calendar.MINUTE);
			int hour=c.get(Calendar.HOUR_OF_DAY);
			if(minute<35)
			{
				if(hour==0){
					hour=23;
					if (!stattdateset) {
						this.statdate = df.format(new java.sql.Timestamp(System.currentTimeMillis()-24*60*60*1000)); // 默认当天
					}
				}else{
					hour=hour-1;
				}
				
			}
			stattime=(hour<10?"0"+hour:""+hour);
		}
		

		String _statdate = statdate.replace("-", "");
		
		
		String _stattime=stattime.startsWith("0")?stattime.substring(1):stattime;
		
		if(sgsnid==null||sgsnid.equals(""))
			sgsnview=true;
		if(cao==null||cao.equals(""))
			caoview=true;
		if(kuang==null||kuang.equals(""))
			kuangview=true;
		
		ChrQueryService queryservice = (ChrQueryService) this.getBean("chrQueryService");
		
		this.page= queryservice.getFlowidStat(statflag, _statdate, _stattime, sgsnid, kuang, cao, pageNo, pageSize);
		
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	private boolean sgsnview;
	private boolean kuangview;
	private boolean caoview;
	
	private String sgsnid;
	private String kuang;
	private String cao;
	private String cellid;




	/**
	 * @return the statdate
	 */
	public String getStatdate() {
		return statdate;
	}
	/**
	 * @param statdate the statdate to set
	 */
	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}
	/**
	 * @return the stattime
	 */
	public String getStattime() {
		return stattime;
	}
	/**
	 * @param stattime the stattime to set
	 */
	public void setStattime(String stattime) {
		this.stattime = stattime;
	}
	/**
	 * @return the statflag
	 */
	public int getStatflag() {
		return statflag;
	}
	/**
	 * @param statflag the statflag to set
	 */
	public void setStatflag(int statflag) {
		this.statflag = statflag;
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
	 * @return the sgsnview
	 */
	public boolean getSgsnview() {
		return sgsnview;
	}
	/**
	 * @return the kuangview
	 */
	public boolean getKuangview() {
		return kuangview;
	}
	/**
	 * @return the caoview
	 */
	public boolean getCaoview() {
		return caoview;
	}

}
