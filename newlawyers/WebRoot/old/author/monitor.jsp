<%@ page import="java.util.Hashtable,
                 java.util.Iterator"%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%

//操作指令，"d" 删除一个会话信息，"da" 删除所有会话信息。
String action = request.getParameter("action");

//从application中取出用于授权的会话信息
Hashtable wmsp = (Hashtable) application.getAttribute("WMSP");

if(action != null && wmsp != null)
{
    if(action.equals("d")) //清除指定的会话信息
      wmsp.remove(request.getParameter("sessionId"));
    else if(action.equals("da")) //清除所有的会话信息
      wmsp.clear();
}

%>

<html>
<head>
<title>WMS功能扩展插件――用户授权演示 </title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<p><font color="#0000FF" size="4" face="黑体">WMS 功能扩展插件――用户授权演示：</font><font color="#FF0000" size="4" face="黑体">会话标识监控</font></p>
<hr>
<p><font color="#0000FF"><strong>提示：</strong></font><strong></strong>Application 存有 <font color="#FF0000"><%out.print(wmsp==null?"0":String.valueOf(wmsp.size()));%></font> 个对象，会话标识如下:</p>
<table width="100%"  border="1" cellspacing="1" cellpadding="4">
  <tr bgcolor="#CCCCCC">
    <td width="5%" align="center">序号</td>
    <td width="10%" align="center">会话标识</td>
    <td width="35%" align="center">点播地址</td>
    <td width="15%" align="center">客户端IP</td>
    <td width="20%" align="center">请求时间</td>
    <td width="10%" align="center">是否播放</td>
    <td width="5%" align="center">操作</td>
  </tr>
  <%
  int i=0;
  if(wmsp != null)
  {
    Iterator sessionIt = wmsp.values().iterator();
    while (sessionIt.hasNext())
    {
      String[] playSession = (String[]) sessionIt.next();
  %>
  <tr>
    <td align="center"><%=++i%></td>
    <td align="center"><%=playSession[0]%></td>
    <td align="center"><%=playSession[1]%></td>
    <td align="center"><%=playSession[2]%></td>
    <td align="center"><%=playSession[3]%></td>
    <td align="center"><%=playSession[4]%></td>
    <td align="center"><a href="?action=d&sessionId=<%=playSession[0]%>">删除</a></td>
  </tr>
  <%
    }
  }
  %>
  <tr>
    <td colspan="7" align="right"><a href="monitor.jsp">刷新</a> | <a href="?action=da">全部删除</a></td>
  </tr>
</table>
<p>&nbsp;</p>
</body>
</html>
