<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%
/**
 * <p>功能： 设置约定还款周期</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-09-02</p>
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
//重新分配
function reassign(){
	if(confirm("您确定要重新分配该任务吗？")){
		document.form1.submit();
	}
}
//撤销分配
function unassignTask(){
	if(confirm("您确定要撤销该任务吗？")){
		document.form1.unassign.value=1;
		document.form1.submit();
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
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >

  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
     <br>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="400"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="setPaydate" validate="true" method="post">
       				<s:hidden name="nonlawtaskid"/>
	 			 	<TR>
						<TD width="15%" class="listheadline">约定还款日期:</TD>
						<TD width="35%" class="listline">
						
						<jscalendar:jscalendar	name="paydate" format="%Y-%m-%d %H:%M" showstime="true"/>
					<!--  
						<s:select name="paydate" list="#{'7':'7天','15':'15天','30':'30天'}"/>
					-->	
					</TD>
					</TR>
				
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT  type="submit" class="botton" value="确定">&nbsp;
			            <INPUT  type=button class="botton" onclick="_close()" value="返回">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
               <br>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
