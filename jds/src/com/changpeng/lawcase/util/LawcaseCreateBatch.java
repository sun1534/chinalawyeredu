package com.changpeng.lawcase.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.changpeng.lawcase.model.TlawJiekuanren;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawStageDate;
import com.changpeng.lawcase.service.LawcaseService;
import com.sxit.system.model.TsysUser;

/**
 * 
 * 对有是否的，只有填了是才认为是“是”，不填和填否的，都认为是否
 * 
 * @author 华锋 Oct 27, 2009-2:31:45 PM
 * 
 */

@SuppressWarnings("unchecked")
public class LawcaseCreateBatch {
	// private static java.text.DecimalFormat df = new
	// java.text.DecimalFormat("####.####");
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
		//			
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
	public static List<TlawLawcase> parseXls(File excel) throws IOException {
		FileInputStream stream = null;
		List<TlawLawcase> lawcaseList = new ArrayList<TlawLawcase>();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		try {
			// lawcaseList
			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			int firstrow = sheet.getFirstRowNum();
			int lastrow = sheet.getLastRowNum();
			// 第一行为表头 不用解析
			for (int i = 3; i <= lastrow; i++) {
				// 委托日期 重复查询日期
				// 委托银行 序号 委托年度 支行 银行分管人 委托时间 合同编号 被告人姓名 "担保人
				// （抵押人/保证人）" 贷款类型 案件承办人 贷款实际发放时期 贷款实际到期日期
				// 贷款金额（万元） 贷款帐号 是否符合起诉条件 是否立案 是否缴费 是否登入进度表 不符合起诉条件/未立案/未缴费原因
				// 是否有档案 备注

				HSSFRow row = sheet.getRow(i); // 表中每一行

				String bank = cellVal(row.getCell(0)).trim();
				String year = cellVal(row.getCell(1)).trim();
				String zhihang = cellVal(row.getCell(2)).trim();
				String fenguanren = cellVal(row.getCell(3)).trim();
				String date = cellVal(row.getCell(4)).trim();
				String querytime = cellVal(row.getCell(5)).trim();
				String contractno = cellVal(row.getCell(6)).trim();
				String beigaoren = cellVal(row.getCell(7)).trim();
				String theidcard = cellVal(row.getCell(8)).trim();
				String thephone = cellVal(row.getCell(9)).trim();
				String danbaoren = cellVal(row.getCell(10)).trim();
				String kuanleixing = cellVal(row.getCell(11)).trim();
				String howmuch = cellVal(row.getCell(12)).trim();
				String remain = cellVal(row.getCell(13)).trim();
				String startdate = cellVal(row.getCell(14)).trim();
				String enddate = cellVal(row.getCell(15)).trim();
				String bankno = cellVal(row.getCell(16)).trim();
				String remarks = cellVal(row.getCell(17)).trim();

				System.out.println(bank + "," + year + "," + zhihang + "," + fenguanren + "," + date + "," + querytime
						+ "," + contractno + "," + beigaoren + "," + danbaoren + "," + kuanleixing + "," + startdate
						+ "," + enddate + "," + howmuch + "," + remain + "," + bankno + "," + remarks);

				TlawLawcase lawcase = new TlawLawcase();
				lawcase.setCreatetime(timestamp);

				TlawJiekuanren jiekuanren = new TlawJiekuanren();
				jiekuanren.setCreatetime(lawcase.getCreatetime());

				lawcase.setBank(bank);
				lawcase.setBankadmin(fenguanren);
				lawcase.setBankbranch(zhihang);
				lawcase.setBankid(com.changpeng.lawcase.util.CommanDatas.CASE_BANKS_ID.get(bank));
				lawcase.setContractno(contractno);
				lawcase.setCreatetype("导入");
				lawcase.setStatusid(-1);
				lawcase.setPlanlogs(null);
				lawcase.setRemarks(remarks);
				lawcase.setRequerytime(querytime);
				lawcase.setThedate(date);
				lawcase.setTheyear(year);

				jiekuanren.setBankno(bankno);
				jiekuanren.setDanbaoren(danbaoren);
				String danbaotype = null;
				jiekuanren.setDanbaotype(danbaotype);
				double _howmuch=0;
				double _remain=0;
				try{
					_howmuch=howmuch.equals("") ? 0d : Double.parseDouble(howmuch);
					_remain=remain.equals("") ? 0d : Double.parseDouble(remain);
				}catch(Exception e){
					e.printStackTrace();
				}
				jiekuanren.setHowmuch(_howmuch);
				jiekuanren.setJiekuanren(beigaoren);
				jiekuanren.setJiekuantype(kuanleixing);
				jiekuanren.setLawcase(lawcase);
				jiekuanren.setRemain(_remain);
				jiekuanren.setRemarks(remarks);
				jiekuanren.setStartdate(startdate);
				jiekuanren.setEnddate(enddate);
				jiekuanren.setTheidcard(theidcard);
				jiekuanren.setThephone1(thephone);
				// jiekuanren.setThephone2(thephone2);

				lawcase.setJiekuanren(jiekuanren);
				lawcaseList.add(lawcase);
			}// end for row
		} finally {
			if (stream != null)
				stream.close();
		}
		return lawcaseList;
	}

	private static DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 将EXCEL中催收记录插入数据库，如果账号重复，做覆盖操作。
	 * 
	 * 这里要记录错误的原因等
	 * 
	 * @param excel
	 * @param consigndate
	 * @param bankid
	 * @param session
	 * @throws IOException
	 */
	public static List save(File excel, TsysUser sysuser, long fileid, org.hibernate.Session session)
			throws IOException {
		List result = new ArrayList();
		List<TlawLawcase> lawcaseList = parseXls(excel);

		for (TlawLawcase lawcase : lawcaseList) {
			try {
				if (lawcase.getSusongworkid() <= 0) {

					result.add("承办人:" + lawcase.getSusongworkname() + "在系统中不存在,忽略掉,请台帐管理员自己导入<br/>");
					continue;
				}

				TlawJiekuanren jiekuanren = lawcase.getJiekuanren();
				lawcase.setCreateuserid(sysuser.getUserid());
				lawcase.setCreateusername(sysuser.getUsername());
				lawcase.setCreatetype(lawcase.getCreatetype() + "," + fileid);
				lawcase.setStatusid(-1);
				session.save(lawcase);

				long caseid = lawcase.getCaseid();
				jiekuanren.setCreateuserid(sysuser.getUserid());
				jiekuanren.setCreateusername(sysuser.getUsername());
				jiekuanren.setCaseid(caseid);
				jiekuanren.setLawcase(lawcase);

				session.save(jiekuanren);

				TlawStageDate stagedate = new TlawStageDate();
				stagedate.setCreatedate(df.format(lawcase.getCreatetime()));
				stagedate.setThedate(lawcase.getThedate());
				stagedate.setCaseid(caseid);
				stagedate.setLawcase(lawcase);
				session.save(stagedate);

				String content = sysuser.getUsername() + "批量上传案件";
				int opertype = 21001;
				LawcaseService.saveOperlog(session, caseid, content, opertype, sysuser);

			} catch (Exception e) {
				System.out.println(e);
				result.add(lawcase.getBank() + "," + lawcase.getBankbranch() + ","
						+ lawcase.getJiekuanren().getJiekuanren() + "导入失败:" + e);

			}

			System.out.println(result);
		}
		session.flush();
		return result;

	}

	public static void main(String[] args) throws Exception {
		File file = new File("c:\\abc.xls");
		parseXls(file);
	}
}