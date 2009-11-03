/**
 * ArrangeService.java
 */

package com.changpeng.arrange.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Arrangesignup;
import com.changpeng.models.system.SysGroup;
import com.changpeng.models.system.SysUser;

/**
 * @author 华锋 2008-5-7 下午05:06:50
 * 
 */
public class ArrangeService extends BasicService {

	private BasicDAO basicDAO = null;

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	public void saveArrangesignup(final Arrangesignup signup, final int userid) throws ServiceException {

		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {

					SysUser user = (SysUser) basicDAO.get(SysUser.class, userid);
					SysGroup group = user.getSysGroup();
					if (group != null) {
						signup.setGroupid(group.getGroupid());
						signup.setGroupname(group.getGroupname());
					}

					basicDAO.save(signup);
					// 客户的话，先不分配，由主办律师自己去进行分配
					return null;
				}
			});
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	public void updateArrangesignup(final Arrangesignup signup) throws ServiceException {

		try {
			basicDAO.update(signup);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}

	}
}
