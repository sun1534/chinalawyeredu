/**
 * OrderService.java
 */
package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

/**
 * 
 * @author 刘哈哈 Mar 30, 20115:37:27 PM
 * 
 */
public class OrderConstant  {
	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(OrderConstant.class);

	// 新增车牌号信息的命令字
	public static String ORDER_MO_CONTENT = "A#车牌号#车牌类型(黄/黑/蓝)#地市电话区号";
	// 修改区域信息的命令字
	public static String AREACODE_MO_CONTENT = "B#地市电话区号";
	// 修改车牌号的命令字
	public static String CHEPAI_MO_CONTENT = "C#新车牌#车牌类型(黄/黑/蓝)";
	// 修改车牌类型的命令字
	public static String CHEPAI_TYPE_MO_CONTENT = "D#车牌类型"; //1、蓝牌2、黄牌
	// 业务暂停时候发送的提醒短信
	public static String ORDER_PAUSE_CONTENT = "您已经成功暂停该业务";
	//业务退订时候发送的提醒短信
	public static String ORDER_CANCEL_CONTENT = "您已成功取消新疆宽洋汽车保姆体验业务，感谢您的使用，询09915881229";
	//没有进行业务订购上行mo短信时候的提醒短信
	public static String NOT_ORDER = "对不起,您没有订购电子警察的业务,请发送内容到进行订购";
	//命令字处理失败的时候的提醒短信
	public static String MO_DO_ERROR="对不起,您上传的命令字处理失败,请重新上传";
	//行政区号不正确时候下发的提醒短信
	public static String MO_AREACODE_ERROR="对不起,您上传的行政区号不正确,请上传新疆某地州的电话区号，比如上传B#0991";
	//车牌类型不正确时候下发的提醒短信
	public static String MO_CHEPAITYPE_ERROR="对不起,您上传的车牌类型不正确。正确上传的内容为L#蓝或者L#黑或者L#黄";
	//命令字不正确的时候的提醒短信
	public static String MO_ERROR="对不起,您上传的命令字不正确,请重新上传";
	//命令字处理正确时候的提醒短信
	public static String MO_HANDLE_OK="您好，您的短信命令字业务处理成功";
	
	public static String SPID = "31100215";
	public static String SPPWD = "2688229";
	public static String DZJC_PRODUCTID = "131100215010000000933";
	public static String TY_DZJC_PRODUCTID = "131100215010000000991";
	public static String QNAME = "http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1";
	
	

	public static String DEFAULT_TABLE_DATABASE = "telcome";
	public static String DEFAULT_MEMBER_TABLE = "members";

	//车牌类型信息
	public static Map<String, String> MEMBER_CHEPAI = new LinkedHashMap<String, String>();
	public static Map<String, String> MEMBER_CHEPAI_NAME = new LinkedHashMap<String, String>();
	
	//区号和地区名称的匹配
	public static Map<String, String> AREA_DATABASE = new LinkedHashMap<String, String>();
	
	public static List<String> PROVINCE_FIRST=new ArrayList<String>(); 
	public static List<String> CHEPAI_FIRST=new ArrayList<String>(); 
	//地区名称和区号的匹配
	public static Map<String, String> CITY_DATABASE = new LinkedHashMap<String, String>();
	
	public static void main(String[] args){
		System.out.println(CHEPAI_FIRST);
	}
	
