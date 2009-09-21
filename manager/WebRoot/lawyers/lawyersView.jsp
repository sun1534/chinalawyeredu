<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<html>
<head>
<title>律师信息查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/common.js"></script>
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
<td height="23" background="../imagesa/top-bg3.gif" class="baseFontBold">
				
					律师资料查看
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" class="border-table">
  <tr class="border-tr">
    <td>
    <s:form name="form1" action="lawyerEdit" method="post" validate="true" enctype="multipart/form-data">
      <table width="80%" border="0" cellspacing="1" cellpadding="1" align="center" class="content-table">
		<tr>
          <td colspan="2">&nbsp;
          	<font color="#FF0000"><b>
      
         </b></font>
         </td>
        </tr>
		<tr>
          <td align="right" class="tab_content1">
             所属事务所:
          </td>
          <td class="tab_content1">
${groupname}
           </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             律师姓名:
          </td>
          <td class="tab_content">
           ${sysUser.lawyername}
              </td>
        </tr>
        <tr>
            <td align="right" class="tab_content1"> 律师执业证号: </td>
          <td class="tab_content1">
         ${sysUser.lawyerno}
             </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 身份证号: </td>
          <td class="tab_content">
         ${sysUser.certno}
          
          </td>
        </tr>
	
		<tr>
            <td align="right" class="tab_content1"> 培训卡卡号: </td>
          <td class="tab_content1">
          ${sysUser.cardno}
            </td>
        </tr>
         <tr> 
            <td align="right" class="tab_content">培训卡发卡日期: </td>
          <td class="tab_content">
       ${sysUser.carddate}
            </td>
        </tr>
 <tr> 
            <td align="right" class="tab_content1">所在律协执业日期: </td>
          <td class="tab_content1">
           
           ${sysUser.zhiyedate}
            </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 手机号码: </td>
          <td class="tab_content">
            ${sysUser.mobile1}
            </td>
        </tr>
        <s:if test="sysUser.photo!=null">
         <tr> 
            <td align="right" class="tab_content1">律师照片: </td>
          <td class="tab_content1">
        
          <img src="../lawyerphotos/${sysUser.photo}" width="106" border="0"/><br/>

            </td>
        </tr>
        </s:if>
		
       <tr> 
            <td align="right" class="tab_content">性别: </td>
          <td class="tab_content">
           <s:select name="sysUser.gender" list="#{'G':'男','M':'女'}"/>
            </td>
        </tr>
        <tr> 
            <td align="right" class="tab_content1">备注信息: </td>
          <td class="input-content">
           ${sysUser.remarks}
            </td>
        </tr>		

        <tr>
          <td colspan="2" align="center"><input type="button" value="返回" onclick="history.go(-1)"/>
      
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>