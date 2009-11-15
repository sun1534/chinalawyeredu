/**
 * 
 */
package com.sxit.netquality.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.sxit.common.action.AbstractListAction;
import com.sxit.netquality.service.HighStreamService;

/**
 * 
 * 高流量小区
 * 从stat_cell里拿流量排名前100的小区
 * 
 * 最好是做成可配置的,高流量小区，排名前多少位的或流量大于多少滴
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class HightStreamCellAction extends AbstractListAction {

	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	private String date;  //日期
	private String hour;  //时间点
	private String resultType="list";
	private String standard="0"; //按排名
//是按流量大小1排名2流量
	private String condition="1000"; //超过多少的处理,需要前面的1000个
	
	private String flag; //1按天统计0按小时
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
        Date thedate=null;
		if(date==null||date.equals("")){
			thedate=com.sxit.stat.util.StatUtil.getPrevDate();
			date=df.format(thedate);
		}else{
			try{
				thedate=df.parse(date);
			}catch(Exception e){
				thedate=com.sxit.stat.util.StatUtil.getPrevDate();
			}
		}
		
		HighStreamService hightService=(HighStreamService)this.getBean("highStreamService");
		topcelllist=	hightService.getHightStreamDayCell(standard, condition, thedate);
		if(resultType.equals("list")){
			
			return SUCCESS;
		}else{
			
			return "excel";
		}
		
	}
	
	private List topcelllist;
	public List getTopcelllist(){
		return this.topcelllist;
	}

}
