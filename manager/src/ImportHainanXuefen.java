import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.action.JifenbudengBatch;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lawyers;

/**
 * 
 */

/**
 * @author 华锋 Aug 22, 2009-1:11:33 PM
 * 
 */
public class ImportHainanXuefen {

	private static List<String> list=new ArrayList<String>();
	static{
		list.add("徐辉");
		
		list.add("李冠");
		
		list.add("白静");
		
		list.add("王邱");
		
		list.add("刘向");
		
		list.add("李扬");
		
		list.add("李昌");
		
		list.add("黎毅");
		
		list.add("向勇");
		
		list.add("杨静");
		
		list.add("刘畅");
		
		list.add("米威");
		
		list.add("郭峰");
		
		list.add("赵兵");
		
		list.add("张宁宁");
		
		list.add("杨佩萱");
		
		list.add("王照龙");
		
		list.add("陈蕾");
		
		list.add("林资");
		
		list.add("王亮");
		
		list.add("鲁珺");
		
		list.add("徐抒音");
		
		list.add("吴宇");
		
		list.add("张达雄");
		
		list.add("杨虹");
		
		list.add("吴镇");
		
		list.add("杨静");
		
		list.add("张明");
		
		list.add("张晓梅");
		
		list.add("丁伏生");
		
		list.add("王虎胜");
		
		list.add("胡怡");
		
		list.add("宫旭");
		
		list.add("陈凯");
		
		list.add("夏忠");
		
		list.add("赵慧荣");
		
		list.add("曾缨");
		
		list.add("吴岳");
		
		list.add("王蓬");
		
		list.add("朱达人");
		
		list.add("程颖");
		
		list.add("马晓东");
		
		list.add("田丽霞");
		
		list.add("邓永泉");
		
		list.add("王盼");
		
		list.add("张强");
		
		list.add("符天");
		
		list.add("许多");
		
		list.add("杜聪");
		
		list.add("粘慧姗");
		
		list.add("田英");
		
		list.add("黄忠");
		
		list.add("冯艺");
		
		list.add("赵卫");
		
		list.add("周超群");
		
		list.add("李君");
		
		list.add("周颖");
		
		list.add("叶斌");
		
		list.add("储一丰");
		
		list.add("姜玲");
		
		list.add("郭亚");
		
		list.add("吴峻");
		
		list.add("张宁");
		
		list.add("罗学礼");
		
		list.add("易强");
		
		list.add("习钦");
		
		list.add("赵颖");
		
		list.add("陈虎");
		
		list.add("吴兵");
		
		list.add("王厚文");
		
		list.add("段浩");
		
		list.add("伍建文");
		
		list.add("李琳");
		
		list.add("刘庚枢");
		
		list.add("李奋");
		
		list.add("冯怀东");
		
		list.add("钱开任");
		
		list.add("蓝毅");
		
		list.add("李明晶");
		
		list.add("唐元");
		
		list.add("吴泉景");
		
		list.add("周扬琼");
		
		list.add("张卫");
		
		list.add("王琳");
		
		list.add("伍奕");
		
		list.add("孟强");
		
		list.add("赵文");
		
		list.add("王萍");
		
		list.add("周全");
		
		list.add("林青");
		
		list.add("吴孔军");
		
		list.add("吴丽");
		
		list.add("吴华");
		
		list.add("莫金泽");
		
		list.add("谭梦龙");
		
		list.add("余照海");
		
		list.add("罗游");
		
		list.add("万明");
		
		list.add("杜鹃");
		
		list.add("高杰");
		
		list.add("刘鹏");
		
		list.add("付强");
		
		list.add("于福成");
		
		list.add("姜丹");
		
		list.add("专磊");
		
		list.add("符国骏");
		
		list.add("高翔");
		
		list.add("裴勇");
		
		list.add("张琳");
		
		list.add("马骏鹏");
		
		list.add("彭慧玲");
		
		list.add("李伟");
		
		list.add("郑杨");
		
		list.add("霍大庆");
		
		list.add("彭东");
		
		list.add("黄煊钰");
		
		list.add("黄景欢");
		
		list.add("陈晓");
		
		list.add("金薇");
		
		list.add("赵亮");
		
		list.add("陈平");
		
		list.add("谭娟");
		
		list.add("黄湘");
		
		list.add("罗艾");
		
		list.add("黄良芳");
		
		list.add("胡涛");
		
		list.add("袁芳");
		
		list.add("何壮");
		
		list.add("王永卫");
		
		list.add("韩非");
		
		list.add("刘利");
		
		list.add("廖晖");
		
		list.add("孟添兴");
		
		list.add("陈琪");
		
		list.add("纪翔");
		
		list.add("陈挺");
		
		list.add("张琳");
		
		list.add("吴蕾");
		
		list.add("李文健");
		
		list.add("初月平");
		
		list.add("林祥");
		
		list.add("谢梅");
		
		list.add("罗荣");
		
		list.add("邢逸");
		
		list.add("罗明");
		
		list.add("王令");
		
		list.add("林诗威");
		
		list.add("陶沛然");
		
		list.add("耿春辉");
		
		list.add("陈颖奇");
		
		list.add("石峰");
		
		list.add("殷允臣");
		
		list.add("吴光雄");
		
		list.add("贾雯");
		
		list.add("张丽");
		
		list.add("黄勇");
		
		list.add("汪东");
		
		list.add("张杰");
		
		list.add("姚立斌");
		
		list.add("黄雅雄");
		
		list.add("唐德锋");
		
		list.add("潘利军");
		
		list.add("王世春");
		
		list.add("朱正良");
		
		list.add("李红月");
		
		list.add("曹龙");
		
		list.add("江雯");
		
		list.add("陈望");
		
		list.add("刘红璐");
		
		list.add("张铁成");
		
		list.add("李铁");
		
		list.add("周成曜");
		
		list.add("李雯");
		
		list.add("姚瑞兰");
		
		list.add("李振安");
		
		list.add("杨清");
		
		list.add("王崇代");
		
		list.add("杨洪涛");
		
		list.add("刘溪");
		
		list.add("秦禹");
		
		list.add("郭涛");
		
		list.add("潘兵");
		
		list.add("张冰");
		
		list.add("徐斌");
		
		list.add("李曙光");
		
		list.add("徐德玲");
		
		list.add("王正佐");
		
		list.add("李明君");
		
		list.add("米蓉");
		
		list.add("单红英");
		
		list.add("洪新敏");
		
		list.add("汪宏娟");
		
		list.add("叶萍");
		
		list.add("陈颖奇");
		
		list.add("黄兵");
		
		list.add("苏鹏");
		
		list.add("陈明");
		
		list.add("田野");
		
		list.add("荆向俊");
		
		list.add("周兆刚");
		
		list.add("张健华");
		
		list.add("吴从锋");
		
		list.add("王志勇");
		
		list.add("黄胜");
		
		list.add("王功林");
		
		list.add("杨春海");
		
		list.add("严波");
		
		list.add("易勇");
		
		list.add("符剑");
		
		list.add("陈雄英");
		
		list.add("黄华朗");
		
		list.add("李勤峰");
		
		list.add("张蕾");
		
		list.add("贾颖");
		
		list.add("沈美萍");
		
		list.add("李方娇");
		
		list.add("王艳");
		
		list.add("邢柏峰");
		
		list.add("文博");
		
		list.add("郝良存");
		
		list.add("王磊");
		
		list.add("孙琴");
		
		list.add("卢秉英");

	}
	
