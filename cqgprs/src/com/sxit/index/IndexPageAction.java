/**
 * IndexPageAction.java
 */

package com.sxit.index;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.Constants;
import com.sxit.common.action.AbstractAction;
import com.sxit.models.system.SysRight;
import com.sxit.models.system.SysUser;
import com.sxit.netquality.service.ZeroService;
import com.sxit.stat.service.StatService;

/**
 * 
 * userlogin成功后,redirect到这个页面。防止刷新，重复登录
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class IndexPageAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(IndexPageAction.class);
	private static final DateFormat dfdate = new java.text.SimpleDateFormat("yyyyMMddHH");
	private static final DateFormat dfhourmm = new java.text.SimpleDateFormat("HHmm");

	public IndexPageAction() {
		// this.rightCode="shouye";

	}

	public static IndexCache cache = null;
	//每天3点30重新跑一遍
private static int standardhourmm=330;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		this.sysUser = this.getLoginUser();

		Date end = super.getPrevDate();
		Date start = super.getPrevCountDate(3);
		Date now=new java.util.Date();
		long today =Long.parseLong(dfdate.format(now));
		int hourmm=Integer.parseInt(dfhourmm.format(now));
//0330
		
//		if (cache == null || (cache != null && cache.getKeydate()!=today)) {
		if (cache == null || (cache != null && cache.getKeyday()!=today&&hourmm>=standardhourmm)) {
			synchronized (this) {
				com.sxit.stat.service.StatService statservice = (StatService) getBean("statService");
				List totallist = statservice.getDaysTotalStream(start, end);
				List total23glist = statservice.getDaysTotalStream23g(start, end);
				Date date = com.sxit.stat.util.StatUtil.getPrevDate();
				ZeroService zeroservice = (ZeroService) this.getBean("zeroService");

				int exceptionapn = zeroservice.getZeroApns(date);
				int exceptioncell = zeroservice.getZeroCells(date);

				List streamlist = statservice.getDaysTotalStream(start, end);

				cache = new IndexCache();
				cache.setExceptionapn(exceptionapn);
				cache.setExceptioncell(exceptioncell);
				cache.setKeyday(today);
				cache.setKeyhourmm(hourmm);
				cache.setTotal23glist(total23glist);
				cache.setTotallist(totallist);
				cache.setStreamlist(streamlist);
				LOG.info("首页显示的数据从数据库拿.....");
			}
		}else{
			LOG.info("获取的缓存数据.........");
		}

		this.userMenus = this.sysUser.getUserMenus();
		LOG.info(sysUser.getUsername() + "进入首页成功......");
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String top() throws Exception {

		this.sysUser = this.getLoginUser();
		this.userMenus = this.sysUser.getUserMenus();
		this.sysUser = getLoginUser();
		this.topMenus = this.sysUser.getTopMenus();
		// LOG.info(sysUser.getUsername() + "进入首页TOP成功......");
		// TODO Auto-generated method stub
		return "top";
	}

	public String left() throws Exception {

		this.sysUser = this.getLoginUser();
		this.userMenus = this.sysUser.getUserMenus();
		this.topMenus = this.sysUser.getTopMenus();
		// TODO Auto-generated method stub
		return "left";
	}

	private List<SysRight> userMenus;
	private List<SysRight> topMenus;

	public List<SysRight> getUserMenus() {
		return this.userMenus;
	}

	public List<SysRight> getTopMenus() {
		return this.topMenus;
	}

	private SysUser sysUser;

	public SysUser getSysUser() {

		return this.sysUser;
	}
}