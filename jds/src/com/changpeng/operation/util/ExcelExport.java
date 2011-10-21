/**
 * ExcelExport.java
 */
package com.changpeng.operation.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author 刘哈哈 Jun 9, 201112:01:21 AM
 * 
 */
public class ExcelExport {
	// 设置cell编码解决中文高位字节截断
	// private static short XLS_ENCODING = Cell;
	// 定制日期格式
	private static String DATE_FORMAT = "yyyy-mm-dd "; // "m/d/yy h:mm"
	// 定制浮点数格式
	private static String NUMBER_FORMAT = " ###0.00 ";

	private String xlsFileName;
	private Workbook workbook;
	private Sheet sheet;
	private Row row;

	private boolean is2003;

	/**
	 * 初始化Excel
	 * 
	 * @param fileName
	 *            导出文件名
	 */
	public ExcelExport(String fileName) {
		this.xlsFileName = fileName;
		int idx = fileName.lastIndexOf(".");
		String regix = fileName.substring(idx + 1);
		if (regix.equalsIgnoreCase("xls")) {
			is2003 = true;
			this.workbook = new HSSFWorkbook();
		} else if(regix.equalsIgnoreCase("xlsx")) {
			this.workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
		}else{
			throw new RuntimeException("文件名后缀不是正确的Excel文件格式后缀");
		}
		this.sheet = workbook.createSheet();
	}
	
	/**
	 * 是创建2003的呢还是创建2007的
	 * @param is2003
	 */
	public ExcelExport(boolean is2003) {
		this.is2003=is2003;
		if (is2003) {
			is2003 = true;
			this.workbook = new HSSFWorkbook();
		} else {
			this.workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
		}
		this.sheet = workbook.createSheet();
	}

	/**
	 * 导出Excel文件
	 * 
	 * @throws XLSException
	 */
	public void exportXLS() throws Exception {
		FileOutputStream fOut = new FileOutputStream(xlsFileName);
		workbook.write(fOut);
		fOut.flush();
		fOut.close();
	}

	/**
	 * 导出Excel文件
	 * 
	 * @throws XLSException
	 */
	public void exportXLS(OutputStream os) throws Exception {
//		FileOutputStream fOut = new FileOutputStream(xlsFileName);
		workbook.write(os);
		os.flush();
		os.close();
	}
	
	/**
	 * 增加一行
	 * 
	 * @param index
	 *            行号
	 */
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}

	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, String value) {
		Cell cell = this.row.createCell(index);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		// cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, Calendar value) {
		Cell cell = this.row.createCell(index);
		// cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value.getTime());
		CellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
		cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
	}

	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, int value) {
		Cell cell = this.row.createCell(index);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 * 
	 * @param index
	 *            列号
	 * @param value
	 *            单元格填充值
	 */
	public void setCell(int index, double value) {
		Cell cell = this.row.createCell(index);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		CellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
		DataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 设置cell样式为定制的浮点数格式
		cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
	}

	public static void main(String args[]) throws Exception {
		ExcelExport xls = new ExcelExport("d:/aaabb.xlsx");
		for (int i = 0; i < 10; i++) {
			xls.createRow(i);
			for (int j = 0; j < 10; j++) {
				if (j == 9) {

					Calendar c = Calendar.getInstance();
					xls.setCell(j, c);
				}

				else if (j == 8)
					xls.setCell(j, 12131421241243.21d);
				else if (j == 7) {
					xls.setCell(j, 1234567890);
				} else {
					xls.setCell(j, "测试:" + i + "," + j);
				}
			}
		}

		xls.exportXLS();

	}

}
