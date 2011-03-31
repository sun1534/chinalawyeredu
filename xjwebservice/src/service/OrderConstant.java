/**
 * OrderService.java
 */
package service;

import org.apache.commons.logging.Log;

import sms.SetSOAPHeaderHelper;

/**
 * 
 * @author 刘哈哈 Mar 30, 20115:37:27 PM
 * 
 */
public class OrderConstant  {
	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(OrderConstant.class);

	// 新增车牌号信息等
	public static String ORDER_MO_CONTENT = "A#车牌号#车牌类型(黄/黑/蓝)#地市电话区号";
	// 修改区域信息等
	public static String AREACODE_MO_CONTENT = "B#地市电话区号";
	// 修改车牌号
	public static String CHEPAI_MO_CONTENT = "C#新车牌#车牌类型(黄/黑/蓝)";
	public static String ORDER_PAUSE_CONTENT = "您已经成功暂停该业务";
	public static String ORDER_CANCEL_CONTENT = "您已经成功退订该业务";
	public static String NOT_ORDER = "对不起,您没有订购电子警察的业务,请发送内容到进行订购";
	public static String MO_DO_ERROR="对不起,您上传的命令字处理失败,请重新上传";
	
	public static String SPID = "31100215";
	public static String SPPWD = "2688229";
	public static String DZJC_PRODUCTID = "131100215010000000933";
	public static String QNAME = "http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1";
}