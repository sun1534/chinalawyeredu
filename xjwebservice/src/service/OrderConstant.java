/**
 * OrderService.java
 */
package service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;

import sms.SetSOAPHeaderHelper;

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
	// 业务暂停时候发送的提醒短信
	public static String ORDER_PAUSE_CONTENT = "您已经成功暂停该业务";
	//业务退订时候发送的提醒短信
	public static String ORDER_CANCEL_CONTENT = "您已经成功退订该业务";
	//没有进行业务订购上行mo短信时候的提醒短信
	public static String NOT_ORDER = "对不起,您没有订购电子警察的业务,请发送内容到进行订购";
	//命令字处理失败的时候的提醒短信
	public static String MO_DO_ERROR="对不起,您上传的命令字处理失败,请重新上传";
	//行政区号不正确时候下发的提醒短信
	public static String MO_AREACODE_ERROR="对不起,您上传的行政区号不正确,请上传新疆某地州的电话区号";
	//命令字不正确的时候的提醒短信
	public static String MO_ERROR="对不起,您上传的命令字不存在,请重新上传";
	//命令字处理正确时候的提醒短信
	public static String MO_HANDLE_OK="您好，您的短信命令字业务处理成功";
	
	public static String SPID = "31100215";
	public static String SPPWD = "2688229";
	public static String DZJC_PRODUCTID = "131100215010000000933";
	public static String QNAME = "http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1";
	
	

	public static String DEFAULT_TABLE_DATABASE = "telcome";
	public static String DEFAULT_MEMBER_TABLE = "members";

	//车牌类型信息
	public static Map<String, Integer> MEMBER_CHEPAI = new LinkedHashMap<String, Integer>();
	//区号和地区名称的匹配
	public static Map<String, String> AREA_DATABASE = new LinkedHashMap<String, String>();


	//地区名称和区号的匹配
	public static Map<String, String> CITY_DATABASE = new LinkedHashMap<String, String>();
	static {
		
		MEMBER_CHEPAI.put("蓝", 1);
		MEMBER_CHEPAI.put("黄", 2);
		MEMBER_CHEPAI.put("黑", 3);
		MEMBER_CHEPAI.put("", -1);
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