import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.elements.LineChart;
import jofc2.model.elements.PieChart;

/** 
 * 
 */

/**
 * @author 华锋 Oct 10, 2009 2:41:59 PM
 * 
 */
public class Test {
	private void testcon() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@218.201.8.150:1521:ora92", "jf_gprs",
				"jf_gprs");
		System.out.println(con);
	}

	private static void piechart() {
		PieChart c2 = new PieChart(); // 饼图

		for (int i = 0; i < 10; i++) {
			c2.addSlice(i + 1, i + "1"); // 增加一块
		}

		c2.setStartAngle(-90); // 开始的角度
		c2.setRadius(90);
		c2.setFontSize(20);
		c2.setBorder(5);
		c2.setNoLabels(false);
		c2.setColours(new String[] { "0x336699", "0x88AACC", "0x999933", "0x666699", "0xCC9933", "0x006666",
				"0x3399FF", "0x993300", "0xAAAA77", "0x666666", "0xFFCC66", "0x6699CC", "0x663366", "0x9999CC",
				"0xAAAAAA", "0x669999", "0xBBBB55", "0xCC6600", "0x9999FF", "0x0066CC", "0x99CCCC", "0x999999",
				"0xFFCC00", "0x009999", "0x99CC33", "0xFF9900", "0x999966", "0x66CCCC", "0x339966", "0xCCCC33" });// 饼图每块的颜色
		c2.setTooltip("#val#  /  #total#<br>占百分之 #percent#\n 角度 = #radius#"); // 鼠标移动上去后提示内容

		Chart flashChart = new Chart("企业性质排序2009年"); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表
		Text title = new Text();
		title.setStyle("font-size:16px");
		title.setText("企业性质排序2009年企业性质排序2009年");
		flashChart.setTitle(title);
		System.out.println(flashChart.toDebugString());
//		System.out.println(flashChart.toString());
	}
	
	private static void linechart() {
		LineChart c2 = new LineChart(); // 饼图
		
	
		for (int i = 5; i < 12; i++) {
			LineChart.Dot dots=new LineChart.Dot(i);
			c2.addDots(dots);
		}
		c2.setText("TD用户浏览");
		c2.setColour("#red");
		

//		c2.setStartAngle(-90); // 开始的角度
//		c2.setRadius(90);
		c2.setFontSize(20);
//		c2.setBorder(5);
//		c2.setNoLabels(false);
//		c2.setColours(new String[] { "0x336699", "0x88AACC", "0x999933", "0x666699", "0xCC9933", "0x006666",
//				"0x3399FF", "0x993300", "0xAAAA77", "0x666666", "0xFFCC66", "0x6699CC", "0x663366", "0x9999CC",
//				"0xAAAAAA", "0x669999", "0xBBBB55", "0xCC6600", "0x9999FF", "0x0066CC", "0x99CCCC", "0x999999",
//				"0xFFCC00", "0x009999", "0x99CC33", "0xFF9900", "0x999966", "0x66CCCC", "0x339966", "0xCCCC33" });// 饼图每块的颜色
		c2.setTooltip("#val#  /  #total#<br>占百分之 #percent#\n 角度 = #radius#"); // 鼠标移动上去后提示内容

		Chart flashChart = new Chart("企业性质排序2009年"); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表
		Text title = new Text();
		title.setStyle("font-size:16px");
		title.setText("企业性质排序2009年企业性质排序2009年");
		flashChart.setTitle(title);
		
		
		jofc2.model.axis.XAxis x=new XAxis();
		x.setMax(40d);
		x.setMin(0d);
		


	x.setSteps(5);
	
	
	XAxisLabels la=new XAxisLabels();
	for(int i=0;i<1;i++){
		jofc2.model.axis.Label l=new Label();
//		l.setColour("#333312");
		l.setText("哈咯");
//			l.setSize(12);
		
//	l.setText("x坐标");
	l.setVisible(true);
	la.addLabels(l);
	}
	
	x.setXAxisLabels(la);

	flashChart.setXAxis(x);
	
	
		
		System.out.println(flashChart.toDebugString());
//		System.out.println(flashChart.toString());
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
	
		System.out.println((5-3)/3);
		
//		piechart();
//		linechart();
		
//		Date date=new Date();
//		date.setTime(1256787292L*1000);
//		
//		System.out.println(date.toLocaleString());
//		
		System.out.println(System.getProperty("file.encoding"));
//		
		System.out.println(Long.MAX_VALUE/1000);
		
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(df.parse("20091117000000").getTime()/1000);
		
	
//		java.text.NumberFormat nf=new   DecimalFormat("####.#");
//
//		
//		int value=13000811;
//		
//		  BigDecimal   num   =   new   BigDecimal(13000811);
//	
//		  float vvv=num.floatValue();

		
		
		
//			System.out.println(System.currentTimeMillis()/1000);


	}

}
