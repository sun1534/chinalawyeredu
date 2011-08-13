//$Id: ListRolesAction.java,v 1.5 2003/12/13 13:37:49 gavin Exp $
package com.sxit.system.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysDepartment;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author zrb
 */
public class TreeJsonAction extends AbstractListAction  {
        private treeNode node;
        private Integer departmentid;
        public TreeJsonAction() {
         // rights="sys1,1";
        }
        public String go() throws HibernateException {
          departmentid=1;
          TsysDepartment treeRootNode = (TsysDepartment) getSession().get(TsysDepartment.class, departmentid);
          if(treeRootNode==null){
            message="未找到此部门！";
            return "message";
            }
          node = new treeNode();

          node.setId("1");
          node.setName("ONE");
          node.setLeaf(false);
          node.setChildren(new HashSet());

          treeNode node1=new treeNode();
          treeNode node2= new treeNode();
          node1.setId("2");
          node1.setName("2");
          node1.setLeaf(false);
          node2.setId("2");
          node2.setName("2");
          node2.setLeaf(false);
          node.getChildren().add(node1);
          node.getChildren().add(node2);
          return SUCCESS;
        }
        /**
         * SQL query for Oracle.
         * queryName = "DepartmentList.HQL";
         */
        public void setDepartmentid(Integer departmentid) {
          this.departmentid = departmentid;
        }
        public treeNode getNode() {
          return node;
        }
}


