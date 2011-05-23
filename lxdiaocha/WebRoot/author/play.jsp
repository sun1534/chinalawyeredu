<%@ page import="java.util.UUID,
                 java.util.Hashtable,
                 java.util.Date,
                 java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%

//点播地址
String url = request.getParameter("url");

//使用UUID对象生成唯一的guid作为会话标识
String sessionId = UUID.randomUUID().toString();

//将信息存入application

if(application.getAttribute("WMSP") == null)
{
  //初始化一个哈希表用来存放会话信息
  application.setAttribute("WMSP", new Hashtable());
}
else
{
  Hashtable wmsp = (Hashtable)application.getAttribute("WMSP");
  //用一个字符串数组存放会话信息，依次为：会话标识、点播地址、客户端IP、点播时间、是否播放过。
  String[] playSession = new String[]{sessionId,
                                  url,
                                  request.getRemoteAddr(),
                                  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                                  "NO"
                                 };

  //用sid作为key将会话信息存入哈希表
  wmsp.put(sessionId, playSession);
}


//生成最终带参数的点播地址
url = url + "?sessionId=" + sessionId;
%>

<HTML>
<BODY>
<OBJECT ID="Player" CLASSID="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
  <PARAM name="URL" value="<%=url%>"/>
</OBJECT>
<br><br>
当前播放的视频地址：<a href="<%=url%>"><%=url%></a>
<br><br>
请点击以上地址尝试调用 Media Player 播放，看看能否播放！
</BODY>
</HTML>