package com.changpeng.common;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import java.sql.*;
/**
 * 来电请求页面 软终端通过发送电话信息至该页面，获取到来电用户基本信息，并将基本信息返回
 * @author sinhoo
 * Jun 26, 2009
 */
public class CallAnswerServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(CallAnswerServlet.class);
	private static Globals global=Globals.getInstance();
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter out=null;
		
		StringBuffer bf=new StringBuffer();
		//PrintWriter out = null;
		try{
			out=response.getWriter();
			/*br=new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String line="";
			while((line=br.readLine())!=null)
				bf.append(line);
			LOG.info(bf.toString());*/
		
			String phone=request.getParameter("AniNum") ;//parseReq(bf.toString());
		//	String baseInfo=queryInfo(phone);
			queryInfo(phone,out);
			//out.println("............................");
			/*
			response.setCharacterEncoding("utf-8");
			out=response.getWriter();
			
			out.println(new String(baseInfo.getBytes("gb2312"),"iso-8859-1"));
				*/	
			//System.out.println(baseInfo);
			response.setContentType("text/xml;charset=utf-8");

		//	response.setCharacterEncoding("gb2312");
			System.out.println("text/xml;charset=utf-8");
			//os = response.getOutputStream();
			//os.write(baseInfo.getBytes());
			//os.flush();	
		}catch(SQLException e){
			LOG.error("数据库异常："+e);
		}finally{
			out.close();
			if(os!=null) os.close();
			//if(out!=null) out.close();
			if(br!=null) br.close();
		}
	}
	/**
	 * 根据来电号码获取用户基本信息
	 * @param reqStr
	 * @throws SQLException
	 */
	private void queryInfo(String phone,PrintWriter out) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		//StringBuffer buffer=new StringBuffer();
		//buffer.append("<?xml version=\"1.0\" standalone=\"yes\"?>");
		out.println("<?xml version=\"1.0\"?>");
		//out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<NewDataSet>");
		try{
			con=global.getCon();
			
			String temp="";
			if(phone.startsWith("0755"))
				temp=" or phone='"+phone.substring(4)+"'";
			if(phone.startsWith("013")||phone.startsWith("015")||phone.startsWith("018"))
				temp=" or phone='"+phone.substring(1)+"'";
			sql="select a.*,b.bqqno  from (select * from tusr_address a where  phone='"+phone+"' "+temp+") a left  join tsys_user b on a.userid=b.userid";
			System.out.println(sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int oprflag=rs.getInt("oprflag"); //1信用卡催收  2 非诉催收 3诉讼业务
				String cusflag="";
				if(oprflag==1) cusflag="信用卡催收客户";
				else if(oprflag==1) cusflag="非诉催收客户";
				else if(oprflag==1) cusflag="诉讼客户";
				String bqqno=nullToSpace(rs.getString("bqqno"));
				String username=nullToSpace(rs.getString("username"));
				String comments=nullToSpace(rs.getString("comments"));
				String homeaddr=nullToSpace(rs.getString("homeaddr"));
				String company=nullToSpace(rs.getString("company"));
				String email=nullToSpace(rs.getString("email"));
				out.println("<Table1>");
				out.println("<客户姓名>"+username+"</客户姓名>");
				out.println("<家庭住址>"+homeaddr+"</家庭住址>");
				out.println("<公司名称>"+company+"</公司名称>");
				out.println("<EMAIL>"+email+"</EMAIL>");
				out.println("<备注>"+comments+"</备注>");
				out.println("<客户标记>"+cusflag+"</客户标记>");
				out.println("<AniType>"+bqqno+"</AniType>");
				
				out.println("</Table1>");
			}
		}finally{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}
		out.println("</NewDataSet>");

		//return buffer.toString();
	}
	private static String nullToSpace(String str){
		if(str==null) str="";
		return str;
	}
	/**
	 * 根据请求信息获取来电号码
	 * @param reqStr
	 * @return
	 */
/*	public String parseReq(String reqStr){
		String phone="";
		return phone;
	}*/
}
