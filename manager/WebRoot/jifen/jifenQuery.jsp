<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-积分查询</title>
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
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="javascript">

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：积分查询
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="jifenQuery" name="form1" method="post">
<s:hidden name="lawyerid"/>
	  <tr>
    <td valign="top">	
    	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24"  >
           	<div align="center"><b>${lawyers.lawyername} 律师的培训积分<b/></div>
           	<div align="left">
          积分年限:<s:select name="year" list="jifentime.years" onchange="document.form1.submit()"/>
           	(积分计算：从【${jifentime.startstr}】到【${jifentime.endstr}】。达标需满【${dabiaofen}】分)
           </div>
          </td>
        </tr>
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="right" background="../imagesa/login_bg1.gif" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
      <tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" width="35%">课程名称</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >培训方式</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">培训日期</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">要求时长（分）</TD>
     
        <TD align="center" background="../imagesa/top-bg1.gif">培训时长（分）</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">获得积分</TD>
      </tr>
      <s:set name="zongjifen" value="0"/>
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="left">${title}</TD>
        <TD class="tab_content" align="center">
        <s:property value="@com.changpeng.jifen.util.CommonDatas@LEARNMODE[learnmode]"/>
   
        </TD>
        <TD class="tab_content" align="center">${pxdate}</TD>
        <TD class="tab_content" align="center">${pxreqminutes} </TD>
        <TD class="tab_content" align="center">${pxminutes}</TD>
         <TD class="tab_content" align="center">${pxxf}</TD>
          <s:set name="zongjifen" value="#zongjifen+pxxf"/>
      </TR>
     </s:iterator> 
     
      
      <tr style='background-color="#F1F1ED"'>
        <td  colspan="7" align="right">&nbsp;
         	<font color="red">
    积分统计：
     现场培训:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(localecnt)"/>&nbsp;
      在线视频:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(video)"/>&nbsp;
       文本课件:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(wenbenkejian)"/>&nbsp;
       补登积分:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(budeng)"/>&nbsp;
  总积分:
  <s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(#zongjifen)"/></font>
   &nbsp;&nbsp; &nbsp; &nbsp;
        	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
       
          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>
</table>
</body>
</html>