	private static PrintWriter loglessons;
	private static PrintWriter logerror;
	private static Map<String, String> lawyersCertno = new HashMap<String, String>();
	private static Map<String, String> lawyersName = new HashMap<String, String>();
	private static Map<Integer, String> alllessons = new HashMap<Integer, String>();
	private static Map<Integer, String> alllessonDate = new HashMap<Integer, String>();
	private static Map<String, String> lawyersLawyerno = new HashMap<String, String>();
	private static Connection con = null;
	static String url = "jdbc:mysql://211.154.157.174:3306/saaspxxt?characterEncoding=utf-8";
	static String user = "saaspxxt";
	static String pass = "saaspxxt";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, pass);
		System.out.println(con);

		getLawyers();
		getXuefen();

	}

	private static 	Map<String, Lawyers> map = new HashMap<String, Lawyers>();
	
	static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-M-dd");


	public static void getLawyers() throws Exception {
		String ssql = "select * from lawyers where directunion=11002750";
		java.sql.Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ssql);
	
		while (rs.next()) {
			String name = rs.getString("lawyername");
			int theoffice = Integer.parseInt(rs.getString("theoffice"));
			int directunion = Integer.parseInt(rs.getString("directunion"));
			int provinceid = Integer.parseInt(rs.getString("provinceunion"));
			String lawyerno = rs.getString("lawyerno");
			int lawyerid = rs.getInt("lawyerid");
			Lawyers lawyer = new Lawyers();
			lawyer.setTheoffice(theoffice);
			lawyer.setDirectunion(directunion);
			lawyer.setProvinceid(provinceid);
			lawyer.setLawyerno(lawyerno);
			lawyer.setLawyerid(lawyerid);
			map.put(name, lawyer);
		}
		rs.close();
		stmt.close();

	}

	public static void getXuefen() throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d:\\jifenbudeng.xls"));
		// 补登内容标题(必填) 计分日期(非必填,不填默认为当天) 律所 姓名 补登积分年度(必填) 补登学分(必填) 是否计为现场培训(是/否)

		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);

		Iterator _ite = sheet.rowIterator();
		Map<JifenbudengBatch,Lawyers> maps=new HashMap<JifenbudengBatch,Lawyers>();

