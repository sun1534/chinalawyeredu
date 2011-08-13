package com.changpeng.nonlaw.util;
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

import com.changpeng.address.model.TusrAddress;
import com.changpeng.common.*;
@SuppressWarnings("unchecked")
public class NonlawUpdateBatch {
	
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
	private static List<TnlwNonlaw> parseXls(File excel) throws IOException{
		FileInputStream stream=null;
		List<TnlwNonlaw> nonlawList=null;
		try{
			nonlawList=new ArrayList<TnlwNonlaw>();
			stream=new FileInputStream(excel);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			//第一行为表头 不用解析
			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {				
					short cellnum=row.getLastCellNum(); //最大列数					
					//第一列为贷款账号 不能为空
					if(cellVal(row.getCell((short)0))==null) //如果那个个贷编号为空，则也不能够导入
						continue;
					else{
						TnlwNonlaw nonlaw=new TnlwNonlaw();						
						nonlaw.setLendaccount(cellVal(row.getCell((short)0)));
						nonlaw.setPayaccount(cellVal(row.getCell((short)1)));
						nonlaw.setUsername(cellVal(row.getCell((short)2)));
						nonlaw.setIdcard(cellVal(row.getCell((short)3)));
						nonlaw.setLendfee(cellVal(row.getCell((short)4)));
						nonlaw.setCurbalancefee(cellVal(row.getCell((short)5)));
						nonlaw.setCuroverfee(cellVal(row.getCell((short)6)));
						nonlaw.setCuraccrualfee(cellVal(row.getCell((short)7)));
//						nonlaw.setCuroverstat(cellVal(row.getCell((short)8)));
						try{
							nonlaw.setCuroverstat(Float.parseFloat(cellVal(row.getCell((short)8))));		
							}catch(Exception e){
								nonlaw.setCuroverstat(0f);
							}
						nonlaw.setBuyaddr(cellVal(row.getCell((short)9)));
						
						
						nonlawList.add(nonlaw);						
					}
				}//end if judge row
			}//end for row
		}finally{
			if(stream!=null) stream.close();
		}	
		return nonlawList;
	}
	/**
	 * 从excel中获取催收记录，并更新数据库中已有相关信息
	 * @param excel
	 * @param bankid
	 * @param session
	 * @return
	 * @throws IOException
	 */
	public static int update(File excel,long bankid,org.hibernate.Session session) throws IOException{
		List<TnlwNonlaw> nonlawList=parseXls(excel);
		
		HashMap<String,TnlwNonlaw> nonlawMap=NonlawUtil.nonlawOfBank(bankid, session);	
		int count=0;
		for(TnlwNonlaw nonlaw:nonlawList){
			//根据借款卡号和费用判断唯一性的，而不是那个借据号
			String key=nonlaw.getLendaccount()+","+nonlaw.getLendfee();
			//System.out.println(key);
			if(nonlawMap.containsKey(key)){
				count++;
				TnlwNonlaw law=nonlawMap.get(key);
				law.setCuraccrualfee(nonlaw.getCuraccrualfee());
				law.setCurbalancefee(nonlaw.getCurbalancefee());
				law.setCuroverfee(nonlaw.getCuroverfee());
				law.setCuroverstat(nonlaw.getCuroverstat());
				law.setBuyaddr(nonlaw.getBuyaddr());
				law.setUpdatetime(new java.util.Date());
				session.update(law);
			}
		}
		session.flush();
		return count;
	}	
}
