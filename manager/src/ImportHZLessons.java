import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 
 */

/**
 * @author 华锋 Aug 22, 2009-1:11:33 PM
 * 
 */
public class ImportHZLessons {

	private static PrintWriter loglessons;

	public static void main(String[] args) throws Exception {

		importGroups();
	}

	public static void importGroups() throws Exception {


		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("c:\\0809lessons.xls"));

		loglessons = new PrintWriter(new FileOutputStream("c:\\lessons.sql", true), true);
		Map list = new HashMap();
		
		
	
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			Iterator _ite = sheet.rowIterator();

//			ID	CS_Year	CS_Name	CS_Time	Address	Teacher	Kind	Flag	

			String sql="insert into lessons(lessonid,title,lessonstate,teachers,lessontype,xuefen,lessondate,lessonaddress,lessoncontent,maxpersons,notexistfen,fenshuoff,createuser,createtime,from_addr,deleteflag,onlineorlocal,teachertype) values(";
			
			int xuefen=4;
			int lessonstate=1;
			int lessontype=1;
			
			int maxpersons=0;
			int notexistxuefen=0;
			int fenshuoff=100;
			int createuser=0;
			String deleteflag="N";
			String onlineorlocal="local";
			int teachertype=1;
			String createtime="2009-9-4 23:19:12";
			String from_addr="hz";
//			DateFormat df=new java.text.SimpleDateFormat("M.dd");
			while (_ite.hasNext()) {
				HSSFRow row = (HSSFRow) _ite.next();
				String id = HSSFCellToString.toString(row.getCell(0));// ID

				if (id == null || id.equals(""))
					break;
				if (id.equals("ID"))
					continue;

				String CS_Year = HSSFCellToString.toString(row.getCell(1));
				String CS_Name = HSSFCellToString.toString(row.getCell(2));
				String CS_Time = HSSFCellToString.toString(row.getCell(3));
				String Address = HSSFCellToString.toString(row.getCell(4));
				String Teacher = HSSFCellToString.toString(row.getCell(5));
				String Kind = HSSFCellToString.toString(row.getCell(6));
				String Flag=HSSFCellToString.toString(row.getCell(7));

				
				String date=CS_Time.replaceAll("上午", "");
//				System.out.println(date);
				date=date.replaceAll("下午", "");
//				System.out.println(date);
				date=date.replace('.', '-');
			
			String lessondate=CS_Year+"-"+date;
//			System.out.println(lessondate);
				String lessoncontent=CS_Name;
				int lessonid=(int)(1000+Float.parseFloat(id));
				
				
			String tmp=sql+lessonid+",'"+CS_Name+"','"+lessonstate+"','"+Teacher+"','"+lessontype+"'," +
					"'"+xuefen+"','"+lessondate+"','"+Address+"','"+lessoncontent+"','"+maxpersons+"','"+notexistxuefen+"','"+fenshuoff+"','"+createuser+"','"+createtime+"','"+from_addr+"','"+deleteflag+"','"+onlineorlocal+"','"+teachertype+"')";				
				System.out.println(id+"->"+CS_Year+"->"+CS_Name+"->"+CS_Time+"->"+Address+"->"+Teacher+"->"+Kind+"->"+Flag);
				
				loglessons.println(tmp);
				
			}
			loglessons.flush();
			loglessons.close();
		
	}
}