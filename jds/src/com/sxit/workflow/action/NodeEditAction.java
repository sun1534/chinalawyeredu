package com.sxit.workflow.action;


import java.util.*;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;

import com.sxit.workflow.util.*;
import org.hibernate.Query;


/**
 *
 * <p>功能： 编辑节点</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class NodeEditAction extends AbstractAction {

	private TwflNode node;
        private int[] bodypower;
        private int[] flowpower;
        private int[] attachpower;
	public NodeEditAction() {
          rights="wfl1,4";
	}

	public String go() throws HibernateException {
          byte temppower=0;
          byte temppower1=0;
          byte temppower2=0;
          if(bodypower!=null)
          {
            for (int temp : bodypower) {
              temppower += temp;
            }
          }
          if(flowpower!=null)
          {
            for (int temp : flowpower) {
              temppower1 += temp;
            }
          }
          if(attachpower!=null)
          {
            for (int temp : attachpower) {
              temppower2 += temp;
            }
          }
          node.setBodydotype(temppower);
          node.setFlowdotype(temppower1);
          node.setAttachdotype(temppower2);
                getSession().update(node);
		set("node", node);
                nextpage="nodeList.action?processid="+node.getTwflProcess().getProcessid();
                message="保存成功！";
		return SUCCESS;
	}

	public TwflNode getNode() {
         if (node==null)
            node = (TwflNode) get("node");
          return node;
	}

        public String input() throws Exception {
          return "input";
        }
        public List getNodetypelist(){
          return NodeType.getTypelist();
        }
        public List getNodedotypelist(){
          return NodeDoType.getTypelist();
        }
    public void setBodypower(int[] bodypower) {
      this.bodypower = bodypower;
    }
    public void setFlowpower(int[] flowpower) {
      this.flowpower = flowpower;
    }
    public void setAttachpower(int[] attachpower) {
      this.attachpower = attachpower;
    }
    //权限位与运算
    //正文权限
    public boolean getBodypower1() {
      int power = this.node.getBodydotype();
      return 1==(power & 1);
    }
    public boolean getBodypower2() {
    int power = this.node.getBodydotype();
    return 2==(power & 2);
    }
    //流程权限
    public boolean getFlowpower1() {
      int power = this.node.getFlowdotype();
      return 1==(power & 1);
    }
    public boolean getFlowpower2() {
    int power = this.node.getFlowdotype();
    return 2==(power & 2);
    }
    public boolean getFlowpower4() {
      int power = this.node.getFlowdotype();
      return 4==(power & 4);
    }
    public boolean getFlowpower8() {
      int power = this.node.getFlowdotype();
      return 8==(power & 8);
    }
    //附件权限
    public boolean getAttachpower1() {
      int power = this.node.getAttachdotype();
      return 1==(power & 1);
    }
    public boolean getAttachpower2() {
    int power = this.node.getAttachdotype();
    return 2==(power & 2);
    }
    public boolean getAttachpower4() {
      int power = this.node.getAttachdotype();
      return 4==(power & 4);
    }
    //职务列表
    public List getPositionlist() throws HibernateException {
            String queryName ;
//            queryName="from TsysPosition as position order by position.positionname";
            queryName="from TsysRole as role order by role.rolename";
            Query query = getSession().createQuery(queryName);
            return query.list();
        }
}
