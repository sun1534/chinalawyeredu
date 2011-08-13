<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script> 

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
               <s:form name="form1" action="addressCreate" validate="true" method="post">
       				<s:hidden name="oprid"/>
					<s:hidden name="oprflag"/>
	 			 	<TR>
						<TD width="15%" class="listheadline">用户姓名:</TD>
						<TD width="35%" class="listline"><s:textfield name="address.username"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">电话号码:</TD>
						<TD width="35%" class="listline"><s:textfield name="address.phone"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">家庭住址:</TD>
						<TD width="35%" class="listline"><s:textfield name="address.homeaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司名称:</TD>
						<TD width="35%" class="listline"><s:textfield name="address.company"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">电话描述:</TD>
						<TD width="35%" class="listline"><s:textfield name="address.comments"/></TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT  type="submit" class="botton" value="保存">&nbsp;
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
