/**
 * ApiQuery.java
 */
package com.icbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author 华锋 May 31, 20104:59:13 PM
 * 
 */
public class ApiQuery {

	/**
	 * 商城证书编号
	 */
	private static final String MER_ID = "3002EC23478693";
	/**
	 * 商城证书的银行帐号
	 */
	private static final String MER_ACCOUNT = "3002016929200112801";
	private static final String QUERY_URL = "https://corporbank.icbc.com.cn/servlet/ICBCINBSEBusinessServlet";
	/**
	 * 查询证书的地址,请设置为真实的物理路径
	 */
	private static final String PFX_PATH = "E:/personal/bankpay/src/com/icbc/common/jxkj2b2c.pfx";
	/**
	 * 查询证书的密钥,分配的为12345678
	 */
	private static final String PFX_PASSWORD = "12345678";

	/**
	 * 
	 * @param orderId
	 *            订单号
	 * @param orderDate
	 *            订单号的日期
	 * @return ApiQueryResult对象
	 * 
	 * <pre>
	 *         如果订单不存在或者有异常情况等,根据ApiQueryResult对象的respId的定义来
	 *         respId的定义如下：
	 *         =0,接口返回成功的xml数据，ApiQueryResult对象的ApiQueryResp属性非空
	 *         -1,接口返回空值
	 *         -2,解析返回的xml数据有错
	 *         -3,发送查询请求失败
	 *         &gt;0,查看APIQueryStat的QUERY_RESULT的key定义
	 *         
	 * </pre>
	 */
	public ApiQueryResult query(String orderId, String orderDate) {
		ApiQueryResult result = new ApiQueryResult();
		String xml = "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><ICBCAPI><in><orderNum>" + orderId
				+ "</orderNum><tranDate>" + orderDate + "</tranDate><ShopCode>" + MER_ID + "</ShopCode><ShopAccount>"
				+ MER_ACCOUNT + "</ShopAccount></in></ICBCAPI>";

		try {
			String url = QUERY_URL;

			String s1 = sendPost(url, "&APIName=EAPI&APIVersion=001.001.002.001&MerReqData=" + xml);
			s1 = java.net.URLDecoder.decode(s1, "GBK");

			System.out.println("支付查询返回结果信息:" + s1);

			if (s1.equals("")) {
				result.setRespId(-1);
				result.setRespMsg("接口返回空值");
			} else if (isNum(s1)) {
				result.setRespId(Integer.parseInt(s1));
				result.setRespMsg(ApiQueryStat.QUERY_RESULT.get(s1.trim()));
			} else {
				try {
					// dom4j来解析这个xml数据
					Document document = DocumentHelper.parseText(s1);
					Element element = document.getRootElement();
					Iterator iterator = element.elementIterator("out");
					while (iterator.hasNext()) {
						Element learn = (Element) iterator.next();
						String tranSerialNum = learn.attributeValue("tranSerialNum");

						String tranStat = learn.elementText("tranStat");
						String bankRem = learn.elementText("bankRem");
						String amount = learn.elementText("amount");
						String currType = learn.elementText("currType");
						String tranTime = learn.elementText("tranTime");
						// String shopAccount =
						// learn.elementText("ShopAccount");
						// String payeeName = learn.elementText("PayeeName");
						String joinFlag = learn.elementText("JoinFlag");
						String merJoinFlag = learn.elementText("MerJoinFlag");
						String custJoinFlag = learn.elementText("CustJoinFlag");
						String custJoinNum = learn.elementText("CustJoinNum");
						String certID = learn.elementText("CertID");

						ApiQueryResp resp = new ApiQueryResp();
						resp.setAmount(amount);
						resp.setBankRem(bankRem);
						resp.setCertID(certID);
						resp.setCurrType(currType);
						resp.setCustJoinFlag(custJoinFlag);
						resp.setCustJoinNum(custJoinNum);
						resp.setJoinFlag(joinFlag);
						resp.setMerJoinFlag(merJoinFlag);
						resp.setTranSerialNum(tranSerialNum);
						resp.setTranStat(tranStat);
						resp.setTranTime(tranTime);

						// System.out.println(payeeName + ",," + shopAccount);
						result.setQueryResp(resp);
						result.setRespMsg("处理成功");
					}
				} catch (Exception e) {
					e.printStackTrace();
					result.setRespId(-2);
					result.setRespMsg(s1 + "的xml解析有误\r\n:" + e.getMessage());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setRespId(-3);
			result.setRespMsg("查询请求失败:" + e.getMessage());
		}

		return result;
	}

	private HostnameVerifier hnv = new HostnameVerifier() {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};

	private TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
			return;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
			return;
		}
	} };

	/**
	 * 
	 * @param url
	 *            工行提供的查询url
	 * @param param
	 *            发送到url的参数
	 * @return 接口返回的数据
	 */
	public String sendPost(String url, String param) throws Exception {
		String keyf = PFX_PATH;
		String pass = PFX_PASSWORD;
		SSLSocketFactory ssf = null;

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		HttpsURLConnection conn = null;
		try {
			// init context
			SSLContext ctx = SSLContext.getInstance("TLS");
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			// KeyStore ks=KeyStore.getInstance( "PKCS12" ) ;
			KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(new FileInputStream(keyf), pass.toCharArray());
			kmf.init(ks, pass.toCharArray());
			ctx.init(kmf.getKeyManagers(), trustAllCerts, null);
			ssf = ctx.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
			HttpsURLConnection.setDefaultHostnameVerifier(hnv);
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpsURLConnection) realUrl.openConnection();

			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 ( compatible; MSIE 6.0; Windows NT 5.1; SV1)");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		}
		// catch (Exception e) {
		// System.out.println("发送POST请求出现异常！" + e);
		// e.printStackTrace();
		// }
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private static String REGIX_NUM = "^[0-9]+$";
	private static Pattern PATTERN = Pattern.compile(REGIX_NUM);

	private static boolean isNum(String result) {
		Matcher matcher = PATTERN.matcher(result);
		boolean b = matcher.matches();
		return b;

	}

	/**
	 * 测试代码部分
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ApiQuery q = new ApiQuery();
		String orderId = "01005202114469436525";
		String orderDate = "20100520";
		ApiQueryResult result = q.query(orderId, orderDate);
		if (result.getRespId() == 0) {
			ApiQueryResp resp = result.getQueryResp();
			// 判断支付情况,resp.getTranStat().equals("1")表示成功
			if (resp.getTranStat() != null && resp.getTranStat().equals("1")) {
				System.out.println("支付成功::::" + ApiQueryStat.PAY_RESULT.get(resp.getTranStat()));
			} else
				System.out.println("支付失败::::" + ApiQueryStat.PAY_RESULT.get(resp.getTranStat()));
		} else {
			System.out.println(result.getRespId() + "=>" + result.getRespMsg());
		}
	}
}
