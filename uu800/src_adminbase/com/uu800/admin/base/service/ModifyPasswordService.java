
package com.uu800.admin.base.service;

import javax.annotation.Resource;

import com.uu800.webbase.BasicService;
import com.uu800.admin.base.dao.ModifyPasswordDAO;

public class ModifyPasswordService  extends BasicService 
{
    private ModifyPasswordDAO modifyPasswordDAO;
	/**
	 * @param ModifyPasswordDAO
	 */
    @Resource
	public void setModifyPasswordDAO(ModifyPasswordDAO modifyPasswordDAO) {		
		this.modifyPasswordDAO = modifyPasswordDAO;		
		super.basicDao = modifyPasswordDAO;
	}

	
}
