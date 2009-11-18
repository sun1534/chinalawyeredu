<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-任务列表</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

function deleteWait(waitid){
  if(confirm('您确定要删除这个任务吗?'))
    window.location.href="deleteWait.action?status=${status}&waitid="+waitid;
  return false;
}
-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">待办事宜&gt;&gt;
              <s:if test="status==1">
已办任务列表
</s:if>
<s:else>
待办任务列表
</s:else>
              </span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="listWait" method="POST">	
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  	<tr class=listline >
                  	  <td align="center" colspan="16">
              
                 	<s:hidden name="pageNo"/>
                 
                 
                  	  </tr>
                  	  <TR class="listheadline">               
                       <TD>标题</TD>
                       <TD>提交人</TD>
                       <TD>状态</TD>
                       <TD>环节</TD>
                       <TD>时间</TD>  
                       <TD>删除</TD>  
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                   
                       <TD><a href="<%=request.getContextPath()%>${url}">${subject}</TD>
                       <TD>${fromUsername}</TD>
                       
                       <TD>
         <s:if test="docstatus==1"> <font color="#FF0000">急件</font> </s:if>
         <s:if test="docstatus==2"> <font color="#009900">办件</font> </s:if>
         <s:if test="docstatus==3"> <font color="#000000">阅件</font> </s:if>	
                       </TD>
                       <TD>${flowstep}</TD>
                        
                       <TD><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></TD>    
                        <TD><a href="#" onclick="deleteWait('${waitid}')">【删除】</a></TD>       
                                                                     
                        </TR>                        				
					</s:iterator>
             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colspan="12">

  <div align="right" style="background-color:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>

                
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>