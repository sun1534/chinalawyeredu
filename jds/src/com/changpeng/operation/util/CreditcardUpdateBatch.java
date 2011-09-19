package com.changpeng.operation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;

import com.changpeng.address.model.TusrAddress;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.operation.model.ToprCreditcard;

/**
 * 信用卡信息的批量更新
 * 
 * @author sinhoo Jul 18, 2009
 */
public class CreditcardUpdateBatch {
	/**
	 * 获取EXCEL单元格中的值
	 * 
	 * @param cell
	 * @return
	 */
	private static String cellVal(HSSFCell cell) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
		String value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = df.format(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			default:
			}
		}
		if (value != null)
			value = value.replaceAll(" ", "");
		return value;
	}

	/**
	 * 解析EXCEL文件
	 * 
	 * @param excel
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static List<ToprCreditcard> parseXls(File excel) throws IOException {
		FileInputStream stream = null;
		List<ToprCreditcard> cardList = null;
		try {
			cardList = new ArrayList<ToprCreditcard>();
			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			// 第一行为表头 不用解析
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {
					if (cellVal(row.getCell((short) 1)) == null || "".equals(cellVal(row.getCell((short) 1))))
						continue;

					ToprCreditcard card = new ToprCreditcard();
					card.setUsername(cellVal(row.getCell((short) 0)));
					card.setCreditcard(cellVal(row.getCell((short) 1)));
					card.setIdcard(cellVal(row.getCell((short) 2)));

					card.setCurcnfee(cellVal(row.getCell((short) 3)));
					card.setCurusafee(cellVal(row.getCell((short) 4)));
					card.setCurhkfee(cellVal(row.getCell((short) 5)));
					card.setCureurfee(cellVal(row.getCell((short) 6)));

					card.setOverstat(cellVal(row.getCell((short) 7)));
					card.setMaxfee(cellVal(row.getCell((short) 8)));

					String cell9Val = cellVal(row.getCell((short) 9));
					if (cell9Val != null && !"".equals(cell9Val))
						card.setKaihudata(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short) 9)
								.getDateCellValue()));
					card.setGuiyuanno(cellVal(row.getCell((short) 10)));

					card.setApplynum(cellVal(row.getCell((short) 11)));
					card.setDossiernum(cellVal(row.getCell((short) 12)));
					card.setBianhao(card.getDossiernum());

					// 以下信息为模板新增
					card.setMobileold(cellVal(row.getCell((short) 13))); // 手机号码
					card.setHomephoneold(cellVal(row.getCell((short) 14))); // 家庭电话
					card.setWorkphoneold(cellVal(row.getCell((short) 15))); // 单位电话
					card.setCompaddr(cellVal(row.getCell((short) 16))); // 单位地址
					card.setCompany(cellVal(row.getCell((short) 17))); // 单位名称
					card.setHomeaddr(cellVal(row.getCell((short) 18))); // 家庭地址

					// 指定退单日期
					String cell19Val = cellVal(row.getCell((short) 19));
					if (cell19Val != null && !"".equals(cell19Val))
						card.setTddate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short) 19)
								.getDateCellValue()));

					// String bianhao=cellVal(row.getCell((short)20));
					// if(bianhao!=null&&!"".equals(bianhao))
					// card.setBianhao(bianhao);

					cardList.add(card);

				}// end if judge row
			}// end for row
		} finally {
			if (stream != null)
				stream.close();
		}
		return cardList;
	}

	/**
	 * 从excel中获取信用卡记录，并更新数据库中已有相关信息
	 * 
	 * @param excel
	 * @param consigndate
	 * @param bankid
	 * @param session
	 * @throws IOException
	 */
	public static int update(File excel, long bankid, long userid) throws Exception {

		// HashMap<String,ToprCreditcard>
		// creditcardMap=OperationUtil.cardOfBank(bankid, session);
		int count = 0;
		Connection con = null;

		try {
			List<ToprCreditcard> cardList = parseXls(excel);
			con = OperationUtil.globals.getCon();

			for (ToprCreditcard card : cardList) {
				long creditcardid = findOprid(con, card);

				if (creditcardid != 0) {

					List list = new ArrayList();
					// 这里还要拿到customerid
					int customerid = NewCustomerUtil.getCustomerByService(con, (int) creditcardid, 1);

					count++;
					long opuserid = findUserid(con, card);
					if (opuserid == 0)
						opuserid = userid;
					if (card.getMobileold() != null && !"".equals(card.getMobileold())) {
						TusrAddress p = new TusrAddress();
						p.setOprid(creditcardid);
						p.setUsername(card.getUsername() == null ? "" : card.getUsername());
						p.setHomeaddr(card.getHomeaddr() == null ? "" : card.getHomeaddr());
						p.setCompany(card.getCompany());
						p.setOprflag(1); // 信用卡业务
						p.setComments("手机");
						p.setPhone(CreditcardCreateBatch.phone(card.getMobileold() == null ? "" : card.getMobileold()));
						p.setCreatetime(new java.util.Date());
						p.setCustomerid(customerid);

						String md5val = com.sxit.common.util.md5.MD5(p.getUsername() + p.getPhone() + p.getHomeaddr()
								+ p.getCustomerid());
						p.setMd5val(md5val);
						if (!list.contains(md5val)) {
							list.add(md5val);
							if (getUsrAddress(con, md5val) == 0)
								saveAddress(con, p, opuserid);
						}
					}
					if (card.getWorkphoneold() != null && !"".equals(card.getWorkphoneold())) {
						TusrAddress p = new TusrAddress();
						p.setOprid(creditcardid);
						p.setUsername(card.getUsername() == null ? "" : card.getUsername());
						p.setHomeaddr(card.getHomeaddr() == null ? "" : card.getHomeaddr());
						p.setCompany(card.getCompany());
						p.setOprflag(1); // 信用卡业务
						p.setComments("工作电话");
						p.setPhone(CreditcardCreateBatch.phone(card.getWorkphoneold() == null ? "" : card
								.getWorkphoneold()));
						p.setCreatetime(new java.util.Date());
						p.setCustomerid(customerid);
						String md5val = com.sxit.common.util.md5.MD5(p.getUsername() + p.getPhone() + p.getHomeaddr()
								+ p.getCustomerid());
						p.setMd5val(md5val);
						if (!list.contains(md5val)) {
							list.add(md5val);
							if (getUsrAddress(con, md5val) == 0)
								saveAddress(con, p, opuserid);
						}
					}
					if (card.getHomephoneold() != null && !"".equals(card.getHomephoneold())) {
						TusrAddress p = new TusrAddress();
						p.setOprid(creditcardid);
						p.setUsername(card.getUsername() == null ? "" : card.getUsername());
						p.setHomeaddr(card.getHomeaddr() == null ? "" : card.getHomeaddr());
						p.setCompany(card.getCompany());
						p.setOprflag(1); // 信用卡业务
						p.setComments("住宅电话");
						p.setPhone(CreditcardCreateBatch.phone(card.getHomephoneold() == null ? "" : card
								.getHomephoneold()));
						p.setCreatetime(new java.util.Date());
						p.setCustomerid(customerid);

						String md5val = com.sxit.common.util.md5.MD5(p.getUsername() + p.getPhone() + p.getHomeaddr()
								+ p.getCustomerid());
						p.setMd5val(md5val);

						if (!list.contains(md5val)) {
							list.add(md5val);
							if (getUsrAddress(con, md5val) == 0)
								saveAddress(con, p, opuserid);
						}
					}
					updateCredit(con, card);
				}

			}
		} finally {
			if (con != null)
				con.close();
		}
		return count;
	}

	private static long findUserid(Connection con, ToprCreditcard card) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long userid = 0;
		try {
			String sql = "select userid from Tusr_Address where oprflag=1 and oprid=" + card.getCreditcardid();

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				userid = rs.getLong(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return userid;
	}

	private static long findOprid(Connection con, ToprCreditcard card) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long oprid = 0;
		try {
			String sql = "select creditcardid from Topr_creditcard where creditcard='" + card.getCreditcard() + "'";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				oprid = rs.getLong(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return oprid;
	}

	private static void updateCredit(Connection con, ToprCreditcard card) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "update Topr_Creditcard set idcard=?,Curcnfee=?,Curusafee=?,Curhkfee=?,Cureurfee=?,Guiyuanno=?,Kaihudata=?,"
					+ "Applynum=?,Dossiernum=?,Mobileold=?,Workphoneold=?,Homephoneold=?,Homeaddr=?,Company=?,Compaddr=?,Updatetime=sysdate,tddate=? where creditcard=?";

			pstmt = con.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, card.getIdcard());
			pstmt.setString(i++, card.getCurcnfee());
			pstmt.setString(i++, card.getCurusafee());
			pstmt.setString(i++, card.getCurhkfee());
			pstmt.setString(i++, card.getCureurfee());
			pstmt.setString(i++, card.getGuiyuanno());
			pstmt.setString(i++, card.getKaihudata());
			pstmt.setString(i++, card.getApplynum());
			pstmt.setString(i++, card.getDossiernum());
			pstmt.setString(i++, card.getMobileold());
			pstmt.setString(i++, card.getWorkphoneold());
			pstmt.setString(i++, card.getHomephoneold());
			pstmt.setString(i++, card.getHomeaddr());
			pstmt.setString(i++, card.getCompany());
			pstmt.setString(i++, card.getCompaddr());
			pstmt.setString(i++, card.getTddate());
			pstmt.setString(i++, card.getCreditcard());
			pstmt.execute();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	private static void saveAddress(Connection con, TusrAddress address, long userid) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into Tusr_Address (addressid,userid,createtime,username,phone,homeaddr,company,comments,oprflag,oprid) values "
					+ "(TusrAddressid.nextval," + userid + ",sysdate,?,?,?,?,?,1," + address.getOprid() + ")";

			pstmt = con.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, address.getUsername());
			pstmt.setString(i++, address.getPhone());
			pstmt.setString(i++, address.getHomeaddr());
			pstmt.setString(i++, address.getCompany());
			pstmt.setString(i++, address.getComments());
			pstmt.execute();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	public static int getUsrAddress(Session con, String md5val) {
		try {
			String sql = "from TusrAddress where md5val='" + md5val + "'";
			List list = con.createQuery(sql).list();
			int len = list == null ? 0 : list.size();
			return len;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getUsrAddress(Connection con, String md5val) throws SQLException {
		String sql = "select count(*) as cnt from tusr_address where md5val=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int s = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, md5val);
			rs = pstmt.executeQuery();
			if (rs.next())
				s = rs.getInt("cnt");
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return s;
	}

}
