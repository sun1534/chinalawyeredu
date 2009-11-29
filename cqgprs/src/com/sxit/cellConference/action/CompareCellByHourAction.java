/**
 * 
 */
package com.sxit.cellConference.action;

import java.text.DateFormat;
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

import com.sxit.cellConference.models.ConferenceCell;
import com.sxit.cellConference.service.CellConferenceService;
import com.sxit.stat.action.StatAction;
import com.sxit.stat.models.CellStatModel;
import com.sxit.stat.util.StatUtil;

/**
 * 
 * 这个cellid和这个lac，在监控的小时，连续n天的一个数据情况
 * 
 * @author 华锋 Oct 19, 2009-11:34:22 PM
 * 
 */
public class CompareCellByHourAction extends StatAction {
	private static final DateFormat dfhh = new java.text.SimpleDateFormat("HH");

	private String cellkey;
	private long stattime;
	private String date;
	private String hour;
	private int days;
	private List comparelist;

	public List getComparelist() {
		return this.comparelist;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * @return the cellkey
	 */
	public String getCellkey() {
		return cellkey;
	}

	/**
	 * @param cellkey
	 *            the cellkey to set
	 */
	public void setCellkey(String cellkey) {
		this.cellkey = cellkey;
	}

	/**
	 * @return the stattime
	 */
	public long getStattime() {
		return stattime;
	}

	/**
	 * @param stattime
	 *            the stattime to set
	 */
	public void setStattime(long stattime) {
		this.stattime = stattime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		this.days = 7;

		CellConferenceService service = (CellConferenceService) this.getBean("cellConferenceService");
		int idx = cellkey.indexOf("-");
		String lac = cellkey.substring(0, idx);
		String cellid = cellkey.substring(idx + 1);
		ConferenceCell cell = service.getConferenceCell(lac, cellid);
		if (cell == null) {
			Date date = new java.util.Date();
			date.setTime(stattime * 1000);
			String _hour = dfhh.format(date);
			this.hour = Integer.parseInt(_hour) + "";

		} else {
			this.hour = cell.getTimeview() + "";
		}

		comparelist = service.getConitnueTimeStatCells(cellid, lac, date, hour, days);
		System.out.println("comparelist.size()::::::::::::::"+comparelist.size());

		if (resultType.equals("list"))
			return SUCCESS;

		else if (resultType.equals("excel"))
			return "excel";
		else if (resultType.equals("flash")) {
			if (flashType.equals("bar")) { // 产生柱状图
				this.flashChart = barChart();
			} else { // 产生线图
				this.flashChart = lineChart();
			}
			return "ofc";
		} else {
			this.message = "返回数据类型错误";
			return "message";
		}

	}

	private Chart lineChart() {
		String cellname="";
		if(com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellkey)!=null){
			cellname=com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellkey).getCellname();
		}
		double min = 0d;
		double max = 0d;
		LineChart c2 = new LineChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = comparelist.size();
		for (int i = 0; i < len; i++) {
			CellStatModel stat = (CellStatModel) comparelist.get(i);
			float value = 0;
			if (flashby.equals("total"))
				value = (float) stat.getTotalStream();
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
			label.setRotation(ration);
			xlables.addLabels(label);
			
			
			System.out.println("stat.getDate():"+stat.getDate());

		}

		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表

		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setXAxisLabels(xlables); // 显示横坐标

		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();
		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(min - steps <= 0 ? 0 : (min - steps)); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长

		flashChart.setYAxis(yaxis);
		Text title = new Text();
		title.setStyle("{font-size:14px}");
//		巴南和平桥-1（13118-10011）从2009-11-28起连续前7天11点之详情
		if (flashby.equals("total"))
			title.setText(cellname+"（"+cellkey+"）从"+date+"起连续前"+days+"天"+hour+"点总流量详情（M）");
		else if (flashby.equals("average")) {
			title.setText(cellname+"（"+cellkey+"）从"+date+"起连续前"+days+"天"+hour+"点平均流量详情（K）");
		} else if (flashby.equals("user")) {
			title.setText(cellname+"（"+cellkey+"）从"+date+"起连续前"+days+"天"+hour+"点总用户数");
		}
		flashChart.setTitle(title);

		return flashChart;
	}

	private Chart barChart() {
		String cellname="";
		if(com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellkey)!=null){
			cellname=com.sxit.netquality.service.BasicSetService.ALL_CELLS.get(cellkey).getCellname();
		}
		double min = 0d;
		double max = 0d;
		BarChart c2 = new BarChart(); // 
		jofc2.model.axis.XAxisLabels xlables = new XAxisLabels();
		int len = comparelist.size();
		
		System.out.println("comparelist.size()::::::::::::::"+comparelist.size());
		
		for (int i = 0; i < len; i++) {
			CellStatModel stat = (CellStatModel) comparelist.get(i);

			float value = 0;
			// 总流量
			if (flashby.equals("total"))
				value =  (float) stat.getTotalStream();
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
			label.setRotation(ration);
			xlables.addLabels(label);
			System.out.println("stat.getDate():"+stat.getDate());
			
		}
	
		flashChart = new Chart(); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表

		jofc2.model.axis.XAxis xaxis = new XAxis();
		xaxis.setXAxisLabels(xlables); // 显示横坐标
		flashChart.setXAxis(xaxis);

		jofc2.model.axis.YAxis yaxis = new YAxis();

		double steps = StatUtil.steps(max, min, 10);
		yaxis.setSteps(steps);
		yaxis.setMin(min - steps <= 0 ? 0 : (min - steps)); // 最小值加一个步长
		yaxis.setMax(max + steps); // 最大值加一个步长
		flashChart.setYAxis(yaxis);
		Text title = new Text();
		title.setStyle("{font-size:14px}");

		if (flashby.equals("total"))
			title.setText(cellname+"（"+cellkey+"）从"+date+"起连续前"+days+"天"+hour+"点总流量详情（M）");
		else if (flashby.equals("average")) {
			title.setText(cellname+"（"+cellkey+"）从"+date+"起连续前"+days+"天"+hour+"点平均流量详情（K）");
		} else if (flashby.equals("user")) {
			title.setText(cellname+"（"+cellkey+"）从"+date+"起连续前"+days+"天"+hour+"点总用户数");
		}

		flashChart.setTitle(title);
		return flashChart;
	}

}
