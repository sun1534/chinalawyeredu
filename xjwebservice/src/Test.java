import service.OrderConstant;
import service.OrderService;

import common.Globals;

/**
 * Test.java
 */

/**
 * @author 刘哈哈 Mar 31, 20115:34:31 PM
 * 
 */
public class Test {
	public static void main(String[] args) throws Exception {

		int useridtype = 1;
		String mobile = "13510073023";
		String packageid = "-1";
		String productid = OrderConstant.DZJC_PRODUCTID;
		String streamno = System.currentTimeMillis() / 1000 + "";
		String remarks = "测试";

		OrderService orderService = (OrderService) Globals.getMainBean("orderService");

//		int r = orderService.order(mobile, packageid, productid, streamno, remarks, useridtype);
//		int r = orderService.cancel(mobile, productid);
		int r=orderService.updateDzjcOrderInfo(mobile, "新新ABCEF", "黑", "0990");
		System.out.println(r);
	}
}
