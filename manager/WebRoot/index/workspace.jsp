<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">

<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
$(document).ready(function(){
$("#forumid").load("indexForum.pl?now="+new Date().getTime());
$("#helpandgoodslessonid").load("goodLessonList.pl?now="+new Date().getTime());
$("#staticid").load("staticsView.pl?now="+new Date().getTime());
});
</script>


</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="1">
  <tr>
    <td align="center" valign="top">
    <s:if test="tongzhi!=null">
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
      <td class="baseFontBold" align="center" width="4"></td>
        <td height="24" colspan="2" class="baseFontBold"  align="left">
      重要通知(
              <s:if test="@com.changpeng.system.util.CommonDatas@groups.containsKey(tongzhi.thegroup)">
      <s:property value="@com.changpeng.system.util.CommonDatas@groups[tongzhi.thegroup]"/>
      </s:if>
      <s:else>
      系统发布
      </s:else>
      
      )：${tongzhi.title}               
        </td>
        </tr>
        <tr>
         <td class="tab_content1" align="center" width="4"></td>
        <td align="left" class="tab_content1">
        ${tongzhi.content}
        </td>
        </tr>
    </table></td>
  </tr>
</table>
<br/></s:if>
     <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="4" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">
        &nbsp;&nbsp;培训课程
          </td>
          <td height="24" colspan="2" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="center">
        <a href="../lessons/lessonsOnlineList.pl?lessonstyle=2">更多...</a>
        </td>
        </tr>
      <tr>
        <td width="30%" height="23" background="../imagesa/top-bg1.gif" align="center">内容标题</td>
        <td width="15%" height="23" background="../imagesa/top-bg1.gif"  align="center">主讲人</td>
        <td width="15%" background="../imagesa/top-bg1.gif"  align="center">授课时间</td>
       <!--  <td width="15%" height="23" background="../imagesa/top-bg1.gif"  align="center">授课地点</td>-->
        <td width="7%" height="23" background="../imagesa/top-bg1.gif"  align="center">学分</td>
        <td width="8%" background="../imagesa/top-bg1.gif"  align="center">来源</td>
      
      </tr>
      <s:iterator value="lessonList" status="statLesson">
      <tr>
        <td class="tab_content1" align="left"><a href="../lessons/lessonsView.pl?lessonid=${lessonid}">&nbsp;&nbsp;${title}</a></td>
        <td class="tab_content1" align="center">${teachers}</td>
        <td class="tab_content1" align="center"><s:date name="lessondate" format="yyyy-MM-dd HH:mm"/></td>
     <!-- <td class="tab_content1" align="center">${lessonaddress}</td> -->
        <td class="tab_content1" align="center">${xuefen}</td>
        <td class="tab_content1" align="center">
        <s:property value="@com.changpeng.system.util.CommonDatas@groups[groupid]"/></td>
     
        </tr>
    </s:iterator>
     <tr>
        <td class="tab_content1" align="left" colspan="7" height="4px">
     
        </td>
      
      </tr>
    </table></td>
  </tr>
</table> 
<br/>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF" id="forumid">
   </td>
  </tr>
</table>
    </td>
    <td align="center" valign="top" width="255px">
  
  <div id="staticid">
  </div>

<div id="helpandgoodslessonid">
   
	</div>
    </td>
  </tr>
</table>
</body>
</html>