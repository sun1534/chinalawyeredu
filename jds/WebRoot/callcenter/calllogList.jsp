<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/xtheme-gray.css" />
<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="../js/callCenter.js"></script>
<script type="text/javascript" src="userselect.js"></script>
<SCRIPT FOR="wmplay" EVENT="PlayStateChange">
	
	if(wmplay.playState==1) $("#showmsg").html("停止播放");
	if(wmplay.playState==2) $("#showmsg").html("暂停播放");
	if(wmplay.playState==3) $("#showmsg").html("正常播放");
	if(wmplay.playState==4) $("#showmsg").html("向前搜索");
	if(wmplay.playState==5) $("#showmsg").html("向后搜索");
	if(wmplay.playState==6) $("#showmsg").html("缓冲处理");
	if(wmplay.playState==7) $("#showmsg").html("等待反应");
	if(wmplay.playState==8) $("#showmsg").html("播放完毕");
	if(wmplay.playState==9) $("#showmsg").html("连接媒体");
	if(wmplay.playState==10) $("#showmsg").html("准备就绪");
</script>
<script language="javascript">
function playSound(playstr){
	var urlStr=strpath(playstr);
	//alert(urlStr);
	document.getElementById("wmplay").URL=urlStr;
	document.getElementById("wmplay").controls.play();
}
function page(str){
  document.form1.pagenumber.value=str;
  document.form1.submit()
  return true;
}
</script>
<jscalendar:head/>

</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">呼叫中心&gt;&gt;呼叫记录</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor="#F9F9F7">	
<s:form name="form1" action="calllogList.action" method="POST">
<s:hidden name="pagenumber"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <tr>
                  	<td colSpan=7>
                  	<table width="100%">
                  		<TR class=listline >
				           <TD colSpan=3>
				           呼叫日期：从<jscalendar:jscalendar name="starttime" format="%Y-%m-%d %H:%M" showstime="true"/>到<jscalendar:jscalendar	name="endtime" format="%Y-%m-%d %H:%M" showstime="true"/>
						 
						 </td>
						 <!--  
						 <TD align="right">
				           <input type="button" value="显示" alt="显示播放器">&nbsp;<input type="button" value="隐藏">
						 </td>
						 -->
					 </tr>
					 <TR class=listline >
			         
			           <td >
					  分机号：<s:textfield name="extid" size="10"/>						 
					  呼叫号码：<s:textfield name="callphone" size="10"/>	
					<!-- 呼叫类型(I,O,X)呼入，呼出，转移 -->
					  呼叫类型：<s:select name="calltype" list="#{'':'全部','I':'呼入','O':'呼出'}"/>	
					  <input type="submit" value="查询" class="botton"/>					
			           </TD>
			          </TR>
                  		</table>
                  	</td>
                  </tr>
                  <TR class=listline >
                  	<td colspan="7" id="showmsg" style="color:red"></td>
                  </TR>
                 
                   <TR class="listheadline">
                      <TD>用户</TD>
	                  <TD>呼叫类型</TD> 
	                   <TD>分机号</TD>                  
                      <TD>对方号码</TD>
                      <TD >开始时间</TD>
                      <TD >结束时间</TD>
                       <TD>操作</TD>
                      </TR>
               <!--  
                     <TR class=listline>
                      <TD >测试</TD>
                      <TD >7890</TD>            
                      <TD >13311111111</TD>
                      <TD >2009-06-26 08:30</TD>
                      <TD >2009-06-26 08:50</TD>
                      <TD>
                      	<a href="#" onclick="playSound('E:/music/怒放的生命.mp3')">播放</a>
                      
                      	&nbsp;&nbsp;&nbsp;
                      	<a href="#" onclick="document.getElementById('wmplay').controls.stop();">暂停</a>
                      	</TD>
                  </TR>
                 -->
<s:iterator value="callloglist" status="status">
                      <TR class=listline>
                      <TD >${callloglist[status.index][1]}</TD>
                      <TD >${callloglist[status.index][2]}</TD>            
                      <TD >${callloglist[status.index][3]}</TD>
                      <TD >${callloglist[status.index][4]}</TD>
                      <TD >${callloglist[status.index][5]}</TD>
                      <TD >${callloglist[status.index][6]}</TD>
                      <TD>
                      	<a href="#" onclick="playSound('${callloglist[status.index][0]}')">电脑播放</a>
                      	&nbsp;&nbsp;
                      	<a href="#" onclick="ajaxPlay('${callloglist[status.index][0]}','${curuser.userno}','${curuser.bqqno}')">分机播放</a>
                        &nbsp;&nbsp;
                      	<a href="#" onclick="document.getElementById('wmplay').controls.stop();">暂停</a>
                      	
                      	</TD>
                  </TR>
</s:iterator>
${pagestring}
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
 <object id="wmplay" style="display:none;WIDTH: 439px; HEIGHT: 60px" type="application/x-oleobject" border="0" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
	<PARAM NAME="URL" VALUE="" />
	<PARAM NAME="rate" VALUE="1">
	<PARAM NAME="balance" VALUE="0">
	<PARAM NAME="currentPosition" VALUE="0">
	<PARAM NAME="defaultFrame" VALUE="">
	<PARAM NAME="playCount" VALUE="1">
	<PARAM NAME="autoStart" VALUE="-1">
	<PARAM NAME="currentMarker" VALUE="0">
	<PARAM NAME="invokeURLs" VALUE="-1">
	<PARAM NAME="baseURL" VALUE="">
	<PARAM NAME="volume" VALUE="80">
	<PARAM NAME="mute" VALUE="0">
	<PARAM NAME="uiMode" VALUE="full">
	<PARAM NAME="stretchToFit" VALUE="-1">
 	<PARAM NAME="windowlessVideo" VALUE="0">
	<PARAM NAME="enabled" VALUE="-1">
	<PARAM NAME="enableContextMenu" VALUE="0">
	<PARAM NAME="fullScreen" VALUE="0">
	<PARAM NAME="SAMIStyle" VALUE="">
	<PARAM NAME="SAMILang" VALUE="">
	<PARAM NAME="SAMIFilename" VALUE="">
	<PARAM NAME="captioningID" VALUE="">
     <PARAM NAME="enableErrorDialogs" VALUE="0">
	<PARAM NAME="_cx" VALUE="7223">
	<PARAM NAME="_cy" VALUE="1693">
</object> 
    </div></TD>
  </TR>
</TABLE>



</BODY>
</HTML>

