/**
 * test.java */


/**
 * @author 华锋 2008-5-5 下午01:55:04
 * 
 */
public class test {
	private static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println(System.currentTimeMillis() / 1000);
		
		System.out.println(com.changpeng.common.util.MD5.md5("123456"));
		// TODO Auto-generated method stub
		// Calendar calendar = Calendar.getInstance();
		// int nowmonth = calendar.get(Calendar.MONTH) + 1;
		// int nowday = calendar.get(Calendar.DATE);
		// int nowyear = calendar.get(Calendar.YEAR);
		//		
		// String nianshen = "05-30";
		// int index = nianshen.indexOf("-");
		// int shenmonth = Integer.parseInt(nianshen.substring(0, index));
		// int shenday = Integer.parseInt(nianshen.substring(index + 1));
		//		
		// System.out.println(nowmonth);
		// System.out.println(nowday);
		// System.out.println(nowyear);
		//		
		// System.out.println(shenmonth);
		//		
		// System.out.println(shenday);
		//
		//		
		//		
		// System.out.println(nowmonth * 100 + nowday);
		// System.out.println(shenmonth * 100 + shenday);

		// BasicDAO bd=(BasicDAO)Globals.getMainBean("basicDAO");
		// bd.findByQuery("select distinct a.lessons from Lessonshared a where
		// 1=1 and a.lessons.deleteflag=false", 15, 1);

		// SysUser s=new SysUser();
		// s.setProvinceid(1);
		// s.setCityid(3);
		// s.setOfficeid(32);
		//		
		// Class c=s.getClass();
		// // Field f=c.getField("officeid");
		//		
		//	
		// System.out.println( PropertyUtils.getSimpleProperty(s, "officeid"));
		// System.out.println( PropertyUtils.getProperty(s, "officeid"));

		// System.out.println(f.get);

	}

}
