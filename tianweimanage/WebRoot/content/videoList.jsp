<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-视(音)频列表</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit()
  return true;
}
function queryit(str){
  document.form1.dirid.value=str;
  document.form1.submit()
  return true;
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
              <td width="97%"><span class="sort-title">内容管理&gt;&gt;视(音)频列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="videoList" method="POST">	
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  	<tr class=listline >
                  	  <td align="center" colspan="16">
              
                 	<s:hidden name="pageNo"/>
                 	<s:hidden name="dirid"/>
                 	类型:<s:select name="typeid" list="#{'1':'视频','2':'音频'}" headerKey="-1" headerValue="全部"/>
                  	状态:<s:select name="statusid" list="@com.sxit.content.util.CommonDatas@STATUS" headerKey="-1" headerValue="全部"/>
                  	上传者:<s:textfield name="userName" size="10"/>
                    上传时间从:<jscalendar:jscalendar name="start"/> 
                  	到:<jscalendar:jscalendar name="end"/> 
                  	  <input type="submit" value="查询" class="botton">
                  	  </td>
                  	  </tr>
                  	  <TR class="listheadline">               
                      <TD>类型</TD>
                   
                         <TD>目录</TD>
                      <TD>名称</TD>
                      <TD>上传人</TD>
                      <TD>上传时间</TD>
                         <TD>状态</TD>
                      <TD>审核</TD>     
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                       <TD>${typename}</TD>
                       <TD> 
                       <a href="#" onclick="queryit('${ dirid}')"><s:property value="@com.sxit.content.util.CommonDatas@FILEDIR[dirid]"/></a>
                       </TD> 
                        <TD>${fileName}</TD>                                                                        
                        <TD>
                        <s:property value="@com.sxit.users.util.CommonDatas@ALLUSERS[userid]"/>
                        </TD>
                        <TD><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></TD>
                          <TD>  <s:property value="@com.sxit.content.util.CommonDatas@STATUS[statusid]"/>
             
                        <TD>
                        <s:if test="statusid!=0">
                        <a href="videoView.action?serviceId=${id}&businessId=4">【审核】</a>
                        </s:if>
                         <s:else>
                      
                       <a href="videoView.action?serviceId=${id}&businessId=4"> 审核情况</a>
                       </s:else>
                        </TD>         
                        </TR>                        				
					</s:iterator>
             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colspan="12">

  <div align="right" style="background-color:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>
<!--
                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
              -->
                    <TR bgcolor="#FEF7E9" class="pt9-18">
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