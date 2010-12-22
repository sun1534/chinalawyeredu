/**
 * GetParseTongnanData.java
 */
package com.sxit.tongnan;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sxit.common.Globals;
import com.sxit.models.tongnan.StatTongnanpower;
import com.sxit.tongnan.ContinueFTP.DownloadStatus;
import com.sxit.tongnan.service.TongnanPowerService;

/**
 * @author 华锋 Nov 23, 20104:33:38 PM
 * 
 */
public class GetParseTongnanData {

	public static final String FTP_IP = "10.190.45.37";
	public static final String FTP_USER = "ossftp";
	public static final String FTP_PASS = "ossftp";
	public static final int PORT = 21;

	// public static final String FTP_IP = "58.60.231.3";
	// public static final String FTP_USER = "sxittest";
	// public static final String FTP_PASS = "sxit!@#*()";
	// public static final int PORT=50003;
	public static final String FTP_PATH = "/opt/sts_file/bb/tongnan/";
	// public static final String FTP_PATH="/ftphome/";
	// public static final String TONGNAN_FILE = "";
	public static final DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
	public static final DateFormat dfdd = new java.text.SimpleDateFormat("dd");
	public static final DateFormat dfhh = new java.text.SimpleDateFormat("yyyy_MM_dd_HH");
	public final static DecimalFormat nf = new DecimalFormat("#,########0.00000000");
	public static final String FILE_PRE = "GSM_CELL_FILIALE_";

	public GetParseTongnanData() {

	}

	/**
	 * 到ftp服务器上下载文件到本地
	 * 
	 * @return 返回保存的文件名
	 */
	public static String getftpfile(String month) {

		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, -1);
		java.sql.Timestamp stamp = new java.sql.Timestamp(c.getTimeInMillis());

		// 取得当天的载频文件
		String path = FTP_PATH + month + dfdd.format(stamp);
		// String path = FTP_PATH;
		// GSM_CELL_FILIALE_2010_11_23_10.csv
		// yyyy_MM_dd_HH

		String file = FILE_PRE + dfhh.format(stamp) + ".csv";
		// String file="GSM_CELL_FILIALE_2010_12_24_20.csv";
		// 下载这个.csv并对这个文件进行分析
		ContinueFTP myFtp = new ContinueFTP();
		String destfile = "" + file;
		boolean downSuccess = false;
		ContinueFTP.DownloadStatus ds = null;
		try {
			myFtp.connect(FTP_IP, PORT, FTP_USER, FTP_PASS);
			ds = myFtp.download(path + "/" + file, destfile);
			System.out.println("下载结果:::" + ds);
			myFtp.disconnect();
			downSuccess = true;
		} catch (IOException e) {
			System.out.println("文件下载出错：" + e.getMessage());
		}

