/**
 * MenuComparator.java
 */
package com.sxit.cellConference.service;

import java.util.Comparator;

import com.sxit.stat.models.CellStatModel;

/**
 * @author 华锋
 * 2008-3-4 下午09:28:54
 *
 */
public class CellStatComparator implements Comparator {
//	private static Log LOG = LogFactory.getLog(RightComparator.class);
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		CellStatModel m1=(CellStatModel)o1;
		CellStatModel m2=(CellStatModel)o2;
//	    LOG.debug(m1.getRightcode()+"="+m1.getCompareid()+","+m2.getRightcode()+"="+m2.getCompareid());
		
		long m11=Long.parseLong(m1.getDate().replace(":","").replace("-", ""));
		long m22=Long.parseLong(m2.getDate().replace(":","").replace("-", ""));
		
        return m11>m22?1:0;
	}
}