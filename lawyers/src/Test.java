/**
 * 
 */

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.jifen.service.LxnetrecsService;
import com.changpeng.models.Lxnetrecs;

/**
 * @author 华锋
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

////		System.out.println(com.changpeng.common.util.MD5.md5("123456"));
//		
//		HanyuPinyinOutputFormat f=new HanyuPinyinOutputFormat();
//		
//		f.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//		f.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		f.setVCharType(HanyuPinyinVCharType.WITH_V);
//		
//		String[] s=PinyinHelper.toHanyuPinyinStringArray('b',f);
////		String[] s=PinyinHelper.toHanyuPinyinStringArray('');
//	
//		for(int i=0;i<s.length;i++){
//			System.out.println(s[i]);
//		}
		LxnetrecsService lxnetrecsService = (LxnetrecsService) Globals.getMainBean("lxnetrecsService");
		System.out.println(lxnetrecsService);
		BasicService basicService = (BasicService) Globals.getMainBean("basicService");
//		Lxnetrecs lxnetrecs = new Lxnetrecs();
		Lxnetrecs lxnetrecs = (Lxnetrecs) basicService.get(Lxnetrecs.class, 114069);
		lxnetrecs.setAllminutes(300f);
		lxnetrecs.setLasttime(new java.sql.Timestamp(System.currentTimeMillis()));
		lxnetrecs.setLessonid(93);
		lxnetrecs.setLookedminutes(300f);
		lxnetrecs.setUserid(1426);
		lxnetrecs.setJifenyear(2009);
		lxnetrecs.setNowyear(2009);
//		float huodexuefen = lxnetrecsService.saveLxnetrecs(lxnetrecs);
		float huodexuefen = lxnetrecsService.updateLxnetrecs(lxnetrecs);
		int netrecsid = lxnetrecs.getNetrecsid();
		
		
		System.out.println(huodexuefen);
		
		System.out.println(netrecsid);
	}

}
