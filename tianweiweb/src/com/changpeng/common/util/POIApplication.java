package com.changpeng.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

public class POIApplication {
	private HSSFWorkbook wb;
	public int headrow; // 第一个有数据的单元格的行坐标
	public int headcell;// 第一个有数据的单元格的列坐标
	// 转换手机号格式:将表示成1.3922433397E10的手机号转化为13922433397
	DecimalFormat df = new DecimalFormat("#");

	public POIApplication(String filename) {
		try {
			wb = new HSSFWorkbook(new FileInputStream(filename));
		} catch (IOException ie) {
			System.out.println(ie);
		}
	}

	/*
	 * //获取该工作表中第一个数据的行和列 public boolean getCellhead() throws Exception{ int
	 * i,j=0; HSSFRow row = null; HSSFCell cell = null; for(i=0;i<255;i++){
	 * if(isExistRow(i)){ for (j = 0; j < 255; j++) { if(isExistCell(i,j)){
	 * this.headrow = i; this.headcell = j; return true; } } } } return false; }
	 * 
	 * public boolean isExistRow(int i){ boolean bl=false;
	 * if(sheet.getRow(i)!=null){ bl = true; }else{ bl = false; } return bl; }
	 * //i为row; j为cell public boolean isExistCell(int i,int j){ boolean bl =
	 * false; HSSFRow row = sheet.getRow(i); if (row.getCell( (short) j) !=
	 * null) { bl = true; } else { bl = false; } return bl; } //i为row; j为cell
	 * public String getCellvalue(int i,int j){ String values=""; HSSFRow row =
	 * sheet.getRow(i); HSSFCell cell = row.getCell((short) j); int cType =
	 * cell.getCellType(); switch (cType){ case HSSFCell.CELL_TYPE_STRING :
	 * values = cell.getStringCellValue(); break; case
	 * HSSFCell.CELL_TYPE_NUMERIC : values
	 * =df.format(cell.getNumericCellValue()); break; default: values = ""; }
	 * 
	 * return values; }
	 */
	// 读取所有 sheet 中的数据
	public List[] readExcel() {
		String values = "";
		// 统计有多少页(sheet)
		int num = wb.getNumberOfSheets();
		// System.out.println("leo114232423---num---"+num);
		List[] list = new ArrayList[num];
		try {
			for (int numSheets = 0; numSheets < wb.getNumberOfSheets(); numSheets++) {
				if (null != wb.getSheetAt(numSheets)) {
					// 获得一个sheet
					HSSFSheet sheet = wb.getSheetAt(numSheets);
					list[numSheets] = new ArrayList();
					for (int rowNumOfSheet = 0; rowNumOfSheet <= sheet.getLastRowNum(); rowNumOfSheet++) {
						if (null != sheet.getRow(rowNumOfSheet)) {
							// 获得一个不为空的row
							HSSFRow row = sheet.getRow(rowNumOfSheet);
							// 再用一个 ArratList 保存某一行的数据值
							ArrayList alist = new ArrayList();
							for (short cellNumOfRow = 0; cellNumOfRow <= row.getLastCellNum(); cellNumOfRow++) {
								if (null != row.getCell(cellNumOfRow)) {
									// 获得一个不为空的cell
									HSSFCell cell = row.getCell(cellNumOfRow);
									// 判断cell中数据类型
									int cType = cell.getCellType();
									switch (cType) {
									case HSSFCell.CELL_TYPE_STRING:
										values = cell.getStringCellValue();
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										values = df.format(cell.getNumericCellValue());
										break;
									default:
										values = "";
									}
									alist.add(values);
								}
							}
							list[numSheets].add(alist);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ReadCellException");
		}
		return list;
	}

	/*
	 * 读取所有 sheet 中的数据 将每一行的数据保存在一个string values中，中间以分隔符separator分隔
	 */
	public Vector readExcel(String separator) {
		// 统计有多少页(sheet)
		// System.out.println("leo11111111111---num---");
		int num = wb.getNumberOfSheets();
		// System.out.println("leo11111111111---num---"+num);
		Vector vc = new Vector();
		try {
			for (int numSheets = 0; numSheets < wb.getNumberOfSheets(); numSheets++) {
				if (null != wb.getSheetAt(numSheets)) {
					// 获得一个sheet
					HSSFSheet sheet = wb.getSheetAt(numSheets);
					for (int rowNumOfSheet = 0; rowNumOfSheet <= sheet.getLastRowNum(); rowNumOfSheet++) {
						if (null != sheet.getRow(rowNumOfSheet)) {
							// 获得一个不为空的row
							HSSFRow row = sheet.getRow(rowNumOfSheet);
							String values = "";
							for (short cellNumOfRow = 0; cellNumOfRow < row.getLastCellNum(); cellNumOfRow++) {

								// System.out.println("row.getCell(cellNumOfRow)======***========="+row.getCell(cellNumOfRow));
								if (null != row.getCell(cellNumOfRow)) {
									// 获得一个不为空的cell
									HSSFCell cell = row.getCell(cellNumOfRow);
									// 判断cell中数据类型
									int cType = cell.getCellType();
									// System.out.println("单元格的格式为是======="+cType);
									switch (cType) {
									case HSSFCell.CELL_TYPE_STRING:

										values = values + cell.getStringCellValue();
										// System.out.println("单元格的格式为字符串,值为==="+values);
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										// System.out.println("单元格的格式为数字");
										// 判断单元格是不是日期格式
										if (HSSFDateUtil.isCellDateFormatted(cell)) {

											values = values + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm ").format(cell.getDateCellValue());
											// System.out.println("单元格的格式为日期,值为==="+values);
										} else {

											values = values + df.format(cell.getNumericCellValue());
											// System.out.println("单元格的格式为数字111111111,值为==="+values);
											// values = new DecimalFormat( "
											// 0.########## "
											// ).format(cell.getNumericCellValue());
										}
										break;
									default:
										values = values + "";
									}
									if (cellNumOfRow < row.getLastCellNum() - 1) {
										values = values + separator;
									}
								} else {

									// System.out.println("单元格为dddddddddddddddddsaaaaaaaawwwwwwwwwwwww==="+values);
									// if (cellNumOfRow < row.getLastCellNum() -
									// 1) {
									// if (values != null && !"".equals(values))
									values = values + " " + separator;
									// System.out.println("单元格为dssssssssssssssssssssssss==="+values);
									// }
								}
							}
							// System.out.println("values=====**************========"+values);
							vc.add(values);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ReadCellException");
		}
		return vc;
	}

	public static void main(String[] args) {
		String filename = "c:/123.xls";
		POIApplication pi = new POIApplication(filename);
		/*
		 * try{ if (pi.getCellhead()) { System.out.println("成功"); String
		 * data=pi.getCellvalue(pi.headrow,pi.headcell);
		 * System.out.println(data); }else{ System.out.println("失败"); }
		 * }catch(Exception e){ e.printStackTrace(); }
		 */
		ArrayList[] list = (ArrayList[]) pi.readExcel();
		for (int i = 0; i < list.length; i++) {
			System.out.println("leo---第" + i + "页:");
			for (int j = 0; j < list[i].size(); j++) {
				System.out.println("leo---第--" + j + "--行");
				ArrayList alist = (ArrayList) list[i].get(j);
				for (Iterator it = alist.iterator(); it.hasNext();) {
					System.out.println("leo:::---it---" + it.next());
				}
			}
		}
	}
}
