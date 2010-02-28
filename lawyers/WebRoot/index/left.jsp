<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=com.changpeng.common.Constants.SYS_NAME%></title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<link href="../css/dtree.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/dtreerightmenu.js"></script>
<script language="javascript" src="../js/rightmenu.js"></script>
<script language="javascript">

function makeMenu() {
	var myMenu, item;

	var homepage_cmd = "this.style.behavior='url(#default#homepage)';this.setHomePage('http://baby.mob8.cn/'); return false;";
	var favorate_cmd = "window.external.addFavorite('http://baby.mob8.cn','网页教学网'); return false;";
	var viewcode_cmd = "window.location = 'view-source:' + window.location.href";

	myMenu = new contextMenu();

	item = new contextItem("返回主页", "",
			"top.location='http://baby.mob8.cn/';", "menu");
	myMenu.addItem(item);

	item = new contextItem("设为主页", "",
			homepage_cmd, "menu");
	myMenu.addItem(item);

	item = new contextItem("添加到收藏夹", "",
			favorate_cmd, "menu");
	myMenu.addItem(item);

	item = new contextItem("联系作者", "",
			"location.href='mailto:46235412@qq.com'", "menu");
	myMenu.addItem(item);

	item = new contextItem("", "", "", "separator");
	myMenu.addItem(item);
	item = new contextItem("察看源码", "",
			viewcode_cmd, "menu");
	myMenu.addItem(item);
	myMenu.show(this.document);
	delete item;
	delete myMenu;
}
</script>
</head>

<body>
 
<table width="99%" height="540" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" class="left_function">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" background="../imagesa/top-bg2.gif"> 　
          <a href="javascript: d.openAll();">打开所有 <img src="../imagesa/icon_2.gif" width="8" height="11" border="0"></a>　
          <a href="javascript: d.closeAll();">关闭所有 <img src="../imagesa/icon_2.gif" width="8" height="11" border="0"></a>
        </td>
      </tr>
      <tr>
        <td height="100%" valign="top"> 　

	<script type="text/javascript">
		<!--
		d = new dTree('d');
		d.config.useIcons =true;
		d.config.useStatusText =false;
		d.config.rightMenu=true;
		d.add('0','-1','功能菜单');
	
		 //  d.add("${rightcode}","${parentcode}","${rightname}","${linkurl}","","${openTarget}");
		    d.add("shouye","0","首页","../index/workspace.pl","","mainFrame");
		    d.add("forum","0","培训论坛","../forum/forumList.pl","","mainFrame");
		    d.add("forum","0","申请转所","../lawyers/officeChangeApplyList.pl","","mainFrame");
 <s:if test="lawyer.provinceunion!=22">
		   d.add("editself","0","个人资料维护","../lawyers/lawyersEditSelf!input.pl","","mainFrame");		
			</s:if>
		   d.add("online","0","在线课程");
		   d.add("online00","online","全部类型","../lessons/lessonsList.pl?lessonstyle=2&lessontype=0","","mainFrame");
		  <s:iterator value="@com.changpeng.lessons.util.CommonDatas@LessonType" status="stat">
		  	d.add("online${stat.index}","online","${value}","../lessons/lessonsList.pl?lessonstyle=2&lessontype=${key}","","mainFrame");
		  </s:iterator>
	
		  
		   d.add("local","0","现场课程");
		   d.add("local1","local","本地现场课程","../lessons/lessonsList.pl?lessonstyle=1","","mainFrame");
		   d.add("local2","local","外地现场课程","../lessons/lessonsList.pl?lessonstyle=100","","mainFrame");
		   
		    d.add("jifenquery","0","积分查询");
		    d.add("jifenquery1","jifenquery","积分查询","../jifen/jifenQuery.pl","","mainFrame");
		    d.add("jifenquery2","jifenquery","积分补登查询","../jifen/jifenbudengQuery.pl","","mainFrame");
		    
		    d.add("sys","0","系统管理");
		     d.add("helpList","sys","系统帮助","../articles/helpList.pl?type=2","","mainFrame");
		    d.add("notifyList","sys","重要通知","../articles/notifyList.pl?type=1","","mainFrame");
		 <s:if test="lawyer.provinceunion!=22">
		    d.add("passwd","sys","密码修改","../common/passwdChange!input.pl","","mainFrame");
		</s:if>
	
		document.write(d);


		d.openAll();
		//d.showRightmenu();
		function hello(){
		  alert(d);
		   document.getElementById("dddd").value=d;
		}
 	   //-->
	</script>
	<script   language="javascript">  
  <!--  
/*  var   menu   =   document.getElementById("menu");  
  
  document.oncontextmenu   =   function(e){  
  e   =   window.event   ||   e;  
 if(e.srcElement.id==null||e.srcElement.id.indexOf("sd")==-1)
   return false;
 
    
  x   =   e.x   ||   e.layerX;  
  y   =   e.y   ||   e.layerY;  
  menu.style.left   =   x   +   "px";  
  menu.style.top     =   y   +   "px";  
  menu.style.display   =   "block";  
  return   false;  
  }  
  document.onclick   =   function(){  
  menu.style.display   =   "none";  
  } */ 
  //-->  
  </script>  
   <!--
<input type="button" value="按我" onclick="hello()"/>
<textarea id="dddd" rows="4" cols="30"/>-->
        </td>
      </tr>
      
    </table></td>
  </tr>
</table>
</body>
</html>
