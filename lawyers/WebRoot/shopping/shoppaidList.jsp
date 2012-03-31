<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${webpara.sysname}</title>
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
//-->
      </script>
</head>

<body >
<div class="header">
  <form name="form1" method="post" action="../common/logout.pl">	
  <div class="logo left"><img src="${resourcePath}${webpara.topbarpic}" width="234" height="51" /></div>
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
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1" >选课中心</a></li>
  <li><a href="../shopping/shoppaidList.pl" class="current">我选购的课程</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1" >培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl">我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl"  >收藏夹</a></li>
  <li><a href="../shopping/shopcartList.pl" >购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>
  <li><a href="../lawyers/officeChangeApplyList.pl" >转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>我选购的课程</strong></div>
<div class="con">
  <div class="con_left2 left">
	<div class="con_left2_title">选课中心</div>        
<table cellSpacing=0 cellPadding=0 width="88%" border=0>
	<tbody>
		<!-- 全部 -->
		<tr>
			<td style="PADDING-LEFT: 20px"  height=23>
				<img src="../images/jiantou.gif" width="4" height="10" /> 
				<a onclick=javascript:ShowFLT(-1)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1">全部</a> 
			</td>
		</tr>
		
		<!-- 民商事法律业务 -->
		<tr>
            <td style="PaDDING-LEFT: 20px"  height=23>
            	<img src="../images/jiantou.gif" width="4" height="10" /> 
            	<a onclick=javascript:ShowFLT(1) href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=1">民商事法律业务</a> 
            </td>
		</tr>		
		<tr id=LM1 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=101" >民事诉讼</a> 
                        </td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=102">合同担保法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=103">侵权法</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=104">物权法</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=105">公司证券法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" />
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=106">知识产权法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=107">建筑房地产法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=108">破产法律业务</a> 
                    	</td>
                  	</tr>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=109">国有资产法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=110">金融保险法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=111">涉外法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=113">劳动关系法律业务</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=112">其他民商法律业务</a> 
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
				<a onclick=javascript:ShowFLT(2) href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=2">刑事法律业务</a> 
			</td>
		</tr>
		<tr id=LM2 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
                  <tr>
                    <td style="PaDDING-LEFT: 40px" height=23>
                    	<img src="../images/dian_.gif" width="3" height="3" /> 
                    	<a  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=201">刑法</a> 
                    </td>
                  </tr>
                  <tr>
                    <td  height=3></td>
                  </tr>
                  <tr>
                    <td style="PaDDING-LEFT: 40px" height=23>
                    	<img src="../images/dian_.gif" width="3" height="3" /> 
                    	<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=202">刑事诉讼</a> 
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
            	<a onclick=javascript:ShowFLT(3) href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=3">行政法律业务</a> 
            </td>
		</tr>
		<tr id=LM3 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=301">行政法</a> 
                    	</td>
                  	</tr>
                  	<tr>
                    	<td  height=3></td>
                  	</tr>
                  	<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=302">行政诉讼</a> 
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
				<a onclick=javascript:ShowFLT(4)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=5">执行法律业务</a> 
			</td>
		</tr>
		<tr id=LM4 style="DISPLaY: none"></tr>
		
		
		
		<!--律师事务所管理 -->
		<tr>
			<td style="PaDDING-LEFT: 20px"  height=23>
				<img src="../images/jiantou.gif" width="4" height="10" /> 
				<a onclick=javascript:ShowFLT(5)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=6">律师事务所管理</a> 
			</td>
		</tr>
		<tr id=LM5 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=7">商务礼仪及业务拓展</a> 
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
				<a onclick=javascript:ShowFLT(6)  href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=8">其他</a> 
			</td>
		</tr>
		<tr id=LM6 style="DISPLaY: none">
			<td>
				<table cellSpacing=0 cellPadding=0 width="100%" border=0>
                <tbody>
					<tr>
                    	<td style="PaDDING-LEFT: 40px" height=23>
                    		<img src="../images/dian_.gif" width="3" height="3" /> 
                    		<a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=4">思想政治教育</a> 
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
  <s:form action="shoporderCreate" name="form2" method="post">
    <div class="con_right2_title"><h2>我购买的课程</h2></div>
    <div class="star">
	   
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
      <td>      
        <table class='tab_list' width=100% cellspacing=1 cellpadding=1 align=center bgcolor=#b5b5b5 border=0>
          <tr bgcolor=#f3f3f3>
            <td align=center height=30 nowrap="nowrap">课程名称</td>
            <td align=center>主讲人</td>
            <td align=center>类别</td>
            <td align=center >学分</td>
            <td align=center>来源</td>
            <td align=center>购买时间</td>
            <td align=center>听课</td>
          </tr>
          <s:iterator value="page.items" status="stat">
          <tr bgcolor=#f3f3f3>            
            <td align=center height=30 nowrap="nowrap">&nbsp;&nbsp;${title}</td>
            <td align=center><span class="tab_content">${teachers}</span></td>
            <td align=center><span class="tab_content"><s:property value="@com.changpeng.lessons.util.CommonDatas@LessonType[lessontype]"/></span></td>
            <td align=center title="广东君言律师事务所">${xuefen}</td>
            <td align=center><span class="tab_content"><s:property value="@com.changpeng.common.CommonDatas@groups[groupid]"/></span></td>
            <td align=center><span class="tab_content"><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></span></td>
            <td align=center><a onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","toolbar=no,location=no,width=600,height=550,menubar=no,scrollbars=no,resizable=no,status=no")><span class="tab_content"><img src="../images/tu_shi.gif" width="16" height="16" /></span></a></td>
          </tr>
          </s:iterator>
		</table>         
		</td> 
  	</tr>
</table>

    </div>
    </s:form>
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>
</body>
</html>