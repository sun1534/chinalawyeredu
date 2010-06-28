import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jofc2.model.Chart;
import jofc2.model.Text;
import jofc2.model.axis.Label;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.XAxisLabels;
import jofc2.model.elements.LineChart;
import jofc2.model.elements.PieChart;

import com.sxit.netquality.models.Sgsn;
import com.sxit.netquality.service.BasicSetService;
import com.sxit.stat.models.SgsnStatModel;

/** 
 * 
 */

/**
 * @author 华锋 Oct 10, 2009 2:41:59 PM
 * 
 */
public class Test {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	public static void main(String[] args){
try{
	
	Timestamp from = new java.sql.Timestamp(df.parse(df.format(new Date())).getTime());
	
	Timestamp to = new java.sql.Timestamp(df.parse(df.format(new Date())).getTime() + 24 * 60 * 60 * 1000);
	System.out.println(from+"==="+to);
	
	String temp="46000333801";
//	46000333801这样的形式，转化为460-00-33380这样的形式
	String first=temp.substring(0,3);
	String second=temp.substring(3,5);
	String lac=temp.substring(5,10);
	String rai=first+"-"+second+"-"+lac;
	System.out.println(rai);
	
	int s=5/0;
	System.out.println(s);
}catch(Exception e){
	java.io.Writer sw=new java.io.StringWriter();
	try{
	java.io.PrintWriter pw=new java.io.PrintWriter(sw,true);
	e.printStackTrace(pw);
	pw.flush();
	pw.close();
	sw.flush();
	sw.close();
	System.out.println("-------"+sw.toString());
	}catch(Exception ee){
		
	}
}
		
	}
	
	public static void main12(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@218.201.8.150:1521:ora92", "jf_gprs",
				"jf_gprs");

