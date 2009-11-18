<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName }-相册查看</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<script language="javascript">

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit()
  return true;
}
</script>
</HEAD>
<BODY>
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
              <td width="97%"><span class="sort-title">内容管理&gt;&gt;<s:property value="@com.sxit.users.util.CommonDatas@ALLUSERS[album.userid]"/>的相册&gt;&gt;${album.albumName}</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
              
              <TABLE width="80%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
					
					<TR>
					<TD colspan="4" class="listline">
                    <div align="left" class="sort-title" style="display:inline;float:left">
                  ${photoName} 
                  <s:if test="album.isrecommand">
                  <a href="albumRecommand.action?albumid=${albumid }&pageNo=${pageNo }">【取消推荐】</a>
                  </s:if>
                  <s:else>
                  <a href="albumRecommand.action?albumid=${albumid }&pageNo=${pageNo }">【推荐】</a>
                  </s:else>
                  </div>
                  <div style="display:inline;float:right"class="sort-title">
           
              ${page.contentPageView}
               
                  </div>                            
                  </TD>
					</TR>
					  <TR>
					<TD colspan="4" class="listline" height="10">
					&nbsp;
                   </TD>
					</TR>
	 			 	  <TR>
					<TD colspan="4" class="listline">
                    <div align="center" class="sort-title">
                    <s:iterator value="page.items">
           <img src="${resourcepath}${pic}" width="500"/>
         </s:iterator>
          
                  </div>                  </TD>
					</TR>
	 			
            </TABLE>   
           
          </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="form1" method="post" action="albumView">
<s:hidden name="albumid"/>
<s:hidden name="pageNo"/>
</s:form>
