package com.changpeng.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import java.util.*;
import com.changpeng.service.model.TserPhobill;

public class PhobillCreateBatch {
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
	public static void savePhobill(File excel,Session session) throws IOException{
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

					TserPhobill cus=new TserPhobill();
					cus.setUsername(cellVal(row.getCell((short)0)));
					cus.setPhone(cellVal(row.getCell((short)1)));
					cus.setFee(cellVal(row.getCell((short)2)));
					if(cellVal(row.getCell((short)3))!=null&&!"".equals(cellVal(row.getCell((short)3))))
						cus.setFeetime(new java.text.SimpleDateFormat("yyyy-MM").format(row.getCell((short)3).getDateCellValue()));
				
					cus.setCreatetime(new Date());
					
					session.save(cus);
				}
			}//end for row
		}finally{
			if(stream!=null) stream.close();
		}	
	}	
}
