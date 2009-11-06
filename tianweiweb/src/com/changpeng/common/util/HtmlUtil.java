package com.changpeng.common.util;

import java.util.regex.*;

/**
 * 处理输入框产生的HTML代码
 * 
 * @author zwx
 * 
 */
public class HtmlUtil {

	/**
	 * 将HTML代码删除，仅保留文本
	 * 
	 * @param inputString
	 * @return
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		try {
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";
			// 过滤script标签
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			// 过滤style标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			// 过滤html标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			// 完成过滤
			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	/**
	 * 转换HTML标签，但保留内容
	 * 
	 * @param inputString
	 * @return
	 */
	public static String HtmlTransform(String inputString) {
		String res = "";
		try {
			res = inputString.replaceAll("<", "&lt;");
			res = res.replaceAll(">", "&gt;");

		} catch (Exception e) {
			System.err.println("HtmlTransform: " + e.getMessage());
		}
		return res;
	}

	// public static void main(String[] args) {
	// String s1 = "<a href=\"common/istudent.html\"
	// title=\"来智慧屋找到你的学习伙伴，它将和你共同成长 \">快乐学习</a></li>";
	// //System.out.println("转换后：：" + Html2Text(s1));
	// System.out.println("转换后：：" + HtmlTransform(s1));
	// }
}
