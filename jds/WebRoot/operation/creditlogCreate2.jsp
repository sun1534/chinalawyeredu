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

                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="creditlogCreate2" validate="true" method="post">
       				
					<s:hidden name="creditlog.toprCredittask.credittaskid" value="${credittaskid}"/>
	 			 	
	 			 	<s:if test="bankid==1">
	 			 	<TR>
						<TD width="15%" class="listheadline">所在银行分行号:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditlog.bankno" /></TD>
				
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联络类型:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditlog.contacttype" /></TD>
					<TD width="15%" class="listheadline">联络对象:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditlog.contactobj" /></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">联络方式:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditlog.contactmanner" /></TD>
					<TD width="15%" class="listheadline">联络结果:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditlog.contactresult"/></TD>
					</TR>
	 			 
					<TR>
						<TD width="15%" class="listheadline">催收日期:</TD>
					<TD width="35%" class="listline">
					<jscalendar:jscalendar	name="creditlog.logtime" format="%Y-%m-%d %H:%M" showstime="true"/>
					<TD width="15%" class="listheadline">催收措施:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditlog.cuishoucuoshi"/></TD>
					</TR>
						<TR>
						<TD width="15%" class="listheadline">催收情况:</TD>
					<TD width="35%" class="listline" colspan="3"><s:textarea name="creditlog.comments" cols="40" rows="3"/></TD>
					</TR>
					</s:if>
					<s:else>
						<TR>
						<TD width="15%" class="listheadline">催收日期:</TD>
					<TD width="35%" class="listline">
					<jscalendar:jscalendar	name="creditlog.logtime" format="%Y-%m-%d %H:%M" showstime="true"/>
					
					</TR>
						<TR>
						<TD width="15%" class="listheadline">催收情况:</TD>
					<TD width="35%" class="listline" colspan="3"><s:textarea name="creditlog.comments" cols="40" rows="3"/></TD>
					</TR>
					</s:else>
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
