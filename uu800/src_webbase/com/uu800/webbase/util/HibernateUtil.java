/**
 * HibernateUtils.java
 */

package com.uu800.webbase.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projection;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.springframework.util.Assert;

/**
 * 
 * 一些常用的 Hibernate 方法, 注意, 一些方法可能只适用于 Hibernate3.0.5 !!!
 * 
 * @author 华锋 2008-3-6 下午09:36:49
 * 
 */
public abstract class HibernateUtil {

	private static Log LOG = LogFactory.getLog(HibernateUtil.class);

	public static final String CRITERIA_ASSERT_ERROR_MESSAGE = " 's type is not " + CriteriaImpl.class
			+ ", please make sure you are using Hibernate3.0.5!!! ";

	/**
	 * 将 Criteria 加上分页条件并输出结果
	 * 
	 * @param criteria
	 *            the criteria
	 * @param offset
	 *            the offset
	 * @param maxPageItems
	 *            the maxPageItems
	 * @return the result list
	 */
	public static List getPageResult(Criteria criteria, int offset, int maxPageItems) throws HibernateException {
		criteria.setFirstResult(offset);
		criteria.setMaxResults(maxPageItems);
		
		return criteria.list();
	}

	/**
	 * 从 criteria 中取得 {@link Projection}, 接口中没有公开此方法, 因此从 {@link CriteriaImpl} 中取得
	 * 
	 * @see CriteriaImpl#getProjection()
	 * @param criteria
	 *            the criteria
	 * @return the Projection
	 */
	public static Projection getProjection(Criteria criteria) {
		assertType(criteria);
		CriteriaImpl impl = (CriteriaImpl) criteria;
		return impl.getProjection();
	}

	private static void assertType(Criteria criteria) {
		Assert.notNull(criteria, " criteria is required. ");
		String message = criteria + CRITERIA_ASSERT_ERROR_MESSAGE;
		if (!CriteriaImpl.class.isInstance(criteria)) {
			if (LOG.isDebugEnabled()) {
				LOG.debug(message);
			}
			throw new RuntimeException(message);
		}
	}

	/**
	 * 得到 criteria 中的 OrderEntry[]
	 * 
	 * @param criteria
	 *            the criteria
	 * @return the OrderEntry[]
	 */
	public static OrderEntry[] getOrders(Criteria criteria) {
		assertType(criteria);
		CriteriaImpl impl = (CriteriaImpl) criteria;
		Field field = getOrderEntriesField(criteria);
		try {
			return (OrderEntry[]) ((List) field.get(impl)).toArray(new OrderEntry[0]);
		}
		catch (Exception e) {
			logAndThrowException(criteria, e);
			throw new InternalError(" Runtime Exception impossibility can't throw ");
		}
	}

	/**
	 * 移除 criteria 中的 OrderEntry[]
	 * 
	 * @param criteria
	 *            the criteria
	 * @return the criteria after removed OrderEntry[]
	 */
	public static Criteria removeOrders(Criteria criteria) {
		assertType(criteria);
		CriteriaImpl impl = (CriteriaImpl) criteria;

		try {
			Field field = getOrderEntriesField(criteria);
			field.set(impl, new ArrayList());
			return impl;
		}
		catch (Exception e) {
			logAndThrowException(criteria, e);
			throw new InternalError(" Runtime Exception impossibility can't throw ");
		}
	}

	/**
	 * 为 criteria 增加 OrderEntry[]
	 * 
	 * @param criteria
	 *            the criteria
	 * @param orderEntries
	 *            the OrderEntry[]
	 * @return the criteria after add OrderEntry[]
	 */
	public static Criteria addOrders(Criteria criteria, OrderEntry[] orderEntries) {
		assertType(criteria);
		CriteriaImpl impl = (CriteriaImpl) criteria;
		try {
			Field field = getOrderEntriesField(criteria);
			for (int i = 0; i < orderEntries.length; i++) {
				List innerOrderEntries = (List) field.get(criteria);
				innerOrderEntries.add(orderEntries[i]);
			}
			return impl;
		}
		catch (Exception e) {
			logAndThrowException(criteria, e);
			throw new InternalError(" Runtime Exception impossibility can't throw ");
		}
	}
/**
 * 
 * @param criteria
 * @param e
 */
	private static void logAndThrowException(Criteria criteria, Exception e) {
		String message = criteria + CRITERIA_ASSERT_ERROR_MESSAGE;
		if (LOG.isDebugEnabled()) {
			LOG.debug(message, e);
		}
		throw new RuntimeException(message, e);
	}
/**
 * 
 * @param criteria
 * @return
 */
	private static Field getOrderEntriesField(Criteria criteria) {
		Assert.notNull(criteria, " criteria is requried. ");
		try {
			Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
			field.setAccessible(true);
			return field;
		}
		catch (Exception e) {
			logAndThrowException(criteria, e);
			throw new InternalError();
		}
	}
	
}
