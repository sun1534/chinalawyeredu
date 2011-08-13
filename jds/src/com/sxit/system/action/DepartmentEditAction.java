//$Id: DepartmentCreateAction.java,v 1.2 2003/12/13 10:17:48 gavin Exp $
package com.sxit.system.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.*;


import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author zrb
 */
public class DepartmentEditAction extends AbstractAction {

	private TsysDepartment department;
        private int departmentid;
        private List<TsysDepartment> departmentlist;
	public DepartmentEditAction() {
          rights="sys1,4";
          departmentlist = new ArrayList<TsysDepartment>();
	}

	public String go() throws HibernateException {
          TsysDepartment department=getDepartment();
                getSession().update(department);
		set("department", department);
                nextpage="departmentTreeView.action?departmentid="+department.getDepartmentid();
                message="保存成功！";
		return SUCCESS;
	}
// 设置下拉选择框 树状迭代
        private String tempstr="";
        public void selectSet(TsysDepartment corp) {
          String diplayname=tempstr+"-";
          if(corp.getDepartmentid()!=department.getDepartmentid())
          {
          corp.setDisplayname(diplayname+corp.getDepartmentname());
          this.departmentlist.add(corp);
          }
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
           department=(TsysDepartment)getSession().get(TsysDepartment.class,Integer.valueOf(departmentid));
           if (department == null) {
             message = "未找到此用户";
             return "message";
           }
          set("department", department);
          if (department.getParent().getDepartmentid()==-1)
          {  //当编辑的是根时 下拉框只能选择root
            TsysDepartment root = (TsysDepartment) getSession().get(TsysDepartment.class, Integer.valueOf(-1));
             if(root!=null){
               root.setDisplayname("根");
               this.departmentlist.add(root);
          }
          }else{
            TsysDepartment corp = (TsysDepartment) getSession().get(TsysDepartment.class, Integer.valueOf(1));
                   if(corp!=null){
                    selectSet(corp);
                   }else{
                     message="部门缺少根，请与管理员联系！";
                     return "message";
                }
          }
          return "input";
        }
        public List getDepartmentlist() {
          return departmentlist;
        }
        public TsysDepartment getDepartment() {
         if (department==null)
            department = (TsysDepartment) get("department");
          return department;
	}
        public void setDepartmentid(int departmentid) {
          this.departmentid = departmentid;
        }
        public List getTypelist(){
          return DepartmentType.getTypelist();
        }
}
