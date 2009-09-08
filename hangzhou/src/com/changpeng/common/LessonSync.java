package com.changpeng.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonsoflog;

public class LessonSync extends TimerTask {
	private static Log LOG = LogFactory.getLog(LessonSync.class);

	// private static final Element SyncElement;
	// static{
	// SyncElement=DocumentHelper.createElement("LessonsSyncRequest");
	// SyncElement.addElement("fromAddr").addText(Constants.FROMADDR);
	// }

	// 创建请求元素
	private Element createElement() {
		Element element = DocumentHelper.createElement("LessonsSyncUpdate");
		element.addElement("flag");
		element.addElement("lessonid");
		element.addElement("title");
		element.addElement("teachertype");
		element.addElement("teachers");
		element.addElement("lessontype");
		element.addElement("lessondesc");
		element.addElement("xuefen");
		element.addElement("lessondate");
		element.addElement("lessoncontent");
		element.addElement("onlinefile");
		element.addElement("fenshuoff");
		element.addElement("shared");
		element.addElement("fromAddr");
		element.addElement("lessonidOfserver");
		element.addElement("onlineorlocal");
		return element;
	}



	@SuppressWarnings("unchecked")
	public void run() {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		BasicService service = (BasicService) wac.getBean("basicService");
		try {
			LOG.info("更新服务端培训系统地市信息..."+Constants.FROMADDR);
			EducationLocation.getLocationEdu();
			LOG.info("更新服务端课程信息..."+Constants.FROMADDR);
			sendUpdateInfo(service);
			LOG.info("同步服务端课程信息..."+Constants.FROMADDR);
			sendSyncInfo(service);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("服务端请求异常：" + e.getMessage()+"===>"+Constants.FROMADDR);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			LOG.error("解析返回信息异常：" + e.getMessage()+"===>"+Constants.FROMADDR);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			LOG.error("数据操作异常：" + e.getMessage()+"===>"+Constants.FROMADDR);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("系统异常：" + e.getMessage()+"===>"+Constants.FROMADDR);
		}
	}

	@SuppressWarnings("unchecked")
	private void clearContent(Element element) {
		Iterator<Element> it = element.elementIterator();
		if (it.hasNext()) {
			while (it.hasNext()) {
				Element e = (Element) it.next();
				e.clearContent();
			}
		}
	}

	// 传送到服务端的信息
	private String createInfo(Element element, Lessons lesson, Lessonsoflog log) {
		clearContent(element);

		element.element("flag").addText(log.getFlag().toString());
		element.element("lessonid").addText(log.getLessonid().toString());
		if (log.getLessonidOfserver() != null)
			element.element("lessonidOfserver").addText(log.getLessonidOfserver().toString());

		if (lesson != null) {
			if (lesson.getTitle() != null)
				element.element("title").addText(lesson.getTitle());
			if (lesson.getTeachertype() != null)
				element.element("teachertype").addText(lesson.getTeachertype().toString());
			if (lesson.getTeachers() != null)
				element.element("teachers").addText(lesson.getTeachers());
			if (lesson.getLessontype() != null)
				element.element("lessontype").addText(lesson.getLessontype().toString());
			if (lesson.getLessondesc() != null)
				element.element("lessondesc").addText(lesson.getLessondesc());
			if (lesson.getXuefen() != null)
				element.element("xuefen").addText(lesson.getXuefen().toString());
			if (lesson.getLessondate() != null)
				element.element("lessondate").addText(lesson.getLessondate());
			if (lesson.getLessoncontent() != null)
				element.element("lessoncontent").addText(lesson.getLessoncontent());
			if (lesson.getOnlinefile() != null)
				element.element("onlinefile").addText(lesson.getOnlinefile());
			if (lesson.getFenshuoff() != null)
				element.element("fenshuoff").addText(lesson.getFenshuoff());
			if (lesson.getOnlineorlocal() != null)
				element.element("onlineorlocal").addText(lesson.getOnlineorlocal());
			element.element("shared").addText(lesson.getShared().toString());
		}
		element.element("fromAddr").addText(Constants.FROMADDR);
		return element.asXML();
	}

