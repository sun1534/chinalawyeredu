package com.sxit.workflow.action;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.TwflNode;
import com.sxit.workflow.model.TwflProcess;
import com.sxit.workflow.util.NodeDoType;
import com.sxit.workflow.util.NodeType;

/**
 *
 * <p>功能： 创建节点</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class NodeCreateAction extends AbstractAction {

	private TwflNode node;

        private TwflProcess process;
        private int processid;

        private int[] bodypower;
        private int[] flowpower;
        private int[] attachpower;
	public NodeCreateAction() {
           rights="wfl1,2";
		node = new TwflNode();
	}

	public String go() throws HibernateException {
          process=(TwflProcess)getSession().get(TwflProcess.class,Integer.valueOf(node.getTwflProcess().getProcessid()));

          int nodeid=process.getNewnodeid() + process.getProcessid()* 1000;
          node.setNodeid(nodeid);
          node.setXcoordinate(process.getXposition());
          node.setYcoordinate(process.getYposition());

          process.setNewnodeid(process.getNewnodeid()+1);
          process.setYposition(process.getYposition()+60);
System.out.println("newnodeid="+process.getNewnodeid());

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
                getSession().update(process);
		getSession().save(node);
                System.out.println("nodeid="+node.getNodeid());
		set("node", node);
                nextpage="nodeList.action?processid="+node.getTwflProcess().getProcessid();
                message="保存成功！";
		return SUCCESS;
	}

	public TwflNode getNode() {
		return node;
	}
        public String input() throws Exception {
          process=(TwflProcess)getSession().get(TwflProcess.class,Integer.valueOf(processid));
          if(process==null){
            message = "未找到此流程";
            return "message";
          }
          return "input";
    }
    public List getNodetypelist(){
      return NodeType.getTypelist();
    }
    public List getNodedotypelist(){
      return NodeDoType.getTypelist();
    }
    public void setProcessid(int processid) {
      this.processid = processid;
    }
    public TwflProcess getProcess() {
            return process;
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
    //职务列表
    public List getPositionlist() throws HibernateException {
            String queryName ;
//            queryName="from TsysPosition as position order by position.positionname";
            queryName="from TsysRole as role order by role.rolename";
            Query query = getSession().createQuery(queryName);
            return query.list();
        }
}
