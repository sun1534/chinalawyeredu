<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<BODY id=bodycontents text=#000000 bottomMargin=0 bgColor=#e6f7c1 leftMargin=0 
topMargin=0 scroll=no>
<FORM action=""></FORM>
<STYLE>TABLE {
	FONT-SIZE: 12px; COLOR: black
}
TD {
	TEXT-ALIGN: center
}
.button {
	WIDTH: 16px; POSITION: absolute; HEIGHT: 16px
}
.tab {
	WIDTH: 100%; CURSOR: hand; COLOR: white; BACKGROUND-COLOR: #ffa500; DBBA39: ; 426410: ; B8DC7A: ; FF7928: ; FFCE7E: ; green: ; B8DC7C: ; 9DE663: ; F94F1B: ; 9CA0FF: 
}
.clicked {
	BORDER-RIGHT: #ffffff 1px inset; BORDER-TOP: #c0c0c0 1px inset; BORDER-LEFT: #c0c0c0 1px inset; CURSOR: hand; BORDER-BOTTOM: #ffffff 1px inset
}
.mouseon {
	BORDER-RIGHT: black 1px outset; BORDER-TOP: white 1px outset; BORDER-LEFT: white 1px outset; CURSOR: hand; BORDER-BOTTOM: black 1px outset
}
.buttonmouseon {
	BORDER-RIGHT: #bbffbb 1px solid; BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid; BORDER-BOTTOM: #bbffbb 1px solid
}
.buttonclicked {
	BORDER-RIGHT: #ffffff 1px solid; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: #ffffff 1px solid
}
</STYLE>

<SCRIPT>
function killErrors() {
return true;
}
window.onerror = killErrors;
</SCRIPT>

<SCRIPT language=Jscript.Encode src="verticalbar.js"></SCRIPT>

<DIV class=button id=buttonup style="DISPLAY: none"><IMG onclick=upclick() 
src="imgbar/down2.gif" border=0> </DIV>
<DIV class=button id=buttondown style="DISPLAY: none"><IMG onclick=downclick() 
src="imgbar/up2.gif" border=0> </DIV>
<TABLE onmousedown="className = 'buttonclicked'" id=frameClose 
style="DISPLAY: none; POSITION: absolute; TOP: 0px" onmouseout="className = ''" 
height=1 cellPadding=0 width=0>
  <TBODY>
  <TR>
    <TD></TD></TR></TBODY></TABLE>
<CENTER>
<SCRIPT language=Javascript>
img = "syntax.gif"
<s:push value="#session">
<s:property value="baroption"/>
</s:push>
helpTarget = "main"
initializeBar("00")
</SCRIPT>
</CENTER>
</BODY>
</HTML>
