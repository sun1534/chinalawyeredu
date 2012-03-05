<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>中国律师培训网-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>

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

function fanye(str){
  document.form2.pageNo.value=str;
  document.form2.submit();
}


</script>

<script language="javascript" id=clientEventHandlersJS>
<!--
var number=6;

function LMYC() {
var lbmc;
//var treePic;
    for (i=1;i<=number;i++) {
        lbmc = eval('LM' + i);
        //treePic = eval('treePic'+i);
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
}
 
function ShowFLT(i) {

    lbmc = eval('LM' + i);
    //treePic = eval('treePic' + i)
    if (lbmc.style.display == 'none') {
        LMYC();
        //treePic.src = 'images/nofile.gif';
        lbmc.style.display = '';
    }
    else {
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
}

function LoadshowFLT(){
	var aa=document.getElementById("lessontype").value;

	if(aa==1 || aa==101 || aa==102 || aa==103 || aa==104 || aa==105 || aa==106 || aa==107 || aa==108 || aa==109 || aa==110 || aa==111 || aa==112 || aa==113 ){
		lbmc = eval('LM' + 1);		
	}else if(aa==2 || aa==201 || aa==202){
		lbmc = eval('LM' + 2);
	}else if(aa==3 || aa==301 || aa==302){
		lbmc = eval('LM' + 3);
	}else if(aa==5){
		lbmc = eval('LM' + 4);
	}else if(aa==6 || aa==7){
		lbmc = eval('LM' + 5);
	}else if(aa==4 || aa==8){
		lbmc = eval('LM' + 6);
	}
	
	
	if (lbmc.style.display == 'none') {
        LMYC();
        //treePic.src = 'images/nofile.gif';
        lbmc.style.display = '';
    }
    else {
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
	
}
//-->
      </script>
</head>

<body onload="LoadshowFLT();">
<div class="header">
  <form name="form1" method="post" action="../common/logout.pl">	
  <div class="logo left"><a href="index.html"><img src="${resourcePath}${topbarpic}" width="234" height="51" /></a></div>
  <div class="denglu right">
  	${lawyer.lawyername}律师 您好，欢迎您登录培训平台！&nbsp;&nbsp; 
  	<a href="../index/index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="../common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">退出</a>
  	
  </div>
  </form>
</div>
<div class="blank15px"></div>
<div class="nav2"><ul>
  <li><a href="../index/index.pl"  >首页</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1" class="current">选课中心</a></li>
     <!-- <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li> -->
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1"  >培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl" >我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl" >转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>选课中心</strong></div>
<div class="con">
  <div class="con_left2 left">
	<div class="con_left2_title">选课中心</div>        
	<table cellSpacing=0 cellPadding=0 width="88%" border=0>
	<tbody>
		<!-- 全部 -->
		<tr>
			<td style="PADDING-LEFT: 20px"  height=23>
				<img src="../images/jiantou.gif" width="4" height="10" /> 
				<a onclick=javascript:ShowFLT(-1)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1&viewtype=${viewtype}">全部</a> 
			</td>
		</tr>
		
		<!-- 民商事法律业务 -->
		<tr>
            <td style="PaDDING-LEFT: 20px"  height=23>
            	<img src="../images/jiantou.gif" width="4" height="10" /> 
            	<a onclick=javascript:ShowFLT(1) href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=1&viewtype=${viewtype}">民商事法律业务</a> 
            </td>
		</tr>		
		<tr id=LM1 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=101&viewtype=${viewtype}" >民事诉讼</a> 
                        </td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=102&viewtype=${viewtype}">合同担保法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=103&viewtype=${viewtype}">侵权法</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=104&viewtype=${viewtype}">物权法</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=105&viewtype=${viewtype}">公司证券法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" />
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=106&viewtype=${viewtype}">知识产权法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=107&viewtype=${viewtype}">建筑房地产法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=108&viewtype=${viewtype}">破产法律业务</a> 
                    	</td>
                  	</tr>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=109&viewtype=${viewtype}">国有资产法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=110&viewtype=${viewtype}">金融保险法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=111&viewtype=${viewtype}">涉外法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=113&viewtype=${viewtype}">劳动关系法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=112&viewtype=${viewtype}">其他民商法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
				</tbody>
			</table></td>
		</tr>
		
		
		<!-- 刑事法律业务 -->
		<tr>
			<td style="PaDDING-LEFT: 20px"  height=23>
				<img src="../images/jiantou.gif" width="4" height="10" /> 
				<a onclick=javascript:ShowFLT(2) href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=2&viewtype=${viewtype}">刑事法律业务</a> 
			</td>
		</tr>
		<tr id=LM2 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
                  <tr>
                    <td style="PaDDING-LEFT: 40px" height=23>
                    	<img src="../images/dian_.gif" width="3" height="3" /> 
                    	<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=201&viewtype=${viewtype}">刑法</a> 
                    </td>
                  </tr>
                  <tr>
                    <td  height=3></td>
                  </tr>
                  <tr>
                    <td style="PaDDING-LEFT: 40px" height=23>
                    	<img src="../images/dian_.gif" width="3" height="3" /> 
                    	<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=202&viewtype=${viewtype}">刑事诉讼</a> 
                    </td>
                  </tr>
                  <tr>
                    <td  height=3></td>
                  </tr>
                </tbody>
            	</table>
            </td>
		</tr>
		
		<!-- 行政法律业务 -->
		<tr>
			<td style="PaDDING-LEFT: 20px"  height=23>
            	<img src="../images/jiantou.gif" width="4" height="10" /> 
            	<a onclick=javascript:ShowFLT(3) href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=3&viewtype=${viewtype}">行政法律业务</a> 
            </td>
		</tr>
		<tr id=LM3 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=301&viewtype=${viewtype}">行政法</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=302&viewtype=${viewtype}">行政诉讼</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
				</tbody>
				</table>
			</td>
		</tr>
		
		<!--执行法律业务 -->
		<tr>
			<td style="PaDDING-LEFT: 20px"  height=23>
				<img src="../images/jiantou.gif" width="4" height="10" /> 
				<a onclick=javascript:ShowFLT(4)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=5&viewtype=${viewtype}">执行法律业务</a> 
			</td>
		</tr>
		<tr id=LM4 style="DISPLaY: none"></tr>
		
		
		
		<!--律师事务所管理 -->
		<tr>
			<td style="PaDDING-LEFT: 20px"  height=23>
				<img src="../images/jiantou.gif" width="4" height="10" /> 
				<a onclick=javascript:ShowFLT(5)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=6&viewtype=${viewtype}">律师事务所管理</a> 
			</td>
		</tr>
		<tr id=LM5 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=7&viewtype=${viewtype}">商务礼仪及业务拓展</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>                  	
				</tbody>
            	</table>
            </td>
		</tr>
		
		<!--其他 -->
		<tr>
			<td style="PaDDING-LEFT: 20px"  height=23>
				<img src="../images/jiantou.gif" width="4" height="10" /> 
				<a onclick=javascript:ShowFLT(6)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=8&viewtype=${viewtype}">其他</a> 
			</td>
		</tr>
		<tr id=LM6 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=4&viewtype=${viewtype}">思想政治教育</a> 
                    	</td>
					</tr>
					<tr>
                    	<td  height=3></td>
                  	</tr>
				</tbody>
            	</table>
            </td>
		</tr>
	</tbody>
    </table>
  </div>
  <div class="con_right2 left">
    <s:form name="form2" action="lessonsList" method="post">
    <div class="con_right2_title"><h2>分类：<s:if test="lessontype==-1">全部</s:if>
    	 <s:else><s:property value="@com.changpeng.lessons.util.CommonDatas@LessonType[lessontype]"/></s:else>
    	 
    </h2>
    	<span>
    	<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=${lessontype }&viewtype=1">详细显示</a>&nbsp;&nbsp;
    	<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=${lessontype }&viewtype=2">列表显示</a>
    	</span>
    </div>
    <div class="star">
	    <div class="startitle">
			<!-- 显示：
			<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=${lessontype }&viewtype=1&viewstyle=0">全部</a>&nbsp;
			<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=${lessontype }&viewtype=1&viewstyle=1">免费</a>&nbsp; 
			<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=${lessontype }&viewtype=1&viewstyle=2">收费</a> -->
		</div>
		<s:iterator value="lessonlist" status="stat">
		<div class="t_n">			
				<s:if test="pic!=null && !pic.equals(\"\")">
					<a title="${title }"  href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","")>
					<img src="http://localhost:8080/saaspxxt/lessonphoto/${pic }" width="150" height="135" /></a>	
				</s:if>
				<s:else>
					<a title="${title }" href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","")>
					<img src="../imagesa/none.jpg" width="150" height="135" /></a>
				</s:else>			
 			<div class="list3_ul_ul">
 	       		<ul>
           			<h3><a title="${title }" href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","") ><strong>${titleTrim1}</strong></a></h3>           			
					<li>主讲人：<span>
							<s:if test="teacherid!=0">
							<!--  <a href="teacherView.pl?userId=${teacherid }">${teachers}</a>-->${teachers}
							</s:if>
							<s:else>
							${teachers}
							</s:else></span></li>
                	
                	<li>提供者：<span><s:property value="@com.changpeng.common.CommonDatas@groups[groupid]"/></span></li>
                	<li>时间：<span><s:date name="lessondate" format="yyyy"/>年</span></li>	
                	<li>类型：<span><s:property value="@com.changpeng.lessons.util.CommonDatas@LessonType[lessontype]"/></span></li>		
                	<li><a href="../lessons/lessonsView.pl?lessonid=${lessonid }" target="_blank">课评</a>&nbsp;<a href="../shopping/shopfavoritesCreate.pl?lessonid=${lessonid }">收藏</a>&nbsp;
                		<s:if test="price!=0">
                			<a href="../shopping/shopcartCreate.pl?lessonid=${lessonid }">选购（￥${price}）</a>
                		</s:if>      
                		      		
                	</li>
                	<li>&nbsp;</li>						
		  		</ul>
			</div>        	
    	</div>    	
    	</s:iterator>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="page-table">
				<tr>
					<td align="right">
						${page.pageView}
					</td>
				</tr>
			</table>
    	<s:hidden name="lessontype"/>
		<s:hidden name="lessonstyle"/>
		<s:hidden name="viewtype"/>
		<s:hidden name="viewstyle"/>
		<s:hidden name="pageNo" value="1"/>
		
    </s:form>	  
  	</div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>
</body>
</html>