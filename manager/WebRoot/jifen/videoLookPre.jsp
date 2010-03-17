<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<HTML>
<HEAD>
	<meta name="GENERATOR" content="Microsoft SharePoint" />
	<meta name="progid" content="SharePoint.WebPartPage.Document" />
	<meta HTTP-EQUIV="Content-Type" content="text/html; charset=utf-8" />
	<meta HTTP-EQUIV="Expires" content="0" />
	<META NAME="ROBOTS" CONTENT="NOHTMLINDEX"/>
	<title>
	<%=com.changpeng.common.Constants.SYS_NAME%>-在线视频
</title>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>

</HEAD>
<BODY scroll="yes">
<TABLE id="Table1" height="100%" cellSpacing="0" cellPadding="0" width="100%" border="0" bgColor="#99ccff">

	<TR>
		<TD vAlign="middle" align="center" bgColor="#99ccff" rowspan="">
			<TABLE id="Table2" style="FONT-SIZE: 9pt; TEXT-TRANSFORM: none; WIDTH: 384px; FONT-FAMILY: 宋体; HEIGHT: 387px" borderColor="#336699" cellSpacing="1" cellPadding="4" width="384" bgColor="#adc7e7" border="1">
				<TR>
					<TD vAlign="middle" style="width: 380px" align="center"><FONT face="宋体">
							<P align="left">
								<span id="LessonNameLab" style="display:inline-block;border-width:0px;font-size:9.5pt;font-weight:bold;width:367px;">
									${lessons.title}<br>讲师:${lessons.teachers}
			
							  </span>
						    &nbsp;</P>
						</FONT>					</TD>
				</TR>
		
				<TR>
					<TD vAlign="middle" align="center" style="width: 380px" height="100%">
						<OBJECT id="Player" height="100%" width="100%" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
							<PARAM NAME="URL" VALUE="${lessons.onlinefile}">
							<PARAM NAME="rate" VALUE="1">
							<PARAM NAME="balance" VALUE="0">
							<PARAM NAME="currentPosition" VALUE="0">
							<PARAM NAME="defaultFrame" VALUE="">
							<PARAM NAME="playCount" VALUE="1">
							<PARAM NAME="autoStart" VALUE="0">
							<PARAM NAME="currentMarker" VALUE="0">
							<PARAM NAME="invokeURLs" VALUE="-1">
							<PARAM NAME="baseURL" VALUE="">
							<PARAM NAME="volume" VALUE="100">
							<PARAM NAME="mute" VALUE="0">
					
							<PARAM NAME="uiMode" VALUE="FULL">
			
							<PARAM NAME="stretchToFit" VALUE="0">
							<PARAM NAME="windowlessVideo" VALUE="0">
							<PARAM NAME="enabled" VALUE="-1">
							<PARAM NAME="enableContextMenu" VALUE="-1">
							<PARAM NAME="fullScreen" VALUE="0">
							<PARAM NAME="SAMIStyle" VALUE="">
							<PARAM NAME="SAMILang" VALUE="">
							<PARAM NAME="SAMIFilename" VALUE="">
							<PARAM NAME="captioningID" VALUE="">
							<PARAM NAME="enableErrorDialogs" VALUE="0">
							<PARAM NAME="_cx" VALUE="9525">
							<PARAM NAME="_cy" VALUE="7408">
						</OBJECT>					
						</TD>
				</TR>
			
			</TABLE>
	  </TD>
	</TR>

</TABLE>
<script language="JavaScript">
<!--
/*
var Duration=document.getElementById("Duration");
var CurrentMinutes=document.getElementById("CurrentMinutes");
var LearnMinutes=document.getElementById("LearnMinutes");
var oPlay=document.getElementById("Player");
var PauseBtn=document.getElementById("PauseBtn");
var PutInBtn=document.getElementById("PutInBtn");
var IsPause=false;
var HaveSeted=false;


var setTimeSuccess=false;
function twoPlaces(amount)
{
   return amount.toFixed(2);
}
*/
-->


</script>

<SCRIPT language="JScript" event="playStateChange(NewState)" for="Player">
<!--
/*if (NewState == 1) 
 {
  
 }
 if (NewState == 2) 
 {
 }
if (NewState == 3) 
{
    Duration.value = twoPlaces(oPlay.currentMedia.duration/60); 

}
if (NewState==8) 
{

}
if (NewState==6)
{
   
      document.getElementById("videoStatus").innerText="正在缓冲......";
} 
if (NewState==7) 
{

 document.getElementById("videoStatus").innerText="等待反应......";
} 
if (NewState==9) 
{
    
 document.getElementById("videoStatus").innerText="连接媒体......";
}
if (NewState==9) 
{
   
       document.getElementById("videoStatus").innerText="准备就绪......";
}
*/
-->
</SCRIPT>


  </BODY>
</HTML>

