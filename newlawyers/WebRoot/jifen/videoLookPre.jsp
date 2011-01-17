<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${webinfo.sysname }-在线视频</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="../js/tab.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>

<body>
<div class="header">
  <div class="logo"><a href="../index/index.pl"><img src="../images/logo.gif" width="234" height="51" /></a></div>
</div>
<div class="blank15px"></div>
<div class="con">
  <div class="con_left left">
    <div class="con_sp">
      <div class="con_sptitle">
      <ul>
	  <li><a href="#">宽带</a></li>
	  <li><a href="#">窄带</a></li>
	  <li><a href="#">音频</a></li>
	 </ul>         
     </div>
     <div class="sptu">
     
     <%
						String sessionId=java.util.UUID.randomUUID().toString();
					String url="";
						if(application.getAttribute("WMSP") == null)
{
  //初始化一个哈希表用来存放会话信息
  application.setAttribute("WMSP", new java.util.Hashtable());
}
else
{
  java.util.Hashtable wmsp = (java.util.Hashtable)application.getAttribute("WMSP");
  //用一个字符串数组存放会话信息，依次为：会话标识、点播地址、客户端IP、点播时间、是否播放过。
  String[] playSession = new String[]{sessionId,
                                  url,
                                  request.getRemoteAddr(),
                                  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()),
                                  "NO"
                                 };


  //用sid作为key将会话信息存入哈希表
  wmsp.put(sessionId, playSession);
  
  //System.out.println("我这里的wmsp:::"+application.getAttribute("WMSP"));
}
						
							 %>
     
     <OBJECT id="Player" height="100%" width="100%"
										classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
										<PARAM NAME="URL" VALUE="${lessons.onlinefile}?sessionId=<%=sessionId %>">
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
     
     
     </div>
     <div class="con_spnr">
       <div class="tu"><img src="../images/tu.gif" width="269" height="11" /></div>
       <div class="con_wen">
       课时总长：<input	name="Duration" type="text" readonly="readonly"	id="Duration" style="color:#0000C0;border-color:#0000C0;border-width:1px;border-style:Solid;font-size:9pt;font-weight:bold;width:56px;" />分钟 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       已培训时长：<input type="text" value="${lookedminutes}" 	name="LearnMinutes" value="30" readonly id="LearnMinutes" style="color: Red; border-color: #C00000; border-style: Solid; font-size: 9pt; font-weight: bold; width: 56px;" />分钟
播放时间：<input name="CurrentMinutes" type="text" value="0" readonly="readonly" id="CurrentMinutes" style="color: #0000C0; border-color: #0000C0; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold; width: 56px;" />分钟&nbsp;&nbsp;&nbsp;&nbsp;
<span id="videoStatus"></span>
<!-- 已获学分：2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
播放状态：正在播放
-->
</div>
<div class="con_xian"></div>
<div class="con_an">
&nbsp;
											<input type="submit" name="PlayBtn" value="从头播放" onClick="Start();return false;" id="PlayBtn" style="color:DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
											<input type="submit" name="LearnBtn" value="从上次得分段播放" onClick="ReLearn();return false;" id="LearnBtn" style="color: DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
											<input type="submit" name="PauseBtn" value="暂停" onClick="Pause();return false;" id="PauseBtn" style="color: DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
											<input type="submit" name="StopBtn" value="停止"	onClick="Stop();return false;" id="StopBtn" style="color: DarkSlateGray; background-color: PaleTurquoise; border-color: Teal; border-width: 1px; border-style: Solid; font-size: 9pt; font-weight: bold;" />
											&nbsp;
											<span id="hinthint"></span>

