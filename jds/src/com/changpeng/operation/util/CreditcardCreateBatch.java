package com.changpeng.operation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.changpeng.address.model.TusrAddress;
import com.changpeng.customer.model.TusrCustomerNew;
import com.changpeng.customer.model.TusrCustomerService;
import com.changpeng.customer.util.NewCustomerUtil;
import com.changpeng.lawcase.util.NumberUtil;
import com.changpeng.operation.model.ToprCreditcard;
import com.opensymphony.xwork2.ActionContext;
import com.sxit.common.HSSFCellToString;
import com.sxit.system.model.TsysUser;

/**
 * 催收记录的批量导入
 * 
 * @author sinhoo Jun 10, 2009
 */
public class CreditcardCreateBatch {
	/**
	 * 获取EXCEL单元格中的值
	 * 
	 * @param cell
	 * @return
	 */
	private static String cellVal(HSSFCell cell) {
		// java.text.DecimalFormat df = new
		// java.text.DecimalFormat("####.####");
		// String value = null;
		// if(cell!=null){
		// switch (cell.getCellType()) {
		// case HSSFCell.CELL_TYPE_NUMERIC:
		// value = df.format(cell.getNumericCellValue());
		// break;
		// case HSSFCell.CELL_TYPE_STRING:
		// value = cell.getStringCellValue();
		// break;
		// default:
		// }
		// }
		// if(value!=null) value=value.replaceAll(" ", "");
		// return value;
		return HSSFCellToString.toString(cell);
	}

