import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UpdateHzJf.java
 */

/**
 * @author 华锋 Feb 24, 201110:09:08 AM
 * 
 */
public class UpdateHzJf {
	private static Connection con = null;
	static String url = "jdbc:mysql://211.154.157.174:3306/saaspxxt?characterEncoding=utf-8";
	static String user = "root";
	static String pass = "password";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		PrintWriter logugroup = new PrintWriter(new FileOutputStream("c:\\group.sql", true), true);
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		con = java.sql.DriverManager.getConnection(url, user, pass);

		System.out.println(con);
		String sql = "select lawyerid from lawyers where directunion=8078";

		// 未达标
		String nosql = "select lawyerid,sum(pxxf) as pxxf from lawyerlessonxf where cityid=8078 and theyear=2010 group by lawyerid having sum(pxxf)<40";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<Integer> alllawyers = new ArrayList<Integer>();
		Map<Integer, Float> weidabiao = new HashMap<Integer, Float>();
		while (rs.next()) {
			alllawyers.add(rs.getInt("lawyerid"));
		}
		
	
	
		
		System.out.println("总数:"+alllawyers.size());
		
		
		Statement stmt3 = con.createStatement();
		
	
		String weipeixunsql="select a.lawyerid from lawyers a where not exists (select distinct lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid and c.theyear=2010 and c.cityid=8078) and a.directunion=8078";
		ResultSet rs3 = stmt3.executeQuery(weipeixunsql);
		while (rs3.next()) {
			weidabiao.put(rs3.getInt("lawyerid"),0f);
		}
		System.out.println("未培训:"+weidabiao.size());
//		总数:2525
//		未培训:391
//		未达标总数:823	
		Statement stmt1 = con.createStatement();
		ResultSet rs1 = stmt1.executeQuery(nosql);
		while (rs1.next()) {
			weidabiao.put(rs1.getInt("lawyerid"), rs1.getFloat("pxxf"));
		}
		System.out.println("未达标总数:"+weidabiao.size());

		String xuefensql = "select lawyerid,pxxf,xfid from lawyerlessonxf where cityid=8078 and theyear=2011";
		Statement stmt2 = con.createStatement();
		ResultSet rs2 = stmt2.executeQuery(xuefensql);
		while (rs2.next()) {
			int lawyerid = rs2.getInt("lawyerid");
			float xf = rs2.getFloat("pxxf");
			int xfid = rs2.getInt("xfid");

			// 如果lawyerid未达标
			if (weidabiao.containsKey(lawyerid)) {

				float zhihouxuefen = xf + weidabiao.get(lawyerid);
		
//				System.out.println(updsql);
				weidabiao.remove(lawyerid);
				System.out.println("===="+zhihouxuefen);
				if(zhihouxuefen>=40){
					String updsql="update lawyerlessonxf set theyear=2010 where theyear=2011 and lawyerid="+lawyerid+";";
					logugroup.println(updsql);
					System.out.println(lawyerid+"到40分了");
				}else{
					System.out.println(lawyerid+"累加");
					weidabiao.put(lawyerid, zhihouxuefen);
				}

			}
		}
		
		con.close();
		
		logugroup.flush();
		logugroup.close();

	}

}
