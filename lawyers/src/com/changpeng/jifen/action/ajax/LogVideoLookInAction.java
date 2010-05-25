/**
 * VideoLookAction.java
 */

package com.changpeng.jifen.action.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.LogVideoLook;

/**
 * <pre>
 * 看视频加分
 * ajax的方式来处理，异步
 * </pre>
 * 
 * @author 华锋 2008-5-5 下午01:21:00
 * 
 */
public class LogVideoLookInAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(LogVideoLookInAction.class);

	public LogVideoLookInAction() {
		this.needsession = false;
	}

	private int lawyerid;
	private String areas;

	private int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param areas
	 *            the areas to set
	 */
	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String go() throws Exception {

		try {

			LogVideoLook log = new LogVideoLook();
			String[] areastr = areas.split("-");

			log.setLawyerid(lawyerid);
			log.setCityid(Integer.parseInt(areastr[1]));
			log.setProvinceid(Integer.parseInt(areastr[0]));
			log.setOfficeid(Integer.parseInt(areastr[2]));
			log.setIntime(new java.sql.Timestamp(System.currentTimeMillis()));
			basicService.save(log);
			this.id = log.getId();

		} catch (Exception e) {

			LOG.error("记录访问有误:::" + e);
		}
		return SUCCESS;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

}
