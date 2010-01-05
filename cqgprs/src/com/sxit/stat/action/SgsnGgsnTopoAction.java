/**
 * 
 */
package com.sxit.stat.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.LineChart;

import com.sxit.stat.models.SgsnStatModel;
import com.sxit.stat.util.StatUtil;

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
		return SUCCESS;
	}
}