</div>
     </div>
    </div>
    <div class="blank10px"></div>
    <div class="con_text"><a href="#">为什么无法拉动播放进度条？</a></div>
    <div class="blank10px"></div>
    
    <div class="slDarcontent2">
	    <div class="slDarcontent2title">
	      <ul>
	        <li id="menus1" onmouseover="setTab ('menus',1,3)" class="hover">课程目录</li>
			<li id="menus2" onmouseover="setTab ('menus',2,3)">主讲老师简介</li>
			<li style="border-right:none;" id="menus3" onmouseover="setTab ('menus',3,3)">相关栏目</li>
          </ul>
         </div>
		<div class="slDarcontent2content">
		 <div id="con_menus_1">
		  <ul>
			<li><a href="#">一、律师职业的点位</a></li>
			<li><a href="#">二、律师的基本技能</a></li>
			<li><a href="#">三、律所和律师的营销（上）</a></li>
			<li><a href="#">四、律所和律师的营销（下）</a></li>
			<li><a href="#">五、如何判断个人是否适合律师职业</a></li>
	      </ul>
		  </div>
		   <div id="con_menus_2" style="display:none">
		    <ul>
            <li><a href="#">四、律所和律师的营销（下）</a></li>
			<li><a href="#">五、如何判断个人是否适合律师职业</a></li>
		    <li><a href="#">一、律师职业的点位</a></li>
			<li><a href="#">二、律师的基本技能</a></li>
			<li><a href="#">三、律所和律师的营销（上）</a></li>
	        </ul>
		  </div>
		  
		   <div id="con_menus_3" style="display:none">
		    <ul>
		    <li><a href="#">四、律所和律师的营销（下）</a></li>
			<li><a href="#">三、律所和律师的营销（上）</a></li>
            <li><a href="#">五、如何判断个人是否适合律师职业</a></li>
		    <li><a href="#">一、律师职业的点位</a></li>
			<li><a href="#">二、律师的基本技能</a></li>
	        </ul>
		  </div>
		  
		</div>

    <!-- 如果要显示背景颜色，这需要加这个清除浮动代码，否则可不加 -->
  </div>
    
  </div>
  <div class="con_right left">
    <div class="con_right_title"><h2>${lessons.title}</h2><span>主讲老师：${lessons.teachers}</span></div>
    <div class="con_right_title">
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
    </div>
    
    
    <div class="starcontent2">
	    <div class="starcontent2title">
	      <ul>
	        <li id="menu1" class="hover"onmouseover="setTab ('menu',1,2)">讲义内容</li>
			<li style="border-right:none;" id="menu2" onmouseover="setTab ('menu',2,2)"> 主讲老师介绍</li>
          </ul>
      </div>
		<div class="starcontent2content">
        <div id="con_menu_1">
		  <ul>
			<li><p>&nbsp;&nbsp;
          ${lessons.lessoncontent }
</li>
	      </ul>
          <div class="news_fanye">

		</div>
</div>
          <div id="con_menu_2" style="display:none">
		  <ul>
			<li>
			<s:if test="lessons.teacherid!=0&&teacher!=null">
			${teacher.comments }
			</s:if>
			<s:else>
			暂时没有该授课老师的简介信息
			</s:else>
			
           </li>
	      </ul>
          <!-- 
          <div class="news_fanye">
		<form action="" method="get">
		  <span><a href="#">首页</a></span>  <span><a href="#">上一页</a></span>  <span><a href="#">下一页</a></span>  <span><a href="#">尾页</a></span>  <span><a href="#">共6页</a></span>
		</form>
		</div>
		-->
          </div>
		</div>
        <div class="con_pj_k">
        <div class="con_pj_title"><h3>课程评价</h3><span><a href="#">我要评价</a></span></div>
        <s:iterator value="replylist" status="stat">
        <div class="con_pj"><strong><!-- 吕老师的课讲得非常生动 --></strong></br>
评论人：${replyuser }   评论日期:${replytime }     </br>
${replycontent }
</div>
</s:iterator>
 </div> </div>
    
  </div>
