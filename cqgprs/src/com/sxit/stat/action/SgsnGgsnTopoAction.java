/**
 * 
 */
package com.sxit.stat.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * SGSN分apn统计
 * （重庆目前9个SGSN，4个GGSN），匹配不上的都是外省的GGSN统一到其他中
 * 如果好做，考虑其他GGSN可以再单独出个报表，按流量排名
 * 
 * 根据sgsnid和ggsnid查询
 * 
 * </pre>
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class SgsnGgsnTopoAction extends StatAction {

	private int count = 1;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	private List datelist=new ArrayList();

	public List getDatelist() {
		return datelist;
	}

	/*
	 * 得到某一天的各个sgsn的流量 显示柱状图和线图
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		if (startDate == null) {
			startDate = getPrevDate();
			this.start = df.format(startDate);
		}
		for (int i = 0; i < count; i++) {
			Date date = com.sxit.stat.util.StatUtil.getPrevCountDate(startDate, i);
			datelist.add(df.format(date));
		}

		return SUCCESS;
	}
}