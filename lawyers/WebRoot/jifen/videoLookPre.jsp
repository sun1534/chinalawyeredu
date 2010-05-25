<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
	<HEAD>
		<meta name="GENERATOR" content="Microsoft SharePoint" />
		<meta name="progid" content="SharePoint.WebPartPage.Document" />
		<meta HTTP-EQUIV="Content-Type" content="text/html; charset=utf-8" />
		<meta HTTP-EQUIV="Expires" content="0" />
		<META NAME="ROBOTS" CONTENT="NOHTMLINDEX" />
		<title><%=com.changpeng.common.Constants.SYS_NAME%>-在线视频</title>
		
		<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script>
	</HEAD>
	<BODY scroll="yes" onunload="exitform()">
		<form name="form1" method="post" action="" onSubmit="" id="form1">
			<TABLE id="Table1" height="100%" cellSpacing="0" cellPadding="0"
				width="100%" border="0" bgColor="#99ccff">

				<TR>
					<TD vAlign="middle" align="center" bgColor="#99ccff" rowspan="">
						<TABLE id="Table2"
							style="FONT-SIZE: 9pt; TEXT-TRANSFORM: none; WIDTH: 484px; FONT-FAMILY: 宋体; HEIGHT: 487px"
							borderColor="#336699" cellSpacing="1" cellPadding="4" width="484"
							bgColor="#adc7e7" border="1">
							<TR>
								<TD vAlign="middle" style="width: 480px" align="center">
									<FONT face="宋体">
										<!-- <P align="left">-->
											<span id="LessonNameLab"
												style="display: inline-block; border-width: 0px; font-size: 9.5pt; font-weight: bold; width: 467px;">
												${lessons.title}<br>讲师:${lessons.teachers} 
												<s:if test="!settime">
													<s:if test="!localelesson">
														<font color=red><br />您已经获得了这个课程的全部积分,您此次培训将不再计入积分</font>
													</s:if>
													<s:else>
														<font color=red><br />您已经参加了这个课程的现场培训,您此次的在线视频将不再计入积分</font>
													</s:else>
												</s:if> 
												<s:else>
													<s:if test="shouldselect">
														<font color=blue> <br />
															您上年度的培训积分为${yearfen}，未达到达标分${totalfen}，您是否选择本次培训课程计入去年的积分？
															 <input type="radio" name="jifenyearradio" value="${lastyear }"	id="jifenyearradio1" onclick="selectit(this.value)" />是
															<input type="radio" name="jifenyearradio" value="${nowyear}" id="jifenyearradio2" onclick="selectit(this.value)" />否 
															<input type="hidden" name="jifenyear" id="jifenyear" /> </font>
													</s:if>
													<s:else>
														<s:if test="islastyear">
															<font color=blue><br />您上次在线培训的时候，选择了将课程设置为上一年度，继续培训的积分，仍然计算到上一年度。上年度积分：${yearfen	}</font>
														</s:if>
														<input type="hidden" name="jifenyear" id="jifenyear" value="${jifenyear }" />
													</s:else>
												</s:else> 
												</span> &nbsp;
										<!-- </P>-->
										 </FONT>
								</TD>
							</TR>
							<TR style="">
								<TD style="width: 480px" align="left" valign="middle">
									<FONT face="宋体"> 
									<STRONG>课时总长：<input	name="Duration" type="text" readonly="readonly"	id="Duration" style="color:#0000C0;border-color:#0000C0;border-width:1px;border-style:Solid;font-size:9pt;font-weight:bold;width:56px;" />
									</STRONG>分钟
									&nbsp;&nbsp;&nbsp;&nbsp; 
									<STRONG>已培训时长： <input type="text" value="${lookedminutes}" 	name="LearnMinutes" value="30" readonly id="LearnMinutes" style="color: Red; border-color: #C00000; border-style: Solid; font-size: 9pt; font-weight: bold; width: 56px;" />
									</STRONG>分钟 
									</FONT>
								</TD>
							</TR>
							<TR style="">
								<TD style="width: 480px" align="left" valign="middle">
									<STRONG>播放时间：</STRONG>
									<input name="CurrentMinutes" type="text" value="0" readonly="readonly" id="CurrentMinutes" style="color: #0000C0; border-color: #0000C0; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold; width: 56px;" />
									分钟&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="submit" name="PutInBtn" value="转为培训时间" onClick="GetLearnMinutes();return false;" id="PutInBtn"	style="color: Crimson; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold; display: none" />
									<span id="videoStatus"></span>
								</TD>
							</TR>
							<TR>
								<TD vAlign="middle" align="center" style="width: 480px"	height="100%">
									<OBJECT id="Player" height="100%" width="100%"
										classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
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
										<s:if test="!settime">
											<PARAM NAME="uiMode" VALUE="FULL">
										</s:if>
										<s:else>
											<PARAM NAME="uiMode" VALUE="none">
										</s:else>
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
										<s:if test="!settime">
											<param name="ShowTracker" value="-1">
											<param name="ShowControls" value="-1">
										</s:if>
									</OBJECT>
								</TD>
							</TR>
							<s:if test="settime">
								<TR>
									<TD vAlign="middle" align="center" bgColor="#3366cc"
										style="width: 480px">
										<P align="right">
											&nbsp;
											<input type="submit" name="PlayBtn" value="从头播放" onClick="Start();return false;" id="PlayBtn" style="color:DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
											<input type="submit" name="LearnBtn" value="从上次得分段播放" onClick="ReLearn();return false;" id="LearnBtn" style="color: DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
											<input type="submit" name="PauseBtn" value="暂停" onClick="Pause();return false;" id="PauseBtn" style="color: DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
											<input type="submit" name="StopBtn" value="停止"	onClick="Stop();return false;" id="StopBtn" style="color: DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
										</P>
									</TD>
								</TR>
							</s:if>
						</TABLE>
					</TD>
				</TR>

			</TABLE>
		</form>

		<script language="JavaScript">