</div>
<div class="blank15px"></div>
<div class="copy">CopyRight(C)  中国律师培训网  版权所有</div>
<script language="JavaScript">
var Duration=document.getElementById("Duration");
var CurrentMinutes=document.getElementById("CurrentMinutes");
var LearnMinutes=document.getElementById("LearnMinutes");
var oPlay=document.getElementById("Player");
var PauseBtn=document.getElementById("PauseBtn");
var PutInBtn=document.getElementById("PutInBtn");
var IsPause=false;
var HaveSeted=false;
var ShowNextMinutes=${videotimeout};
var visitid=${visitid};
var nowyear=${nowyear};
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
  var timeelapsed=twoPlaces(Number(CurrentMinutes.value)-Number(LearnMinutes.value));
  //document.getElementById("hinthint").innerText=timeelapsed+","+HaveSeted+","+IsPause;
  window.status=timeelapsed+","+HaveSeted+","+IsPause;
   //如果需要设置时间的话，这里是看这个是人是不是已经获取了全部积分了
   //这里也就是如果听课的时长>那个设置的时长就计分，而不是10分钟后来自动执行
   //如果听课的时长>已听课的时长并这次听课的时长大于10分钟，就记录
  <s:if test="settime">     
  //if (Number(CurrentMinutes.value)>Number(LearnMinutes.value) & !HaveSeted & !IsPause)
  //播放时间大于已听课时间
  // if (Number(CurrentMinutes.value)>Number(LearnMinutes.value) & !HaveSeted & !IsPause&timeelapsed>=Number(ShowNextMinutes))
   if (timeelapsed>=Number(ShowNextMinutes) &&!HaveSeted && !IsPause)
   {
     var MinutesLength=Number(twoPlaces(Number(Duration.value)-Number(CurrentMinutes.value)));
     //为啥非得还剩10分钟才执行呢，应该是只有5秒钟就不跑了，留待后面的完成来跑
   //  if (Number(MinutesLength)>Number(ShowNextMinutes)) 
    if (Number(MinutesLength)>0.1) 
      {
       	 HaveSeted=true; //你丫的你要放前面，nnd
     	 ShowLearnMinutes(timeelapsed);
      //  settimeoutflag=setTimeout('ShowLearnMinutes('+ShowNextMinutes.toString()+')',ShowNextMinutes*60*1000);
       //  alert("尻，这个是后续的，有个冒用啊");
        
      }
   } 
 </s:if>
  setTimeout('GetInfo()', 1000)
}

var NewLearnMinutes=0;
function ShowLearnMinutes(Minutes)
{
   Pause();//暂停课程
   if (oPlay.fullScreen==true) 
   		oPlay.fullScreen=false;
   if (Minutes==null) 
     {
       alert("恭喜,您已完成了该课程的培训...")
       NewLearnMinutes=-1;
     }
      else 
       {
          alert("新获【"+Minutes+"】分钟的网上培训时间")
          NewLearnMinutes=Minutes;
        }
    GetLearnMinutes();
}

function GetLearnMinutes()
{
   var SetMode=(NewLearnMinutes==-1)?"All":"";   
   //新听课时间
   var SetMinutes=(NewLearnMinutes==-1)?Duration.value:NewLearnMinutes;
   //总计听课时间，总听课时间+新听课时间
   var LookedAllMinutes=(NewLearnMinutes==-1)?Duration.value:twoPlaces(Number(LearnMinutes.value)+Number(SetMinutes));
  //设置上去
   SetLearnMinutes (twoPlaces(Number(Duration.value)),twoPlaces(Number(LookedAllMinutes)));
   //	alert(setTimeSuccess);
   if(setTimeSuccess){
	  	LearnMinutes.value=(NewLearnMinutes==-1)?SetMinutes:twoPlaces(Number(LearnMinutes.value)+Number(SetMinutes));
   	    HaveSeted=false;
	    NewLearnMinutes=0; 
	//    alert(HaveSeted+",,,,"+NewLearnMinutes);
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
//var hascleartimeout=false;
function Pause()
{  
  IsPause=!IsPause;
 

  if (IsPause) 
    {
       oPlay.controls.pause();
       PauseBtn.value="继续";
     //  if(settimeoutflag&&settimeoutflag!=0){
      //  clearTimeout(settimeoutflag);//清除掉10分钟后的定时。这里要记忆住已经听了多少时间了
      //  hascleartimeout=true;
      //  }
    }
    else
    {
       oPlay.controls.play();
       PauseBtn.value="暂停";
       //多少时间后继续执行
      // if(hascleartimeout){
      //  	settimeoutflag=setTimeout('ShowLearnMinutes('+ShowNextMinutes.toString()+')',(ShowNextMinutes*60*1000-thetimeinterval*1000));
      // }
    }
}

function Stop()
{
  oPlay.controls.stop();
}

</script>

		<SCRIPT language="JScript" event="playStateChange(NewState)"
			for="Player">
			//alert(NewState);
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
//还剩多少时间。。。。即总时间和已记录的听课时间的差
     var LostLength=Number(twoPlaces(Number(Duration.value)-Number(LearnMinutes.value)));
        
    // if (LostLength>0 & LostLength<=Number(ShowNextMinutes) & !HaveSeted) 
   // if(LostLength>0 & LostLength<=Number(ShowNextMinutes)) //为啥非得这样才跑呢？？？
   	if(LostLength>0)//如果不加这个条件，一直都是完成播放的状态。
     	setTimeout('ShowLearnMinutes(null)',1000);
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
</body>
</html>