		if (downSuccess
				&& (ds == ContinueFTP.DownloadStatus.Download_New_Success || ds == DownloadStatus.Download_From_Break_Success))
			return destfile;
		return null;

	}

	public static void main(String[] args) throws Exception {

		// 1先到ftp服务器上下载
		System.out.println("开始从网管服务器上下载文件");
		String destfile = getftpfile("201010");
		System.out.println("文件下载后保存地:" + destfile);
		Map<String, String> maps = null;
		if (destfile != null) {
			maps = parseDownloadFile(destfile);
		}
		System.out.println("解析完毕从服务器上获取的数据");

		// // 找到上个月的数据
		// List<StatTongnanpower> stats = parseUploadFile("");
		// System.out.println("解析完毕潼南移动提供的电度数据");
		// saveExcelFile(stats);
		// System.out.println("潼南移动提供的电度数据存入数据库完毕");
		// if (maps != null)
		// updateDownloadFile(maps);
		// System.out.println("根据服务器上下载的文件更新载频数");
	}

	/**
	 * 插入解析的数据到数据库
	 * 
	 * @param stats
	 */
	public static void saveExcelFile(List<StatTongnanpower> stats) {
		TongnanPowerService service = (TongnanPowerService) Globals.getBean("tongnanPowerService");
		// 插入到数据库里面
		service.save(stats);
	}

	/**
	 * 更新数据到数据库
	 * 
	 * @param maps
	 */
	public static void updateDownloadFile(Map<String, String> maps) {
		TongnanPowerService service = (TongnanPowerService) Globals.getBean("tongnanPowerService");

		Iterator<String> keys = maps.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			String[] values = maps.get(key).split(",");
			service.update(values[0], key, Integer.parseInt(values[1]));

		}
	}

	/**
	 * 接下下载下来的文件,更新到数据库的载频字段里面
	 * 
	 * @param csvFile
	 * @throws Exception
	 */
	public static Map<String, String> parseDownloadFile(String csvFile) throws Exception {
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(new FileInputStream(
				csvFile)));
		String line = null;
		int cellcell = 0;
		int trxcell = 0;
		int laccell = 0;
		int datecell = 0;
		int i = 0;
		Map<String, String> map = new HashMap<String, String>();
		while ((line = br.readLine()) != null) {
			String[] splits = line.split(",");
			if (i == 0) {
				for (int k = 0; k < splits.length; k++) {
					if (splits[k].trim().equalsIgnoreCase("lac")) {
						laccell = k;
					} else if (splits[k].trim().equalsIgnoreCase("ci")) {
						cellcell = k;
					} else if (splits[k].trim().equalsIgnoreCase("TRX配置数")) {
						trxcell = k;
					} else if (splits[k].trim().equalsIgnoreCase("日期")) {
						datecell = k;
					}
				}
			} else {
				String month = splits[datecell].trim().replace("\"", "").replace("年", "").replace("月", "").substring(0,
						6);
				String lac = splits[laccell].trim().replace("\"", "");
				String cellid = splits[cellcell].trim().replace("\"", "");
				String zaipinstr = splits[trxcell].trim().replace("\"", "");
				int zaipin = 0;
				if (!zaipinstr.equals("")) {
					if (zaipinstr.indexOf(".") != -1)
						zaipinstr = zaipinstr.substring(0, zaipinstr.indexOf("."));
					zaipin = Integer.parseInt(zaipinstr);
				}
				String cellidfour = cellid.substring(0, 4);
				String value = month + "," + zaipin;
				if (map.containsKey(cellidfour)) {
					String v = map.get(cellidfour);
					int zaipinhas = Integer.parseInt(v.split(",")[1]);
					value = month + "," + (zaipin + zaipinhas);
					map.remove(cellidfour);
				}
				map.put(cellidfour, value);
				// System.out.println(splits[datecell].trim() + ",,," + month +
				// ",,," + lac + ",," + cellid + ",,,,,"
				// + cellidfour + ",,," + zaipin);

			}
			i++;

		}
		br.close();
		return map;
	}

	/**
	 * 解析提供的电度表
	 * 
	 * @param excelFile
	 * @return
	 * @throws Exception
	 */
	public static List<StatTongnanpower> parseUploadFile(String excelFile) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFile));
		HSSFWorkbook wb = new HSSFWorkbook(fs);

		HSSFSheet sheet = wb.getSheetAt(0);

		// int first = sheet.getFirstRowNum();
		int last = sheet.getLastRowNum();
		// Iterator _ite = sheet.rowIterator();
		String yyyyMMdd = df.format(new java.util.Date());
		String month = yyyyMMdd.substring(0, 6);
		List<StatTongnanpower> stats = new ArrayList<StatTongnanpower>();

		// while (_ite.hasNext()) {
		int i = 1;
		for (; i <= last; i++) {
			// 序号 基站名称 中达日均 电力公司日均 载频数 日均话务量 中达与电力数据比例

			HSSFRow row = (HSSFRow) sheet.getRow(i);
			String idx = HSSFCellToString.toString(row.getCell(1)).trim();
			String cellName = HSSFCellToString.toString(row.getCell(2)).trim();

			double zhongdanrijun = Double.parseDouble(nf.format(Float.parseFloat(HSSFCellToString.toString(
					row.getCell(3)).trim())));
			double powerrijun = Double.parseDouble(nf.format(Float.parseFloat(HSSFCellToString.toString(row.getCell(4))
					.trim())));
			double huwuliangrijun = Double.parseDouble(nf.format(Float.parseFloat(HSSFCellToString.toString(
					row.getCell(6)).trim())));

			StatTongnanpower power = new StatTongnanpower();
			power.setCellName(cellName);
			power.setHuawuliangrijun(huwuliangrijun);
			if (cellName.length() > 4)
				power.setLacCell(cellName.substring(0, 4));
			else
				power.setLacCell(cellName);
			power.setMonth(month);
			power.setPowerrijun(powerrijun);
			power.setTherate(0.0d);
			power.setZhongdanrijun(zhongdanrijun);
			// +","+power.getTherate()+","+power.getTheratePercent()
			// System.out.println(cellName + "," + zhongdanrijun + "," +
			// powerrijun + "," + huwuliangrijun + ","
			// + power.getTheratePercent());

			stats.add(power);

		}

		return stats;

	}
}