	private String sendInfo(String text) throws IOException {
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		try {

			url = new URL(Constants.LESSON_SYNC_URL);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			// 超时设置
			httpurlconnection.setConnectTimeout(3 * 1000);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");
			OutputStream out = httpurlconnection.getOutputStream();
			out.write(text.getBytes());
			out.flush();
			out.close();
			InputStream response = httpurlconnection.getInputStream();
//			byte[] buffer = new byte[256];
//			int numberRead;
//			StringBuffer bf = new StringBuffer();
//			while ((numberRead = response.read(buffer)) >= 0) {
//				bf.append(new String(buffer, 0, numberRead));
//			}
			BufferedReader br=new BufferedReader(new InputStreamReader(response));
			
			
//			byte[] buffer = new byte[256];
//			int numberRead;
			String line="";
			StringBuffer bf = new StringBuffer();
			while ((line=br.readLine())!=null) {
				bf.append(line);
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
	
	

	// 发送信息至服务端请求
	@SuppressWarnings("unchecked")
	private void sendUpdateInfo(BasicService service) throws IOException, DocumentException, ServiceException {
		List<Lessonsoflog> list = service.find(" from Lessonsoflog");
		Element element = createElement();
		if (list != null && list.size() > 0) {
			for (Lessonsoflog log : list) {
				Lessons lesson = (Lessons) service.get(Lessons.class, log.getLessonid());
				String text = createInfo(element, lesson, log);
				String response = sendInfo(text);

				parseResponse(response, lesson, log, service);
			}
		}
	}

	private void sendSyncInfo(BasicService service) throws IOException, DocumentException, ServiceException {
		Element SyncElement = DocumentHelper.createElement("LessonsSyncRequest");
		SyncElement.addElement("fromAddr").addText(Constants.FROMADDR);
		String response = sendInfo(SyncElement.asXML());
		parseResponse(response, null, null, service);
	}

	// 解析服务端返回请求
	private void parseResponse(String response, Lessons lesson, Lessonsoflog log, BasicService service) throws DocumentException, ServiceException {
		Document doc = DocumentHelper.parseText(response);
		Element root = doc.getRootElement();
		if (root.getName().equals("LessonsSyncUpdate")) { // 服务端更新返回
			String flag = root.elementText("flag");
			// String lessonid=root.elementText("lessonid");
			String lessonidOfserver = root.elementText("lessonidOfserver");
			String result = root.elementText("result");
			if ("0".equals(result)) { // 同步信息成功 删除log表中记录
				service.delete(log);
				// 如果客户端记录的lessonidofserver为空或为0 更新该值
				if (lesson != null && (lesson.getLessonidOfserver() == null || lesson.getLessonidOfserver() == 0)) {
					lesson.setLessonidOfserver(Integer.parseInt(lessonidOfserver));
					service.update(lesson);
				}
			} else if ("2".equals(result) && "3".equals(flag)) { // 服务端记录不存在的删除记录
				service.delete(log);
			}
		} else if (root.getName().equals("LessonsSyncResponse")) { // 服务端同步返回
			List<Element> elements = root.elements("lesson");
			if (elements != null) {
				for (Element e : elements) {
					String flag = e.elementText("flag");
					String _lessonidOfserver = e.elementText("lessonidOfserver");
					Integer lessonidOfserver = 0;
					if (_lessonidOfserver != null && !"".equals(_lessonidOfserver))
						lessonidOfserver = Integer.parseInt(_lessonidOfserver);
					if ("3".equals(flag)) { // 删除
						deleteLesson(service, lessonidOfserver);
					} else {
						String title = e.elementText("title");
						String _teachertype = e.elementText("teachertype");
						String teachers = e.elementText("teachers");
						String _lessontype = e.elementText("lessontype");
						String lessondesc = e.elementText("lessondesc");
						String _xuefen = e.elementText("xuefen");
						String lessondate = e.elementText("lessondate");
						String lessoncontent = e.elementText("lessoncontent");
						String onlinefile = e.elementText("onlinefile");
						String fenshuoff = e.elementText("fenshuoff");
						String shared = e.elementText("shared");
						String fromAddr = e.elementText("fromAddr");
						String onlineorlocal = e.elementText("onlineorlocal");

						Short teachertype = 0;
						Byte lessontype = 0;
						Float xuefen = 0f;
						// String shared="0";
						if (_teachertype != null && !"".equals(_teachertype))
							teachertype = Short.parseShort(_teachertype);
						if (_lessontype != null && !"".equals(_lessontype))
							lessontype = Byte.parseByte(_lessontype);
						if (_xuefen != null && !"".equals(_xuefen))
							xuefen = Float.parseFloat(_xuefen);
						// if(_shared!=null&&!"".equals(_shared))
						// shared=Short.parseShort(_shared);
						if ("2".equals(flag)) { // 更新
							updateLesson(service, lessonidOfserver, title, teachertype, teachers, lessontype, lessondesc, xuefen, lessondate,
									lessoncontent, onlinefile, fenshuoff, shared, fromAddr, onlineorlocal);
						} else { // 新增
							insertLesson(service, lessonidOfserver, title, teachertype, teachers, lessontype, lessondesc, xuefen, lessondate,
									lessoncontent, onlinefile, fenshuoff, shared, fromAddr, onlineorlocal);
						}
					}
				}
			}
		}

	}

	private void deleteLesson(BasicService service, Integer lessonidOfserver) throws ServiceException {
		service.execute("delete from Lessons where lessonidOfserver=" + lessonidOfserver);
	}

	private void insertLesson(BasicService service, Integer lessonidOfserver, String title, Short teachertype, String teachers, Byte lessontype,
			String lessondesc, Float xuefen, String lessondate, String lessoncontent, String onlinefile, String fenshuoff, String shared,
			String fromAddr, String onlineorlocal) throws ServiceException {
		Lessons lesson = new Lessons();
//		lesson.setLessonid((int)(System.currentTimeMillis()/10000));
		lesson.setTitle(title);
		lesson.setTeachertype(teachertype);
		lesson.setTeachers(teachers);
		lesson.setLessontype(lessontype);
		lesson.setLessondesc(lessondesc);
		lesson.setXuefen(xuefen);
		lesson.setLessondate(lessondate);
		lesson.setLessoncontent(lessoncontent);
		lesson.setOnlinefile(onlinefile);
		lesson.setFenshuoff(fenshuoff);
		lesson.setShared(shared);
		lesson.setFromAddr(fromAddr);
		lesson.setLessonidOfserver(lessonidOfserver);
		lesson.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		lesson.setOnlineorlocal(onlineorlocal);
		service.save(lesson);
	}

	private void updateLesson(BasicService service, Integer lessonidOfserver, String title, Short teachertype, String teachers, Byte lessontype,
			String lessondesc, Float xuefen, String lessondate, String lessoncontent, String onlinefile, String fenshuoff, String shared,
			String fromAddr, String onlineorlocal) throws ServiceException {
		List list = service.find(" from Lessons where lessonidOfserver=" + lessonidOfserver);
		Lessons lesson = null;
		if (list != null && list.size() > 0) {
			lesson = (Lessons) list.get(0);
			lesson.setTitle(title);
			lesson.setTeachertype(teachertype);
			lesson.setTeachers(teachers);
			lesson.setLessontype(lessontype);
			lesson.setLessondesc(lessondesc);
			lesson.setXuefen(xuefen);
			lesson.setLessondate(lessondate);
			lesson.setLessoncontent(lessoncontent);
			lesson.setOnlinefile(onlinefile);
			lesson.setFenshuoff(fenshuoff);
			lesson.setShared(shared);
			lesson.setFromAddr(fromAddr);
			lesson.setLessonidOfserver(lessonidOfserver);
			lesson.setOnlineorlocal(onlineorlocal);
			service.update(lesson);
		} else {
			insertLesson(service, lessonidOfserver, title, teachertype, teachers, lessontype, lessondesc, xuefen, lessondate, lessoncontent,
					onlinefile, fenshuoff, shared, fromAddr, onlineorlocal);
		}
	}

	/*
	 * public static void main(String args[]) throws DocumentException{ String
	 * s="<LessonsSyncResponse><result>0</result><lesson><flag>1</flag><lessonid/><title>新增测试</title><teachertype>0</teachertype><teachers></teachers><lessontype>0</lessontype><lessondesc></lessondesc><xuefen>0.0</xuefen><lessondate>2009-02-23
	 * 14:00</lessondate><lessoncontent></lessoncontent><onlinefile>新增同步测试</onlinefile><fenshuoff>100</fenshuoff><shared>0</shared><fromAddr>dongguan</fromAddr><lessonidOfserver/></lesson></LessonsSyncResponse>";
	 * Document doc=DocumentHelper.parseText(s); Element
	 * root=doc.getRootElement();
	 * if(root.getName().equals("LessonsSyncResponse")){ //服务端同步返回 List<Element>
	 * elements=root.elements("lesson"); if(elements!=null){ for(Element
	 * e:elements){ String flag=e.elementText("flag"); String
	 * _lessonidOfserver=e.elementText("lessonidOfserver"); Integer
	 * lessonidOfserver=0;
	 * if(_lessonidOfserver!=null&&!"".equals(_lessonidOfserver))
	 * lessonidOfserver=Integer.parseInt(_lessonidOfserver);
	 * if("3".equals(flag)){ //删除 System.out.println("delete................");
	 * //deleteLesson(service,lessonidOfserver); }else{ String
	 * title=e.elementText("title"); String
	 * _teachertype=e.elementText("teachertype"); String
	 * teachers=e.elementText("teachers"); String
	 * _lessontype=e.elementText("lessontype"); String
	 * lessondesc=e.elementText("lessondesc"); String
	 * _xuefen=e.elementText("xuefen"); String
	 * lessondate=e.elementText("lessondate"); String
	 * lessoncontent=e.elementText("lessoncontent"); String
	 * onlinefile=e.elementText("onlinefile"); String
	 * fenshuoff=e.elementText("fenshuoff"); String
	 * _shared=e.elementText("shared"); String
	 * fromAddr=e.elementText("fromAddr");
	 * 
	 * Short teachertype=0; Byte lessontype=0; Float xuefen=0f; Short shared=0;
	 * if(_teachertype!=null&&!"".equals(_teachertype))
	 * teachertype=Short.parseShort(_lessonidOfserver);
	 * if(_lessontype!=null&&!"".equals(_lessontype))
	 * lessontype=Byte.parseByte(_lessontype);
	 * if(_xuefen!=null&&!"".equals(_xuefen)) xuefen=Float.parseFloat(_xuefen);
	 * if(_shared!=null&&!"".equals(_shared)) shared=Short.parseShort(_shared);
	 * if("2".equals(flag)){ //更新 System.out.println("update................");
	 * }else{ //新增 System.out.println("insert................"); } } } } } }
	 */
}
