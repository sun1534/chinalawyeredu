<%@ page contentType="text/xml;charset=gb2312" import="com.changpeng.callcenter.util.UserInfo" %><%
String phone=request.getParameter("AniNum");
out.println("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
out.println(new UserInfo().userInfo(phone));
%>
