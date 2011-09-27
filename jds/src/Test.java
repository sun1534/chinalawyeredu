import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
		InputStream stream = new FileInputStream("e:/repaylog.xls");
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
					boolean td=false;
					if (status == null)
						status = "";
					int repaystatus = 1;
					if (status.trim().equals("全清"))
						repaystatus = 2;
					else if (status.trim().equals("备注清零"))
						repaystatus = 3;
					else if(status.trim().equals(""))//这样的执行退单
						td=true;
						
					
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
					if(comments==null)
						comments="";
					comments+="(批量导入还款)";

					String consigndate = "";
					String cell10Val = cellVal(row.getCell(10));
					
					
					
					
					if (cell10Val != null && !"".equals(cell10Val))
						consigndate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(row.getCell(10)
								.getDateCellValue());
					System.out.println(cell8Val+"===>"+repaytime+"===>"+cell10Val+",,"+consigndate);
					if (comments == null)
						comments = "";
				}
			}
	}
	}
	public static void main(String args[]) throws Exception{
		parseXLS(new File("d:/1.xls"));

	}
}
