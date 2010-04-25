/**
 * Test.java
 */
package com.abc;

/**
 * @author 华锋
 * Apr 5, 20109:45:12 PM
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		cmb.netpayment.Security s = new cmb.netpayment.Security(
		"E:/personal/.metadata/.plugins/com.genuitec.eclipse.easie.tomcat.myeclipse/tomcat/webapps/bankpay/WEB-INF/classes/com/cmb/public.key");

		String ss="58|114|65|250|93|101|160|223|107|43|58|222|144|56|128|108|91|211|51|204|113|84|105|140|130|216|148|65|136|10|174|79|47|185|135|191|105|52|247|183|183|37|104|231|119|255|164|35|85|18|96|241|154|65|54|209|53|236|219|109|227|188|6|150|";
		String[] sss=ss.split("\\|");
		
		String params = "Succeed=Y&CoNo=000011&BillNo=000000&Amount=0.01&Date=20100405&Msg=09910000112010040500000000000000000000&signature="+ss;
		
		String sssss="&signature="+ss;
		System.out.println(sss.length);
		byte[] b=new byte[sss.length];
		for(int i=0;i<b.length;i++){
			b[i]=(byte)(Integer.parseInt(sss[i]));
//			System.out.println(b[i]);
		}
		
//		byte[] bytes = params.getBytes("gb2312");
boolean bb = s.checkInfoFromBank(params.getBytes("gb2312"));
System.out.println("签名结果:::" + bb);
	}

}
