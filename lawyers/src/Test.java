/**
 * 
 */

import java.util.Date;

import net.sourceforge.pinyin4j.*;
import net.sourceforge.pinyin4j.format.*;
import net.sourceforge.pinyin4j.format.exception.*;

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
		
		Date date=new Date();
		date.setTime(1255881610723L);
		System.out.println(date);
	}

}
