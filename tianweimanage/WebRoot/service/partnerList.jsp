 <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-合作伙伴列表></title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="partnerCreateEdit!input.action";
}
function deleteInfo(id){
   if(confirm('您确定要删除这个合作伙伴吗?'))
     window.location.href="partnerDelete.action?id="+id;
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;合作伙伴列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="adList" method="POST">	
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  	<tr class=listline >
                  	  <td align="left" colspan="16">&nbsp;
              
                 	<s:hidden name="pageNo"/>
   
                  	
                  	  </td>
                  	  </tr>
                  	  <TR class="listheadline">               
                    
                      <TD>序号</TD>
                       <TD>状态</TD>
                      <TD>LOGO</TD>
                      <TD>链接</TD> 
                       <TD>说明</TD>              
                      <td>修改</td>
                      <td>删除</td>
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                 
                       <TD>${id }</TD>  
                         <TD>
                         <s:if test="status==1">禁用
                         </s:if>
                         <s:else>发布
                         </s:else>
                         </TD>
                       <TD><a href="${url}" target="_blank"><img src="${resourcepath}${logo }" width="120" border="0"/></a></TD>  
                        <TD><a href="${url}" target="_blank">${url }</a></TD>  
                        <TD>${description }</TD>  
                        <TD><a href="partnerCreateEdit!input.action?id=${id}">修改</a></TD>
                         <TD><a href="#" onclick="deleteInfo('${id}')">【删除】</a></TD>
                         
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
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
<input class="botton" type=button onclick="javascript:getAdd()" value="新增">&nbsp;&nbsp;

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