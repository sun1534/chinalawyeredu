<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<title></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script> 
<script language="javascript">
function _close(){
	parent.location.reload();
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
</head>
<body>
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >

  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor="#F9F9F7">
              <TABLE align="center" border=0 cellSpacing=1 cellPadding=2 width="50%">
                <TBODY>
                  <TR class="listheadline">
                    <TD  align="center">系统消息</TD>
                  </TR>
                  <TR>
                    <TD height="40" class="listline"><div align="center">${message}</div></TD>
                  </TR>
                  <TR bgcolor="#CCCCCC">
                    <TD  align="center">  <input type=button class="mask" onClick="_close()" value="返回">
                    </TR>
                </TBODY>
              </TABLE>
              </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
