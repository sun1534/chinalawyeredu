<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="message/leavelist_h.ftl" >
<div class="in-main">
	<div class="title-h"><h3><a href="../message/messagecenter.action">消息</a></h3></div>

        <div class="msgcollect">
			<ul>
            <li>站内信：<span><a href="../message/shortmessagelist.action?type=1" id="topshortmsg1">0条</a></span></li>
            <li class="xitong">系统消息：<span><a href="../message/systemmessagelist.action" id="topsysmsg1">0条</a></span></li>
            <li class="liuyan">留言板：<span><a href="../message/leavemessagelist.action?status=1" id="topreply1">0条</a></span></li>
            <#if currentRole=3 >
            <li class="jiaxiaohudong"><span><a href="../message/sendsmsMessageinput.action">家校互动</a></span></li>
            </#if>
          </ul>
       </div>
    <#if currentRole=1>
        <div class="message_top">
           <a href="sendMessage!input.action?sendto=3" class="s1">与老师交流</a>
           <a href="sendMessage!input.action?sendto=2" class="s2">与家长交流</a>
           <a href="sendMessage!input.action?sendto=1" class="s3">与学生交流</a>
           <a href="sendMessage!input.action?sendto=4" class="s4">与好友交流</a>
       </div>
    </#if>
    <#if currentRole=2>
        <div class="message_top">
           <a href="sendMessage!input.action?sendto=3" class="s1">与老师交流</a>
           <a href="sendMessage!input.action?sendto=2" class="s2">与家长交流</a>
           <a href="sendMessage!input.action?sendto=1" class="s3">与学生交流</a>
           <a href="sendMessage!input.action?sendto=4" class="s4">与好友交流</a>
       </div>
    </#if>
    <#if currentRole=3>
	<div class="message_top">
           <a href="sendMessage!input.action?sendto=3" class="s1">与老师交流</a>
           <a href="sendMessage!input.action?sendto=2" class="s2">与家长交流</a>
           <a href="sendMessage!input.action?sendto=1" class="s3">与学生交流</a>
           <a href="sendMessage!input.action?sendto=4" class="s4">与好友交流</a>
       </div>
	   <div class="title-info">
			<div class="m-tag">
				<ul>
					<li><a href="shortmessagelist.action?type=1" ><span>收件箱</span></a></li>
					<li><a href="shortmessagelist.action?type=2" ><span>已发消息</span></a></li>
                    <li class="current"><a href="sendMessage!input.action?sendto=1" ><span>写站内信</span></a></li>
				</ul>
			</div>
      </div>

      <form action="messagecenter.action" id="form1" method="post" name="form1">
      <input type="hidden" name="sendmsg" value="send" />
      <div class="mag_sendbox">
			<div class="mag_wrap" >
				<div class="mag_sendbox_in">
				<p style="display:none"><label for="cname" class="title">信息类别：</label>
				<span><input name="msgtype" type="checkbox" value="1" /> 家校互动&nbsp;&nbsp;&nbsp;&nbsp;<input name="msgtype" type="checkbox" checked="checked" value="2" />站内信&nbsp;&nbsp;&nbsp;&nbsp;<!--<input name="msgtype" type="checkbox" value="3" /> 邮件--></span></p>
				<p class="sendto">发送内容：</p>
				<div class="form Mymsg"><textarea name="msgcontent" maxlen="500" cols="80" rows="6" id="textfield"></textarea>
				</div>
				<p class="formop"><input class="delBtn igreen" type="button" id="saveBtn1" value="发送消息" /></p>
                <p class="sendselect">请选择班级：
                 <select id="classsel" onchange="changeclass(this)">
                  <#list classeslist as cla >
                  <option value="${cla.id}" >${cla.className}</option>
                  </#list>
                 </select>
                </p>
