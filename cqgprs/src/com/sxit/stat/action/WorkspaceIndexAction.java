/**
 * 
 */
package com.sxit.stat.action;

import java.util.List;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.axis.YAxis;
import jofc2.model.axis.YAxisLabels;
import jofc2.model.elements.BarChart;

import com.sxit.stat.models.TotalStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * 
 * xlabels是时间,要找出时间来
 * 
 * 总流量分析
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class WorkspaceIndexAction extends StatAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		if (startDate == null) {
			startDate = super.getPrevCountDate(3);
			this.start = df.format(startDate);
		}
		if (endDate == null) {
			endDate = getPrevDate();
			this.end = df.format(endDate);
		}
	

//		streamlist = statservice.getDaysTotalStream(startDate, endDate);

		while(com.sxit.index.IndexPageAction.cache==null){
			Thread.sleep(50);
		}
		streamlist=com.sxit.index.IndexPageAction.cache.getStreamlist();
		
		this.flashChart = barChart();

		return "ofc";
	}

	// private Chart lineChart() {
	// double min = 0d;
	// double max = 0d;
	// LineChart c2 = new LineChart(); //
	// jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
	// int len = streamlist.size();
	// for (int i = 0; i < len; i++) {
	// TotalModel stat = (TotalModel) streamlist.get(i);
	// float value = 0;
	//
	// if (flashby.equals("total"))
	// value = stat.getTotalStreamStr();
	// else if (flashby.equals("average"))
	// value = stat.getAverageStream();
	// else if (flashby.equals("user"))
	// value = stat.getTotalUser();
	// if (value < min)
	// min = value;
	// if (value > max)
	// max = value;
	// c2.addValues(value);
	// jofc2.model.axis.Label label = new Label();
	// label.setText(stat.getDate());
	// xlables.addLabels(label);
	//
	// }
	//
	// flashChart = new Chart(); // 整个图的标题
	// flashChart.addElements(c2); // 把饼图加入到图表
	//
	// jofc2.model.axis.XAxis xaxis = new XAxis();
	// xaxis.setXAxisLabels(xlables); // 显示横坐标
	// flashChart.setXAxis(xaxis);
	//
	// jofc2.model.axis.YAxis yaxis = new YAxis();
	//
	// double steps = StatUtil.steps(max, min, 10);
	// yaxis.setSteps(steps);
	// yaxis.setMin(min - steps); // 最小值加一个步长
	// yaxis.setMax(max + steps); // 最大值加一个步长
	// flashChart.setYAxis(yaxis);
	// Text title = new Text();
	// title.setStyle("font-size:16px,font:bold");
	// if (flashby.equals("total"))
	// title.setText(start + "至" + end + "总流量分析");
	// else if (flashby.equals("average")) {
	// title.setText(start + "至" + end + "平均流量分析");
	// } else if (flashby.equals("user")) {
	// title.setText(start + "至" + end + "总用户数分析");
	// }
	// flashChart.setTitle(title);
	//
	// return flashChart;
	// }

	private Chart barChart() {
		float min = 0.0f;
		float max = 0.0f;
		BarChart c2 = new BarChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		jofc2.model.axis.YAxisLabels ylables=new YAxisLabels();
		int len = streamlist.size();
		for (int i = 0; i < len; i++) {
			TotalStatModel stat = (TotalStatModel) streamlist.get(i);
			
			float value = 0;
			// 总流量
			if (flashby.equals("total"))
				value = (float)stat.getTotalStream();
			else if (flashby.equals("average"))
				value = stat.getAverageStream();
			else if (flashby.equals("user"))
				value = stat.getTotalUser();
			if (value < min)
				min = value;
			if (value > max)
				max = value;
			c2.addValues(value);
			jofc2.model.axis.Label label = new Label();
			label.setText(stat.getDate());
	
			xlables.addLabels(label);
		}
		System.out.println("max::::"+max);
		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表

		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setXAxisLabels(xlables); // 显示横坐标
		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 6);
		yaxis.setSteps(steps);
		yaxis.setMin(min - steps<=0?0:min-steps); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		
		
		
//		jofc2.model.axis.Label label = new Label();
//		label.setText("#val#M");
//		ylables.addLabels(label);
//		yaxis.setLabels("#val#M");
//		System.out.println("===");
		
		
		flashChart.setYAxis(yaxis);
		Text title = new Text();
//		title.setStyle("{font-size:14px;font-weight:bold}");
		title.setStyle("{font-size:14px;}");

		if (flashby.equals("total")){
			title.setText("最近3天总流量情况(M)");
		}
		else if (flashby.equals("average")) {
			title.setText("最近3天平均流量情况(K)");		    

		} else if (flashby.equals("user")) {
			title.setText("最近3天总用户数情况");		    
		}
//		Tooltip tooltip=new Tooltip();
//		tooltip.set
//		flashChart.setTooltip(tooltip);

		flashChart.setTitle(title);
		return flashChart;
	}

	private List streamlist;

}
