package com.changpeng.callcenter.util;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.changpeng.common.Globals;

import java.sql.*;
public class UserInfo {
	private static String nullToSpace(String str){
		if(str==null) str="";
		return str;
	}
	/**
	 * 业务客户
	 */
	private Element addressInfo(Connection con,String phone) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		Element tab1 = null;
		try{
			//sql="select a.*,b.bqqno  from (select * from tusr_address a where  phone='"+phone+"') a left  join tsys_user b on a.userid=b.userid order by addressid desc";	
			
			sql="select * from tusr_address a where  phone='"+phone+"' order by addressid desc";
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			tab1 = DocumentHelper.createElement("Table1");
			if(rs.next()){				
				int oprflag=rs.getInt("oprflag"); //1信用卡催收  2 非诉催收 3诉讼业务
				String cusflag="";
				if(oprflag==1) cusflag="信用卡催收客户";
				else if(oprflag==2) cusflag="非诉催收客户";
				else if(oprflag==3) cusflag="诉讼客户";
				
				String username=nullToSpace(rs.getString("username"));
				String comments=nullToSpace(rs.getString("comments"));
				String homeaddr=nullToSpace(rs.getString("homeaddr"));
				String company=nullToSpace(rs.getString("company"));
				String email=nullToSpace(rs.getString("email"));

				tab1.addElement("客户姓名").addText(username);
				tab1.addElement("家庭住址").addText(homeaddr);
				tab1.addElement("公司名称").addText(company);
				tab1.addElement("EMAIL").addText(email);
				tab1.addElement("备注").addText(comments);
				tab1.addElement("客户标记").addText(cusflag);
				
				long oprid=rs.getLong("oprid");
				if(oprflag==1){
					pstmt=con.prepareStatement("select * from topr_creditcard where creditcardid="+oprid);
					rs=pstmt.executeQuery();
					if(rs.next()){
						tab1.addElement("卡号").addText(nullToSpace(rs.getString("creditcard")));
						tab1.addElement("透支人民币").addText(nullToSpace(rs.getString("curcnfee")));
						tab1.addElement("身份证").addText(nullToSpace(rs.getString("idcard")));
					}
				}
				
				if(oprflag==2){
					pstmt=con.prepareStatement("select * from tnlw_nonlaw where nonlawid="+oprid);
					rs=pstmt.executeQuery();
					if(rs.next()){
						tab1.addElement("贷款账号").addText(nullToSpace(rs.getString("lendaccount")));
						tab1.addElement("扣款账号").addText(nullToSpace(rs.getString("payaccount")));
						tab1.addElement("身份证").addText(nullToSpace(rs.getString("idcard")));
						tab1.addElement("贷款金额").addText(nullToSpace(rs.getString("lendfee")));
						tab1.addElement("贷款余额").addText(nullToSpace(rs.getString("curbalancefee")));
					}
				}					
			}
			
			String bqqno="";
			String mobile="";
			sql="select bqqno,mobile from tsys_user where userid in (select userid from tusr_address where phone='"+phone+"') and bqqno is not null ";
		
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bqqno=rs.getString("bqqno");
				mobile=rs.getString("mobile");
			}
			tab1.addElement("AniType").addText(bqqno);
			tab1.addElement("AgtAni").addText(mobile);
			
		}finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
		return tab1;
	}
	/**
	 * 客户信息
	 * @param con
	 * @param phone
	 * @throws SQLException
	 */
	private Element customerInfo(Connection con,String phone) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		Element tab1 = null;
		try{
			sql="select * from tusr_customer where mobile='"+phone+"' or phone='"+phone+"'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				tab1 = DocumentHelper.createElement("Table1");
				String username=nullToSpace(rs.getString("username"));
				String company=nullToSpace(rs.getString("company"));
				String address=nullToSpace(rs.getString("address"));
				String fax=nullToSpace(rs.getString("fax"));
				
				int customertype=rs.getInt("customertype"); //1:机构客户 2:个人客户
				if(customertype==1){
					tab1.addElement("负责人").addText(username);
					tab1.addElement("联系人").addText(nullToSpace(rs.getString("linkman")));
				}else
					tab1.addElement("客户姓名").addText(username);
				
				tab1.addElement("公司名称").addText(company);
				tab1.addElement("地址").addText(address);
				tab1.addElement("传真").addText(fax);
				tab1.addElement("AniType").addText("8000");
			}
		}finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}
		return tab1;
	}
	
	public String userInfo(String phone){
		Connection con=null;
		Element res = DocumentHelper.createElement("NewDataSet");
		try{
			con=Globals.getInstance().getCon();
			Element tab = customerInfo(con,phone);
			if(tab==null)
				tab = addressInfo(con,phone);
			if(tab!=null)
				res.add(tab);
		}catch(SQLException e){
			System.out.println("获取客户信息数据库错。");
		}finally{
			try{
				if(con!=null) con.close();
			}catch(SQLException e){}			
		}
		return res.asXML();
	}
}