<script type="text/javascript">
function TabChange(obj,showContent,selfObj){
	// 操作标签
	var tag = document.getElementById(obj).getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "normal";
		tag[i].childNodes[0].className = "pointer";
	}
	selfObj.parentNode.className = "current";
	selfObj.parentNode.childNodes[0].className = "default";
	// 操作内容
	for(i=0; j=document.getElementById(obj+"Con"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
}
 </script>
 <style>
 .pointer {cursor:pointer;}
.default {cursor:pointer;}

.none {display:none; }
.show {display:block; }
ul li.normal a {cursor:pointer;}
ul li.current a {cursor:normal;}
 </style>
                <div class="title-info">
                    <div class="m-tag">
                        <ul id="tagorange">
                            <li class="current"><a onmouseover="TabChange('tagorange','tagorangeCon0',this)" href="#" ><span>教师通讯录</span></a></li>
                            <li><a onmouseover="TabChange('tagorange','tagorangeCon1',this)" href="#" ><span>家长通讯录</span></a></li>
                            <li><a onmouseover="TabChange('tagorange','tagorangeCon2',this)" href="#" ><span>学生通讯录</span></a></li>
                        </ul>
                    </div>
              </div>

       	<div class="show" id="tagorangeCon0">
		<div class="contentmain-body">
	      	  <div class="choice_all">
	      	  	<input type="checkbox" id="teachersel" onclick="checkall($(this))" />选择所有老师
	      	  </div>
		      <ul class="name" id="teacherul">
		      <#if teacherlist??>
		      <#list teacherlist as st >
		        <li><label><input name="teacherseli" type="checkbox" value="${st.userid}" />
		        <strong>${st.userbase.username}</strong> 老师</label></li>
		      </#list>
		      </#if>
		      </ul>
		      </div>
	      </div>
	      <div class="none" id="tagorangeCon1" style="display:none">
	      <div class="contentmain-body">
	      	  <div class="choice_all">
	      	  	<input type="checkbox" id="parentsel" onclick="checkall($(this))" />选择所有家长
	      	  </div>

		      <ul class="name" id="parentul">
		      <#if studentlist??>
		      <#list studentlist as st >
		        <li><label><input name="parentseli" type="checkbox" value="${st.userid}" />
		        <strong>${st.userbase.username}</strong> 同学的家长</label></li>
		      </#list>
		      </#if>
		      </ul>
		      </div>
	      </div>
	      <div  class="none" id="tagorangeCon2" style="display:none">
	      <div class="contentmain-body">
	      	  <div class="choice_all">
	      	  	<input type="checkbox" id="studentsel" onclick="checkall($(this))" />选择所有学生
	      	  </div>

		      <ul class="name" id="studentul">
		      <#if studentlist??>
		      <#list studentlist as st >
		        <li><label><input name="studentseli" type="checkbox" value="${st.userid}" />
		        <strong>${st.userbase.username}</strong> 同学</label></li>
		      </#list>
		      </#if>
		      </ul>
		      </div>
	      </div>

			</div>
		</div>
      </div>
      </form>


<script>
var msg1;
var msg2;
var msg3;
$.ajax({
	type:"post",
	url:"../home/messageCountShow.action",
	success:function(data){
	   msgcnt=data.split(",");
	   msg1=msgcnt[0];
	   msg2=msgcnt[1];
	   msg3=msgcnt[2];

	   $("#topsysmsg1").html(packmsg(msg1)+"条");
	   $("#topshortmsg1").html(packmsg(msg2)+"条");
	   $("#topreply1").html(packmsg(msg3)+"条");
	}
});
function packmsg(m){
	var msg;
	if(m>0){
		msg="<img border=0 src='${staticpath}/css/images/icon/newmsg1.gif' />"+m;
	}else{
		msg=m
	}
	return msg;
}
function changeclass(obj){
	$("#teacherul").empty();
	$("#parentul").empty();
	$("#studentul").empty();
	var hh1="<li><label><input name=\"";
	var hh2="\" type=\"checkbox\" value=\"";
	var hh3="\" />";
	//var hh4="</strong> ";
	var hh5="</label></li>"
	/*$.getJSON(
		"../json/getmember.action",
		{classid: obj.options[obj.selectedIndex].value,type:"9"},
		function(json){
			for(i=0;i<json.teachers.length;i++){
				$("#teacherul").append(hh1+"teacherseli"+hh2+json.teachers[i].id+hh3+json.teachers[i].name+hh4+"老师"+hh5);
			}
			for(i=0;i<json.students.length;i++){
				$("#studentul").append(hh1+"studentseli"+hh2+json.students[i].id+hh3+json.students[i].name+hh4+"同学"+hh5);
				$("#parentul").append(hh1+"parentseli"+hh2+json.students[i].id+hh3+json.students[i].name+hh4+"同学的家长"+hh5);
			}
	});
	*/
	$.getJSON(
		"../json/getmember.action",
		{classid: obj.options[obj.selectedIndex].value,type:"1"},
		function(json){
			for(i=0;i<json.members.length;i++){
				$("#studentul").append(hh1+"studentseli"+hh2+json.members[i].id+hh3+json.members[i].name+hh5);
			}
		}
	);
	$.getJSON(
		"../json/getmember.action",
		{classid: obj.options[obj.selectedIndex].value,type:"2"},
		function(json){
			for(i=0;i<json.members.length;i++){
				$("#parentul").append(hh1+"parentseli"+hh2+json.members[i].id+hh3+json.members[i].name+hh5);
			}
		}
	);
	$.getJSON(
		"../json/getmember.action",
		{classid: obj.options[obj.selectedIndex].value,type:"3"},
		function(json){
			for(i=0;i<json.members.length;i++){
				$("#teacherul").append(hh1+"teacherseli"+hh2+json.members[i].id+hh3+json.members[i].name+hh5);
			}
		}
	);
}
changeclass(document.getElementById("classsel"));
</script>
</#if>
</div>
</@home.home>
</#escape>