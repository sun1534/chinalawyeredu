<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 创建creditlog</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script> 
<jscalendar:head/>
<script language="javascript">
function _close(){

	//触发窗口关闭事件
	var comment = parent.document.getElementById("TB_closeWindowButton");
	

	if (document.all) {
		 // For IE 	 
		comment.click();
	} else if (document.createEvent) {
	   //FOR DOM2
		 var ev = document.createEvent('HTMLEvents');
		 ev.initEvent('click', false, true);
		 comment.dispatchEvent(ev);
	}	
} 
</script>
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
<BODY >
<br>

                <TABLE width="350"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="creditlogCreate2" validate="true" method="post">
       				<!--  
					<TR>
						<TD width="15%" class="listheadline">客户姓名:</TD>
					<TD width="35%" class="listline"><s:select name="creditlog.toprCredittask.credittaskid" list="tasklist" listKey="credittaskid" listValue="toprCreditcard.username" value="${credittaskid}"/></TD>
					</TR>
					-->
					<s:hidden name="creditlog.toprCredittask.credittaskid" value="${credittaskid}"/>
	 			 	
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">催收情况:</TD>
					<TD width="35%" class="listline"><s:textarea name="creditlog.comments" cols="20" rows="4"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">催收日期:</TD>
					<TD width="35%" class="listline">
					<jscalendar:jscalendar	name="creditlog.logtime" format="%Y-%m-%d %H:%M" showstime="true"/>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT  type="submit" class="botton" value="保存">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
          
</BODY>
</HTML>
