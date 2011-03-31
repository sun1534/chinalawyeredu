/**
 * OrderService.java
 */
package service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import common.BasicService;

import entity.LogDianxinmo;
import entity.LogUserOrder;
import entity.UserOrder;

/**
 * 
 * @author 刘哈哈 Mar 30, 20115:37:27 PM
 * 
 */
public class MoService extends BasicService {
	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(MoService.class);

	/**
	 * 
	 * @param mobile
	 * @param content
	 * @param destnumber
	 * @param remarks
	 * @param createtime
	 * @param doTime
	 * @return
	 */
	public int logMo(String mobile, String content, String destnumber, String remarks, Timestamp createtime,
			Timestamp doTime) {
		try {
			LogDianxinmo mo = new LogDianxinmo();
			mo.setContent(content);
			mo.setCreateTime(createtime);
			mo.setDestnumber(destnumber);
			mo.setMobile(mobile);
			mo.setRemarks(remarks);
			mo.setDoTime(doTime);
			basicDao.save(mo);
			return mo.getId();
		} catch (Exception e) {
			LOG.error("logMo", e);
			return -1;
		}

	}

	/**
	 * 更新这条mo的处理时间和处理结果
	 * 
	 * @param id
	 */
	public void updateMoDotime(int id,String result) {
		try {
			String hql = "update LogDianxinmo set doTime=?,result=? where id=?";
			basicDao.execute(hql, new Object[] { new java.sql.Timestamp(System.currentTimeMillis()),result, id });
		} catch (Exception e) {
			LOG.error("updateMoDotime", e);
		}
	}

	/**
	 * 记录mo消息,只新增
	 * 
	 * @param mobile
	 * @param content
	 * @param destnumber
	 * @param remarks
	 * @return
	 */
	public int logMo(String mobile, String content, String destnumber, String remarks) {
		try {
			LogDianxinmo mo = new LogDianxinmo();
			mo.setContent(content);
			mo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			mo.setDestnumber(destnumber);
			mo.setMobile(mobile);
			mo.setRemarks(remarks);
			basicDao.save(mo);
			return mo.getId();
		} catch (Exception e) {
			LOG.error("logMo", e);
			return -1;
		}
	}
}