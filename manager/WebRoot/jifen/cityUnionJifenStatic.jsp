<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-参数设置</title>
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
function getAdd(){
	window.location.href="sysParameterCreate!input.pl";
}
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

function queryit(str){
  document.form1.resultType.value="list";
  document.form1.submit();
}

function exportit(str){
  document.form1.resultType.value="excel";
  document.form1.submit();
}
</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置：律协积分统计-${group.groupname }
    	  <s:if test="group.groupid!=8078">
    	-从【${jifentime.startstr }】到【${jifentime.endstr }】达标条件,总积分>=${dabiaofen }<s:if test="localfen>0">，现场培训积分>=${localfen}</s:if>
    	</s:if>
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
				
<s:form action="cityUnionJifenStatic" name="form1" method="post">
<s:hidden name="selectcityid"/>
	  <tr>
    <td valign="top">	
    	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" >
          <s:hidden name="pageNo"/>
积分年限:<s:select name="year" list="jifentime.years"/> 
	分类:<s:select name="isdabiao" list="#{'0':'全部','1':'已达标','2':'未达标','3':'未培训'}"/>
	 	姓名:<s:textfield name="lawyername" size="10"/>
            	执业证号:<s:textfield name="lawyerno" size="15"/> 
            		
               <s:hidden name="resultType"/>
        	   <input type="button" name="query" value=" 查 询 " onclick="queryit()"/>
        	   <input type="button" name="export" value=" 导 出 " onclick="exportit()"/>
          </td>
        </tr>
        <tr>
          <td height="24" background="../imagesa/top-bg2.gif" align="center">
<b> 当前查询条件下：达标人数【${jifenstatics.dabiaoshu}】 未达标人数【${jifenstatics.weidabiao}】 未培训人数【${jifenstatics.weipeixun}】</b>
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
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif" >姓名</TD>
        <TD align="center" background="../imagesa/top-bg1.gif" >事务所</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">现场积分</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">网上积分</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">文本课件</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">补登积分</TD>
       <TD align="center" background="../imagesa/top-bg1.gif">未到扣分</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">总积分</TD>
         <TD align="center" background="../imagesa/top-bg1.gif">是否达标</TD>
            <TD align="center" background="../imagesa/top-bg1.gif">明细</TD>
      </tr>
      
<s:iterator value="page.items" status="stat">
      <TR>
        <TD class="tab_content" align="center"  title="查看律师信息"><a href="../lawyers/lawyerView.pl?userid=${lawyerid}">${name}</TD>
        <TD class="tab_content" align="left">&nbsp;&nbsp;
        <a href="officeJifenStatic.pl?selectoffice=${groupid }">${groupname}</a>
      
        </TD>
         <TD class="tab_content" align="center"> ${xianchang}</TD>
       	<TD class="tab_content" align="center">${video}</TD>
     	<TD class="tab_content" align="center">${doc}</TD>
        <TD class="tab_content" align="center">${budeng}</TD>
        <TD class="tab_content" align="center">${koufen}</TD>
        <TD class="tab_content" align="center">${zongjifen}</TD>
        <TD class="tab_content" align="center">${dabiaostr }</TD> 
         <TD class="tab_content" align="center">
         <a href="../jifen/jifenQuery.pl?fromwhere=fromwhere&lawyerid=${lawyerid}&year=${year}" title="查看积分明细">查看</a>
         </TD>
          </TR>
        </s:iterator> 
     
      
       <tr style='background-color="#F1F1ED"'>
        <td  colspan="7" align="center">&nbsp;	
       </td>
      </tr>
     
    </table>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="left" background="../imagesa/login_bg1.gif" >

          </td>
        </tr>
      </table>   
    </td>
  </tr>
    </s:form>
</table>
</body>
</html>