<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${webpara.sysname}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript" type="text/javascript">
 
var issubmit=false;
function submitform (){	
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
  if(!issubmit){//如果没有提交,直接关闭窗口的话
  
    $.getJSON("../commonajax/ajaxlogout.pl", {"now":new Date().getTime()}, function(resp){
      }
    ); 
  }
}

function showpic(picnum){
	var picname=document.getElementById("pic"+picnum).value;	
	if(picname==null || picname==""){
		document.getElementById("bigpic").src="http://localhost:8080/saaspxxt/lessonphoto/dftpic.gif";
	}else{
		document.getElementById("bigpic").src="http://localhost:8080/saaspxxt/lessonphoto/"+picname;
	}
}
</script>

<div id="left_ccl" style="position:absolute"> 
  <a href="../articles/articlesView.pl?type=1&articleid=54"  target="_blank">
  <img src="../images/培训系统升级通知漂浮蓝.jpg"  border="0"></a>	
</div>
<script> 
var lx = 50,ly = 60 
var lxin = true, lyin = true 
var lstep = 1 
var ldelay = 30 
var lobj=document.getElementById("left_ccl") 
function floatleftccl() { 
var lL=lT=0
var lR= document.body.clientWidth-lobj.offsetWidth-1000 
var lB = document.body.clientHeight-lobj.offsetHeight 
lobj.style.left = lx + document.body.scrollLeft 
lobj.style.top = ly + document.body.scrollTop 
lx = lx + lstep*(lxin?1:-1) 
if (lx < lL) { lxin = true;lx = lL} 
if (lx > lR){ lxin = false; lx = lR} 
ly = ly + lstep*(lyin?1:-1) 
if (ly < lT) { lyin = true; ly = lT } 
if (ly > lB) { lyin = false; ly = lB } 
} 
var litl= setInterval("floatleftccl()", ldelay) 
lobj.onmouseover=function(){clearInterval(litl)} 
lobj.onmouseout=function(){litl=setInterval("floatleftccl()", ldelay)} 
</script>



<div id="right_ccl" style="position:absolute"> 
  <a href="http://http://diaocha.lawyeredu.com/diaocha/diaochaView.pl?diaochaid=12&lawyernum=${lawyer.lawyerno }"  target="_blank">
  <img src="../images/培训系统调查问卷漂浮浅灰.jpg"  border="0"></a>	
</div>
<script> 
var rx = 50,ry = 60 
var rxin = true, ryin = true 
var rstep = 1 
var rdelay = 30 
var robj=document.getElementById("right_ccl") 
function floatrightccl() { 
var rL=rT=0
var rR= document.body.clientWidth-robj.offsetWidth-1000  
var rB = document.body.clientHeight-robj.offsetHeight 
robj.style.right = rx + document.body.scrollLeft 
robj.style.top = ry + document.body.scrollTop 
rx = rx + rstep*(rxin?1:-1) 
if (rx < rL) { rxin = true;rx = rL} 
if (rx > rR){ rxin = false; rx = rR} 
ry = ry + rstep*(ryin?1:-1) 
if (ry < rT) { ryin = true; ry = rT } 
if (ry > rB) { ryin = false; ry = rB } 
} 
var ritl= setInterval("floatrightccl()", rdelay) 
robj.onmouseover=function(){clearInterval(ritl)} 
robj.onmouseout=function(){ritl=setInterval("floatrightccl()", rdelay)} 
</script>

</head>
<body onload="showpic(1);">
<form name="form1" method="post" action="../common/logout.pl">
<div class="header">

  <div class="logo left"><img src="${resourcePath}${webpara.topbarpic}" width="234" height="51" /></div>
  <div class="denglu right">${lawyer.lawyername}律师 您好，欢迎您登录培训平台！&nbsp;&nbsp;  	
  	<a href="index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="../common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">退出</a></div>
