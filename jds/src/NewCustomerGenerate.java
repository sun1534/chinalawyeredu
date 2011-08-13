import java.io.FileOutputStream;
import java.sql.Connection;
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
 * @author 华锋 Nov 27, 201010:36:58 PM
 * 
 */
public class NewCustomerGenerate {

	private static String user = "jds";
	private static String password = "jds";
	private static String url = "jdbc:oracle:thin:@localhost:1521:ora10";
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

	static class Customer {
		public int id;
		public String idcard;
		public String username;
		public int createsrc;
		public int createsrcid;
	}

	private static Connection con = null;

	public static void updateUsraddressNew() throws Exception {
		Connection con = null;
		try {
			con = getConn();
			con.setAutoCommit(false);
			// String sql="select distinct oprflag,oprid from tusr_address";
			// java.sql.Statement stmt = con.createStatement();
			// ResultSet rs = stmt.executeQuery(sql);
			// List addresses=new ArrayList();
			// while(rs.next()){
			// addresses.add(rs.getInt("oprflag")+","+rs.getInt("oprid"));
			// }
			// rs.close();
			// stmt.close();

			String sql = "select distinct servicetype,serviceid,customerid from tusr_customer_service";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List customerservices = new ArrayList();
			while (rs.next()) {
				// addresses.add(rs.getInt("oprflag")+","+rs.getInt("oprid"));
				int customerid = rs.getInt("customerid");
				int serviceid = rs.getInt("serviceid");
				int servicetype = rs.getInt("servicetype");
				String updsql = "update tusr_address set customerid=" + customerid + " where oprflag=" + servicetype
						+ " and oprid=" + serviceid;
				customerservices.add(updsql);
			}
			rs.close();
			stmt.close();

			executeBatch(con, customerservices);
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		}
		con.close();
	}
	
	/**
	 * 更新tusraddress里的customerid
	 * 
	 * @throws Exception
	 */
	public static void updateUsraddress() throws Exception {
		Connection con = null;
		try {
			con = getConn();
			con.setAutoCommit(false);
			// String sql="select distinct oprflag,oprid from tusr_address";
			// java.sql.Statement stmt = con.createStatement();
			// ResultSet rs = stmt.executeQuery(sql);
			// List addresses=new ArrayList();
			// while(rs.next()){
			// addresses.add(rs.getInt("oprflag")+","+rs.getInt("oprid"));
			// }
			// rs.close();
			// stmt.close();

			String sql = "select distinct servicetype,serviceid,customerid from tusr_customer_service";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List customerservices = new ArrayList();
			while (rs.next()) {
				// addresses.add(rs.getInt("oprflag")+","+rs.getInt("oprid"));
				int customerid = rs.getInt("customerid");
				int serviceid = rs.getInt("serviceid");
				int servicetype = rs.getInt("servicetype");
				String updsql = "update tusr_address set customerid=" + customerid + " where oprflag=" + servicetype
						+ " and oprid=" + serviceid;
				customerservices.add(updsql);
			}
			rs.close();
			stmt.close();

			executeBatch(con, customerservices);
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		}
		con.close();
	}
private static String int2str(int i){
	if(i<1000)
		return "0"+i;
	if(i<100)
		return "00"+i;
	if(i<10)
		return "000"+i;
	return ""+i;
}
	/**
	 * 更新tusraddress里的customerid
	 * 
	 * @throws Exception
	 */
	public static void updateJiejuhao() throws Exception {
		Connection con = null;
		try {
			con = getConn();
			con.setAutoCommit(false);
			// String sql="select distinct oprflag,oprid from tusr_address";
			// java.sql.Statement stmt = con.createStatement();
			// ResultSet rs = stmt.executeQuery(sql);
			// List addresses=new ArrayList();
			// while(rs.next()){
			// addresses.add(rs.getInt("oprflag")+","+rs.getInt("oprid"));
			// }
			// rs.close();
			// stmt.close();

			String sql = "select nonlawid,bankid from tnlw_nonlaw where duebill is null ";
			int icbc=1;
			int ccb=1;
			String icbcj="B_ICBC";
			String ccbj="B_CCB";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List customerservices = new ArrayList();
			int i=1;
			while (rs.next()) {
				// addresses.add(rs.getInt("oprflag")+","+rs.getInt("oprid"));
				int nonlawid = rs.getInt("nonlawid");
				int bankid = rs.getInt("bankid");
				String s=int2str(i++);
				if(bankid==1){
					s=ccbj+s;
				}else{
					s=icbcj+s;
				}
				
				String updsql = "update tnlw_nonlaw set duebill='" + s + "' where nonlawid=" + nonlawid;
				System.out.println(updsql);
				customerservices.add(updsql);
			}
			rs.close();
			stmt.close();

			executeBatch(con, customerservices);
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		}
		con.close();
	}
	
