package main.readerrors;

import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import main.util.DBUtils;

import org.apache.commons.logging.Log;

/**
 * 
 */

/**
 * 
 * 读华为的错误代码情况
 * 
 * @author 华锋 Nov 4, 2009-9:21:28 PM
 * 
 */
public class ReadHWErrorApns_OLD {
	
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 处理前放文件的目录
	 */
	private static String SRCDIR = "d:\\home";
	/**
	 * 处理完毕后的备份目录
	 */
	private static String DESTDIR = "d:\\bak";

	public static String SGSNCMD="/export/home/jf/sgsnbill/sgsnbill";
//	private static Log LOG = org.apache.commons.logging.LogFactory.getLog(ReadHWErrorApns.class);


	// public static void errorlog(String msg) {
	// ERRORLOG.println(df.format(new Date()) + "=>" + msg);
	// }

	private static void init() {
		InputStream in = null;
		try {
			in = ReadHWErrorApns_OLD.class.getResourceAsStream("/main/readerrors/readhw.properties");
			Properties prop = new Properties();
			prop.load(in);
			SRCDIR = prop.getProperty("SRCDIR");
			DESTDIR = prop.getProperty("DESTDIR");
			SGSNCMD = prop.getProperty("SGSNCMD");
		} catch (Exception e) {
			try {
				if (in != null)
					in.close();
			} catch (Exception ee) {
				// LOG.error("关闭ice.properties异常:" + e);
				e.printStackTrace();
			}
		}
	}



	/**
	 * <pre>
	 * 处理流程: 
	 * 1.到原目录下copy所有文件到某个目录下,每天1个目录,记录下这些个文件copy后的路径
	 * 2.上面copy文件的时候,以休息100毫秒的形式判断这个文件是否有改动,如果有,记录下来,处理完其他的后再处理这个
	 * 3.copy过后的文件名,将之前文件所在的目录带入,同时加入此次处理的时间戳
	 * 4.处理之前,判断原文件名+sgsn号是否已经处理过,如果处理过,拿到行数后忽略掉,否则从0开始
	 * 5.处理完毕后,将之前的都删掉,将处理过的都入库,字段为id,sgsnid,filename,System.currentMillis()
	 * 6.处理时间定为每10分钟1次 
	 * 7.先通过crontab的方式执行
	 * 
	 * </pre>
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		Connection con = null;
		System.out.println("============================================================");
		long begin = System.currentTimeMillis();
		
		String today=args[0]; //对应的目录
		String flag=args[1]; //此次的标志
		
		try {
			init();
			HWChrFileHandle filehandle = new HWChrFileHandle();
			Map<String, ErrorFile> mapfiles = filehandle.copyHWFile(SRCDIR, DESTDIR);

			con = DBUtils.getOracleCon();
			System.out.println("成功获取到数据库连接OK");
			System.out.println("mapfiles.size():" + mapfiles.size());
			if (mapfiles.size() > 0) {

				ReadHandleHistory readHistory = new ReadHandleHistory(con);
				readHistory.getFromDB(mapfiles);
				long now = System.currentTimeMillis();
				java.util.Iterator<ErrorFile> files = mapfiles.values().iterator();
				List<ErrorApnsCdr> cdrs = null;
				while (files.hasNext()) {

					ErrorFile file = files.next();
					cdrs = filehandle.parseHWErrorFile(file);
					
					System.out.println(df.format(new Date())+"=>cdrs.size():::"+cdrs.size());
					
					filehandle.saveHWError(cdrs);

					System.out.println("解析并存储" + file.getSgsnid() + "_" + file.getSrcfilename() + "所花时间:"
							+ (System.currentTimeMillis() - now));
					now = System.currentTimeMillis();
				}

				// 然后这里入库
				readHistory.saveLatestErrors(mapfiles);
				System.out.println("所有文件处理完毕!!!" + (System.currentTimeMillis() - begin));

			} else {
				System.out.println("目录下无文件,跳过处理...");
			}

		} catch (Exception e) {
			System.out.println("处理异常:::"+e);
		} finally {
			DBUtils.closeConnection(con);
		}
	}

	public static void test(String[] args) {
		FileHandle filehandle = new FileHandle();
		Map<String, ErrorFile> mapfiles = filehandle.copyFile("d:\\home", "d:\\bak");
		java.util.Iterator<ErrorFile> files = mapfiles.values().iterator();

		while (files.hasNext()) {
			ErrorFile file = files.next();
			System.out.println(file.getDestfile().getName() + ",,," + file.getSgsnid() + ",,," + file.getSrcfilename());
		}
	}

}
