package com.tinylight.common.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Struts2 Util类.
 * 
 * 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 */
public class Struts2Util {

	//header 常量定义
	private static final String ENCODING_PREFIX = "encoding:";
	private static final String NOCACHE_PREFIX = "no-cache:";
	private static final String ENCODING_DEFAULT = "UTF-8";
	private static final boolean NOCACHE_DEFAULT = true;

	private static Logger log = LoggerFactory.getLogger(Struts2Util.class);

	private Struts2Util() { 
	}

	// 取得Request/Response/Session的简化函数 //

	/**
	 * 取得HttpSession的简化方法.
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 取得HttpRequest的简化方法.
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpResponse的简化方法.
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// 绕过jsp/freemaker直接输出文本的函数 //

	/**
	 * 直接输出内容的简便函数.
	 
	 * eg.
	 * render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.                 
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		try {
			//分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}

			HttpServletResponse response = ServletActionContext.getResponse();

			//设置headers参数
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render("text/plain", text, headers);
	}

	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render("text/html", html, headers);
	}

	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render("text/xml", xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param string json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String string, final String... headers) {
		render("application/json", string, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param map Map对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	@SuppressWarnings("unchecked")
	public static void renderJson(final Map map, final String... headers) {
		String jsonString = JSONObject.fromObject(map).toString();
		renderJson(jsonString, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param object Java对象,将被转化为json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final Object object, final String... headers) {
		String jsonString =  JSONObject.fromObject(object).toString();
		renderJson(jsonString, headers);
	}
}
