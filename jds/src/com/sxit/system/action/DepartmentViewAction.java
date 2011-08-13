//$Id: DepartmentCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import org.hibernate.Query;
import java.util.List;

/**
 *
 * @author zrb
 */
public class DepartmentViewAction extends AbstractAction {
	private TsysDepartment department;
        private int departmentid;
	public DepartmentViewAction() {
          rights="sys1,1";
		department = new TsysDepartment();
	}

	public String go() throws HibernateException {

           nextpage="departmentList.action?pagenumber="+pagenumber;
           department=(TsysDepartment)getSession().get(TsysDepartment.class,Integer.valueOf(departmentid));
           if(department==null){
             message="未找到此用户";
             return "message";
           }
        //  System.out.println("user:"+department.getParent().getDepartmentname());
        //  System.out.println("user:"+user.getTsysDepartment().getDepartmentname());
       //    set("department", department);
           return SUCCESS;

	}
        public void setDepartmentid(int departmentid) {
          this.departmentid = departmentid;
        }
        public int getDepartmentid() {
          return this.departmentid;
        }
        public TsysDepartment getDepartment() {
          return department;
	}
//        private Query getQuery() throws HibernateException {
//                String queryName;
//                queryName="from TsysDepartment as department where departmentid=:departmentid";
//                return getSession().createQuery(queryName);
//        }
}
