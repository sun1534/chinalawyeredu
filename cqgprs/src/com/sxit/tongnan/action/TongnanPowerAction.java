/**
 * 
 */
package com.sxit.tongnan.action;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.LineChart;

import com.sxit.common.Globals;
import com.sxit.common.action.AbstractListAction;
import com.sxit.models.tongnan.StatTongnanpower;
import com.sxit.stat.util.StatUtil;
import com.sxit.tongnan.service.TongnanPowerService;

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
public class TongnanPowerAction extends AbstractListAction {

	private String month;
	private String uploadmonth;
	private String cellid;
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyyMM");
	public TongnanPowerAction(){
		this.needsession=false;
	}
	// 1中达日均/载频 2中达日均/话务 3电力日均/载频 4电力日均/话务 5中达日均和电力日均
	//
	private int flashby;

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/*
	 * 得到某一天的各个sgsn的流量 显示柱状图和线图
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		uploadmonth = df.format(new Date());
		Calendar c = Calendar.getInstance();

		c.add(Calendar.MONTH, -1);

		java.sql.Timestamp ts = new java.sql.Timestamp(c.getTimeInMillis());
		if (month == null || month.equals(""))
			month = df.format(ts);

		TongnanPowerService service = (TongnanPowerService) Globals.getBean("tongnanPowerService");
		this.powerlist = service.getMonthPowers(month, cellid);

		if (resultType.equals("list")) {
			return SUCCESS;
		} else if (resultType.equals("excel"))
			return "excel";

		else {
			if (flashby == 4)
				lineChart();
			else
				chart();
			return "ofc";
		}
	}

	// 1中达日均/载频 2中达日均/话务 3电力日均/载频 4电力日均/话务 5中达日均和电力日均
	private void chart() {
		float min = 0.0f;
		float max = 0.0f;

		BarChart bar = new BarChart(); // 
		LineChart line = new LineChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = powerlist.size();
		List labels = new ArrayList();

		float father = 0.0f;
		float son = 0.0f;

		Text title = new Text();
		title.setStyle("{font-size:14px;}");
		if (flashby == 1) {
			title.setText(month + "潼南电度对比之中达日均/载频数据分析");
			bar.setText("中达日均/载频");
			
		} else if (flashby == 2) {
			title.setText(month + "潼南电度对比中达日均/话务数据分析");
			bar.setText("中达日均/话务");
			
		} else if (flashby == 3) {
			title.setText(month + "潼南电度对比电力日均/载频数据分析");
			bar.setText("电力日均/载频");
			
		} else if (flashby == 4) {
			title.setText(month + "潼南电度对比电力日均/话务数据分析");
			bar.setText("电力日均/话务");
			
		}
		bar.setFontSize(12);
		bar.setFontSize(12);
		bar.setColour("#0000ff");
		line.setFontSize(12);
		line.setFontSize(12);
		line.setColour("#FF0000");

		for (int i = 0; i < len; i++) {
			StatTongnanpower stat = (StatTongnanpower) powerlist.get(i);
			if(stat.getZaipin()==0)
				continue;
			float value = 0.0f;
			if (flashby == 1) {
				father += stat.getZhongdanrijun();
				son += stat.getZaipin();
				value = Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney(stat.getZhongdanrijun()
						/ stat.getZaipin()));
			} else if (flashby == 2) {
				father += stat.getZhongdanrijun();
				son += stat.getHuawuliangrijun();
				value = Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney(stat.getZhongdanrijun()
						/ stat.getHuawuliangrijun()));

			} else if (flashby == 3) {
				father += stat.getPowerrijun();
				son += stat.getZaipin();
				value = Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney(stat.getPowerrijun()
						/ stat.getZaipin()));

			} else if (flashby == 4) {
				father += stat.getPowerrijun();
				son += stat.getHuawuliangrijun();
				value = Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney(stat.getPowerrijun()
						/ stat.getHuawuliangrijun()));

			}

			// 总流量
			if (value < min)
				min = value;
			if (value > max)
				max = value;
			bar.addValues(value);
			jofc2.model.axis.Label label = new Label();
			label.setText(stat.getCellName());
			label.setRotation(ration);
			label.setSize(12);
			xlables.addLabels(label);
		}

		float averge = Float.parseFloat(com.sxit.system.util.NumberUtil.toMoney(father / son));
		for (int i = 0; i < len; i++) {
			line.addValues(averge);
		}
		line.setText("平均值:"+averge);
		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(bar); // 把饼图加入到图表
		flashChart.addElements(line); // 把饼图加入到图表
		jofc2.model.axis.XAxis xaxis = new XAxis();
		// xaxis.setSteps(20);
		xaxis.setXAxisLabels(xlables); // 显示横坐标

		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(0); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		flashChart.setYAxis(yaxis);

		flashChart.setTitle(title);
	}

	private void lineChart() {
		double min1 = 0d;
		double max1 = 0d;
		double min2 = 0d;
		double max2 = 0d;
		LineChart c1 = new LineChart(); // 
		LineChart c2 = new LineChart(); // 
		c2.setTooltip("#x_label#: #val# ");
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = powerlist.size();
		List labels = new ArrayList();
		for (int i = 0; i < len; i++) {
			StatTongnanpower stat = (StatTongnanpower) powerlist.get(i);

			// 总流量
			double value1 = stat.getZhongdanrijun();
			double value2 = stat.getPowerrijun();
			// 总流量

			if (value1 < min1)
				min1 = value1;
			if (value1 > max1)
				max1 = value1;

			if (value2 < min2)
				min2 = value2;
			if (value2 > max2)
				max2 = value2;

			c2.addValues(value1);
			c1.addValues(value2);
			jofc2.model.axis.Label label = new Label();
			label.setText(stat.getCellName());
			label.setRotation(ration);
			xlables.addLabels(label);
		}

		c1.setText("电力公司日均");
		c2.setText("中达日均");

		c1.setFontSize(12);
		c2.setTooltip("#x_label#: #val# ");
		c2.setFontSize(12);
		c1.setColour("#0000ff");
		c2.setColour("#ff0000");

		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表
		flashChart.addElements(c1); // 把饼图加入到图表
		c2.setTooltip("#x_label#: #val# ");
		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setSteps(20);
		// xlables.setSteps(20);
		xaxis.setXAxisLabels(xlables); // 显示横坐标
		// xaxis.setLabels(labels);
		// xlables.setSteps(20);

		flashChart.setXAxis(xaxis);

		double max = max1 > max2 ? max1 : max2;
		double min = min1 < min2 ? min1 : min2;

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(0); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		flashChart.setYAxis(yaxis);
		Text title = new Text();
		title.setStyle("{font-size:14px;}");

		title.setText(month + "潼南电度对比");
		// Text legend = new Text();
		// legend.setText("9999999999999999");
		// flashChart.setXLegend(legend);
		flashChart.setTitle(title);

	}

	protected jofc2.model.axis.Label.Rotation ration = jofc2.model.axis.Label.Rotation.VERTICAL;

	/**
	 * 显示图形用的
	 */
	protected Chart flashChart;

	public Chart getFlashChart() {
		return this.flashChart;
	}

	private List powerlist;

	public List getPowerlist() {
		return this.powerlist;
	}

	/**
	 * @return the cellid
	 */
	public String getCellid() {
		return cellid;
	}

	/**
	 * @return the flashby
	 */
	public int getFlashby() {
		return flashby;
	}

	/**
	 * @param flashby
	 *            the flashby to set
	 */
	public void setFlashby(int flashby) {
		this.flashby = flashby;
	}

	/**
	 * @return the uploadmonth
	 */
	public String getUploadmonth() {
		return uploadmonth;
	}

}