	public static void main(String[] args) throws Exception {
//		 System.out.println("开始处理数据");
//		 generateCustomers();
//		 System.out.println("客户数据新增成功");
		 updateUsraddress();
		 System.out.println("地址数据修改成功");
//		updateOvernum();
//		System.out.println("更新非诉的逾期期数成功");
//		updateJiejuhao();
//		System.out.println("更新非诉的借据号成功");
//		updateOverStat();
//		System.out.println("更新非诉的当前逾期期数成功");
	}
	
	public static void updateOverStat() throws Exception {
		Connection con = null;
		try {
			con = getConn();

			String sql = "select nonlawid,curoverstat from tnlw_nonlaw ";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Map<String, String> maps = new HashMap<String, String>();// 存储最开始的那个customerid
			// List existkey=new ArrayList();
			java.io.PrintWriter log = new java.io.PrintWriter(new FileOutputStream("d:/nonlaw.sql", true), true);
			while (rs.next()) {
				int nonlawid = rs.getInt("nonlawid");
				String curoverstat = rs.getString("curoverstat");
				try {
					log.println("update tnlw_nonlaw set curoverstat=" + Float.parseFloat(curoverstat) + " where nonlawid="
							+ nonlawid + ";");
				} catch (Exception e) {
					e.printStackTrace();
					log.println("update tnlw_nonlaw set curoverstat=0 where nonlawid=" + nonlawid + ";");

				}

			}
			log.flush();
			log.close();

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		}
		con.close();
	}


	public static void updateOvernum() throws Exception {
		Connection con = null;
		try {
			con = getConn();

			String sql = "select nonlawid,overnum from tnlw_nonlaw ";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Map<String, String> maps = new HashMap<String, String>();// 存储最开始的那个customerid
			// List existkey=new ArrayList();
			java.io.PrintWriter log = new java.io.PrintWriter(new FileOutputStream("d:/nonlaw.sql", true), true);
			while (rs.next()) {
				int nonlawid = rs.getInt("nonlawid");
				String overnum = rs.getString("overnum");
				try {
					log.println("update tnlw_nonlaw set overnum=" + Integer.parseInt(overnum) + " where nonlawid="
							+ nonlawid + ";");
				} catch (Exception e) {
					log.println("update tnlw_nonlaw set overnum=0 where nonlawid=" + nonlawid + ";");

				}

			}
			log.flush();
			log.close();

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		}
		con.close();
	}

	public static void generateCustomers() throws Exception {
		Connection con = null;
		try {
			con = getConn();
			con.setAutoCommit(false);
			// String sql="select username,idcard from tusr_customer_new group
			// by username,idcard having count(*)>=2";
			// String sql="select * from tusr_customer_new where idcard is null
			// order by username,idcard ";
			String sql = "select a.* from tusr_customer_new a inner join (select username,idcard from tusr_customer_new group by username,idcard having count(*)>=2) b on a.username=b.username and a.idcard=b.idcard  order by a.username,a.idcard ";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String temp = "";
			List deletecustomersql = new ArrayList(); // 删除掉后面的customerid
			List updatecustomerservicesql = new ArrayList(); // 更新tusr_customer_service里的customerid
			Map<String, String> maps = new HashMap<String, String>();// 存储最开始的那个customerid
			// List existkey=new ArrayList();
			while (rs.next()) {
				int customerid = rs.getInt("customerid");
				String username = rs.getString("username");
				String idcard = rs.getString("idcard");
				int createsrc = rs.getInt("createsrc");
				int createsrcid = rs.getInt("createsrcid");
				String key = username + "," + idcard;
				String customerids = "";
				if (maps.containsKey(key)) {
					String firstid = maps.get(key);
					// 删掉后面的
					String delsql = "delete from tusr_customer_new where customerid=" + customerid;
					// 更新后面的customerid关联到第一个customerid
					String updsql = "update tusr_customer_service set customerid=" + firstid + " where customerid="
							+ customerid;

					// System.out.println(delsql);
					// System.out.println(updsql);
					deletecustomersql.add(delsql);
					updatecustomerservicesql.add(updsql);
				} else {
					maps.put(key, customerid + "");
				}
			}

			rs.close();
			stmt.close();

			// 先更新
			executeBatch(con, updatecustomerservicesql);
			// 后删除
			executeBatch(con, deletecustomersql);
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
			con.rollback();
		}
		con.close();

	}

	private static void executeBatch(Connection con, List sqls) throws Exception {
		if (sqls == null || sqls.size() == 0)
			return;
		Statement stmt = con.createStatement();
		int count = 300;
		for (int i = 0; i < sqls.size(); i++) {
			String sql = sqls.get(i).toString();
			System.out.println("====>" + sql);
			// stmt.execute(sql);
			stmt.addBatch(sql);
			if ((i + 1) % count == 0) {
				stmt.executeBatch();
				stmt.clearBatch();
			}
		}
		stmt.executeBatch();
		stmt.close();

	}

}
