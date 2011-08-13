<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
    <!-- Include Ext and app-specific scripts: -->
    <script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="../ext2.0/ext-all-debug.js"></script>
    <script type="text/javascript" src="left.js"></script>
<script language=javascript src=tree_maker.js></script>
<script language=javascript>
document.write('<style type=text/css>');
document.write('TD.Tree_FOLDER_1{height:0pt;padding:0pt 5pt 0pt 2pt;}');
document.write('TD.Tree_FOLDER_2{height:0pt;padding:0pt 5pt 0pt 2pt;}');
document.write('TD.Tree_FOLDER_3{height:0pt;padding:0pt 5pt 0pt 2pt;}');
document.write('TD.Tree_FILE_1{height:0pt;padding:0pt 5pt 0pt 2pt;}');
document.write('TD.Tree_FILE_2{height:0pt;padding:0pt 5pt 0pt 2pt;}');
document.write('TD.Tree_FILE_3{height:0pt;padding:0pt 5pt 0pt 2pt;}');
document.write('TD {FONT-SIZE: 12px}');
document.write('P {FONT-SIZE: 12px}');
document.write('A:hover {COLOR: #ff0000}');
document.write('A.nav:link {COLOR: #000000; TEXT-DECORATION: none}');
document.write('A.nav:visited {COLOR: #000000; TEXT-DECORATION: none}');
document.write('A.nav:active {COLOR: #ff0000; TEXT-DECORATION: none}');
document.write('A.nav:hover {	COLOR: #ff0000; TEXT-DECORATION: none}');
document.write('</style>');

var tree=new Tree_treeView();
tree.useImage=true;
tree.useTitleAsHint=true;
tree.useTitleAsStatus=true;
tree.useHint=true;
tree.useStatus=true;
tree.showSelect=true;
tree.showLine=true;
tree.Indent=13;
//tree.folderImg1="img/clsfld.png";
tree.folderImg1="img/close.gif";
tree.lineFolder="img/";
//tree.folderImg2="img/openfld.png";
tree.folderImg2="img/close.gif";
//tree.fileImg="img/file.png";
tree.fileImg="img/open.gif";
tree.target="main";
tree.folderClass1="Tree_FOLDER_1";
tree.folderClass2="Tree_FOLDER_2";
tree.folderClass3="Tree_FOLDER_3";
tree.fileClass1="Tree_FILE_1";
tree.fileClass2="Tree_FILE_2";
tree.fileClass3="Tree_FILE_3";
</script>
</head>

<body>
<table height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" background="images_2/left_bg.gif">
     <table height="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22"><img src="images_2/left1.gif" width="168" height="22"></td>
      </tr>
      <tr>
        <td height="30"><table width="127" border="0" align="center" cellpadding="1" cellspacing="1">
          <tr>
            <td width="14" align="center"><img src="images_2/ico1.gif" width="16" height="14"></td>
            <td width="88" height="25" valign="middle"><a href="../wait/listWait1.action" target="main">待批文档</a>:<span id="num"></span>件</td>          </tr>
        
        </table></td>
      </tr>
      <tr>
        <td height="22"><img src="images_2/left2.gif" width="168" height="22"></td>
      </tr>
      <tr>
        <td valign="top">
<div id="tree1" align="left" style="width:100%;height:100%;overflow-x:hidden;overflow-y:auto">
<script language=javascript>
var tree_node=new Array(${treeoption});
if(tree_node=="")
{
 tree_node=new Array(0,"您尚未登录","","","","",4,"","url","",5);
}
Tree_buildTree(tree_node,tree);
var node=tree.getNode(1);
node.expand(true);
//tree.expandAll();

</script>
</div>        </td>
      </tr>
    </table>    </td>
    <td width="6" background="images_2/left_line.gif">&nbsp;</td>
  </tr>
</table>
</body>
</html>
