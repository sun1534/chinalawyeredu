//$Id: DepartmentCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;
import java.util.*;

/**
 *
 * @author zrb
 */
public class DepartmentCreateAction extends AbstractAction {

	private TsysDepartment department;
        private List<TsysDepartment> departmentlist;

	public DepartmentCreateAction() {
          rights="sys1,2";
		department = new TsysDepartment();
                departmentlist = new ArrayList<TsysDepartment>();
	}

	public String go() throws HibernateException {
                department.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		getSession().save(department);
		set("department", department);
		return SUCCESS;
	}

	public TsysDepartment getDepartment() {
		return department;
	}
        public List getDepartmentlist() {
          return departmentlist;
        }
        // 设置下拉选择框 树状迭代
        private String tempstr="";
        public void selectSet(TsysDepartment corp) {
          String diplayname=tempstr+"-";
          corp.setDisplayname(diplayname+corp.getDepartmentname());
          this.departmentlist.add(corp);
          if(!corp.getChildren().isEmpty())
          for (TsysDepartment child : corp.getChildren()) {
            if(child.getDepartmentid()!=1)
            {
            tempstr=diplayname;
            selectSet(child);
            }
          }
        }
        public String input() throws Exception {
          TsysDepartment corp = (TsysDepartment) getSession().get(TsysDepartment.class, Integer.valueOf(1));
                 if(corp!=null){
                  selectSet(corp);
                 }else{
                   message="部门缺少根，请与管理员联系！";
                   return "message";
                }
            return "input";
        }
        public List getTypelist(){
          return DepartmentType.getTypelist();
        }
}
