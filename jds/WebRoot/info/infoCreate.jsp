<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<%
/**
 * <p>功能： 创建info</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
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
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;新增信息</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
			<br>
                <TABLE width="630"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="infoCreate" validate="true" method="post" enctype="multipart/form-data">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
	 			 <s:hidden name="info.statusid" value="0"/>
	 			 	<TR>
						<TD width="13%" class="listheadline">名称:</TD>
						<TD colspan="3" class="listline"><s:textfield name="info.infoname" size="50"/></TD>
					
					</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">文件编号:</TD>
	 			 	  <TD class="listline"><s:textfield name="info.filenumber" size="15" /></TD>
					  <TD width="13%" class="listheadline">发布部门:</TD>
					  <TD  class="listline">						
						<s:select 
						name="info.department.departmentid" 
						listKey="departmentid" 
						listValue="displayname" 
						list="departmentlist" 
						value="${curuser.tsysDepartment.departmentid}" 
						/>
					  </TD>
	 			 	</TR>
					<TR>
						<TD class="listheadline">信息类别:</TD>
						<TD class="listline"> <s:select 
						 name="info.tinfType.typeid"
						 list="typelist"
						 listKey="tinfType.typeid" 
						 listValue="tinfType.name" 
						 ></s:select> </TD>
                      <TD width="13%" class="listheadline">附件:</TD>
                      <TD class="listline">
					  	<table id="fileTable" cellspacing="0" cellpadding="0" border="1"  >
					  		<input name='upload' type='file' size='20' id='Create_upload' >
					  	</table>
					  </TD>

                    </TR>
                    <!--  
                    <TR>
	 			 	  <TD class="listheadline">发布到外网</TD>
	 			 	  <TD colspan="3" class="listline"><s:checkbox name="info.isweb"/></TD>
					</TR>
					-->
	 			 	<TR>
						<TD colspan=6  class="listheadline">详细内容:</TD>
					</TR>
                    <tr>
                    	<TD colspan=6  class="listline">
                    	<FCK:editor id="info.content" height="350" width="100%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"	> 
		  		        </FCK:editor>
                    	</TD>
                    </tr>

                    <TR bgcolor="#eeeeee">
                      <TD colspan="6" align="center">
                        
                        <INPUT name="sub2" type="submit" class="botton" value="保存" onClick="javascript:return saveall(1)">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
              </TABLE>
               <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
 <script language="JavaScript" type="text/JavaScript">
	function saveall(flag){
		document.forms['form1'].elements['info.statusid'].value=flag;
		return true;
	}
</script>
</TABLE>
</BODY>
</HTML>
