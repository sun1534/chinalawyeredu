package com.changpeng.operation.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import com.changpeng.common.Globals;
import com.changpeng.operation.model.ToprBank;
import com.changpeng.operation.model.ToprCreditcard;

@SuppressWarnings("unchecked")
public class OperationUtil {
	public static HashMap<String, String> consigntypeMap;
	public static HashMap<String, String> consignflagMap;
	// public static List<ToprBank> listBank; //委托银行
	// public static HashMap<String,String> bankMap;
	public static Globals globals = Globals.getInstance();
	static {
		consigntypeMap = new HashMap<String, String>();
		consigntypeMap.put("1", "3000-5000");
		consigntypeMap.put("4", "3000以下");
		consigntypeMap.put("2", "10000以上");
		consigntypeMap.put("3", "5000-10000");

		consignflagMap = new HashMap<String, String>();
		consignflagMap.put("1", "90天内");
		consignflagMap.put("2", "90-180天");
		consignflagMap.put("3", "181-270天");
		consignflagMap.put("4", "366-730天");
		consignflagMap.put("5", "731天以上");
		consignflagMap.put("6", "271-365天");

		/*
		 * listBank=com.changpeng.common.Globals.getInstance().findAll(" from
		 * ToprBank"); bankMap=new HashMap<String,String>();
		 * System.out.println(".................."); for(ToprBank
		 * bank:listBank){ bankMap.put(bank.getBankid()+"", bank.getBankname()); }
		 */
	}

	public static List<ToprBank> listBank() {
		// return com.changpeng.common.Globals.getInstance().findAll(" from
		// ToprBank");
		return com.changpeng.common.CommonDatas.bankList;
	}

	public static HashMap<String, String> bankMap() {
		/*
		 * List<ToprBank>
		 * listBank=com.changpeng.common.Globals.getInstance().findAll(" from
		 * ToprBank"); HashMap<String,String> bankMap=new HashMap<String,String>();
		 * for(ToprBank bank:listBank){ bankMap.put(bank.getBankid()+"",
		 * bank.getBankname()); } return bankMap;
		 */
		return com.changpeng.common.CommonDatas.bankMap;
	}

	public static ToprCreditcard getExistCredit(String creditcard) {
		String q = "from ToprCreditcard where creditcard='" + creditcard + "'";
		List list = com.changpeng.common.Globals.getInstance().findAll(q);
		int len = list == null ? 0 : list.size();
		if (len >= 1)
			return (ToprCreditcard) list.get(0);
		return null;
	}

//	/**
//	 * 
//	 * @return
//	 */
//	public static HashMap<String, ToprCreditcard> creditcardMap() {
//		HashMap<String, ToprCreditcard> creditcardMap = new HashMap<String, ToprCreditcard>();
//		List<ToprCreditcard> listCreditcard = com.changpeng.common.Globals.getInstance()
//				.findAll(" from ToprCreditcard");
//		for (ToprCreditcard card : listCreditcard) {
//			creditcardMap.put(card.getCreditcard(), card); // 账号唯一
//		}
//		return creditcardMap;
//	}

	/**
	 * 选择催收记录及人员进行任务分配
	 * 
	 * @param userid
	 *            用户ID
	 * @param check
	 *            催收记录
	 * @throws SQLException
	 */
	public static void consignTask(long userid, long[] check) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = globals.getCon();
			stmt = con.createStatement();
			for (long creditcardid : check) {
				updateTask(con, creditcardid);
				// 插入用户任务表
				stmt
						.addBatch("insert into topr_credittask (credittaskid,creditcardid,userid,createtime,taskstat) values (ToprCredittaskid.nextval,"
								+ creditcardid + "," + userid + ",sysdate,0)");
				// 更新记录状态
				stmt.addBatch("update topr_creditcard set state=1 where creditcardid=" + creditcardid + "");
				// 将对应的客户信息记录同步到该用户通讯录
				stmt.addBatch("update tusr_address set userid=" + userid + " where oprid=" + creditcardid
						+ " and oprflag=1");

				// 将对应的日志记录更改到新接单人
				stmt
						.addBatch("update topr_creditlog set userid=" + userid + " where creditcardid=" + creditcardid
								+ "");

			}
			stmt.executeBatch();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}
	}

	// 将原有任务状态更新为被重新分配
	private static void updateTask(Connection con, long creditcardid) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update topr_credittask set taskstat=1 where creditcardid=" + creditcardid);
			stmt.execute();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/**
	 * 获取指定银行下的信用卡记录，且当前还款状态为非全清（不等于2）的 信用卡账号作为key
	 * 
	 * @param bankid
	 * @param session
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, ToprCreditcard> cardOfBank(long bankid, org.hibernate.Session session) {
		HashMap<String, ToprCreditcard> map = new HashMap<String, ToprCreditcard>();
		List<ToprCreditcard> list = session.createQuery(
				" from ToprCreditcard where repaystatus<>2 and bankid=" + bankid).list();
		for (ToprCreditcard card : list) {
			map.put(card.getCreditcard(), card);
		}
		return map;
	}

	public static String taskuser(long creditcardid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String taskuser = "";
		try {
			con = globals.getCon();
			pstmt = con
					.prepareStatement("select a.username from tsys_user a,Topr_Credittask b where a.userid=b.userid and b.taskstat=0 and CREDITCARDID=?");
			pstmt.setLong(1, creditcardid);
			rs = pstmt.executeQuery();
			if (rs.next())
				taskuser = rs.getString(1);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}
		return taskuser;
	}

	public static String showDate(String logtime) {
		if (logtime != null && !"".equals(logtime))
			logtime = logtime.replaceAll("-", "").substring(0, 8);
		return logtime;
	}
}
