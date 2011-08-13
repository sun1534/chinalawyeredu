package com.sxit.wait.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.Type;


import com.sxit.common.action.AbstractAction;
import com.sxit.common.action.AbstractListAction;
import com.sxit.wait.model.TwatWait;

/**
 *
 * <p>功能： 查询急件</p>
 * <p>作者： zrb</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
 */

public class SearchWaitAction  extends AbstractAction {
  private List waitlist;
  private TwatWait waitExample=new TwatWait();
  private String flag=""; //flag为in的时候表述输入查询条件 flag为out的时候表述输出查询结果
  public SearchWaitAction() {
    rights="wat1,1";
  }
  //忽略空字符串
  private static final Example.PropertySelector NON_EMPTY_STRING = new Example.PropertySelector() {
          public boolean include(Object value, String name, Type type) {
                  return value!=null && !"".equals(value);
          }
  };
  protected String go() throws org.hibernate.HibernateException {
    if("in".equals(flag)){
      return SUCCESS;
    }else
    {
      Criteria criteria = getSession().createCriteria(TwatWait.class)
          .add(Example.create(waitExample) //事例查询
               .enableLike() //模糊查询
               .excludeProperty("statusid")
               .ignoreCase() //忽略大小写
               .setPropertySelector(NON_EMPTY_STRING) //忽略空字符串
               );
      //waitlist = criteria.setMaxResults(maxperpage).list();//一次最多输出maxpaerpage条记录
      waitlist = criteria.list(); //将结果集一次输出
      return "search";
    }
   }
   public List getWaitlist() {
     return waitlist;
   }
   public TwatWait getWaitExample() {
           return waitExample;
   }
   public void setFlag(String flag)
   {
     this.flag=flag;
   }
   public String getFlag()
   {
     return this.flag;
   }
}