</div>
<div class="blank15px"></div>
<div class="nav2"><ul>
  <li><a href="../index/index.pl"  class="current">首页</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1">选课中心</a></li>
  <!-- <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li> -->
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1">培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl">我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl">个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1">系统消息</a></li>
</ul></div>
<div class="blank15px"></div>
<div class="con">
  <div class="con_user_left left">
    <div class="con_user">
      <div class="con_user_title"></div>
      <div class="">
        <table width="210px" border="0" cellpadding="0" cellspacing="0" >
			<tr align="center">
			  <td height="150px" colspan="2" >
			  <s:if test="lawyer.photo==null||lawyer.photo.equals(\"\")"> 
      		 			<img src="../imagesa/none.jpg" width="110" height="135"/>       
      				</s:if>
      				<s:else>
      					<img src="${logopath}/${lawyer.photo}" width="110" height="135"/>    
      				</s:else> 
			  </td>
		  </tr>
			<tr>
				<td height="23" colspan="2"  align="center" >				
				<b>年度：<s:if test="lawyer.directunion!=37777">${jifentime.startstr}至${jifentime.endstr}</s:if><s:else>2010-06-20至2011-04-20</s:else></b>				                </td>
			</tr>
			<tr>
				<td align="right"  width="72" height="23"  ><b>姓名：</b></td>
				<td align="left" > ${lawyer.lawyername} </td>      
			</tr>
			<tr>
				<td align="right" height="23"><b>执业证： </b></td>                   
				<td align="left" >${lawyer.lawyerno}</td>  
			</tr>
			<tr>
				<td align="right" height="23"><b>执业所：</b> </td>                   
				<td align="left" >
					  <s:property value="@com.changpeng.common.CommonDatas@groups[lawyer.theoffice]"/>
				</td>
			</tr>
			<tr>
				<td align="right" height="23"><b>积分： </b> </td>                  
				<td align="left" >${nowxuefen}</td>
			</tr>
			<tr>
				<td align="right" height="23"><b>达标分：</b> </td>                   
				<td align="left" >总学分:${needfen}&nbsp;现场分:${needxcfen}</td>
			</tr>
			<tr>
				<td align="right" height="23"><b>执业时间： </b>   </td>                
				<td align="left" >${lawyer.zhiyedate}</td>
			</tr>
			<s:if test="lawyer.lawyertype>=0">
			<tr>
				<td align="right" height="23"><b>是否达标：</b> </td>                   
				<td align="left" >
					<font color="red">
           			<s:if test="nowxuefen>=needfen">
           				<s:if test="nowxcxuefen>=needxcfen">
           					已达标
           				</s:if>
           				<s:else>未达标
            			</s:else>            	 			
            		</s:if>
             		<s:else>未达标
            		</s:else> 
            		</font>
				</td>
			</tr>
			</s:if>
			<tr>
				<td align="center"  height="10"> </td>                   
				<td align="left" ></td>
			</tr>     
		</table>
      </div>
    </div>
  <!--   <div class="con_ke">
      <div class="con_ke_title">
        <h2>重要通知</h2>
      </div>
      <div class="line1"></div>
      <p class="baseFontBold">培训系统在线课程重要通知【广西自治区律协】<span class="tab_content">2010年度的培训工作已经结束，补听时间于2011年6月16日截止，未获得足够学分的律师请和省律协王晓辉联系。联系电话：0371-65900674。</span><span class="tab_content">南省律师协会</span></p>
      <p class="baseFontBold"><span class="tab_content">2011年6月16日 </span></p>
    </div> -->
    <div class="con_kej">
      <div class="con_kej_title">
        <h2>课件类型及数量</h2>
      </div>
      <div class="line1"></div>
      <ul class="con_right_bottom_con">
        <li><span>${lessonType1}个</span><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=1">民商事法律业务</a> </li>
        <li><span>${lessonType2}个</span><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=2">刑事法律业务</a> </li>
        <li><span>${lessonType3}个</span><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=3">行政法律业务</a></li>
        <li><span>${lessonType5}个</span><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=5">执行法律业务</a> </li>
        <li><span>${lessonType6}个</span><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=6">律师事务所管理</a> </li>
        <li><span>${lessonType8}个</span><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=8">其他</a> </li>
      </ul>
    </div>
  </div>
  <!--  通知   
  <div class="tz">重要通知：培训系统在线课程重要通知【广西自治区律协】<span class="tab_content">2010年度的培训工作已经结束，补听时间于2011年6月16日截止，未获得足够学分的律师请和省律协王晓辉联系。联系电话：0371-65900674。</span><span class="tab_content">南省律师协会</span></p>