	/**
	 * 解析EXCEL文件
	 * 
	 * @param excel
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static List<ToprCreditcard> parseXls(File excel, List errorlist) throws IOException {
		FileInputStream stream = null;
		List<ToprCreditcard> cardList = null;
		try {
			cardList = new ArrayList<ToprCreditcard>();
			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			// 第一行为表头 不用解析
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				// java.text.DecimalFormat df = new
				// java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {

					// short cellnum=row.getLastCellNum(); //最大列数
					// 第一列为客户姓名，第二列为帐单号，不允许为空 且列数不得小于39列
					if (/* cellnum<27|| */cellVal(row.getCell(0)) == null || cellVal(row.getCell(1)) == null
							|| cellVal(row.getCell(0)).equals("") || cellVal(row.getCell(1)).equals("")) {
						// System.out.println(i+":::::::::"+cellnum);
						continue;
					} else {
						ToprCreditcard card = new ToprCreditcard();
						card.setUsername(cellVal(row.getCell(0)));// 姓名
						card.setCreditcard(cellVal(row.getCell(1)));// 帐号
						String msg = "第" + (i + 1) + "行（姓名："+card.getUsername()+"）数据错误:";
						boolean iserror = false;
						// System.out.println(i+"========="+cellnum);
					
						
						card.setIdcard(cellVal(row.getCell(2))); // 身份证号
						card.setCnfee(cellVal(row.getCell(3)).replace(",", ""));
						card.setUsafee(cellVal(row.getCell(4)).replace(",", ""));
						card.setHkfee(cellVal(row.getCell(5)).replace(",", ""));
						card.setEurfee(cellVal(row.getCell(6)).replace(",", ""));

						// 当前所欠费用,这里要判断费用是否都正确
						if (card.getCnfee() != null && !card.getCnfee().equals("")
								&& !NumberUtil.isMoney(card.getCnfee())) {
							msg += "人民币透支金额不正确:" + card.getCnfee();
							iserror = true;
						}
						if (card.getUsafee() != null && !card.getUsafee().equals("")
								&& !NumberUtil.isMoney(card.getUsafee())) {
							msg += "，美元透支金额不正确:" + card.getUsafee();
							iserror = true;
						}
						if (card.getHkfee() != null && !card.getHkfee().equals("")
								&& !NumberUtil.isMoney(card.getHkfee())) {
							msg += "，港币透支金额不正确:" + card.getHkfee();
							iserror = true;
						}
						if (card.getEurfee() != null && !card.getEurfee().equals("")
								&& !NumberUtil.isMoney(card.getEurfee())) {
							msg += "，欧元透支金额不正确:" + card.getEurfee();
							iserror = true;
						}

						if (iserror) {
							errorlist.add(msg);
							continue;
						}

						card.setOverstat(cellVal(row.getCell(7)));
						card.setMaxfee(cellVal(row.getCell(8)).replace(",", ""));

						String cell9Val = cellVal(row.getCell(9));
						if (cell9Val != null && !"".equals(cell9Val)) {
							try {
								card.setKaihudata(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell(9)
										.getDateCellValue()));
							} catch (Exception e) {
								card.setKaihudata(cell9Val);
							}

						}
						card.setGuiyuanno(cellVal(row.getCell(10)));

						card.setConsigntype(cellVal(row.getCell(11)));
						card.setConsignflag(cellVal(row.getCell(12)));
						card.setApplynum(cellVal(row.getCell(13)));
						card.setDossiernum(cellVal(row.getCell(14)));
						card.setBianhao(cellVal(row.getCell(14)));
						card.setMobileold(cellVal(row.getCell(15)));
						card.setHomephoneold(cellVal(row.getCell(16)));
						card.setWorkphoneold(cellVal(row.getCell(17)));
						card.setCompany(cellVal(row.getCell(18)));
						card.setCompaddr(cellVal(row.getCell(19)));
						card.setHomeaddr(cellVal(row.getCell(20)));
						card.setIdcardaddr(cellVal(row.getCell(21)));
						card.setBillpostcode(cellVal(row.getCell(22)));
						card.setBilladdr(cellVal(row.getCell(23)));
						card.setBillpostaddr(cellVal(row.getCell(24)));
						card.setEmail(cellVal(row.getCell(25)));
						card.setContactpeople1(cellVal(row.getCell(26)));
						card.setContactp1phone1(cellVal(row.getCell(27)));
						card.setContactp1phone2(cellVal(row.getCell(28)));
						card.setContactp1phone3(cellVal(row.getCell(29)));
						card.setContactpeople2(cellVal(row.getCell(30)));
						card.setContactp2phone1(cellVal(row.getCell(31)));
						card.setContactp2phone2(cellVal(row.getCell(32)));
						card.setContactp2phone3(cellVal(row.getCell(33)));
						card.setCautioner(cellVal(row.getCell(34)));
						card.setCaucompany(cellVal(row.getCell(35)));
						card.setCaucompaddr(cellVal(row.getCell(36)));
						card.setCauhomeaddr(cellVal(row.getCell(37)));
						card.setCauworkphone(cellVal(row.getCell(38)));
						card.setCauhomephone(cellVal(row.getCell(39)));
						card.setCaumobile(cellVal(row.getCell(40)));

						// 指定退单日期
						String cell41Val = cellVal(row.getCell(41));
						if (cell41Val != null && !"".equals(cell41Val))
							card.setTddate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell(41)
									.getDateCellValue()));

						// String bianhao=cellVal(row.getCell(42));
						// card.setBianhao(bianhao);

						cardList.add(card);
					}
				}// end if judge row
			}// end for row
		} finally {
			if (stream != null)
				stream.close();
		}
		return cardList;
	}

	/**
	 * 将EXCEL中催收记录插入数据库，如果账号重复，做覆盖操作。
	 * 
	 * @param excel
	 * @param consigndate
	 * @param bankid
	 * @param session
	 * @throws IOException
	 */
	public static List<ToprCreditcard> save(File excel, String consigndate, long bankid, org.hibernate.Session session,
			List existCustomerList, List errorlist) throws IOException {
		List<ToprCreditcard> cardList = parseXls(excel, errorlist);
		if(errorlist.size()!=0){
			return new ArrayList();
		}
//		HashMap<String, ToprCreditcard> creditcardMap = OperationUtil.creditcardMap();
		List<ToprCreditcard> reCard = new ArrayList<ToprCreditcard>();
		// NewCustomerUtil customerutil=new NewCustomerUtil();
		TsysUser sysUser = (TsysUser) ActionContext.getContext().getSession().get("curuser");
		for (ToprCreditcard card : cardList) {

			card.setConsigndate(consigndate);
			card.setBankid(bankid);
			card.setCreatetime(new java.util.Date());

			card.setRefee("0"); // 总还0
			card.setRemonthfee("0"); // 当月已还0
			card.setRepaystatus(1); // 部分还款
			card.setLawflag(0); // 未转诉讼

			card.setCurcnfee(card.getCnfee());
			card.setCurusafee(card.getUsafee());
			card.setCurhkfee(card.getHkfee());
			card.setCureurfee(card.getEurfee());
			card.setState(0);

			card.setTdflag(0); // 退单标记
			ToprCreditcard exist=OperationUtil.getExistCredit(card.getCreditcard());
			if(exist!=null){
//			if (creditcardMap.containsKey(card.getCreditcard())) { // 账户重复，覆盖操作。
				card.setUpdatetime(new java.util.Date());
//				card.setCreditcardid(creditcardMap.get(card.getCreditcard()).getCreditcardid());
				card.setCreditcardid(exist.getCreditcardid());
				// session.update(card);
				reCard.add(card);
			} else { // 新增操作

				session.save(card);

				int cardid = (int) card.getCreditcardid();

				TusrCustomerNew customer = NewCustomerUtil.getCustomer(session, card.getUsername(), card.getIdcard());
				int customerid = 0;
				if (customer != null) {
					customerid = customer.getCustomerid();
					existCustomerList.add(customer);
				} else {// 新增一个用户,同时将这个要加到那个service里面去
					customer = new TusrCustomerNew();
					// insert into tusr_customer_new(customerid,
					// username,idcard,mobile1,homephone,compphone,company,compaddr,homeaddr,idcardaddr,compemail,personalemail,createsrc,createsrcid,createtime)select
					// tusrcustomerid.nextval,
					// username,idcard,mobileold,homephoneold,workphoneold,company,compaddr,homeaddr,idcardaddr,email,email,1
					// as createsrc,creditcardid,createtime from
					// topr_creditcard;
					customer.setUsername(card.getUsername());
					customer.setIdcard(card.getIdcard());
					customer.setMobile1(card.getMobileold());
					customer.setHomephone(card.getHomephoneold());
					customer.setCompphone(card.getWorkphoneold());
					customer.setCompany(card.getCompany());
					customer.setCompaddr(card.getCompaddr());
					customer.setHomeaddr(card.getHomeaddr());
					customer.setIdcardaddr(card.getIdcardaddr());
					customer.setCompemail(card.getEmail());
					customer.setPersonalemail(card.getEmail());
					customer.setCreatesrc(1);
					customer.setCreatesrcid(cardid);
					customer.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
					customer.setCreateuser((int) sysUser.getUserid());
					customer.setCustomerflag(2);// 1:VIP 2:一般
					customer.setCustomertype(3);// 1:机构客户 2:个人客户 3当事人客户

					session.save(customer);
					customerid = customer.getCustomerid();
				}

				TusrCustomerService service = new TusrCustomerService();
				service.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				service.setCreateuser(sysUser.getUsername());
				service.setCreateuserid((int) sysUser.getUserid());
				service.setServiceid((int) cardid);
				service.setServicetype(1);
				service.setTusrCustomerNew(customer);
				service.setRemarks("");
				session.save(service);

				// 保存住宅电话
				if (card.getHomephoneold() != null && !"".equals(card.getHomephoneold().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("住宅电话");
					p.setPhone(phone(card.getHomephoneold()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				// 保存手机
				if (card.getMobileold() != null && !"".equals(card.getMobileold().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("手机");
					p.setPhone(phone(card.getMobileold()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				// 保存工作电话
				if (card.getWorkphoneold() != null && !"".equals(card.getWorkphoneold().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("工作电话");
					p.setPhone(phone(card.getWorkphoneold()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}

				// 保存联系人
				if (card.getContactp1phone1() != null && !"".equals(card.getContactp1phone1().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("联系人【" + card.getContactpeople1() + "】");
					p.setPhone(phone(card.getContactp1phone1()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				if (card.getContactp1phone2() != null && !"".equals(card.getContactp1phone2().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("联系人【" + card.getContactpeople1() + "】");
					p.setPhone(phone(card.getContactp1phone2()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				if (card.getContactp1phone3() != null && !"".equals(card.getContactp1phone3().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("联系人【" + card.getContactpeople1() + "】");
					p.setPhone(phone(card.getContactp1phone3()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				if (card.getContactp2phone1() != null && !"".equals(card.getContactp2phone1().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("联系人【" + card.getContactpeople2() + "】");
					p.setPhone(phone(card.getContactp2phone1()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				if (card.getContactp2phone2() != null && !"".equals(card.getContactp2phone2().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("联系人【" + card.getContactpeople2() + "】");
					p.setPhone(phone(card.getContactp2phone2()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
				if (card.getContactp2phone3() != null && !"".equals(card.getContactp2phone3().trim())) {
					TusrAddress p = new TusrAddress();
					p.setUsername(card.getUsername());
					p.setHomeaddr(card.getHomeaddr());
					p.setCompany(card.getCompany());
					p.setOprid(card.getCreditcardid());
					p.setOprflag(1); // 信用卡业务

					p.setComments("联系人【" + card.getContactpeople2() + "】");
					p.setPhone(phone(card.getContactp2phone3()));
					p.setCreatetime(new java.util.Date());
					p.setCustomerid(customerid);
					session.save(p);
				}
			}
		}
		session.flush();
		return reCard;
	}

	public static String phone(String str) {
		if (str != null)
			str = str.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(
					"（", "").replaceAll("）", "");
		return str;
	}

	public static String saveRepaylog(File excel, long userid) throws IOException, SQLException {
		String message = "";
		FileInputStream stream = null;
		List<ToprCreditcard> cardList = null;
		Connection con = null;
		Statement stmt = null;
		try {
			con = OperationUtil.globals.getCon();
			stmt = con.createStatement();
			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			// 第一行为表头 不用解析
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {
					short cellnum = row.getLastCellNum(); // 最大列数

					// 第一列为客户姓名，第二列为帐单号，不允许为空 且列数不得小于39列
					if (cellVal(row.getCell(1)) == null || cellVal(row.getCell(1)).equals("")/* ||cellnum<10 */) {
						// System.out.println(i+":::::::::"+cellnum);
						continue;
					} else {
						String creditcard = cellVal(row.getCell(1)); // 信用卡卡号

						String status = cellVal(row.getCell(3)); // 还款状态
						if (status == null)
							status = "";
						int repaystatus = 1;
						if (status.equals("全清"))
							repaystatus = 2;
						if (status.equals("备注清零"))
							repaystatus = 3;
						String fee = cellVal(row.getCell(4)); // 还款人民币
						if (fee == null)
							fee = "";
						String usafee = cellVal(row.getCell(5)); // 还款美元
						if (usafee == null)
							usafee = "";
						String hkfee = cellVal(row.getCell(6)); // 还款港元
						if (hkfee == null)
							hkfee = "";
						String eurfee = cellVal(row.getCell(7)); // 还款欧元
						if (eurfee == null)
							eurfee = "";
						String repaytime = "";
						String cell8Val = cellVal(row.getCell(8));
						if (cell8Val != null && !"".equals(cell8Val))
							repaytime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell(8)
									.getDateCellValue());

						String comments = cellVal(row.getCell(9));

						String consigndate = "";
						String cell10Val = cellVal(row.getCell(10));
						if (cell10Val != null && !"".equals(cell10Val))
							consigndate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell(10)
									.getDateCellValue());

						if (comments == null)
							comments = "";

						int count = count(con, creditcard, consigndate);
						if (count == 0)
							message += "【" + creditcard + " " + consigndate + "】在系统中不存在<br>";
						else if (count > 1)
							message += "【" + creditcard + " " + consigndate + "】在系统中存在【" + count + "】条记录<br>";
						else if (count == 1) {
							long creditcardid = creditcardid(con, creditcard, consigndate);
							long taskuserid = userid(con, creditcardid);
							stmt.addBatch("update topr_creditcard set repaystatus=" + repaystatus + ",refee=refee+'"
									+ fee + "' where creditcard='" + creditcard + "'");
							String sql = "insert into topr_repaylog (repaylogid,creditcardid,fee,usafee,hkfee,eurfee,repaytime,comments,createtime,userid,repaystatus) values"
									+ "(toprrepaylogid.nextval,"
									+ creditcardid
									+ ",'"
									+ fee
									+ "','"
									+ usafee
									+ "','"
									+ hkfee
									+ "','"
									+ eurfee
									+ "','"
									+ repaytime
									+ "','"
									+ comments
									+ "',sysdate,"
									+ taskuserid + "," + repaystatus + ")";
							// System.out.println(sql);
							stmt.addBatch(sql);
						}
					}
				}
			}
			stmt.executeBatch();
		} finally {
			if (stream != null)
				stream.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}
		return message;
	}

	/**
	 * 
	 * @param con
	 * @param creditcard
	 * @return
	 * @throws SQLException
	 */
	private static int count(Connection con, String creditcard, String consigndate) throws SQLException {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from topr_creditcard where creditcard='" + creditcard + "'";
			if (consigndate != null && !"".equals(consigndate))
				sql += " and consigndate='" + consigndate + "'";
			pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, creditcard);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return count;
	}

	private static long creditcardid(Connection con, String creditcard, String consigndate) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select creditcardid from topr_creditcard where creditcard='" + creditcard + "'";
			if (consigndate != null && !"".equals(consigndate))
				sql += " and consigndate='" + consigndate + "'";
			pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, creditcard);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getLong(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
	 * 当前任务催收员ID
	 * 
	 * @param con
	 * @param creditcard
	 * @return
	 * @throws SQLException
	 */
	private static long userid(Connection con, long creditcardid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select userid from topr_credittask where taskstat=0 and creditcardid=?");
			pstmt.setLong(1, creditcardid);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getLong(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
	}
}
