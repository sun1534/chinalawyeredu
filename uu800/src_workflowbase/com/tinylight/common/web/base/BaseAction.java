
package com.tinylight.common.web.base;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.tinylight.common.utils.Struts2Util;

/**
 * @author scott.cgi	
 * @since  2009-4-11
 * 所有action的基类提供日记能力和通用功能
 */
public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 5041319454608776163L;
	//让子类拥有日志记录的能力
	protected  Logger log=LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 提供获取request,response,session的能力;向客户端返还多种数据格式的辅助方法
	 */
	protected static Struts2Util s2Util;

}
