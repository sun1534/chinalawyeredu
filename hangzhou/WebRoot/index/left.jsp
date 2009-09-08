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
		<s:iterator value="userMenus" status="stat">
		    d.add("${rightcode}","${parentcode}","${rightname}","${linkurl}","","${opentarget}");
		</s:iterator>
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
