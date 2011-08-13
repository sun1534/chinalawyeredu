<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
/**
 * <p>功能： 查看department列表</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-09-24</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<s:head theme="ajax" debug="true" />
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script>
     //加div以及在节点写object="href:http://...".
     dojo.require("dojo.widget.TreeLinkExtension");
			function expandTreeNode() {
					var tree = dojo.widget.byId("departmentTree");
			
					if (tree == null ) {
						return;
					}
			
					var expandedTreeNodes = "";
					for(var i=0; i < tree.children.length; i++) {
						var child = tree.children[i];
						dojo.lang.forEach(child.getDescendants(), function(node) {
							node.expand();
						});
					}
				}
            function treeNodeSelected(arg) {
             var target="depmain";
             var actionurl="departmentTreeView.action?departmentid="+arg.source.widgetId;
             window.open(actionurl,target);
             arg.source.expand();
            // alert(arg.source.widgetId +arg.source.title + 'selected');
            }
            dojo.addOnLoad(function() {                
                var s = dojo.widget.byId('departmentTree').selector;                
                dojo.event.connect(s, 'select', 'treeNodeSelected');
            });
</script>
</HEAD>
<BODY onLoad="expandTreeNode()" >
					<s:tree id="departmentTree"
						theme="ajax"
						rootNode="%{treeRootNode}" 
						childCollectionProperty="children" 
						nodeIdProperty="departmentid"
						nodeTitleProperty="departmentname"
						treeSelectedTopic="treeSelected">
					 </s:tree>
</BODY>
</HTML>