var Duration=document.getElementById("Duration");
var CurrentMinutes=document.getElementById("CurrentMinutes");
var LearnMinutes=document.getElementById("LearnMinutes");
var oPlay=document.getElementById("Player");
var PauseBtn=document.getElementById("PauseBtn");
var PutInBtn=document.getElementById("PutInBtn");
var IsPause=false;
var HaveSeted=false;
//var ShowNextMinutes=10+Math.floor(Math.random()*10);
var ShowNextMinutes=${videotimeout};
//var ShowNextMinutes=0.8;
var visitid=${visitid};
var nowyear=${nowyear};
//var ShowNextMinutes=5+twoPlaces(Math.random())*10;
//     alert(ShowNextMinutes);
setTimeout('GetInfo()', 1000);
var setTimeSuccess=false;

function exitform(){
   $.ajax({
		type: "POST",
		async: true,
		url:"../jifenajax/logVideoLookOut.pl",
		data:"id="+visitid,
	    success:function(resp){
	    }
	 });
}
function twoPlaces(amount)
{
   //return (amount==Math.floor(amount))?amount + '.00':((amount*10== Math.floor(amount*10)) ? amount + '0':Math.floor(amount*100)/100);
   return amount.toFixed(2);
}
function GetInfo()
{ 
  CurrentMinutes.value = twoPlaces(oPlay.controls.currentPosition/60);
   //如果需要设置时间的话，这里是看这个是人是不是已经获取了全部积分了
  <s:if test="settime">     
  if (Number(CurrentMinutes.value)>Number(LearnMinutes.value) & !HaveSeted & !IsPause)
   {
     var MinutesLength=Number(twoPlaces(Number(Duration.value)-Number(CurrentMinutes.value)));
     if (Number(MinutesLength)>Number(ShowNextMinutes)) 
      {
        setTimeout('ShowLearnMinutes('+ShowNextMinutes.toString()+')',ShowNextMinutes*60*1000);
        HaveSeted=true;
      }
   } 
 </s:if>
  setTimeout('GetInfo()', 1000);
}

var NewLearnMinutes=0;
function ShowLearnMinutes(Minutes)
{
   Pause();
   
   if (oPlay.fullScreen==true) oPlay.fullScreen=false;
   
   if (Minutes==null) 
     {
       //PutInBtn.value="完成培训";
       alert("恭喜,您已完成了该课程的培训...")
       NewLearnMinutes=-1;
     }
      else 
        {
          alert("新获【"+Minutes+"】分钟的网上培训时间")
          //PutInBtn.value="获取【"+Minutes+"】分钟";
          NewLearnMinutes=Minutes;
        }

    GetLearnMinutes();
        
}