		String sql = "select a.*,b.loginname,b.loginpwd from  SET_SGSN a,set_sgsn_server b where a.sgsnid=b.sgsnid(+) and a.opttype!=2 ";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		BasicSetService.ALL_SGSNS.clear();
		while (rs.next()) {
			Sgsn model = new Sgsn();
			model.setSgsnid(rs.getString("SGSNID"));
			// model.setSgsnarea(rs.getString("sgsnarea"));
			model.setLastopt(rs.getString("OPTTYPE"));
			Date date = new Date();
			date.setTime(rs.getLong("UPDATETIME") * 1000);
			model.setLastupdate(date);
			model.setSgsntype(rs.getString("provider"));
			model.setBsccount(rs.getInt("bsccount"));
			model.setSgsnip(rs.getString("sgsnip"));
			model.setSlotcount(rs.getInt("slotcount"));
			model.setCapacity(rs.getDouble("capacity"));
			model.setSgsnarea(rs.getString("sgsnarea"));
			model.setLoginname(rs.getString("loginname"));
			model.setLoginpwd(rs.getString("loginpwd"));
			
			BasicSetService.ALL_SGSNS.put(rs.getString("SGSNID"), model);
		}
		rs.close();
		stmt.close();
		con.close();
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
		// System.out.println(flashChart.toString());
	}

	private static void linechart() {
		LineChart c2 = new LineChart(); // 饼图

		for (int i = 5; i < 12; i++) {
			LineChart.Dot dots = new LineChart.Dot(i);
			c2.addDots(dots);
		}
		c2.setText("TD用户浏览");
		c2.setColour("#red");

		// c2.setStartAngle(-90); // 开始的角度
		// c2.setRadius(90);
		c2.setFontSize(20);
		// c2.setBorder(5);
		// c2.setNoLabels(false);
		// c2.setColours(new String[] { "0x336699", "0x88AACC", "0x999933",
		// "0x666699", "0xCC9933", "0x006666",
		// "0x3399FF", "0x993300", "0xAAAA77", "0x666666", "0xFFCC66",
		// "0x6699CC", "0x663366", "0x9999CC",
		// "0xAAAAAA", "0x669999", "0xBBBB55", "0xCC6600", "0x9999FF",
		// "0x0066CC", "0x99CCCC", "0x999999",
		// "0xFFCC00", "0x009999", "0x99CC33", "0xFF9900", "0x999966",
		// "0x66CCCC", "0x339966", "0xCCCC33" });// 饼图每块的颜色
		c2.setTooltip("#val#  /  #total#<br>占百分之 #percent#\n 角度 = #radius#"); // 鼠标移动上去后提示内容

		Chart flashChart = new Chart("企业性质排序2009年"); // 整个图的标题
		flashChart.addElements(c2); // 把饼图加入到图表
		Text title = new Text();
		title.setStyle("font-size:16px");
		title.setText("企业性质排序2009年企业性质排序2009年");
		flashChart.setTitle(title);

		jofc2.model.axis.XAxis x = new XAxis();
		x.setMax(40d);
		x.setMin(0d);

		x.setSteps(5);

		XAxisLabels la = new XAxisLabels();
		for (int i = 0; i < 1; i++) {
			jofc2.model.axis.Label l = new Label();
			// l.setColour("#333312");
			l.setText("哈咯");
			// l.setSize(12);

			// l.setText("x坐标");
			l.setVisible(true);
			la.addLabels(l);
		}

		x.setXAxisLabels(la);

		flashChart.setXAxis(x);

		System.out.println(flashChart.toDebugString());
		// System.out.println(flashChart.toString());

	}

	/**
	 * 存储sgsn在svg中的x坐标
	 */
	private static Map<String, Integer> SGSNIDLOC = new LinkedHashMap<String, Integer>();
	/**
	 * 存储ggsn在svg中的x坐标
	 */
	private static Map<String, Integer> GGSNIDLOC = new LinkedHashMap<String, Integer>();

	static {
		SGSNIDLOC.put("SGSNCQ01", 50);
		SGSNIDLOC.put("SGSNCQ02", 150);
		SGSNIDLOC.put("SGSNCQ03", 250);
		SGSNIDLOC.put("SGSNCQ04", 350);
		SGSNIDLOC.put("SGSNCQ05", 450);
		SGSNIDLOC.put("SGSNCQ06", 550);
		SGSNIDLOC.put("SGSNCQ07", 650);
		SGSNIDLOC.put("SGSNCQ08", 750);
		SGSNIDLOC.put("SGSNCQ09", 850);

		GGSNIDLOC.put("GGSN02", 250);
		GGSNIDLOC.put("GGSN03", 450);
		GGSNIDLOC.put("GGSN04", 650);
		GGSNIDLOC.put("GGSN05", 750);
		GGSNIDLOC.put("GGSN其他", 850);
	}
	/**
	 * 基本的量
	 */
	private static int BASICSTREAM = 10 * 1024;
	private static int OTHERBASICSTREAM = 1024;
	private static int STREAMDIFF = 3 * BASICSTREAM;
	private static int OTHERSTREAMDIFF = 2 * OTHERBASICSTREAM;

	/**
	 * @param args
	 */
	public static void main1(String[] args) throws Exception {

		Iterator<String> sgsniterator = SGSNIDLOC.keySet().iterator();
		while (sgsniterator.hasNext()) {
			String sgsnid = sgsniterator.next();
			int sgsnloc = SGSNIDLOC.get(sgsnid);
			int sgsntextloc = sgsnloc + 20;
			System.out.println("<rect x=\"" + sgsnloc
					+ "\" y=\"25\" width=\"80\" height=\"40\" fill=\"#FFFFFF\" stroke=\"#000000\"/>");
			System.out.println("<text x=\"" + sgsntextloc
					+ "\" y=\"50\" xml:space=\"preserve\" font-size=\"14\" fill=\"black\">" + sgsnid + "</text>");
		}

		Iterator<String> iterator = GGSNIDLOC.keySet().iterator();
		while (iterator.hasNext()) {
			String ggsnid = iterator.next();
			int ggsnloc = GGSNIDLOC.get(ggsnid);
			int ggsntextloc = ggsnloc + 20;
			System.out.println("<rect x=\"" + ggsnloc
					+ "\" y=\"150\" width=\"80\" height=\"40\" fill=\"#FFFFFF\" stroke=\"#000000\"/>");
			System.out.println("<text x=\"" + ggsntextloc
					+ "\" y=\"175\" xml:space=\"preserve\" font-size=\"14\" fill=\"black\">" + ggsnid + "</text>");
		}

		// <rect x="0" y="25" width="80" height="40" fill="#FFFFFF"
		// stroke="#000000"/>
		// <text x="20" y="50" xml:space="preserve" font-size="14"
		// fill="black">SGSN01</text>
Connection con=null;
//		Connection con = testcon();
		String sql = "select SGSNID,sum(USERCOUNT) as usercount1,sum(ALLVOLUME) as allvolume1,sum(upvolume) as upvolume1,sum(downvolume) as downvolume1,ggsnid,apnni from  STAT_SGSN where ggsnid like 'GGSN%' and dayflag=1 and STATTIME=20100102 group by sgsnid,ggsnid,apnni";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List list = new ArrayList();

		while (rs.next()) {
			SgsnStatModel model = new SgsnStatModel();
			int usercount = rs.getInt("USERCOUNT1");
			double all = rs.getDouble("ALLVOLUME1");
			String sgsnid = rs.getString("SGSNID");
			String ggsnid = rs.getString("ggsnid");

			model.setTotalStream(all);
			model.setTotalUser((int) (usercount));
			model.setDate("20100102");
			model.setSgsnid(sgsnid);
			model.setUpvolume(rs.getDouble("upvolume1"));
			model.setDownvolume(rs.getDouble("downvolume1"));
			model.setGgsnid(ggsnid);
			model.setApnni(rs.getString("apnni"));
			list.add(model);
		}

		rs.close();
		stmt.close();
		con.close();

		for (int i = 0; i < list.size(); i++) {
			SgsnStatModel model = (SgsnStatModel) list.get(i);

			double allvolume = model.getTotalStream();
			String sgsnid = model.getSgsnid();
			String ggsnid = model.getGgsnid();

			int sgsnidloc = SGSNIDLOC.get(sgsnid);
			int ggsnidloc = GGSNIDLOC.get(ggsnid);
			int sgsnaver = 60 / 3;
			int ggsnaver = 60 / 3;

			int sgsncmnetloc = sgsnidloc + sgsnaver;
			int sgsncmwaploc = sgsnidloc + 2 * sgsnaver;
			int sgsnotherloc = sgsnidloc + 3 * sgsnaver;

			int ggsncmnetloc = ggsnidloc + ggsnaver;
			int ggsncmwaploc = ggsnidloc + 2 * ggsnaver;
			int ggsnotherloc = ggsnidloc + 3 * ggsnaver;

			if (model.getApnni().equals("cmnet")) {
				if (allvolume > BASICSTREAM) {

					String width = com.sxit.system.util.NumberUtil.toMoney(allvolume / STREAMDIFF);

					System.out.println("<line x1=\"" + sgsncmnetloc + "\" y1=\"65\" x2=\"" + ggsncmnetloc
							+ "\" y2=\"150\" stroke=\"RED\" stroke-width=\"" + width + "\"/>");
					// System.out.println(model.getSgsnid()+"==>"+model.getGgsnid()+"=>"+allvolume+"==>cmnet");
				}

			} else if (model.getApnni().equals("cmwap")) {
				if (allvolume > BASICSTREAM) {
					String width = com.sxit.system.util.NumberUtil.toMoney(allvolume / STREAMDIFF);
					// System.out.println(model.getSgsnid()+"==>"+model.getGgsnid()+"=>"+allvolume+"==>cmwap");
					System.out.println("<line x1=\"" + sgsncmwaploc + "\" y1=\"65\" x2=\"" + ggsncmwaploc
							+ "\" y2=\"150\" stroke=\"BLUE\" stroke-width=\"" + width + "\"/>");
				}
			} else if (model.getApnni().equals("other")) {
				if (allvolume > OTHERBASICSTREAM) {
					String width = com.sxit.system.util.NumberUtil.toMoney(allvolume / OTHERSTREAMDIFF);

					// System.out.println(model.getSgsnid()+"==>"+model.getGgsnid()+"=>"+allvolume+"==>other");
					System.out.println("<line x1=\"" + sgsnotherloc + "\" y1=\"65\" x2=\"" + ggsnotherloc
							+ "\" y2=\"150\" stroke=\"YELLOW\" stroke-width=\"" + width + "\"/>");

				}
			}
		}

		// piechart();
		// linechart();

		// Date date=new Date();
		// date.setTime(1256787292L*1000);
		//		
		// System.out.println(date.toLocaleString());
		//		
		// System.out.println(System.getProperty("file.encoding"));
		// //
		// System.out.println("中文");
		//		
		// java.text.DateFormat df=new
		// java.text.SimpleDateFormat("yyyyMMddHHmmss");
		// System.out.println(df.parse("20091117000000").getTime()/1000);

		// java.text.NumberFormat nf=new DecimalFormat("####.#");
		//
		//		
		// int value=13000811;
		//		
		// BigDecimal num = new BigDecimal(13000811);
		//	
		// float vvv=num.floatValue();

		// System.out.println(System.currentTimeMillis()/1000);

	}

}
