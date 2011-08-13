import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.changpeng.operation.model.ToprCreditcard;
public class Test {
	
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
		//if(value!=null) value=value.replaceAll(" ", "");
		return value;
	}

	public static void parseXLS(File file) throws IOException{
		FileInputStream stream=null;
		try{
			stream=new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(stream);
			HSSFSheet sheet = workbook.getSheetAt(0); // 第一张工作表
			//第一行为表头 不用解析
			/*for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				
				if (cellVal(row.getCell((short)0)) != null) {
					System.out.println(i+"    "+cellVal(row.getCell((short)0)) +"  "+cellVal(row.getCell((short)1)));
				}//end if judge row
			}*///end for row
			
		
			//第一行为表头 不用解析
			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
				HSSFRow row = sheet.getRow(i); // 表中每一行
				if (row != null) {			
					short cellnum=row.getLastCellNum(); //最大列数
					//System.out.println(cellnum);
					//第一列为客户姓名，第二列为帐单号，不允许为空 且列数不得小于39列
					if(cellVal(row.getCell((short)1))==null||cellVal(row.getCell((short)1)).equals("")){
						//System.out.println(i+":::::::::"+cellnum);
						continue;
					}else{
						String creditcard=cellVal(row.getCell((short)1)); //信用卡卡号
						
						System.out.println(i+"   "+creditcard);
					}
				}
			}
		}finally{
			if(stream!=null) stream.close();
		}	
	}
	public static void main(String args[]) throws Exception{
		parseXLS(new File("d:/1.xls"));

	}
}
