<%@ page
	import="java.util.Hashtable,java.util.Date,java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%
	//设置每个会话标识的有效期，单位：秒
	int expires = 60;

	//设置会话标识被清理的时间，单位：秒，清理完在监控页面将不再看到
	int timeout = 300;

	String sessionId = request.getParameter("sessionId");

	//从application中读取会话信息
	Hashtable wmsp = (Hashtable) application.getAttribute("WMSP");
//System.out.println("=======>"+wmsp);
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	System.out.println("==>" + request.getHeader("x-forwarded-for") + "==>" + sessionId);
	//清理过期会话信息
	for (Object key : wmsp.keySet().toArray()) {
		String[] checkSession = (String[]) wmsp.get(key);
		if (System.currentTimeMillis() - timeFormat.parse(checkSession[3]).getTime() > timeout * 1000)
			wmsp.remove(key);
	}

	//检查点播请求
	if (sessionId == null) {
		out.print("缺少会话标识");
		System.out.println("缺少会话标识");
	} else {
		if (wmsp != null) {
			String[] playSession = (String[]) wmsp.get(sessionId);

			

			if (playSession == null) {
				out.print("无效会话标识");
				System.out.println("无效会话标识");
			} else {
			
				System.out.println("==>" + request.getHeader("x-forwarded-for") + "==>" +playSession[0]+"=>"+playSession[4] );
				if (System.currentTimeMillis() - timeFormat.parse(playSession[3]).getTime() > expires * 1000) {
					out.print("会话标识已过期");
					System.out.println("会话标识已过期");
				}
				//如果想让一个会话标识只可点播一次，请取消以下两行的注释，
				//但经部分用户反映，有些客户端会同时提交两次点播请求，所以请慎用！  
				else if (playSession[4].equals("YES4")) {
					out.print("此标识已点播过");
					System.out.println("会话标识已过期");
				}
				else if(playSession[4].equals("YES1")){
					out.print("allow=true");
					playSession[4] = "YES2";
					System.out.println("allow=true第2次");
				}
				else if(playSession[4].equals("YES2")){
					out.print("allow=true");
					playSession[4] = "YES3";
					System.out.println("allow=true第3次");
				}
				else if(playSession[4].equals("YES3")){
					out.print("allow=true");
					playSession[4] = "YES4";
					System.out.println("allow=true第4次");
				}

				else {
					out.print("allow=true");
					System.out.println("allow=true");
					//标记为已播放状态
					playSession[4] = "YES1";
				}
			}
		} else {
			out.print("会话标识未初始化");
			System.out.println("会话标识未初始化");
		}
	}
%>