package com.changpeng.customer.util;
import java.util.*;

import com.changpeng.nonlaw.model.*;
import com.changpeng.nonlaw.util.NonlawUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;

import com.changpeng.customer.model.*;
import com.changpeng.common.*;
@SuppressWarnings("unchecked")
public class CustomerCreateBatch {
	
	/**
	 * 获取EXCEL单元格中的值
	 * @param cell
	 * @return
	 */
	private static String cellVal(HSSFCell cell){
		java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
		String value = null;
		if(cell!=null){			
			
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
		if(value!=null) value=value.replaceAll(" ", "");
		return value;
	}
	/**
	 * 解析EXCEL文件
	 * @param excel
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static void saveCustomer(File excel,Session session,long userid) throws IOException{
		FileInputStream stream=null;
	
		try{
			stream=new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			//第一行为表头 不用解析
			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {

					TusrCustomer cus=new TusrCustomer();
						cus.setCompany(cellVal(row.getCell((short)0)));
						cus.setAddress(cellVal(row.getCell((short)1)));
						cus.setPostcode(cellVal(row.getCell((short)2)));
						cus.setUsername(cellVal(row.getCell((short)3)));
						if(cellVal(row.getCell((short)4))!=null&&!"".equals(cellVal(row.getCell((short)4))))
							cus.setBirthday(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short)4).getDateCellValue()));
						cus.setPhone(cellVal(row.getCell((short)5)));
						cus.setMobile(cellVal(row.getCell((short)6)));
						cus.setFax(cellVal(row.getCell((short)7)));
						cus.setLinkman(cellVal(row.getCell((short)8)));
						cus.setLinkphone(cellVal(row.getCell((short)9)));
						cus.setLinkmobile(cellVal(row.getCell((short)10)));
						cus.setLinkfax(cellVal(row.getCell((short)11)));
						cus.setEmail(cellVal(row.getCell((short)12)));
						cus.setQq(cellVal(row.getCell((short)13)));
						if(cellVal(row.getCell((short)14))!=null&&cellVal(row.getCell((short)14)).equalsIgnoreCase("vip")){
							cus.setCustomerflag(1);
						}else
							cus.setCustomerflag(2);
						cus.setCustomertype(1);
						cus.setCreateuser(userid);
						session.save(cus);
					}
			}//end for row
		}finally{
			if(stream!=null) stream.close();
		}	

	}
	
	
	
	@SuppressWarnings("deprecation")
	public static void saveCustomer2(File excel,Session session,long userid) throws IOException{
		FileInputStream stream=null;
	
		try{
			stream=new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			//第一行为表头 不用解析
			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {

					TusrCustomer cus=new TusrCustomer();
						cus.setUsername(cellVal(row.getCell((short)0)));
						cus.setCompany(cellVal(row.getCell((short)1)));
						cus.setAddress(cellVal(row.getCell((short)2)));
						cus.setPostcode(cellVal(row.getCell((short)3)));
						cus.setNativeplace(cellVal(row.getCell((short)4)));
						if(cellVal(row.getCell((short)5))!=null&&!"".equals(cellVal(row.getCell((short)5))))
							cus.setBirthday(new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell((short)5).getDateCellValue()));
						cus.setPhone(cellVal(row.getCell((short)6)));
						cus.setMobile(cellVal(row.getCell((short)7)));
						cus.setFax(cellVal(row.getCell((short)8)));
						cus.setEmail(cellVal(row.getCell((short)9)));
						cus.setQq(cellVal(row.getCell((short)10)));
						
						cus.setLinkman(cellVal(row.getCell((short)11)));
						cus.setLinkphone(cellVal(row.getCell((short)12)));
						cus.setLinkmobile(cellVal(row.getCell((short)13)));
						
						if(cellVal(row.getCell((short)14))!=null&&cellVal(row.getCell((short)14)).equalsIgnoreCase("vip")){
							cus.setCustomerflag(1);
						}else
							cus.setCustomerflag(2);
						cus.setCustomertype(2);
						cus.setCreateuser((int)userid);
						session.save(cus);
					}
			}//end for row
		}finally{
			if(stream!=null) stream.close();
		}	

	}
}
