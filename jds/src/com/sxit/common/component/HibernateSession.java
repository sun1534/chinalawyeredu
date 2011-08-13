//$Id: HibernateSession.java,v 1.1 2003/11/16 17:15:35 max Exp $
package com.sxit.common.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Gavin King
 */
public class HibernateSession {

	private static final Log LOG = LogFactory.getLog(HibernateSession.class);

	private Session session;
	private Transaction transaction;
	private HibernateSessionFactory factory;
	private boolean rollBackOnly;
	private boolean havecommit;

	public Session getSession() throws HibernateException {
		if (session == null) {
			session = factory.getSessionFactory().openSession();
			transaction = session.beginTransaction();
		}
		return session;
	}
	
	public void commitSession() throws HibernateException {
		if (session == null)
			return;
		if (transaction != null)
			transaction.commit();
	
	}

	public void setHibernateSessionFactory(HibernateSessionFactory factory) {
		this.factory = factory;
	}

	/*
	 * public void dispose() { try { disposeSession(); } catch
	 * (HibernateException he) { throw new RuntimeException("could not cleanly
	 * dispose() HibernateSession", he); } }
	 */
	public void sessionCommit() throws JDBCException, HibernateException {
		if (session == null)
			return;
		if (transaction != null)
			transaction.commit();
	}

	public void disposeSession() throws HibernateException {

		LOG.debug("disposing");

		if (session == null)
			return;

		if (rollBackOnly) {
			try {
				LOG.debug("rolling back");
				if (transaction != null)
					transaction.rollback();
			} catch (HibernateException e) {
				LOG.error("error during rollback", e);
				throw e;
			} finally {
				session.close();
				session = null;
				transaction = null;
			}
		} else {
				session.close();
				session = null;
				transaction = null;
		}
	}

	public boolean isRollBackOnly() {
		return rollBackOnly;
	}

	public boolean isHavecommit() {
		return havecommit;
	}

	public void setRollBackOnly(boolean rollBackOnly) {
		this.rollBackOnly = rollBackOnly;
	}

}
