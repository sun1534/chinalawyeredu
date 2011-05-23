<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="javascript">

function baoming(lessonid){
		var now=new Date().getTime();
		 var url="../lessonajax/lessonBaoming.pl";
	
		
		 var pars = 'now='+now+'&lessonid='+lessonid;
		
         var myAjax = new Ajax.Request(url,{method: 'post', parameters:pars, onComplete: showResponse});		

}
function showResponse(originalRequest){
     var res=eval('(' + originalRequest.responseText + ')');
     if(res.code=="0"||res.code==0){
       alert("网上报名成功!");
    }else{
       alert(res.msg);
    }
}
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
        <td height="24" colspan="2" class="baseFontBold"  align="left">重要通知：${tongzhi.title}          
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
        <a href="../lessons/lessonsLocalList.pl">更多...</a>
        </td>
        </tr>
      <tr>
        <td width="30%" height="23" background="../imagesa/top-bg1.gif" align="center">内容标题</td>
        <td width="15%" height="23" background="../imagesa/top-bg1.gif"  align="center">培训师</td>
        <td width="15%" background="../imagesa/top-bg1.gif"  align="center">授课时间</td>
        <td width="15%" height="23" background="../imagesa/top-bg1.gif"  align="center">授课地点</td>
        <td width="7%" height="23" background="../imagesa/top-bg1.gif"  align="center">学分</td>
        <td width="8%" background="../imagesa/top-bg1.gif"  align="center">限制</td>
       <!-- <td width="10%" height="23" background="../imagesa/top-bg1.gif" align="center" >报名</td>-->
       
      </tr>
      <s:iterator value="lessonList" status="statLesson">
      <tr>
        <td class="tab_content1" align="left"><a href="../lessons/lessonsLocalView.pl?lessonid=${lessonid}">&nbsp;&nbsp;${title}</a></td>
        <td class="tab_content1" align="center">${teachers}</td>
        <td class="tab_content1" align="center">${lessondate}</td>
        <td class="tab_content1" align="center">${lessonaddress}</td>
        <td class="tab_content1" align="center">${xuefen}</td>
        <td class="tab_content1" align="center">${maxpersons}</td>
       <!-- <td class="tab_content1" align="center">
        <s:if test="baominguser==2">
        报名截止
        </s:if>
        <s:elseif test="baominguser==1">
        已报名
        </s:elseif>
        <s:else>
        <a href="#" onClick="baoming(${lessonid})">报名</a>
        </s:else>
        </td>-->
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
        <td width="55%" height="23" background="../imagesa/top-bg1.gif" align="center">内容标题</td>
        <td width="13%" height="23" background="../imagesa/top-bg1.gif"  align="center">创建者</td>
        <td width="10%" background="../imagesa/top-bg1.gif"  align="center">答复数</td>
        <td width="20%" height="23" background="../imagesa/top-bg1.gif"  align="center">上次更新时间</td>
      </tr>
      <s:iterator value="forumList" status="stat">
      <tr>
       
        <td class="tab_content1" align="left"><a href="../forum/forumView.pl?mainforumid=${forumid}">&nbsp;&nbsp;${title}</a></td>
        <td class="tab_content1" align="center">${createuser}</td>
        <td class="tab_content1" align="center">${replycount}</td>
        <td class="tab_content1" align="center">${lastupdate}</td>
      </tr>
      </s:iterator>
      <tr>
        <td colspan="4" align="left" class="tab_content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" 我要发帖 " name="addForum" class="button" onClick="javascript:window.location.href='../forum/forumCreate!input.pl'"/></td>
        </tr>
    </table></td>
  </tr>
