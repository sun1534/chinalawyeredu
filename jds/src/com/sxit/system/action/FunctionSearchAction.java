package com.sxit.system.action;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.Type;

import com.sxit.system.model.*;
import com.sxit.common.action.AbstractAction;

import java.util.*;


/**
 *
 * <p>功能： 查询用户</p>
 * <p>作者： 张如兵</p>
 * <p>部门： 深圳信科</p>
 * <p>日期： 2004-11-15</p>
 * @版本： V1.0
 * @修改：
 */
public class FunctionSearchAction  extends AbstractAction {
  private List functionlist;
  private TsysFunction functionExample=new TsysFunction();
  private String flag=""; //flag为in的时候表述输入查询条件 flag为out的时候表述输出查询结果
  private String modulename;
  public FunctionSearchAction() {
   rights="sys5,1";
  }
  //忽略空字符串
  private static final Example.PropertySelector NON_EMPTY_STRING = new Example.PropertySelector() {
          public boolean include(Object value, String name, Type type) {
                  return value!=null && !"".equals(value);
          }
  };
  protected String go() throws HibernateException {
      Criteria criteria = getSession().createCriteria(TsysFunction.class)
          .add(Example.create(functionExample) //事例查询
               .enableLike(MatchMode.ANYWHERE) //模糊查询
               .ignoreCase() //忽略大小写
               .setPropertySelector(NON_EMPTY_STRING) //忽略空字符串
               );
      if ( !"".equals(modulename) ) {
              //if the module supplied a rolename,
              //also constrain the roles
              criteria.createCriteria("tsysModule")
                      .add( Expression.like("modulename", modulename,MatchMode.ANYWHERE) );
                //完全匹配  Expression.eq("departmentname", departmentname)
      }
      functionlist = criteria.list(); //将结果集一次输出
      return SUCCESS;
   }
   public String input() throws Exception {
     return "input";
   }
   public List getFunctionlist() {
     return functionlist;
   }
   public TsysFunction getFunctionExample() {
           return functionExample;
   }
   public void setFlag(String flag)
   {
     this.flag=flag;
   }
   public String getFlag()
   {
     return this.flag;
   }
   public void setModulename(String modulename)
   {
     this.modulename=modulename;
   }

}

