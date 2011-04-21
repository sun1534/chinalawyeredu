/**
 * TemplateUtil.java
 */
package main;

import java.io.IOException;
import java.io.StringWriter;

import entity.Members;
import entity.UserDzjcVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author 刘哈哈 Apr 18, 201111:07:28 AM
 * 
 */
public class TemplateUtil {

	/**
	 * 返回模板类
	 * 
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static Template getTemplate(String content) {
		try {
			Configuration cfg_simple = new Configuration();
			cfg_simple.setTemplateLoader(new StringTemplateLoader(content));
			Template template_simple = cfg_simple.getTemplate("");
			return template_simple;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 返回调用模板后的内容
	 * 
	 * 返回${chepei}无违章记录
	 * 
	 * @param template
	 * @param dzjc
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String getSimpleContent(Template template, Members member) throws TemplateException, IOException {
		try {
			String content = "";
			StringWriter writer = new StringWriter();
			template.process(member, writer);
			content = writer.toString();
			writer.close();
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回调用模板后的内容
	 * 
	 * @param template
	 * @param dzjc
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String getDetailedContent(Template template, UserDzjcVo dzjc) {
		try {
			String content = "";
			StringWriter writer = new StringWriter();
			template.process(dzjc, writer);
			content = writer.toString();
			writer.close();
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