//		System.out.println(map);
		while (_ite.hasNext()) {
			HSSFRow row = (HSSFRow) _ite.next();
			String title = HSSFCellToString.toString(row.getCell(0));// ID
			// 补登内容标题(必填) 计分日期(非必填,不填默认为当天) 律所 姓名 补登积分年度(必填) 补登学分(必填)
			// 是否计为现场培训(是/否)

			if (title == null || title.equals(""))
				break;
			if (title.indexOf("补登内容标题") != -1)
				continue;
			String jfdate = HSSFCellToString.toString(row.getCell(1)).trim();
			String office = HSSFCellToString.toString(row.getCell(2)).trim();
			String name = HSSFCellToString.toString(row.getCell(3)).trim();

			String theyear = HSSFCellToString.toString(row.getCell(4)).trim();
			String xuefen = HSSFCellToString.toString(row.getCell(5)).trim();
			if(!list.contains(name)){
//				System.out.println(name+"=>已经处理了");
				continue;
			}
			Lawyers lawyer=map.get(name);
			if(lawyer!=null){
				int lawyerid=lawyer.getLawyerid();
				int officeid=lawyer.getTheoffice();
				int city=lawyer.getCityid();
				int province=lawyer.getProvinceid();
				
				
				JifenbudengBatch b=new JifenbudengBatch();
				b.setBudengdate(jfdate);
				b.setIslocal("是");
				b.setLawyerno(lawyer.getLawyerno());
				b.setTheyear(theyear);
				b.setTitle(title);
				b.setXuefen(xuefen);
				
				maps.put(b, lawyer);
			}else{
				System.out.println("姓名=>"+name+"<=没有对应的律师信息");
//				Sy
			}
		}
		
	List list=	saveJifenbudeng(maps);
	System.out.println(list);
		
	}

	@Transactional
	public static List<String> saveJifenbudeng(Map<JifenbudengBatch,Lawyers> budenglist) throws ServiceException {
		BasicDAO basicDao=(BasicDAO)Globals.getMainBean("basicDAO");
//		int provinceid=23;
//		int cityid=11002750;
		List<String> result = new ArrayList<String>();
		try {

			java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
			Iterator<JifenbudengBatch> b=budenglist.keySet().iterator();
//			for (JifenbudengBatch budengbatch : budenglist) {
			while(b.hasNext()){
				JifenbudengBatch budengbatch=b.next();
				Lawyers lawyer=budenglist.get(budengbatch);
				StringBuffer sb = new StringBuffer();
				float xuefen = 0f;
				Date budengdate = null;
				int theyear = 0;
				try {
					xuefen = Float.parseFloat(budengbatch.getXuefen());
				} catch (Exception e) {
					sb.append("学分数据错,应为数字类型,输入为:" + budengbatch.getXuefen() + "|||");
				}
//				try {
//					theyear = Integer.parseInt(budengbatch.getTheyear());
//				} catch (Exception e) {
//					sb.append("积分年度数据错,应为数字类型,输入为:" + budengbatch.getTheyear() + "|||");
//				}
				theyear=2010;
				try {

					budengdate = df.parse(budengbatch.getBudengdate());
				} catch (Exception e) {
					sb.append("积分日期数据错,应为2010-3-11的形式,输入为:" + budengbatch.getBudengdate() + "|||");
				}

				if (sb.length() == 0) {
				
					if (lawyer != null) {

						// 根据课程名得到课程的id

						Jifenbudeng budeng = new Jifenbudeng();
						Lawyerlessonxf xf = new Lawyerlessonxf();
						int lessonid = 0;
						// if (budengbatch.getIslocal().equals("否")) {
//						xf.setLearnmode(4);
						budeng.setIslocal(true);
						// } else {
						// Integer _lessonid =
						// com.changpeng.lessons.util.CommonDatas.ALL_LOCAL_LESSONS
						// .get(budengbatch.getTitle());
						// if (_lessonid != null)
						// lessonid = _lessonid.intValue();
						 xf.setLearnmode(1);
						 budeng.setIslocal(true);
						// }
						budeng.setLawyerno(budengbatch.getLawyerno());
						budeng.setTitle(budengbatch.getTitle());
						budeng.setTheyear(theyear);
						budeng.setBudengdate(budengdate);
						budeng.setXuefen(xuefen);
						budeng.setLessonid(lessonid);
						budeng.setBudengfrom(lessonid);

						budeng.setLawyerid(lawyer.getLawyerid());
						budeng.setLawyername(lawyer.getLawyername());
						budeng.setProvinceid(lawyer.getProvinceunion());
						budeng.setCityid(lawyer.getDirectunion());
						budeng.setOfficeid(lawyer.getTheoffice());
						budeng.setCreatetime(timestamp);
						budeng.setCreateuser("lhf");
						budeng.setCreateuserid(0);
						basicDao.save(budeng);

						xf.setLawyerid(budeng.getLawyerid());
						xf.setLawyername(budeng.getLawyername());

						xf.setLessonid(0 - budeng.getBudengid());

						xf.setZongjifen(budeng.getXuefen());
						xf.setPxxf(budeng.getXuefen());
						xf.setZongjifen(xf.getPxxf());
						xf.setRemarks(budeng.getCreateuser() + "批量补登的积分");
						xf.setProvinceid(lawyer.getProvinceunion());
						xf.setCityid(lawyer.getDirectunion());
						xf.setOfficeid(lawyer.getTheoffice());
						xf.setTitle(budeng.getTitle());
						xf.setLastupdate(budeng.getCreatetime());
						xf.setPxdate(budeng.getBudengdate());
						xf.setIsfull(true);
						xf.setTheyear(budeng.getTheyear());
						basicDao.save(xf);
					} else {
						result.add("第" + budengbatch.getExcelline() + "行执业证号不存在:" + budengbatch.getLawyerno());
					}
				} else {
					result.add("第" + budengbatch.getExcelline() + "行数据错误:" + sb.toString());
				}
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}