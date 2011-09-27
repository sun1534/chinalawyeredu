import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NewCustomer.java
 */

/**
 * 
 * 删除重复的tusr_address数据
 * 
 * @author 华锋 Nov 27, 201010:36:58 PM
 * 
 */
public class Export {

	private static String user = "jds";
	private static String password = "jds";
	private static String url = "jdbc:oracle:thin:@localhost:1521:ora10";
//	private static String url = "jdbc:oracle:thin:@58.60.231.4:21521:ora10";
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() throws Exception {
		return java.sql.DriverManager.getConnection(url, user, password);
	}

	private static Connection con = null;

	private static String getLogs(Connection con,long creditcard)throws Exception{
		String sql="select * from topr_creditlog where creditcardid="+creditcard;
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String str="";
		boolean b=false;
		while(rs.next()){
			b=true;
			str+=(rs.getString("logtime")==null?"":rs.getString("logtime"))+","+(rs.getString("comments")==null?"":rs.getString("comments"))+",";
		}
		if(!b){
			str=",,";
		}
		rs.close();
		stmt.close();
		return str;
		
	}
	
	public static void main(String args[]) throws Exception {
		Connection con = null;
		java.io.PrintWriter log = new java.io.PrintWriter(new java.io.FileOutputStream("d:/delete.csv", true), true);
		try {
			con = getConn();

			String sql = "select * from topr_creditcard where consigndate like '2011-08%'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			log.println("案件编号,客户姓名,卡号,身份证,逾期金额,联系人,担保人,催收时间,催收记录");
			
			long totalnow=System.currentTimeMillis();
			int i=0;
			while (rs.next()) {
				System.out.println("="+(i++));
				String str="";
				str+=(rs.getString("bianhao")==null?"":rs.getString("bianhao"))+",";
				str+=(rs.getString("username")==null?"":rs.getString("username"))+",";
				str+=(rs.getString("creditcard")==null?"":rs.getString("creditcard"))+",";
				str+=(rs.getString("idcard")==null?"":rs.getString("idcard"))+",";
				str+=(rs.getString("curcnfee")==null?"":rs.getString("curcnfee"))+",";
				str+=(rs.getString("contactp1phone1")==null?"":rs.getString("contactp1phone1"))+",";
				str+=(rs.getString("cautioner")==null?"":rs.getString("cautioner"))+",";
				str+=getLogs(con,rs.getLong("creditcardid"));
				log.println(str);
				
			}
			rs.close();
			stmt.close();

		
		

		
			log.flush();
			log.close();

			System.out.println("总计时间为:" + (System.currentTimeMillis() - totalnow));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		con.close();
	}

	public static int getUsrAddress(Connection con, String md5val) throws SQLException {
		String sql = "select addressid from tusr_address where md5val=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int s = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, md5val);
			rs = pstmt.executeQuery();
			if (rs.next())
				s = rs.getInt("addressid");
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return s;
	}

}
