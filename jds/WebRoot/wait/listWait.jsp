<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看Wait列表</p>
 * <p>作者： zrb</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--
function getSearch(){
     document.form1.action = "searchUser.action";
     document.form1.submit();
}
function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=document.form1.selectAll.checked;
               }
          }else{
               document.form1.check.checked=document.form1.selectAll.checked;
          }
     }
}
function getDelete(){
     if(noChecked()){
          alert("请选择记录，删除需要选择记录！");
          return false;
     }
     if (confirm("您确定要进行删除?")) {
          document.form1.action="deleteWaits.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
-->
</script>
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 1px;
	margin-right: 2px;
	margin-bottom: 1px;
}
-->
</style></HEAD>
<BODY >
<TABLE cellSpacing=0 borderColorDark=#ffffff cellPadding=0 width="100%" 
borderColorLight=#336699 background=topimg/bar_6.gif border=0>
  <TBODY>
  <TR>
    <TD vAlign=top height=22><IMG src="topimg/left.gif"></TD>
    <TD></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR vAlign=top>
    <TD width=858><IMG src="topimg/daibanshiyi.gif"></TD></TR>
  <TR vAlign=top>
    <TD width=858>
      <TABLE width="100%" border=0>
        <TBODY>
        <TR>
          <TD vAlign=top height=350>
            <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
              <TR>
                <TD align=left valign="top" bgColor=#F9F9F7><TABLE>
                  <TBODY>
                    <TR>
                      <TD  width=150 height=60>
					  <A  href="listWait1.action"><IMG  src="topimg/newflow.gif" border=0></A> <BR>
                      <A  href="listWait1.action"><b>急件办理</b></A> <BR>
<iframe width="40" noResize frameborder="0" height="30" name="main" src="listNum.action?docstatus=1"  marginwidth="0"  scrolling="no" marginheight="0" ></iframe>
						</TD>
                      <TD  width=150 height=60>
					  <A href="listWait2.action"><IMG src="topimg/newdoc.gif" border=0></A> <BR>
                      <A href="listWait2.action"><b>办件办理</b></A> <BR>
<iframe width="40" noResize frameborder="0" height="30" name="main" src="listNum.action?docstatus=2"  marginwidth="0"  scrolling="no" marginheight="0" ></iframe>
						</TD>
                      <TD  width=150 height=60>
					  <A href="listWait3.action"><IMG src="topimg/docout.gif" border=0></A> <BR>
                      <A href="listWait3.action"><b>阅件办理</b></A> <BR>
<iframe width="40" noResize frameborder="0" height="30" name="main" src="listNum.action?docstatus=3"  marginwidth="0"  scrolling="no" marginheight="0" ></iframe>
						</TD>
                    </TR>
                  </TBODY>
                </TABLE></TD>
              </TR>
              <TR>
                <TD  valign="top" bgColor=#F9F9F7> <s:form name="'form1'" action="'deleteWaits.action'" enctype="'whith=100%'"  method="'post'">
                  <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 >
                    <TBODY>
                      <s:iterator value="Waitlist" status="status">
                      <TR  >
                        <TD width="30" height="20"  bgcolor="#FFFFFF"><FONT color=#666666 size=2><IMG 
            height=15 src="topimg/7.gif" width=25></FONT></TD>
                        <TD height="20" align=left bgcolor="#FFFFFF" class="pt9-18">
						<a href="<s:property value='url'/>&backurl=listWait.action&pagenumber=<s:property value='pagenumber'/>" target="<s:property value='target'/>">
						请处理《<s:property value="subject"/>》
						</a>&nbsp;&nbsp;&nbsp;
                          <s:if test="docstatus==1"> <font color="#FF0000">急件</font> </s:if>
                          <s:if test="docstatus==2"> <font color="#009900">办件</font> </s:if>
                          <s:if test="docstatus==3"> <font color="#000000">阅件</font> </s:if>	
						  &nbsp;&nbsp;	<FONT color=#666666>(<s:date name="createtime" format="yyyy-MM-dd"/>)</FONT></TD>
                         </TR>
                      </s:iterator>
                    </TBODY>
                  </TABLE>
                </s:form> </TD>
              </TR>

            </TABLE></TD></TR>
        </TBODY></TABLE></TD></TR>
  <TR vAlign=top>
    <TD width=858><IMG height=1 alt="" src="topimg/ecblank.gif" 
      width=1 border=0></TD></TR>
  <TR vAlign=top>
<TD width=858><IMG height=1 alt="" src="topimg/ecblank.gif" 
      width=1 border=0></TD></TR></TBODY></TABLE>

</BODY>
</HTML>


