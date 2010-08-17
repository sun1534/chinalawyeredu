<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">

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
        <td height="24" colspan="2" class="baseFontBold"  align="left">重要通知：${tongzhi.title}
        【<s:if test="tongzhi.thegroup==0">系统</s:if><s:else><s:property value="@com.changpeng.common.CommonDatas@groups[tongzhi.thegroup]"/></s:else>】
                 
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
          <td height="24" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="center">
        <a href="../lessons/lessonsList.pl">更多...</a>
        </td>
        </tr>
      <tr>
        <td width="35%" height="23" background="../imagesa/top-bg1.gif" align="center">内容标题</td>
        <td width="20%" height="23" background="../imagesa/top-bg1.gif"  align="center">主讲人</td>
        <td width="20%" background="../imagesa/top-bg1.gif"  align="center">授课时间</td>
        <td width="10%" height="23" background="../imagesa/top-bg1.gif"  align="center">学分</td>
        <td width="15%" background="../imagesa/top-bg1.gif"  align="center">来源</td>
       
      </tr>
      <s:iterator value="lessonList" status="statLesson">
      <tr>
        <td class="tab_content1" align="left"><a href="../lessons/lessonsView.pl?lessonid=${lessonid}">&nbsp;&nbsp;${title}</a></td>
        <td class="tab_content1" align="center">${teachers}</td>
        <td class="tab_content1" align="center"><s:date name="lessondate" format="yyyy-MM-dd HH:mm"/></td>
     
        <td class="tab_content1" align="center">${xuefen}</td>
 <td class="tab_content1" align="center"><s:property value="@com.changpeng.common.CommonDatas@groups[groupid]"/></td>
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
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="3"  align="left" background="../imagesa/top-bg2.gif" class="baseFontBold">&nbsp;&nbsp;培训论坛          </td>
        <td height="24" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="center">
        <a href="../forum/forumList.pl">更多...</a>
        </td>
      </tr>
      <tr>
        <td width="40%" height="23" background="../imagesa/top-bg1.gif" align="center">内容标题</td>
        <td width="15%" height="23" background="../imagesa/top-bg1.gif"  align="center">创建者</td>
        <td width="10%" background="../imagesa/top-bg1.gif"  align="center">答复数</td>
        <!--  <td width="15%" background="../imagesa/top-bg1.gif"  align="center">板块</td> -->
        <td width="20%" height="23" background="../imagesa/top-bg1.gif"  align="center">上次更新时间</td>
      </tr>
      <s:iterator value="forumList" status="stat">
      <tr>
       
        <td class="tab_content1" align="left"><a href="../forum/forumView.pl?mainforumid=${forumid}">&nbsp;&nbsp;${title}</a></td>
        <td class="tab_content1" align="center">${createuser}</td>
        <td class="tab_content1" align="center">${replycount}</td>
       <!--  <td class="tab_content1" align="center"><s:property value="@com.changpeng.common.CommonDatas@groups[thegroup]"/></td> -->
        <td class="tab_content1" align="center"><s:date name="lastupdate" format="yyyy-MM-dd HH:mm"/></td>
      </tr>
      </s:iterator>
      <tr>
        <td colspan="5" align="left" class="tab_content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" 我要发帖 " name="addForum" class="button" onClick="javascript:window.location.href='../forum/forumCreate!input.pl'"/></td>
        </tr>
    </table></td>
  </tr>
</table>
    </td>
    <td align="center" valign="top" width="255px">
     	<!--律师的话显示这个
     	 		-->
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="11" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left" title="点此修改您的个人信息">&nbsp;&nbsp;<a href="../lawyers/lawyersEditSelf!input.pl">个人信息</a></td>
        </tr>
      <tr>
        <td height="23" colspan="3"  align="left" background="../imagesa/top-bg1.gif"><b>&nbsp;&nbsp;积分年度：${jifentime.startstr} 至 ${jifentime.endstr}</b></td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="30" valign="middle">
        <b>号：</b></td>
        <td align="left" bgcolor="#F2F8FF"> ${lawyers.cardno} </td>      
        <td width="120" rowspan="8" align="center" valign="middle" bgcolor="#F2F8FF" >
       <s:if test="lawyers.photo==null||lawyers.photo.equals(\"\")"> 
      		 <img src="../imagesa/none.jpg" width="106"/>       
      	</s:if>
      	<s:else>
      		<img src="${logopath}/${lawyers.photo}" width="106"/>    
      	</s:else>        <br/> <b>您好，${lawyers.lawyername} </b> </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>证： </b></td>                   
        <td align="left" bgcolor="#F2F8FF">${lawyers.lawyerno}  </td>  </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>所：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF"><s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.theoffice]"/></td></tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>分： </b> </td>                  
        <td align="left" bgcolor="#F2F8FF">${nowxuefen}	</td></tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>需：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">${needfen}分才能达标	</td></tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>执： </b>   </td>                
        <td align="left" bgcolor="#F2F8FF">${lawyers.zhiyedate}</td>
      </tr>
      <s:if test="lawyers.lawyertype>=0">
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>审：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">
         	<font color="red">
           <s:if test="nowxuefen>=needfen">
           	 已通过
            </s:if>
             <s:else>未通过
            </s:else> </font>
        </td>
        </tr>
        </s:if>
         <tr>
        <td align="center" bgcolor="#F2F8FF" height="10"> </td>                   
        <td align="left" bgcolor="#F2F8FF"></td></tr>     
    </table></td>
  </tr>
</table>
    <br/>

  


  


    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="9" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;系统帮助</td>
        </tr>
           <s:iterator value="helpList" status="stat">
      <tr>
      
        <td class="tab_content1" align="left"><a href="../articles/articlesView.pl?articleid=${articleid}">&nbsp;&nbsp;${title}</a></td>
       
      </tr>
    </s:iterator>

    </table></td>
  </tr>
</table>
<br/>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="9" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">
        &nbsp;&nbsp;好评课程
          </td>
        </tr>
      <tr>
      
        <td width="80%" height="23" background="../imagesa/top-bg1.gif"  align="center">内容标题</td>
        <td width="20%" background="../imagesa/top-bg1.gif"  align="center">好评数</td>
        
      </tr>
        <s:iterator value="goodlessonList" status="goodstat">
      <tr>
       
        <td class="tab_content1" align="left">

     <s:set name="lessonid" value="goodlessonList[#goodstat.index][1]"/>
      <a href="../lessons/lessonsView.pl?lessonid=${lessonid}">  &nbsp;&nbsp;<s:property value="@com.changpeng.lessons.util.CommonDatas@AllLessonMap[#lessonid]"/></a>
        </td>
        <td  align="center" class="tab_content1"><s:property value="goodlessonList[#goodstat.index][0]"/></td>
 
      </tr>
         </s:iterator>

    </table></td>
  </tr>
</table>
<br/>
	
    </td>
  </tr>
</table>
</body>
</html>