function GetLearnMinutes()
{
   var SetMode=(NewLearnMinutes==-1)?"All":"";   
   var SetMinutes=(NewLearnMinutes==-1)?Duration.value:NewLearnMinutes;
   var LookedAllMinutes=(NewLearnMinutes==-1)?Duration.value:twoPlaces(Number(LearnMinutes.value)+Number(SetMinutes));
   SetLearnMinutes (twoPlaces(Number(Duration.value)),twoPlaces(Number(LookedAllMinutes)));
   if(setTimeSuccess){
	  	LearnMinutes.value=(NewLearnMinutes==-1)?SetMinutes:twoPlaces(Number(LearnMinutes.value)+Number(SetMinutes));
   	    HaveSeted=false;
	    NewLearnMinutes=0; 
   }
   ReLearn();
}
var hasselect=false;
function selectit(val){
  hasselect=true;
  document.getElementById("jifenyear").value=val;
}
//利用ajax,异步的方式将看视频的时间保存到服务器上
function SetLearnMinutes(allminutes,lookedminutes) {
	 var _url="../jifenajax/videoLook.pl";
	 var jifenyear=document.getElementById("jifenyear").value;
     var pars = "userid=${userid}&lessonid=${lessonid}&allminutes="+allminutes+"&lookedminutes="+lookedminutes+"&jifenyear="+jifenyear+"&nowyear="+nowyear+"&visitid="+visitid;
    $.ajax({
		type: "POST",
		async: false,
		url:_url,
		data:pars,
		dataType:"json",
		beforeSend:function(){
	    },
	    success:function(resp){
		   if(parseFloat(resp.huodexuefen)==-1){
           	 alert("无法保存网上培训时间，请与系统管理员联系...");
         	 setTimeSuccess =false;
       	   }
       	   else{
         	 alert("到现在为止,该课程您获得了"+resp.huodexuefen+"个学分!!!");
         	 setTimeSuccess =true;
          }
	   }
	 });

}
var confirmed=false;
function Start()
{
 //  oPlay.controls.currentPosition=0;
 //  oPlay.controls.play(); 
<s:if test="shouldselect">
 if(!hasselect){
   alert("请确定您的本次培训课程的积分年度");
   return;
 }
 var theyear=document.getElementById("jifenyear").value;
 //alert(theyear);
 var yes=true;
 if(!confirmed){
   yes=confirm("您确定您的本次培训课程的积分计算到"+theyear+"年吗？");
   confirmed=true;
 }
 if(yes){
    document.getElementById("jifenyearradio1").disabled=true;
    document.getElementById("jifenyearradio2").disabled=true;
    oPlay.controls.currentPosition=0;
    oPlay.controls.play(); 
 }else{
    return;
 }
</s:if>
<s:else>
   oPlay.controls.currentPosition=0;
   oPlay.controls.play(); 
</s:else>
}
 
function ReLearn()
 {
  // oPlay.controls.currentPosition=LearnMinutes.value*60;
  // if (IsPause) 
  //  Pause();
  //   else oPlay.controls.play();
 oPlay.controls.currentPosition=LearnMinutes.value*60;
 <s:if test="shouldselect">
  if(!hasselect){
   alert("请确定您的本次培训课程的积分年度");
   return;
 }
 var theyear=document.getElementById("jifenyear").value;
 var yes=true;
 if(!confirmed){
   yes=confirm("您确定您的本次培训课程的积分计算到"+theyear+"年吗？");
   confirmed=true;
 }
 
 //  if(confirm("您确定您的本次培训课程的积分计算到"+theyear+"年吗？")){
  if(yes){
    document.getElementById("jifenyearradio1").disabled=true;
    document.getElementById("jifenyearradio2").disabled=true;
  }
   if (IsPause) 
      Pause();
   else 
      oPlay.controls.play();
 
 </s:if>
 <s:else>
    if (IsPause) 
     Pause();
   else 
     oPlay.controls.play();
 </s:else>
  
}

function Pause()
{  
  IsPause=!IsPause;

  if (IsPause) 
    {
       oPlay.controls.pause();
       PauseBtn.value="继续";
    }
    else
    {
       oPlay.controls.play();
       PauseBtn.value="暂停";
    }
}

function Stop()
{
  oPlay.controls.stop();
}

</script>

		<SCRIPT language="JScript" event="playStateChange(NewState)"
			for="Player">
<!--
if (NewState == 1) //停止
 {
    if (IsPause) 
		{
			IsPause=false;
			PauseBtn.value="暂停";
		}
 }
 if (NewState == 2) //暂停
 {
 }
if (NewState == 3) //从头开始
{
    Duration.value = twoPlaces(oPlay.currentMedia.duration/60); 
    if (IsPause) 
		{
			IsPause=false;
			PauseBtn.value="暂停";
		}
}
if (NewState==8) //完成播放
{
     var LostLength=Number(twoPlaces(Number(Duration.value)-Number(LearnMinutes.value)));
        
     if (LostLength>0 & LostLength<=Number(ShowNextMinutes) & !HaveSeted) setTimeout('ShowLearnMinutes(null)',1000);
}
if (NewState==6) //缓冲处理
{
     //alert("正在缓冲......");
      document.getElementById("videoStatus").innerText="正在缓冲......";
} 
if (NewState==7) //等待反应
{
   //  alert("等待反应......");
 document.getElementById("videoStatus").innerText="等待反应......";
} 
if (NewState==9) //连接媒体
{
     //alert("连接媒体......");
 document.getElementById("videoStatus").innerText="连接媒体......";
}
if (NewState==9) //准备就绪
{
    // alert("准备就绪......");
       document.getElementById("videoStatus").innerText="准备就绪......";
}

-->
</SCRIPT>


	</BODY>
</HTML>

