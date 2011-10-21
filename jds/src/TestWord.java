import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

/**
 * @author 刘哈哈 Jun 8, 201111:12:39 PM
 * 
 */
public class TestWord {

	private static String user = "jds";
	private static String password = "jds";
	private static String url = "jdbc:oracle:thin:@localhost:1521:ora10";
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() throws Exception {
		return java.sql.DriverManager.getConnection(url, user, password);
	}

	private static String getUser(String card) throws Exception {

		String sql = " select a.credittaskid,b.creditcardid,c.userid,c.username,a.createtime from tsys_user c,topr_credittask a,topr_creditcard b where a.taskstat=0 and c.userid=a.userid and a.creditcardid=b.creditcardid and b.creditcard=? order by a.credittaskid desc";

		java.sql.PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, card);
		ResultSet rs = stmt.executeQuery();
		String s = "";
		if (rs.next()) {

			s = rs.getString("credittaskid") + "," + rs.getString("creditcardid") + "," + rs.getString("userid") + ","
					+ rs.getString("username") + "," + rs.getString("createtime") + ",";

			// int userid=rs.getInt("userid");//这里再判断下这个userid是否存在
			System.out.println("数据库中的记录::" + s);
		}
		rs.close();
		stmt.close();
		return s;

	}

	private static void insert(PreparedStatement stmt, String dbresult, String date, String comments) throws Exception {

		String[] s = dbresult.split(",");

		// logid,credittaskid,userid,createtime,logtime,comments,creditcardid,username

		stmt.setString(1, s[0]);
		stmt.setString(2, s[2]);
		stmt.setString(3, date);
		stmt.setString(4, comments);
		stmt.setString(5, s[1]);
		stmt.setString(6, s[3]);

		stmt.addBatch();
		long now = System.currentTimeMillis();
		if ((count++) % 100 == 0) {
//			stmt.executeBatch();
			stmt.clearBatch();
			System.out.println("插入100条的时间为::::" + (System.currentTimeMillis() - now));
		}

	}

	public static int count = 1;
	public static Connection con;
	private static java.io.PrintWriter warn = null;
	private static java.io.PrintWriter error = null;
	private static java.io.PrintWriter info = null;

	private static void digui(File file, java.sql.PreparedStatement stmt) {
		String filename = file.getName().toLowerCase();
		if (file.isFile()) {

			if (filename.endsWith(".doc")) {
				// System.out.println("====>" +
				// filename+file.getAbsolutePath());
				testWord(file, stmt);
			} else {
				System.out.println("文件格式不对=>" + file.getAbsolutePath());
			}
		} else if (file.isDirectory()) {// 递归处理
			System.out.println("digui" + filename);
			java.io.File[] files = file.listFiles();
			for (File d : files)
				digui(d, stmt);
		}
	}

	public static void main(String[] a) throws Exception {
		long now = System.currentTimeMillis();
		info = new java.io.PrintWriter(new java.io.FileOutputStream("d:\\info.log", true), true);
		warn = new java.io.PrintWriter(new java.io.FileOutputStream("d:\\warn.log", true), true);
		error = new java.io.PrintWriter(new java.io.FileOutputStream("d:\\error.log", true), true);
		con = getConn();

		// File file = new File("d:\\a.doc");
		// FileInputStream fis = new FileInputStream(file);
		// WordExtractor wordExtractor = new WordExtractor(fis);
		// System.out.println("【 使用getText()方法提取的Word文件的内容如下所示：】");
		// String[] paragraph = wordExtractor.getParagraphText();
		// System.out.println("该Word文件共有" + paragraph.length + "段。");
		// for (int i = 0; i < paragraph.length; i++) {
		// System.out.println("< 第 " + (i + 1) + " 段的内容为 >");
		// System.out.println(paragraph[i]);
		// }

		String sql = "insert into topr_creditlog(logid,credittaskid,userid,createtime,logtime,comments,creditcardid,username)values(ToprCreditlogid.nextval,?,?,sysdate,?,?,?,?) ";
		java.sql.PreparedStatement stmt = con.prepareStatement(sql);

		File dir = new File("d:\\20111021提供");
		// File dir = new File("d:\\20111019.doc");

		digui(dir, stmt);

//		stmt.executeBatch();

		con.close();

		System.out.println("总共时间::::" + (System.currentTimeMillis() - now));
	}

	/**
	 * 1行1列是帐号 0行1列是姓名 12行开始是催收情况，1列是日期，2列是催收内容 催收记录完结的标志是看后续的行的0列是否有“是□”的标记
	 * 
	 */
	public static void testWord(File file, java.sql.PreparedStatement stmt) {
		try {
			FileInputStream in = new FileInputStream(file);// 载入文档
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			HWPFDocument hwpf = new HWPFDocument(pfs);
			Range range = hwpf.getRange();// 得到文档的读取范围
			TableIterator it = new TableIterator(range);
			// 迭代文档中的表格
			String cardno = "";
			String username = "";

			int iii = 0;

			// 迭代行，默认从0开始

			while (it.hasNext()) {
				int startrow = 0;
				boolean issstartset = false;
				iii++;
				boolean iscontinue = true;
				Table tb = (Table) it.next();
				Map<Integer, String> list = new HashMap<Integer, String>();
				System.out.println("第" + iii + "个表格===========================>>>>>");
				try {
					for (int i = 0; i < tb.numRows(); i++) {
						TableRow tr = tb.getRow(i);
						// * 1行1列是帐号
						// * 0行1列是姓名
						// * 12行开始是催收情况，1列是日期，2列是催收内容
						// * 催收记录完结的标志是看后续的行的0列是否有“是□”的标记
						// 迭代列，默认从0开始

						for (int j = 0; j < tr.numCells(); j++) {

							TableCell td = tr.getCell(j);// 取得单元格
							// 取得单元格的内容
							for (int k = 0; k < td.numParagraphs(); k++) {
								Paragraph para = td.getParagraph(k);
								String s = para.text();
								// System.out.println(i + "行，" + j + "列" +
								// "，内容：" + s.trim().replace(" ", "").replace("
								// ", ""));

								// if(!issstartset&&(s.trim().replace(" ",
								// "").replace(" ",
								// "").indexOf("日期")!=-1||s.trim().replace(" ",
								// "").replace(" ", "").indexOf("催收情况")!=-1)){
								if (!issstartset && (s.trim().replace(" ", "").replace("　", "").indexOf("催收情况") != -1)) {

									startrow = i;
									issstartset = true;
									// System.out.println("startrow===="+startrow);
								}

								if (i == 0 && j == 1
										&& !s.trim().replace(" ", "").replace("　", "").replace("", "").equals("")) {
									username = s.trim().replace(" ", "").replace("　", "").replace("", "");
								} else if (i == 1 && j == 1
										&& !s.trim().replace(" ", "").replace("　", "").replace("", "").equals("")) {
									cardno = s.trim().replace(" ", "").replace("　", "").replace("", "");
								} else if (i > startrow && iscontinue && issstartset) {

									if (j >= 1) {
										// System.out.println(j+"=====>"+s);
										if (s.indexOf("是□") != -1) {
											iscontinue = false;
											System.out.println(iii + "个表格退出，不再处理");
										}
										if (iscontinue) {

											String sss = s.replace("", "");
											// System.out.println(i+"行====>>"+sss);
											if (!list.containsKey(i)) {
												if (sss.equals("")) {
													if (list.get(i - 1) != null && !list.get(i - 1).trim().equals(",")) {
														// error.println("list.get("+(i-1)+")=>>>>"+list.get(i-1));

														sss = list.get(i - 1).split(",")[0];
													}
												}
												list.put(i, sss);

											} else {
												String ss = list.get(i).toString();
												list.put(i, ss + "," + sss);
											}
										}
									}
								}
							} // end for
						} // end for
					} // end for
					System.out.println("----------------->>>" + cardno + ",,,,," + username);

					String realcardno = cardno.trim().replace("　", "").replace("", "").split(":|：")[1];
					String realusername = username.trim().replace("　", "").replace("", "").replace("男", "").replace(
							"女", "").split(":|：")[1];

					String dbresult = getUser(realcardno);
					info.println("文件:" + file + "=>" + realcardno + "=>" + realusername);

					// System.out.println(realcardno + ",,," + realusername);
					if (dbresult == null || dbresult.equals("")) {
						warn.println(realusername + "=>" + realcardno + "没有找到对应的人员=>"+file.getName());
					} else {

						Iterator<Integer> iterator = list.keySet().iterator();
						while (iterator.hasNext()) {
							int i = iterator.next();
							String s = list.get(i).replace("", "");

							String[] ss = s.split(",");
							if (ss.length > 1 && ss[1] != null && !ss[1].equals("")) {

								// System.out.println(i + "=====>" + s);

								String date = ss[0].replace(".", "-").replace(" ", "-").replace("　", "-").replace("、",
										"-");

								if (date.equals("")) {
									String[] ssss = dbresult.split(",");

									date = ssss[ssss.length - 1].substring(0, 10);
									// System.out.println("====MMMMM"+date+"<<<====");
								}
								if (!date.startsWith("20")) {
									date = "2011-" + date;
								}
								Date d = null;
								try {
									d = df.parse(date);
								} catch (Exception e) {
									try {
										d = df1.parse(date);
									} catch (Exception ee) {
										d = df2.parse(date);
									}
								}

								date = dfminute.format(d);
								String comments = ss[1];
								insert(stmt, dbresult, date, comments);

							}
						}
					}

				} catch (Exception e) {
					error.println(file.getAbsolutePath() + "=>第" + iii + "个表格处理失败=>" + e + "=>" + cardno);
					e.printStackTrace();
				}

			} // end while

		} catch (Exception e) {
			e.printStackTrace();
			error.println(file.getAbsolutePath() + "解析失败");
		}
	}// end method

	// 2011-0501

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat df1 = new java.text.SimpleDateFormat("yyyy-MMdd");
	private static final DateFormat df2 = new java.text.SimpleDateFormat("yyyyMMdd");
	private static final DateFormat dfminute = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static void testWord1() {
		try {
			// word 2003： 图片不会被读取
			InputStream is = new FileInputStream(new File("D:\\sinye.doc"));
			WordExtractor ex = new WordExtractor(is);
			String text2003 = ex.getText();
			System.out.println(text2003);
			// word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
			OPCPackage opcPackage = POIXMLDocument.openPackage("D:\\sinye.doc");
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			String text2007 = extractor.getText();
			System.out.println(text2007);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}