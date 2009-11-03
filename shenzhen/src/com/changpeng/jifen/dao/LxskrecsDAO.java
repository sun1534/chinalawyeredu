/**
 * LxnetrecsService.java
 */
package com.changpeng.jifen.dao;

import java.util.List;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Lxskrecs;

/**
 * 
 * 刷卡情况
 * @author 华锋
 * 2008-5-5 下午01:11:04
 *
 */
public class LxskrecsDAO extends BasicDAO {
	public Lxskrecs getLxskrecs(int lessonid,String kahao)  {
	
			List list = find("from com.changpeng.models.Lxskrecs xf where xf.lessonid=" + lessonid+" and xf.kahao='"+kahao+"'");
			if (list == null || list.size() == 0)
				return null;
			return (Lxskrecs) list.get(0);
		
	}
}
