/**
 * ElearningInterfaceServlet.java
 */

package com.changpeng.service;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.changpeng.common.BasicService;
import com.changpeng.common.context.Globals;
import com.changpeng.common.util.Base64;
import com.changpeng.jifen.service.LawyerlessonxfService;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.models.LogClientRequest;
import com.changpeng.models.SysUnionparams;

/**
 * <pre>
 * 和培训管理系统的接口
 * 用dom4j来解析xml
 * </pre>
 * 
 * @author 华锋 2008-5-12 上午10:37:46
 * 
 */
public class HzlawyersJfServlet extends HttpServlet {

	private static final Log LOG = LogFactory.getLog(HzlawyersJfServlet.class);

	public void init() throws ServletException {

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/*
	 * 这里要解析xml (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		InputStream is = request.getInputStream();
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		LOG.debug("杭州点睛积分请求消息:\r\n" + sb.toString());
		StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		result.append("<resps>\r\n");
		com.changpeng.models.LogClientRequest logclient = new LogClientRequest();
		logclient.setReqtime(new java.sql.Timestamp(System.currentTimeMillis()));
		logclient.setReqcontent(sb.toString());
		try {
			Document document = DocumentHelper.parseText(sb.toString());
			LOG.debug("document:::" + document);
			Element element = document.getRootElement();
			LOG.debug("element:::" + element.getName() + ",,,=" + element.attributeValue("name"));

			logclient.setCpuid(System.currentTimeMillis() + "");
			logclient.setGroupid(8078);
			logclient.setMethod("hzlawyerjf");

			Iterator iterator = element.elementIterator("learn");
			LawyerlessonxfService skservice = (LawyerlessonxfService) Globals.getBean("lawyerlessonxfService");
			BasicService basicService = (BasicService) Globals.getBean("basicService");
			SysUnionparams params = (SysUnionparams) basicService.get(SysUnionparams.class, 8078);

			// 根据用户选择的年份以及年审时间得到查询的起始终止时间段
			JifenTime jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, params.getNianshen());
			int theyear = jifentime.getNianshenyear();

			while (iterator.hasNext()) {
				Element learn = (Element) iterator.next();
				String id = learn.attributeValue("id");
				try {

					String _lawyerid = learn.elementText("lawyerid");
					String lawyername = learn.elementText("lawyername");
					String lessonid = learn.elementText("lessonid");
					String title = learn.elementText("lessonname");
					String maxjifen = learn.elementText("maxjifen");
					String date = learn.elementText("learndate");
					String jifen = learn.elementText("learnjifen");

					System.out.println(_lawyerid + "," + lawyername + "," + lessonid + "," + title + "," + maxjifen
							+ "," + jifen + ",");

					int djlessonid = Integer.parseInt(lessonid);
					float learnjifen = Float.parseFloat(jifen);
					Date learndate = df.parse(date);
//					int lawyerid = Integer.parseInt(_lawyerid);
					skservice.saveDianjingJifen(_lawyerid, lawyername, djlessonid, title, learnjifen, maxjifen,
							learndate, theyear);

					// <resps>
					// <resp id="post提交到我方的时候的听课id,即<learn>节点中的id属性"
					// value="成功或失败说明"/>
					// <resp id="post提交到我方的时候的听课id,即<learn>节点中的id属性"
					// value="成功或失败说明"/>
					// <resp id="post提交到我方的时候的听课id,即<learn>节点中的id属性"
					// value="成功或失败说明"/>
					// </resps>

					// <learn id="在你方系统的一个听课序号，唯一，response消息的时候带回">
					// <lawyerid> 通过上面的url传送过去的律师id </lawyerid>
					// <lawyername> 通过上面的url传送过去的律师名字 </lawyername>
					// <lessonid> 在你方系统该课程的编号 </lessonid>
					// <lessonname> 在你方系统该课程的名称 </lessonname>
					// <maxjifen> 该课程的最大积分 </maxjifen>
					// <learnjifen> 该课程已学习积分
					// </learnjifen>(如果听10分钟积0.1分并调用一次接口，则第10分钟时传递的积分值为0.1，第60分钟的时候传递的积分值为0.6。依此类推)
					// <learndate> 课程的学习时间 </learndate>
					// </learn>
					result.append("<resp id=\"" + id + "\" value=\"成功\">").append("</resp>");
				} catch (Exception e) {
					result.append("<resp id=\"" + id + "\" value=\"失败:" + e.getMessage() + "\">").append("</resp>");
				}
			}
			result.append("</resps>\r\n");

		} catch (Exception e) {
			result.append("<resps>");
			result.append("<resp id=\"0\" value=\"系统异常:" + e.getMessage() + "\">").append("</resp>");
			result.append("</resps>");
			LOG.error("异常:::" + e);
		}

		finally {
			logclient.setRestime(new java.sql.Timestamp(System.currentTimeMillis()));
			logclient.setRescontent(result.toString());

			BasicService basicService = (BasicService) Globals.getBean("basicService");
			try {
				basicService.save(logclient);
			} catch (Exception e) {
				LOG.error("保存log信息失误", e);
			}
			LOG.debug("返回消息:\r\n" + result);

			response.setCharacterEncoding("utf-8");
			OutputStream os = response.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write(result.toString());
			writer.flush();
			writer.close();
		}
	}

	public static void main(String[] args) throws Exception {

		String name = "刘0华锋1";
		int lawyerids = 123452;
		String url = "";
		url += "?stoken=" + new String(Base64.encode((lawyerids + "|" + name).getBytes("gb2312")), "gb2312");

		System.out.println(url);

		String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?><learns><learn id=\"1\"><lawyerid>11</lawyerid><lawyername>111的名字</lawyername><lessonid>22</lessonid><lessonname>在你方系统该课程的名称</lessonname><maxjifen>5</maxjifen><learnjifen>3</learnjifen><learndate>2010-3-15</learndate></learn><learn id=\"2\"><lawyerid>111</lawyerid><lawyername>1111的名字</lawyername><lessonid>222</lessonid><lessonname>在你方系统该课程的名称</lessonname><maxjifen>5</maxjifen><learnjifen>3</learnjifen><learndate>2010-4-15</learndate></learn></learns>";

		Document document = DocumentHelper.parseText(s);
		LOG.debug("document:::" + document);
		Element element = document.getRootElement();

		// Element skrecselement = element.element("learns");
		Iterator iterator = element.elementIterator("learn");
		// LawyerlessonxfService skservice = (LawyerlessonxfService)
		// Globals.getBean("lawyerlessonxfService");
		// BasicService basicService = (BasicService)
		// Globals.getBean("basicService");
		// SysUnionparams params = (SysUnionparams)
		// basicService.get(SysUnionparams.class, 8078);

		// 根据用户选择的年份以及年审时间得到查询的起始终止时间段
		// JifenTime jifentime =
		// com.changpeng.jifen.util.CommonDatas.getJifenTime(0,
		// params.getNianshen());
		// int theyear = jifentime.getNianshenyear();
		int theyear = 2000;

		while (iterator.hasNext()) {
			Element learn = (Element) iterator.next();
			String id = learn.attributeValue("id");
			try {

				String _lawyerid = learn.elementText("lawyerid");
				String lawyername = learn.elementText("lawyername");
				String lessonid = learn.elementText("lessonid");
				String title = learn.elementText("lessonname");
				String maxjifen = learn.elementText("maxjifen");
				String date = learn.elementText("learndate");
				String jifen = learn.elementText("learnjifen");

				System.out.println(_lawyerid + "," + lawyername + "," + lessonid + "," + title + "," + maxjifen + ","
						+ jifen + ",");

				int djlessonid = Integer.parseInt(lessonid);
				float learnjifen = Float.parseFloat(jifen);
				Date learndate = df.parse(date);
				int lawyerid = Integer.parseInt(_lawyerid);
				// skservice
				// .saveDianjingJifen(lawyerid, lawyername, djlessonid, title,
				// learnjifen,maxjifen, learndate, theyear);

				// <resps>
				// <resp id="post提交到我方的时候的听课id,即<learn>节点中的id属性"
				// value="成功或失败说明"/>
				// <resp id="post提交到我方的时候的听课id,即<learn>节点中的id属性"
				// value="成功或失败说明"/>
				// <resp id="post提交到我方的时候的听课id,即<learn>节点中的id属性"
				// value="成功或失败说明"/>
				// </resps>

				// <learn id="在你方系统的一个听课序号，唯一，response消息的时候带回">
				// <lawyerid> 通过上面的url传送过去的律师id </lawyerid>
				// <lawyername> 通过上面的url传送过去的律师名字 </lawyername>
				// <lessonid> 在你方系统该课程的编号 </lessonid>
				// <lessonname> 在你方系统该课程的名称 </lessonname>
				// <maxjifen> 该课程的最大积分 </maxjifen>
				// <learnjifen> 该课程已学习积分
				// </learnjifen>(如果听10分钟积0.1分并调用一次接口，则第10分钟时传递的积分值为0.1，第60分钟的时候传递的积分值为0.6。依此类推)
				// <learndate> 课程的学习时间 </learndate>
				// </learn>

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}