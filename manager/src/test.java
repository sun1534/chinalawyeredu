import java.sql.Connection;
import java.sql.Statement;

import com.changpeng.common.sync.DBUtils;
import java.sql.ResultSet;

/**
 * test.java */


/**
 * @author 华锋 2008-5-5 下午01:55:04
 * 
 */
public class test {
	private static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		java.io.PrintWriter logerror = new java.io.PrintWriter(new java.io.FileOutputStream("c:\\a.sql", true), true);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String sql="select  GRADUATE_SCHOOL,CERTIFICATE_NO,OFFICE_TELEPHONE,SPECIALITY,BUS_SPECIALITY1 from t_person_info where data_type='inserted'";
		Connection con=DBUtils.getOracleCon();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()){
			String school=rs.getString("GRADUATE_SCHOOL")==null?"":rs.getString("GRADUATE_SCHOOL");
				String phone=rs.getString("OFFICE_TELEPHONE")==null?"":rs.getString("OFFICE_TELEPHONE");
				String spe=rs.getString("SPECIALITY")==null?"":rs.getString("SPECIALITY");
				String memo=rs.getString("BUS_SPECIALITY1")==null?"":rs.getString("BUS_SPECIALITY1");
			String s="update trususer set University='"+school+"',Phoneno='"+phone+"',Speciality='"+spe+"',Memo='"+memo+"' where licenceno='"+rs.getString("CERTIFICATE_NO")+"';";
			System.out.println(s);
			logerror.println(s);
		}
		logerror.flush();
		logerror.close();
	}

}
