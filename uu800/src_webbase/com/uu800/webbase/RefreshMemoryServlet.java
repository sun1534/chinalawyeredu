package com.uu800.webbase;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 刷新内存
 * 
 * @author 华锋 Jun 2, 20108:48:50 PM
 */
public class RefreshMemoryServlet extends HttpServlet {
	private static final long serialVersionUID = 4011318990328536812L;
	private static final Log LOG = LogFactory.getLog(RefreshMemoryServlet.class);

	public RefreshMemoryServlet() {
		// this.moduleId=201;
	}

	
	
	public void service(HttpServletRequest request,HttpServletResponse response){
//		WebSiteParamterUtil util = new WebSiteParamterUtil();
//		util.loadParameters();
//		util.loadAreas();
//		util.setMaxServiceId();
//		LOG.info("内存数据刷新成功");
	}

}
