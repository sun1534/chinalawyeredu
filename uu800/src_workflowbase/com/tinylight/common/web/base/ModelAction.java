/**
 * 
 */
package com.tinylight.common.web.base;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author scott.Cgi
 * @since  2009-5-15
 * 模型驱动action,实现ModelDriven<T>泛型接口,T为利用浏览器参数装配的类,类属性要页面name匹配
 */
public abstract class ModelAction<T> extends BaseAction implements ModelDriven<T>{

	private static final long serialVersionUID = 3481444824052776265L;

}
