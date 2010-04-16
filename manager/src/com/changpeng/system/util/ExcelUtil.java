/**
 * 
 */
package com.changpeng.system.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.changpeng.common.util.HSSFCellToString;
import com.changpeng.jifen.action.JifenbudengBatch;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.SysGroup;

/**
 * @author 华锋 Nov 30, 2009-9:40:07 PM
 * 
 */
public class ExcelUtil {
	private static Log LOG = LogFactory.getLog(ExcelUtil.class);

	public static List<SysGroup> parseGroupXls(File excel) throws IOException {
		FileInputStream stream = null;
		List<SysGroup> lawcaseList = new ArrayList<SysGroup>();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		try {

			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int lastrow = sheet.getLastRowNum();
			// "事务所名称
			// (必填)" "事务所地址
			// (非必填项)" "邮政编码
			// (非必填项)" "执业证号
			// (必填)" "会员编号(必填)
			// [由开发商填写]" "负责人
			// (非必填项)" "联系电话
			// (非必填项)" "事务所传真
			// (非必填项)" "主管机关
			// (非必填项)"

			for (int i = 1; i <= lastrow; i++) {

				HSSFRow row = sheet.getRow(i);
				String groupname = HSSFCellToString.toString(row.getCell(0)).trim();

				// System.out.println(groupname);
				if (groupname == null || groupname.equals(""))
					break;
				String address = HSSFCellToString.toString(row.getCell(1)).trim();
				String postcode = "";
				// String postcode =
				// HSSFCellToString.toString(row.getCell(2)).trim();
				String groupenname = HSSFCellToString.toString(row.getCell(2)).trim();
				String systemno = HSSFCellToString.toString(row.getCell(3)).trim();
				String contacter = HSSFCellToString.toString(row.getCell(4)).trim();
				String phone = HSSFCellToString.toString(row.getCell(5)).trim();
				String fax = HSSFCellToString.toString(row.getCell(6)).trim();
				String district = HSSFCellToString.toString(row.getCell(7)).trim();

				LOG.debug(groupname + "=" + address + "=" + groupenname + "=" + systemno + "=" + contacter + "="
						+ phone + "=" + fax + "=" + district);

				SysGroup group = new SysGroup();
				//
				group.setGroupenname(groupenname);
				group.setAddress(address);
				group.setComments("批量导入");
				group.setPostcode(postcode);
				group.setContacter(contacter);
				group.setCreatetime(timestamp);
				group.setCreatetype(3);
				// group.setCreateuser(user.getUsername());
				group.setDelflag(false);
				// group.setDirectgroup(directgroup);
				group.setFax(fax);
				group.setGroupenname(groupenname);
				group.setGrouplevel(3);
				group.setGroupname(groupname);
				group.setGrouptype(1);
				// group.setParentid(parentid);
				group.setPhone(phone);
				group.setSystemno(systemno);
				group.setDistrict(district);
				group.setExcelline(i);
				lawcaseList.add(group);

			}// end for row
		} finally {
			if (stream != null)
				stream.close();
		}
		return lawcaseList;
	}

