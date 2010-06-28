/**
 * Save2DbThread.java
 */
package com.sxit.query.service;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;

import com.sxit.common.BasicDAO;
import com.sxit.models.UserStateQuery;

/**
 * @author 华锋 Jun 19, 201011:39:21 AM
 * 
 */
public class Save2DbThread extends Thread {
	private static Log _LOG = LogFactory.getLog(SHQueryService.class);

	private UserStateQuery query;

	public Save2DbThread(UserStateQuery query, DataSource dataSource, BasicDAO basicDAO) {
		this.dataSource = dataSource;
		this.basicDAO = basicDAO;
		this.query = query;
	}

	private DataSource dataSource;

	private BasicDAO basicDAO;

	public void run() {
		try {
			long now = System.currentTimeMillis();
			query.setDetails(Hibernate.createClob(query.getDetailsStr()));
			query.setQuerydate(new java.sql.Timestamp(System.currentTimeMillis()));
			basicDAO.save(query);
			_LOG.debug("插入数据库的时间为:" + (System.currentTimeMillis() - now));
		} catch (Exception e) {
			_LOG.error("入库有误:", e);
		}

	}
}
