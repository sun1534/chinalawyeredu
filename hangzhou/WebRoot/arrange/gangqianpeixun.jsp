<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<html>
<head>
<title>岗前培训报名</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font">
    <img src="images/b_02.gif" width="4" height="7"> 当前位置：岗前培训报名 </td>
  </tr>
</table>
<s:form action="arrangesignup" method="post" name="form1">
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"><div align="left">　</div>
        </td>
        </tr>
          <tr>
        <td width="37%" class="tab_content" align="right">培训主题：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        ${arrange.title}
        </td>
        </tr>

      <tr>
        <td width="37%" class="tab_content" align="right">培训人：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        	     	<s:hidden name="arrangeid"/>
                    <s:hidden name="signupid"/>
        	     		<s:hidden name="arrangetype"/>
        	<s:textfield name="signup.arrangeperson" size="20"/></td>
        </tr>
    
       <tr>
        <td width="37%" class="tab_content" align="right">性别：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:select name="signup.gender" list="#{'男':'男','女':'女'}"/>
         	
        	</td>
        </tr>
     
       <tr>
        <td width="37%" class="tab_content" align="right">出生年月：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><jscalendar:jscalendar name="signup.birthday" format="%Y-%m-%d" showstime="false"/></td>
        </tr>
            <tr>
        <td width="37%" class="tab_content" align="right">民族：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="signup.minzu" size="10"/>
        	</td>
        </tr>
         <tr>
        <td width="37%" class="tab_content" align="right">政治面目：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="signup.policy" size="15"/>
        	</td>
        </tr> 
         <tr>
        <td width="37%" class="tab_content" align="right">毕业时间：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><jscalendar:jscalendar name="signup.granduatedate" format="%Y-%m-%d" showstime="false"/>
        	</td>
        </tr> 
         <tr>
        <td width="37%" class="tab_content" align="right">实习证号：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="signup.shixizhenghao" size="25"/>
        	</td>
        </tr> 
         <tr>
        <td width="37%" class="tab_content" align="right">实习证时间：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        	<jscalendar:jscalendar name="signup.shixidate" format="%Y-%m-%d" showstime="false"/>
        
        	</td>
        </tr> 
         <tr>
        <td width="37%" class="tab_content" align="right">资格证号：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="signup.zigezhenghao" size="25"/>
        	</td>
        </tr> 
         <tr>
        <td width="37%" class="tab_content" align="right">资格证时间：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
         	<jscalendar:jscalendar name="signup.zigedate" format="%Y-%m-%d" showstime="false"/>

        	</td>
        </tr> 
         <tr>
        <td width="37%" class="tab_content" align="right">联系电话：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="signup.phone" size="25"/>
        	</td>
        </tr> 
         <tr>
        <td width="37%" class="tab_content" align="right">通讯地址：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="signup.address" size="35"/>
        	</td>
        </tr>
           <tr>
        <td width="37%" class="tab_content" align="right">个人经历：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textarea name="signup.career" rows="4" cols="50"/>
        	</td>
        </tr>
      <tr>
      <tr>
       	
        <td class="tab_content1"></td>
        <td colspan="2" class="tab_content1">　
         
          <input type="submit" name="Submit" value=" 保存 "/>
          　 
          <input type="reset" name="Submit2" value=" 重置 "/>
          　
          <input type="button" name="Submit3" value=" 返回 " onClick="javascript:history.go(-1)">
          </td>
        </tr>
    </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>