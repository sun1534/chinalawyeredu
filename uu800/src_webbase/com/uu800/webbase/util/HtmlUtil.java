package com.uu800.webbase.util;

import java.util.regex.*;

/**
 * 
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
		//String textStr = "";
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
			//textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return htmlStr;
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

	// 过滤特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("");//.trim();
	}

	public static void main(String[] args) {
		String s2 = "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";

		String s1 = "<a>sss</a><SCRIPT LANGUAGE=\"JavaScript\">alert('ssssss');</SCRIPT>";
		System.out.println("1转换后：：" + Html2Text(s1));
		System.out.println("2转换后：：" + HtmlTransform(s1));
		//System.out.println("3转换后：：" + StringFilter(s2));
		System.out.println("4转换后：：" + StringFilter(s1));
	}

    public static String getTxtWithoutHTMLElement (String inputString)
    {
    		        String htmlStr = inputString; // 含html标签的字符串   
    		        String textStr = "";  
    		        String lastStr = "";
    		        java.util.regex.Pattern p_script;   
    		        java.util.regex.Matcher m_script;   
    		        java.util.regex.Pattern p_style;   
    		        java.util.regex.Matcher m_style;   
    		        java.util.regex.Pattern p_html;   
    		        java.util.regex.Matcher m_html;   
      
    		        try {   
    		          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>   
    		                                                                                                        // }   
    	           String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>   
    		                                                                                                    // }   
    		            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式   
    		  
    		            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);   
    		           m_script = p_script.matcher(htmlStr);   
    		            htmlStr = m_script.replaceAll(""); // 过滤script标签   
    		  
    		            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);   
    		           m_style = p_style.matcher(htmlStr);   
    		            htmlStr = m_style.replaceAll(""); // 过滤style标签   
    		  
    		            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);   
    		            m_html = p_html.matcher(htmlStr);   
    		            htmlStr = m_html.replaceAll(""); // 过滤html标签   
//    		            textStr=htmlStr;
    		            textStr = htmlStr.replace("     ", "<br/>");
    		            lastStr = textStr.replace("　　", "<br/>");   
    		            
    		        } catch (Exception e) {   
    		            System.err.println("Html2Text: " + e.getMessage());   
    		        }   
    		  
    		        return lastStr;// 返回文本字符串   
    		   
    }
}