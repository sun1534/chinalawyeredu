//$Id: ListRolesAction.java,v 1.5 2003/12/13 13:37:49 gavin Exp $
package com.sxit.system.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysDepartment;

/**
 *
 * @author zrb
 */
public class DepartmentTreeAction extends AbstractListAction  {
        private TsysDepartment treeRootNode;
        private Integer departmentid;
        public DepartmentTreeAction() {
          rights="sys1,1";
        }
        public String go() throws HibernateException {
          treeRootNode = (TsysDepartment) getSession().get(TsysDepartment.class, departmentid);
          if(treeRootNode==null){
            message="未找到此部门！";
            return "message";
            }
          return SUCCESS;
        }
        /**
         * SQL query for Oracle.
         * queryName = "DepartmentList.HQL";
         */
        public TsysDepartment getTreeRootNode() {
          return treeRootNode;
        }
        public void setDepartmentid(Integer departmentid) {
          this.departmentid = departmentid;
        }
}