</div>   -->
  
  <div class="con_tu_right left">
    <div class="con_t_w">
      <div class="con_t_title">
        <h2>本周推荐</h2>
      </div>
      <div class="con_zhengwen">
     
        <div class="con_l left"><img src="${logopath}/lesson/dftpic.gif" width="207" height="162" id="bigpic"/></div>
        
        <s:iterator value="lessonTuijianList"  status="index" id="stat">  

        <div class="con3_left_center_list left">
          <h3><a href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank" onmouseover="showpic(${index.index+1});">
          
          	<s:if test="pic==null||pic.equals(\"\")"> 
          		<img src="${logopath}/lesson/dftpic.gif"  id="img${index.index+1}" width="52" height="33" />
          		
          	</s:if>
             <s:else>
             	<img src="http://localhost:8080/saaspxxt/lessonphoto/${pic}" id="img${index.index+1}" width="52" height="33" />
             </s:else>
             <input id="pic${index.index+1}" value="${pic}" type="hidden"/>
             </a>
          </h3>
          <ul class="con_ww">
            <li><a href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank" title="${title }"><strong>${titleTrim1}</strong></a></li>
            <li><a href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank" title="${tuijianContent }">${tuijianContentTrim}</a></li>
            </ul>
        </div>
        </s:iterator>     
      </div>
    </div>
	
    <div class="con_text_t">
      <div class="con_text_title">
        <h2>上升最快课程</h2>
      </div>
      <div class="line1"></div>
      <div class="con_wenzi">
        <ul>
        	 <s:iterator value="lessonWeekPlayNum" status="index"  >        	    
        	       <li><span>${count}</span> <a href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank"  title="${title }">${titleTrim}</a></li>
        	 </s:iterator>          
        </ul>
      </div>
    </div>
    <div class="con_bz">
      <div class="con_bz_title">
        <h2>最受欢迎课程</h2>
      </div>
      <div class="con_renwu">
      	<!--  
        <div class="con_sz">
         <ul>
            <li id="two1" onmouseover="setTab('two',1,7)" class="hover">民商事&nbsp;&nbsp;|</li>
            <li id="two2" onmouseover="setTab('two',2,7)">刑事&nbsp; |</li>
            <li id="two3" onmouseover="setTab('two',3,7)">行政&nbsp;&nbsp;|</li>
            <li id="two4" onmouseover="setTab ('two',4,7)">执行&nbsp;&nbsp;|</li>
            <li id="two5" onmouseover="setTab ('two',5,7)">事务所管理</li>
            <li id="two6" onmouseover="setTab ('two',6,7)">|&nbsp;&nbsp;宣传&nbsp;|</li>
            <li id="two7"onmouseover="setTab ('two',7,7)">其他&nbsp;&nbsp;|</li>
          </ul>
        </div>
        -->
        <div class="Contentbox2">
        <div id="con_two_1">
          <ul class="con22_pic">
          <s:iterator value="lessonPlayNum">          
            <li><a title="${title }" href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank">
             <s:if test="pic==null||pic.equals(\"\")">             	
            		<img src="${logopath}/lesson/dftpic.gif" width="127" height="99" />            	
             </s:if>
             <s:else>            
             	<img src="http://localhost:8080/saaspxxt/lessonphoto/${pic}" width="127" height="99" />               
             </s:else>
            </a>
            <span><a href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank">${titleTrim} </a>
            <br/>观看量：${count}</span>
            </li>
            </s:iterator>
          </ul>
        </div>
        
		<div id="con_two_2" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
		<div id="con_two_3" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">刑事辩护散谈</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">刑事辩护散谈</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
          </ul>
        </div>
		<div id="con_two_4" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
          </ul>
        </div>
		<div id="con_two_5" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
		<div id="con_two_6" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">不可错过</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
		<div id="con_two_7" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
		</div>
        
        <div id="con_two_2" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
        <div id="con_two_3" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
        <div id="con_two_4" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
        <div id="con_two_5" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
        <div id="con_two_6" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
        <div id="con_two_7" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
        <div id="con_two_8" style="display:none">
          <ul class="con22_pic">
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
            <li><img src="images/tu_ren.gif" width="127" height="99" /><span><a href="#">合同效力的认定</a></span></li>
          </ul>
        </div>
      
      
      </div>
    </div>
    <div class="con_zui">
      <div class="con_zui_title">
        <h2>最新课程</h2>
      </div>
      <div class="line1"></div>
      <s:iterator value="lessonList" status="statLesson">
            <div class="wzi">
        <h4><a href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank">${titleTrim2}</a></h4>
        <p>${lessoncontentTrim}</p>
      </div>
      </s:iterator>


    </div>
</div>

<div class="blank10px"></div>
 <div class="friends_links">
    <ul>
      <span>合作单位：</span>
      <li><a href="http://www.hnlawyer.org/" target="_blank">河南省律师协会</a></li>
      <li><a href="http://www.ctlawyer.cn/" target="_blank">湖北省律师协会</a></li>
      <li><a href="http://www.gxlawyer.org.cn/" target="_blank">广西律师协会</a></li>
      <li><a href="http://www.hainanlawyer.org/" target="_blank">海南省律师协会</a></li>
      <li><a href="http://www.szlawyers.com/" target="_blank">深圳市律师协会</a></li>
      <li><a href="http://www.njlawyers.org.cn/" target="_blank">南京市律师协会</a></li>
      <li><a href="http://www.hzlawyer.net/" target="_blank">杭州市律师协会</a></li>
      <li><a href="http://www.wzlawyers.cn/" target="_blank">温州市律师协会</a></li>
      <li><a href="http://www.cclawyer.com.cn" target="_blank">长春市律师协会</a></li>
      <li><a href="http://www.xblaw.com/" target="_blank">重庆市律师协会</a></li>
      <li><a href="http://www.dglawyer.cn" target="_blank">东莞市律师协会</a></li>
      <li><a href="http://www.law.pku.edu.cn/" target="_blank">北京大学法学院</a></li>
      <li><a href="http://www.law.ruc.edu.cn/" target="_blank">人民大学法学院</a></li>
      <li><a href="http://fxy.whu.edu.cn/" target="_blank">武汉大学法学院</a></li>
    </ul>
  </div>
  
  <div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>

</form>
</body>
</html>