	static {
		for(char s='A';s<='Z';){
			CHEPAI_FIRST.add(s+"");
			s=(char)(s+1);
		}
		for(char s='a';s<='z';){
			CHEPAI_FIRST.add(s+"");
			s=(char)(s+1);
		}
		CHEPAI_FIRST.add("京");
		CHEPAI_FIRST.add("湘");
		CHEPAI_FIRST.add("津");
		CHEPAI_FIRST.add("鄂");
		CHEPAI_FIRST.add("沪");
		CHEPAI_FIRST.add("粤");
		CHEPAI_FIRST.add("渝");
		CHEPAI_FIRST.add("琼");
		CHEPAI_FIRST.add("冀");
		CHEPAI_FIRST.add("川");
		CHEPAI_FIRST.add("晋");
		CHEPAI_FIRST.add("黔");
		CHEPAI_FIRST.add("辽");
		CHEPAI_FIRST.add("云");
		CHEPAI_FIRST.add("滇");
		CHEPAI_FIRST.add("吉");
		CHEPAI_FIRST.add("陕");
		CHEPAI_FIRST.add("秦");
		CHEPAI_FIRST.add("黑");
		CHEPAI_FIRST.add("甘");
		CHEPAI_FIRST.add("陇");
		CHEPAI_FIRST.add("苏");
		CHEPAI_FIRST.add("青");
		CHEPAI_FIRST.add("浙");
		CHEPAI_FIRST.add("台");
		CHEPAI_FIRST.add("皖");
		CHEPAI_FIRST.add("藏");
		CHEPAI_FIRST.add("闽");
		CHEPAI_FIRST.add("蒙");
		CHEPAI_FIRST.add("赣");
		CHEPAI_FIRST.add("桂");
		CHEPAI_FIRST.add("鲁");
		CHEPAI_FIRST.add("宁");
		CHEPAI_FIRST.add("豫");
		CHEPAI_FIRST.add("新");
		
		PROVINCE_FIRST.add("京");
		PROVINCE_FIRST.add("湘");
		PROVINCE_FIRST.add("津");
		PROVINCE_FIRST.add("鄂");
		PROVINCE_FIRST.add("沪");
		PROVINCE_FIRST.add("粤");
		PROVINCE_FIRST.add("渝");
		PROVINCE_FIRST.add("琼");
		PROVINCE_FIRST.add("冀");
		PROVINCE_FIRST.add("川");
		PROVINCE_FIRST.add("晋");
		PROVINCE_FIRST.add("黔");
		PROVINCE_FIRST.add("辽");
		PROVINCE_FIRST.add("云");
		PROVINCE_FIRST.add("滇");
		PROVINCE_FIRST.add("吉");
		PROVINCE_FIRST.add("陕");
		PROVINCE_FIRST.add("秦");
		PROVINCE_FIRST.add("黑");
		PROVINCE_FIRST.add("甘");
		PROVINCE_FIRST.add("陇");
		PROVINCE_FIRST.add("苏");
		PROVINCE_FIRST.add("青");
		PROVINCE_FIRST.add("浙");
		PROVINCE_FIRST.add("台");
		PROVINCE_FIRST.add("皖");
		PROVINCE_FIRST.add("藏");
		PROVINCE_FIRST.add("闽");
		PROVINCE_FIRST.add("蒙");
		PROVINCE_FIRST.add("赣");
		PROVINCE_FIRST.add("桂");
		PROVINCE_FIRST.add("鲁");
		PROVINCE_FIRST.add("宁");
		PROVINCE_FIRST.add("豫");
		PROVINCE_FIRST.add("新");
		
		MEMBER_CHEPAI.put("蓝", "1");
		MEMBER_CHEPAI.put("黄", "2");
		MEMBER_CHEPAI.put("黑", "3");
		
		MEMBER_CHEPAI_NAME.put("1", "蓝");
		MEMBER_CHEPAI_NAME.put("2", "黄");
		MEMBER_CHEPAI_NAME.put("3", "黑");
		
		AREA_DATABASE.put("0901","塔城");//
		AREA_DATABASE.put("0902","哈密");//
		AREA_DATABASE.put("0903","和田");//
		AREA_DATABASE.put("0906","阿勒泰");//
		AREA_DATABASE.put("0908","阿图什");//
		AREA_DATABASE.put("0909","博乐");//
		AREA_DATABASE.put("0990","克拉玛依");//
		AREA_DATABASE.put("0991","乌鲁木齐");//
		AREA_DATABASE.put("0992","奎屯");//
		AREA_DATABASE.put("0992","乌苏");//
		AREA_DATABASE.put("0993","石河子");//
		AREA_DATABASE.put("0994","昌吉");//
		AREA_DATABASE.put("0995","吐鲁番");//
		AREA_DATABASE.put("0996","库尔勒");//
		AREA_DATABASE.put("0997","阿克苏");//
		AREA_DATABASE.put("0998","喀什");
		AREA_DATABASE.put("0999","伊宁");
		
		CITY_DATABASE.put("伊宁", "0999");
		CITY_DATABASE.put("伊犁", "0999");
		CITY_DATABASE.put("塔城",    "0901" );
		CITY_DATABASE.put("哈密",    "0902" );
		CITY_DATABASE.put("和田",    "0903" );
		CITY_DATABASE.put("阿勒泰",  "0906");//
		CITY_DATABASE.put("阿图什",  "0908");//
		CITY_DATABASE.put("博乐",    "0909" );
		CITY_DATABASE.put("克拉玛依","0990" );
		CITY_DATABASE.put("乌鲁木齐","0991" );
		CITY_DATABASE.put("奎屯",    "0992");//
		CITY_DATABASE.put("乌苏",    "0992" );
		CITY_DATABASE.put("石河子",  "0993" );
		CITY_DATABASE.put("昌吉",    "0994" );
		CITY_DATABASE.put("吐鲁番",  "0995");//
		CITY_DATABASE.put("库尔勒",  "0996");//
		CITY_DATABASE.put("巴州",  "0996");//
		CITY_DATABASE.put("阿克苏","0997" );//
		CITY_DATABASE.put("喀什","0998" );//

		//没巴州，高支队，伊犁,邦士


	}
}