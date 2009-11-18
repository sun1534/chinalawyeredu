/**
 * SysRightService.java
 */

package com.sxit.system.service;

import java.util.ArrayList;
import java.util.List;

import com.sxit.common.exception.ServiceException;
import com.sxit.models.system.SysRight;
import com.sxit.system.dao.SysRightDAO;
import com.sxit.system.util.RightComparator;

/**
 * @author 华锋 2008-3-9 下午07:44:01
 * 
 */
public class SysRightService {

	private SysRightDAO sysRightDAO;

	public void setSysRightDAO(SysRightDAO sysRightDAO) {
		this.sysRightDAO = sysRightDAO;
	}

	/**
	 * 得到排好序的权限列表
	 * 
	 * @return
	 */
	public List<SysRight> findAllOrderdRights() {
		List<SysRight> rightList = new ArrayList<SysRight>();
		List list = sysRightDAO.findAll(SysRight.class);
		for (int i = 0; i < list.size(); i++) {
			SysRight right = (SysRight) list.get(i);
			rightList.add(right);
		}
		java.util.Collections.sort(rightList, new RightComparator());
		return rightList;
	}
	public List findAll()throws ServiceException{
		try{
			return sysRightDAO.findAll(SysRight.class);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}
	/**
	 * 
	 * 
	 * @param rightcode
	 * @return
	 * @throws ServiceException
	 */
	public List getChildRight(String rightcode) throws ServiceException {
		try {
			List list = sysRightDAO.find("from com.sxit.models.system.SysRight right where right.parentcode=?", rightcode);
			return list;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 
	 * 得到某一层级的所有菜单
	 * @param rightcode
	 * @return
	 * @throws ServiceException
	 */
	public List getGradeRight(int grade) throws ServiceException {
		try {
			List list = sysRightDAO.find("from com.sxit.models.system.SysRight right where right.grade=?", grade);
			return list;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	
}