	public static List<Lawyers> parseLawyersXls(File excel) throws IOException {
		FileInputStream stream = null;
		List<Lawyers> lawcaseList = new ArrayList<Lawyers>();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		try {

			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int lastrow = sheet.getLastRowNum();

			// "律师姓名
			// (必填)" "所属事务所
			// (必填)" "律师执业证号
			// (必填)" "律师身份证号
			// (必填)" "会员编号(必填)
			// [由开发商填写]" 首次执业时间(必填) "性别
			// (非必填)" "手机号码
			// (非必填)" "EMAIL地址
			// (非必填)"

			for (int i = 1; i <= lastrow; i++) {

				HSSFRow row = sheet.getRow(i);
				String lawyername = HSSFCellToString.toString(row.getCell(0)).trim();

				// System.out.println(groupname);
				if (lawyername == null || lawyername.equals(""))
					break;
				String officename = HSSFCellToString.toString(row.getCell(1)).trim();
				String lawyerno = HSSFCellToString.toString(row.getCell(2)).trim();
				String certno = HSSFCellToString.toString(row.getCell(3)).trim();
				String systemno = HSSFCellToString.toString(row.getCell(4)).trim();
				String zhiyedate = HSSFCellToString.toString(row.getCell(5)).trim();
				String gender = HSSFCellToString.toString(row.getCell(6)).trim();
				String mobile = HSSFCellToString.toString(row.getCell(7)).trim();
				String email = HSSFCellToString.toString(row.getCell(8)).trim();
				String postcode = HSSFCellToString.toString(row.getCell(9)).trim();
				LOG.debug(lawyername + "=" + officename + "=" + lawyerno + "=" + certno + "=" + systemno + "="
						+ zhiyedate + "=" + gender + "=" + mobile + "=" + email);

				Lawyers lawyers = new Lawyers();
				lawyers.setCertno(certno);
				lawyers.setEmail(email);
				lawyers.setGender(gender);
				lawyers.setLawyername(lawyername);
				lawyers.setLawyerno(lawyerno);
				lawyers.setOfficename(officename);
				lawyers.setSystemno(systemno);
				lawyers.setZhiyedatestr(zhiyedate);
				lawyers.setMobile1(mobile);
				lawyers.setPostcode(postcode);
				lawyers.setExcelline(i);
				lawcaseList.add(lawyers);

			}// end for row
		} finally {
			if (stream != null)
				stream.close();
		}
		return lawcaseList;
	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private static Date parseDate(String date) {
		if (date == null || date.equals(""))
			date = df.format(new Date());
		try {
			return df.parse(date);
		} catch (Exception e) {

			return null;
		}
	}

	public static List<JifenbudengBatch> parseBudengjifen(File excel) throws IOException {
		FileInputStream stream = null;
		List<JifenbudengBatch> budenglist = new ArrayList<JifenbudengBatch>();
		// java.sql.Timestamp timestamp = new
		// java.sql.Timestamp(System.currentTimeMillis());
		try {

			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int lastrow = sheet.getLastRowNum();

			// 补登内容标题(必填) 计分日期(非必填,不填默认为当天) 执业证号(必填) 补登积分年度(必填) 补登学分(必填)

			for (int i = 1; i <= lastrow; i++) {

				HSSFRow row = sheet.getRow(i);
				String title = HSSFCellToString.toString(row.getCell(0)).trim();

				// System.out.println(groupname);
				if (title == null || title.equals(""))
					break;
				String budengdate = HSSFCellToString.toString(row.getCell(1)).trim();
				String lawyerno = HSSFCellToString.toString(row.getCell(2)).trim();
				String theyear = HSSFCellToString.toString(row.getCell(3)).trim();
				String xuefen = HSSFCellToString.toString(row.getCell(4)).trim();
				String islocal= HSSFCellToString.toString(row.getCell(5)).trim();
				LOG.debug(title + "=" + budengdate + "=" + lawyerno + "=" + theyear + "=" + xuefen+"="+islocal);

				JifenbudengBatch budeng = new JifenbudengBatch();
				budeng
						.setBudengdate(budengdate == null || budengdate.endsWith("") ? df.format(new Date())
								: budengdate);
				budeng.setXuefen(xuefen);
				budeng.setLawyerno(lawyerno);
				budeng.setTheyear(theyear);
				budeng.setTitle(title);
				budeng.setExcelline(i);
				budeng.setIslocal(islocal==null||islocal.equals("")?"否":islocal);
				budenglist.add(budeng);

			}// end for row
		} finally {
			if (stream != null)
				stream.close();
		}
		return budenglist;
	}

	public static List<Lessons> parseLessonXls(File excel) throws IOException {
		FileInputStream stream = null;
		List<Lessons> lawcaseList = new ArrayList<Lessons>();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		try {

			stream = new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int lastrow = sheet.getLastRowNum();
			// 课程标题(必填) 授课老师(必填) 老师行业(非必填项) 课程类别(必填) 课程类别描述(非必填项) 学分(必填)
			// 授课时间(必填) 课程描述(非必填项) 视频文件地址(必填)

			for (int i = 1; i <= lastrow; i++) {

				HSSFRow row = sheet.getRow(i);
				String title = HSSFCellToString.toString(row.getCell(0)).trim();

				// System.out.println(groupname);
				if (title == null || title.equals(""))
					break;
				String teacher = HSSFCellToString.toString(row.getCell(1)).trim();
				String teachertype = HSSFCellToString.toString(row.getCell(2)).trim();
				String lessontype = HSSFCellToString.toString(row.getCell(3)).trim();
				String lessontypedesc = HSSFCellToString.toString(row.getCell(4)).trim();
				String xuefen = HSSFCellToString.toString(row.getCell(5)).trim();
				String lessondate = HSSFCellToString.toString(row.getCell(6)).trim();
				String content = HSSFCellToString.toString(row.getCell(7)).trim();
				String onlinefile = HSSFCellToString.toString(row.getCell(8)).trim();

				LOG.debug(title + "=" + teacher + "=" + teachertype + "=" + lessontype + "=" + lessontypedesc + "="
						+ xuefen + "=" + lessondate + "=" + content + "=" + onlinefile);

				Lessons lessons = new Lessons();
				lessons.setTitle(title);
				lessons.setTeachers(teacher);
				lessons.setLessoncontent(content);
				lessons.setXuefenstr(xuefen);
				lessons.setOnlinefile(onlinefile);
				lessons.setLessondatestr(lessondate);
				lessons.setLessontypestr(lessontype);
				lessons.setTeachertypestr(teachertype);
				lessons.setExcelline(i);

				lawcaseList.add(lessons);

			}// end for row
		} finally {
			if (stream != null)
				stream.close();
		}
		return lawcaseList;
	}

	public static void main(String args[]) throws Exception {
		parseGroupXls(new File("d:\\a.xls"));
		parseGroupXls(new File("d:\\b.xls"));
	}
}
