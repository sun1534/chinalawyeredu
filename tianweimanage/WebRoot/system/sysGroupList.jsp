<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-部门列表</title>
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
	window.location.href="sysGroupCreate!input.action?parentid=${parentid}";
}
function delGroup(groupid,parentid){
	if(confirm("确定要删除该部门吗？"))
		location.href="sysGroupDelete.action?groupid="+groupid+"&parentid="+parentid;
	else
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
              <td width="97%"><span class="sort-title">系统管理&gt;&gt;部门列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="sysUserList" method="POST">	
			
				
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  
               <tr class=listline >
                  	  <td align="left" colspan="16">
              
                 	<s:hidden name="pageNo"/>
    部门名称：<s:textfield name="groupname" size="10"/>
   
	  <input type="submit" value="查询" class="botton">
                  	  </td>
                  	  </tr>
               
                  	  <TR class="listheadline">               
                      <TD>部门名称</TD>
                      <TD>上级部门</TD>
                      <TD>联系人</TD>
                  
                      <TD>修改</TD>               
                      <TD>删除</TD>        
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                      <td>${groupname}</td>
        <td>
      
        <s:property value="@com.sxit.system.util.CommonDatas@groupsMap[parentid]"/>
      </td>
        <td>${contacter}</td>
      
		<td><a href="sysGroupEdit!input.action?groupid=${groupid}">修改</a></td>
		<td><a href="#" onclick="javascript:delGroup(${groupid},${parentid})">【删除】</a></td>
                        </TR>                        				
					</s:iterator>
             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colspan="12">

  <div align="right" style="background-color:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>

                    <TR bgcolor="#FEF7E9" class="pt9-18">
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
<input class="botton" type=button onclick="javascript:getAdd()" value=" 新增 ">&nbsp;&nbsp;

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