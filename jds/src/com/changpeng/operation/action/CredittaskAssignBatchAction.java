package com.changpeng.operation.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.changpeng.common.Globals;
import com.changpeng.operation.model.ToprCreditcard;
import com.changpeng.operation.model.ToprCredittask;
import com.sxit.common.action.AbstractListAction;
import com.sxit.system.model.TsysRole;
import com.changpeng.operation.util.*;
/**
 * 对信用卡催收任务进行批量分配
 * @author sinhoo
 * Aug 9, 2009
 */
public class CredittaskAssignBatchAction extends AbstractListAction  {
		//催收任务的条件
		private String consigntype;
	    private String consignflag;
	    private long bankid;

	    //催收员
	    private Set roleusers; 
	    //选定的催收员
        private long[] check;
        
        
        public String getConsigntype() {
			return consigntype;
		}
		public String getConsignflag() {
			return consignflag;
		}
		
		public Set getRoleusers() {
			return roleusers;
		}
		public long[] getCheck() {
			return check;
		}
		public void setConsigntype(String consigntype) {
			this.consigntype = consigntype;
		}
		public void setConsignflag(String consignflag) {
			this.consignflag = consignflag;
		}
		
		public long getBankid() {
			return bankid;
		}
		public void setBankid(long bankid) {
			this.bankid = bankid;
		}
		
		public void setRoleusers(Set roleusers) {
			this.roleusers = roleusers;
		}
		public void setCheck(long[] check) {
			this.check = check;
		}
		public CredittaskAssignBatchAction() {
          rights="opr3,1";
        }
        public String go() throws HibernateException {
        	if(check==null||check.length==0){
        		this.message="请选择催收人员.";
        		return ERROR;
        	}
        	int size=check.length;
        	
        	String queryName="from ToprCreditcard as creditcard where state=0";
            if (bankid != 0)
            	queryName+=" and bankid="+bankid;        		
    		if (consigntype!= null&&!"".equals(consigntype))
    			queryName+=" and consigntype="+consigntype;     
    		if (consignflag!= null&&!"".equals(consignflag))
    			queryName+=" and consignflag="+consignflag;    
    		
    		queryName+=" order by to_number(curcnfee) desc";
            List<ToprCreditcard> list = getSession().createQuery(queryName).list();
            
            
            Connection con=null;	
    		Statement stmt=null;
    		try{
    			con=Globals.getInstance().getCon();
    			stmt=con.createStatement();
    			
    			int index=0;
    			for(ToprCreditcard card:list){		
    				//更新任务表中该笔记录为任务撤销状态
    				//(本来查的就仅仅只有未分配的记录)
    				//updateTask(con,card.getCreditcardid());
    				
    				if(index>=size)
    					index=0;
    				long userid=check[index];  				
    				long creditcardid=card.getCreditcardid();
    				//插入用户任务表
    				stmt.addBatch("insert into topr_credittask (credittaskid,creditcardid,userid,createtime,taskstat) values (ToprCredittaskid.nextval,"+creditcardid+","+userid+",sysdate,0)");
    				//更新记录状态
    				stmt.addBatch("update topr_creditcard set state=1 where creditcardid="+creditcardid+"");
    				//将对应的客户信息记录同步到该用户通讯录
    				stmt.addBatch("update tusr_address set userid="+userid+" where oprid="+creditcardid+" and oprflag=1");		
    				index++;
    			}
    			stmt.executeBatch();		
    		}catch(SQLException e){
    			this.message="批量分配异常【"+e.getMessage()+"】";
    			return ERROR;
    		}finally{
    			try{
    				if(stmt!=null) stmt.close();
        			if(con!=null) con.close();
    			}catch(SQLException e){}		
    		}
    		message="批量分配成功";
    		this.nextpage="credittaskList.action";
        	return SUCCESS;  	
        }
        
      //将原有任务状态更新为被重新分配
    	private static void updateTask(Connection con,long creditcardid) throws SQLException{
    		PreparedStatement stmt=null;
    		try{
    			stmt=con.prepareStatement("update topr_credittask set taskstat=1 where creditcardid="+creditcardid);
    			stmt.execute();
    		}finally{
    			if(stmt!=null) stmt.close();
    		}
    	}
    	
        public String input(){     	
        	//信用卡催收角色
        	TsysRole role=(TsysRole)getSession().get(TsysRole.class, 1);
        	if(role!=null)
        		roleusers=role.getTsysUserRoles();
        	return INPUT;
        }
        
}
