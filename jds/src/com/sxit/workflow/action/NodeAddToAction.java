package com.sxit.workflow.action;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.sxit.common.action.AbstractListAction;
import com.sxit.workflow.model.TwflProcess;
import com.sxit.workflow.model.TwflNode;
import com.sxit.workflow.model.TwflDirection;


/**
 *
 * <p>功能： 列表节点</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class NodeAddToAction extends AbstractListAction  {
        private TwflNode node;
        private TwflProcess process;
        private int nodeid;
        private Set <TwflNode> tonodes;
        public NodeAddToAction() {
          rights="wfl1,4";
        }
        public String go() throws HibernateException {
          node=getNode();
          TwflNode tonode=(TwflNode)getSession().get(TwflNode.class,Integer.valueOf(nodeid));
          TwflDirection direction=new TwflDirection();
          direction.setFromNode(node);
          direction.setToNode(tonode);
          getSession().save(direction);
          nextpage="nodeView.action?nodeid="+node.getNodeid();
          message="添加成功";
          return SUCCESS;
        }
        public TwflNode getNode() {
         if (node==null)
            node = (TwflNode) get("node");
          return node;
	}
        public TwflProcess getProcess() {
         if (process==null)
            process = (TwflProcess) get("process");
          return process;
        }
        public void setNodeid(int nodeid) {

          this.nodeid = nodeid;
        }
}
