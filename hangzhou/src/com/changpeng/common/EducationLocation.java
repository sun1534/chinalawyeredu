/**
 * 
 */
package com.changpeng.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <pre>
 * </pre>
 * 
 * @author 华锋 2009-3-2-下午09:22:53
 */
public class EducationLocation {
	private static Log LOG = LogFactory.getLog(LessonSync.class);


	
	public static void getLocationEdu() {

		try {
			String response = sendEducationInfo();

			Document doc = DocumentHelper.parseText(response);
			Element root = doc.getRootElement();

			List elements = root.elements();

			// 每次都清掉后再更新数据
			com.changpeng.common.CommonDatas.AllLawyers.clear();
			for (Object obj : elements) {
				Element ele = (Element) obj;
				LOG.info(ele.attributeValue("id") + "=" + ele.getTextTrim());
				com.changpeng.common.CommonDatas.AllLawyers.put(ele.attributeValue("id"), ele.getTextTrim());
			}
		} catch (Exception e) {
			LOG.error("从服务端获取信息失败::" + e);
		}
	}

	private static String sendEducationInfo() throws IOException {
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		try {

			url = new URL(Constants.LOCATION_EDU_URL);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			// 超时设置
			httpurlconnection.setConnectTimeout(3 * 1000);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");
//			OutputStream out = httpurlconnection.getOutputStream();
//			 out.write("TEST".getBytes());
//			 out.flush();
//			 out.close();
			InputStream response = httpurlconnection.getInputStream();
		
			byte[] buffer = new byte[256];
			int numberRead;
			StringBuffer bf = new StringBuffer();
			while ((numberRead = response.read(buffer)) >= 0) {
				bf.append(new String(buffer, 0, numberRead));
			}
			LOG.debug("--------------------------");
			LOG.debug(bf.toString());
			LOG.debug("--------------------------");
			return bf.toString();
		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}
	}
	
	public static void main(String args[]){
		getLocationEdu();
	}
}
