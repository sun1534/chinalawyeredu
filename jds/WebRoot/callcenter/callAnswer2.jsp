<%@ page contentType="text/xml;charset=gb2312" import="com.changpeng.common.*,java.sql.*,org.dom4j.DocumentHelper,org.dom4j.Element" %><%
String phone=request.getParameter("AniNum");
Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs=null;
String sql="";
out.println("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
Element res = DocumentHelper.createElement("NewDataSet");
try{
	
	con=Globals.getInstance().getCon();
	sql="select a.*,b.bqqno  from (select * from tusr_address a where  phone='"+phone+"') a left  join tsys_user b on a.userid=b.userid";
	System.out.println(sql);
	pstmt=con.prepareStatement(sql);
	rs=pstmt.executeQuery();
	while(rs.next()){
		Element tab1 = DocumentHelper.createElement("Table1");
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

		tab1.addElement("客户姓名").addText(username);
		tab1.addElement("家庭住址").addText(homeaddr);
		tab1.addElement("公司名称").addText(company);
		tab1.addElement("EMAIL").addText(email);
		tab1.addElement("备注").addText(comments);
		tab1.addElement("客户标记").addText(cusflag);
		tab1.addElement("AniType").addText(bqqno);
		res.add(tab1);
	}
}finally{
	if(rs!=null) rs.close();
	if(pstmt!=null) pstmt.close();
	if(con!=null) con.close();
}
out.println(res.asXML());
%>
<%!
public static String nullToSpace(String str){
	if(str==null) str="";
	return str;
}
%>