</table>
    </td>
    <td align="center" valign="top" width="255px">
     	 	<s:if test="sysUser.roleid!=null&&sysUser.roleid==1"><!--律师的话显示这个
     	 		-->
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="11" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left" title="点此修改您的个人信息">&nbsp;&nbsp;<a href="../system/lawyerEditSelfPre.pl">个人信息</a></td>
        </tr>
      <tr>
        <td height="23" colspan="3"  align="left" background="../imagesa/top-bg1.gif"><b>&nbsp;&nbsp;积分年度：${from} 至 ${end}</b></td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="30" valign="middle">
        <b>号：</b></td>
        <td align="left" bgcolor="#F2F8FF"> ${sysUser.cardno} </td>      
        <td width="120" rowspan="8" align="center" valign="middle" bgcolor="#F2F8FF" >
       <s:if test="sysUser.photo==null||\"\".equals(sysUser.photo)"> 
      		 <img src="../imagesa/none.jpg" width="106"/>       
      	</s:if>
      	<s:else>
      		<img src="../lawyerphotos/${sysUser.photo}" width="106"/>    
      	</s:else>        <br/> <b>您好，${sysUser.username} 律师</b> </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>证： </b></td>                   
        <td align="left" bgcolor="#F2F8FF">${sysUser.lawerno}  </td>  </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>所：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">${sysUser.sysGroup.groupname}</td></tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>分： </b> </td>                  
        <td align="left" bgcolor="#F2F8FF">	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(nowxuefen)"/></td></tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>需：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">${needfen}分才能达标	</td></tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>深： </b>   </td>                
        <td align="left" bgcolor="#F2F8FF">${sysUser.zhiyedate}</td></tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>审：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">
         	<font color="red">
           <s:if test="nowxuefen>=needfen">
           	 已通过
            </s:if>
             <s:else>未通过
            </s:else> </font>
        </td></tr>
         <tr>
        <td align="center" bgcolor="#F2F8FF" height="10"> </td>                   
        <td align="left" bgcolor="#F2F8FF"></td></tr>     
    </table></td>
  </tr>
</table>
    <br/>
 
  </s:if>
  <s:if test="isadminer">
  <!--是管理员的话显示这个
  -->
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="10" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;统计信息</td>
        </tr>
      <tr>
        <td height="23" colspan="3"  align="right" background="../imagesa/top-bg1.gif"><b>【${from}】
            到 【${end}】</b></td>
      </tr>
      <tr  bgcolor="#F2F8FF" >
        <td align="right" width="45%" height="18" valign="middle">
        <b>新设现场培训：</b></td>
        <td align="left" >${lessoncnt}</td>      
        </tr>
      <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>新增视频课件： </b></td>                   
        <td align="left" >${onlinelessoncnt}</td>  
        </tr>
   <!--    <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"> <b>新增文本课件：</b> </td>                   
        <td align="left" > 0</td>
        </tr>
      -->
     <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>参加现场培训： </b> </td>                  
        <td align="left" >${localecnt}</td>
        </tr>
  
     <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>参加网上培训： </b> </td>                  
        <td align="left" >${video}</td>
        </tr>
     
 
<tr  bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>已达标人数： </b>   </td>                
        <td align="left" >${dabiaoshu}</td>
        </tr>
      <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>未达标人数：</b> </td>                   
        <td align="left" >${weidabiaoshu}</td>
        </tr>
         <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>未培训人数：</b></td>                   
        <td align="left" >${weipeishun}</td>
        </tr>     
    </table></td>
  </tr>
</table> <br/>
  </s:if>
 
   <!--是事务所管理员的话显示这个
   -->
<s:if test="isofficeadminer">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="11" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;事务所信息</td>
        </tr>

      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="30" valign="middle">
        <b>证：</b></td>
        <td align="left" bgcolor="#F2F8FF"> ${group.groupenname} </td>      
        <td width="120" rowspan="7" align="center" valign="middle" bgcolor="#F2F8FF" >
      	<s:if test="sysUser.photo==null||\"\".equals(sysUser.photo)"> 
      		 <img src="../imagesa/none.jpg" width="106"/>       
      	</s:if>
      	<s:else>
      		<img src="../lawyerphotos/${sysUser.photo}" width="106"/>    
      	</s:else>      </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>所： </b></td>                   
        <td align="left" bgcolor="#F2F8FF">${group.groupname}  </td>  
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>人：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">${groupusers}</td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>管： </b> </td>                  
        <td align="left" bgcolor="#F2F8FF">${sysUser.username}	</td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>电：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">${sysUser.officephone}
        <s:if test="sysUser.mobile!=null&&!\"\".equals(sysUser.mobile)">
            ${sysUser.mobile}
        </s:if>
        
        </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>区： </b>   </td>                
        <td align="left" bgcolor="#F2F8FF">${group.comments}</td>
      </tr>
      <tr>
        <td width="35" align="center" bgcolor="#F2F8FF">  </td>                   
        <td align="left" bgcolor="#F2F8FF">        </td></tr>
    </table></td>
  </tr>
</table>
<br/>
</s:if>
  


    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="9" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;系统帮助</td>
        </tr>

<!--      <tr>
  
        <td width="5%" height="23" background="../imagesa/top-bg1.gif"  align="left">费用</td>
     
      </tr>-->
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
        <s:set name="lessonid" value="goodlessonList[#goodstat.index][1].lessonid"/>
      <a href="../lessons/lessonsOnlineView.pl?lessonid=${lessonid}">  &nbsp;&nbsp;<s:property value="lessonMap[#lessonid]"/></a>
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