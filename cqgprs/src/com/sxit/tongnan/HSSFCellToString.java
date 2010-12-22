package com.sxit.tongnan;
/**
 * 
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

/**
 * @author 华锋
 * 
 */
public class HSSFCellToString {
	private static java.text.DecimalFormat df = new java.text.DecimalFormat("####.####");
	public static String toString(HSSFCell cell) {
		if(cell==null)
			return "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
		case HSSFCell.CELL_TYPE_ERROR:
			// return ErrorEval.getText((( BoolErrRecord )
			// cell.getCellValueRecord()).getErrorValue());
			return "错误";
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula().replace("\"", "");
		case HSSFCell.CELL_TYPE_NUMERIC:
			// TODO apply the dataformat for this cell
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(cell.getDateCellValue());
			} else {
//				return cell.getNumericCellValue() + "";
				return df.format(cell.getNumericCellValue());
			}
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().getString().replace("\"", "");
		default:
			return "未知类型:" + cell.getCellType();
		}
	}
}