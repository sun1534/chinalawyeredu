package com.sxit.workflow.action;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.Type;

import com.sxit.workflow.model.*;
import com.sxit.common.action.AbstractAction;
import java.util.*;


/**
 *
 * <p>功能： 查询业务</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-11-13</p>
 * @版本： V1.0
 * @修改：
 */

public class BusinessSearchAction  extends AbstractAction {
  private List businesslist;
  private TwflBusiness businessExample=new TwflBusiness();
  private String flag=""; //flag为in的时候表述输入查询条件 flag为out的时候表述输出查询结果
  //private String departmentname;
  public BusinessSearchAction() {
    rights="wfl2,1";
  }
  //忽略空字符串
  private static final Example.PropertySelector NON_EMPTY_STRING = new Example.PropertySelector() {
          public boolean include(Object value, String name, Type type) {
                  return value!=null && !"".equals(value);
          }
  };
  protected String go() throws HibernateException {
      Criteria criteria = getSession().createCriteria(TwflBusiness.class)
          .add(Example.create(businessExample) //事例查询
               .enableLike() //模糊查询
               .excludeProperty("statusid")
               .excludeProperty("businessid")
               .excludeProperty("usertype")
               .ignoreCase() //忽略大小写
               .setPropertySelector(NON_EMPTY_STRING) //忽略空字符串
               );
//      if ( !"".equals(departmentname) ) {
//              criteria.createCriteria("twflDepartment")
//                      .add( Expression.like("departmentname", departmentname,MatchMode.ANYWHERE) );
//                //完全匹配  Expression.eq("departmentname", departmentname)
//      }
      //businesslist = criteria.setMaxResults(maxperpage).list();//一次最多输出maxpaerpage条记录
      businesslist = criteria.list(); //将结果集一次输出
      return SUCCESS;
   }
   public String input() throws Exception {
     return "input";
   }
   public List getBusinesslist() {
     return businesslist;
   }
   public TwflBusiness getBusinessExample() {
           return businessExample;
   }
   public void setFlag(String flag)
   {
     this.flag=flag;
   }
   public String getFlag()
   {
     return this.flag;
   }
  // public void setDepartmentname(String departmentname)
  // {
  //   this.departmentname=departmentname;
  // }

}

