<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>%=com.changpeng.common.Constants.SYS_NAME%>-参数设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>

</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：积分统计
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="jifenStaticsQuery" name="form1" method="post">
	  <tr>
    <td valign="top">	
    	
    	<!-- <table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
&nbsp;
          </td>
        </tr>
      
      </table> 
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
        &nbsp;
          </td>
        </tr>
      </table>-->
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >律协名称</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >律师数</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">年审时间</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">达标分</TD>

            <TD align="center" background="../imagesa/top-bg1.gif">明细</TD>
      </tr>
      
<s:iterator value="cityunions" status="stat">
      <TR>
        <TD class="tab_content" align="center">${groupname}</TD>
        <TD class="tab_content" align="left">${usercnts}</TD>
        <TD class="tab_content" align="center"> ${sysUnionparams.nianshen}</TD>
       <TD class="tab_content" align="center">	${sysUnionparams.dabiaofen}</TD>
        <TD class="tab_content" align="center" title="点击查看查看统计明细">
        <a href="cityUnionJifenStatic.pl?selectcityid=${groupid}">查看
        </TD>
      </TR>
        </s:iterator> 
     
      
      <tr style="background-color='#F1F1ED'">
        <td  colspan="7" align="center">&nbsp;
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="left" background="../imagesa/login_bg1.gif" >
    <!-- <ul>
<li>总计培训课程【${lessoncnt}】，其中有【${onlinelessoncnt}】门课程提供了视频等影像资料。</li>
   <li>
       有【${localecnt}】位律师参加了现场培训，
       有【${video}】位律师观看了在线视频，
       有【${wenbenkejian}】位律师观看了文本课件
 </li>
<li>有【0】位律师使用了网上报名，其中有【0】位因未到被扣分；有【3】位律师参与了课程评价。</li>

<li>
       有【${budeng}】提供了外部培训记录，并获得学分
</li>
</ul>-->
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>




</table>
</body>
</html>