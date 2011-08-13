//$Id: DepartmentCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;

//import org.hibernate.ObjectDeletedException;
//import org.hibernate.StaleStateException;



/**
 *
 * @author zrb
 */
public class DepartmentDeleteAction extends AbstractAction {

	private TsysDepartment department;
        private int departmentid;

	public DepartmentDeleteAction() {
          rights="sys1,8";
	}
	public String go() throws HibernateException {
                TsysDepartment department = department=(TsysDepartment)getSession().get(TsysDepartment.class,Integer.valueOf(departmentid));;
                nextpage="departmentList.action?pagenumber="+pagenumber;
                if(department==null){
                  message="未找到此用户";
                  return "message";
                }
                getSession().delete(department);
                commit();
                LOG.info("成功删除部门"+department.getDepartmentid()+department.getDepartmentname());
                message="删除成功！";
		return SUCCESS;
	}

        public void setDepartmentid(int departmentid) {
          this.departmentid = departmentid;
        }
}
