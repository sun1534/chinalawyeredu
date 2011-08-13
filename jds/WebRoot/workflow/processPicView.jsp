<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看流程</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
**/
%> 

<HTML  xmlns:v="urn:schemas-microsoft-com:vml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
    <script charset="UTF-8" src="js/XiorkFlowWorkSpace.js" language="javascript"></script>  
    <script type="text/javascript" language="javascript">  
//load all js   
XiorkFlowWorkSpace.build();   
  
function init(){   
  
  //create ur workflow container   
  var container = new ScrollPanel(document.getElementById("xiorkflowViewer"));   
  container.setClassName("NAME_XIO_UI_FONT NAME_XIO_XIORKFLOW_VIEWER");   
  
  //create XiorkFlow   
  var model = new XiorkFlowModel();   
  // model.setEditable(false);//important   
  	model.setEditable(true);//important   
	var statem=new StateMonitor();
	var state= new StatusLabel();
  var xiorkFlowWrapper = new XiorkFlowWrapper(container, model,statem,state);   
  
<s:iterator value="process.twflNodes" status="status">
<s:if test="nodetype==1">
var newNodeModel${nodeid} = new StartNodeModel();
    newNodeModel${nodeid}.setText("${nodename}");  
    newNodeModel${nodeid}.setPosition(new Point(${xcoordinate},${ycoordinate}));   
    model.addMetaNodeModel(newNodeModel${nodeid});   
</s:if>
<s:if test="nodetype==2">
var newNodeModel${nodeid} = new NodeModel(); 
    newNodeModel${nodeid}.setText("${nodename}");  
    newNodeModel${nodeid}.setPosition(new Point(${xcoordinate},${ycoordinate}));   
    model.addMetaNodeModel(newNodeModel${nodeid});   
</s:if>
<s:if test="nodetype==3">
var newNodeModel${nodeid} = new ForkNodeModel(); 
    newNodeModel${nodeid}.setText("${nodename}");  
    newNodeModel${nodeid}.setPosition(new Point(${xcoordinate},${ycoordinate}));   
    model.addMetaNodeModel(newNodeModel${nodeid});   
</s:if>
<s:if test="nodetype==4">
var newNodeModel${nodeid} = new JoinNodeModel(); 
    newNodeModel${nodeid}.setText("${nodename}");  
    newNodeModel${nodeid}.setPosition(new Point(${xcoordinate},${ycoordinate}));   
    model.addMetaNodeModel(newNodeModel${nodeid});   
</s:if>
<s:if test="nodetype==5">
var newNodeModel${nodeid} = new EndNodeModel(); 
    newNodeModel${nodeid}.setText("${nodename}");  
    newNodeModel${nodeid}.setPosition(new Point(${xcoordinate},${ycoordinate}));   
    model.addMetaNodeModel(newNodeModel${nodeid});   
</s:if>
</s:iterator>

<s:iterator value="process.twflNodes" status="status">
 <s:iterator value="toNode" >
  var transitionModel${directionid} = new TransitionModel(newNodeModel${nodeid}, newNodeModel${toNode.nodeid});  
  model.addTransitionModel(transitionModel${directionid});  
 </s:iterator>
</s:iterator>
}   
    </script>  
</HEAD>
  <body onload="init()">  
    <div id="xiorkflowViewer" style="width: 100%; height: 100%; border: #e0e0e0 1px solid;"></div>  
    <div id="message"></div>
  </body>  
</html>  

