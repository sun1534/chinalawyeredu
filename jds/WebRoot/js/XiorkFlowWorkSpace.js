
/**
 * XiorkFlow工作空间，报考建立工作空间所需要的资源。
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */

//
/**
 * XiorkFlow的工作空间
 */
function XiorkFlowWorkSpace() {
}

//
XiorkFlowWorkSpace.BASE_PATH = "WebModule\workflow";

//
XiorkFlowWorkSpace.XIORK_FLOW_PATH = XiorkFlowWorkSpace.BASE_PATH + "";                                                                        

//
XiorkFlowWorkSpace.DEFAULT_PROCESS_NAME = "default";

//
XiorkFlowWorkSpace.URL_ADD_PROCESS = XiorkFlowWorkSpace.BASE_PATH + "addprocess.xf";
XiorkFlowWorkSpace.URL_DELETE_PROCESS = XiorkFlowWorkSpace.BASE_PATH + "deleteprocess.xf";
XiorkFlowWorkSpace.URL_GET_PROCESS = XiorkFlowWorkSpace.BASE_PATH + "getprocess.xf";
XiorkFlowWorkSpace.URL_LIST_PROCESS = XiorkFlowWorkSpace.BASE_PATH + "listprocess.xf";
XiorkFlowWorkSpace.URL_UPDATE_PROCESS = XiorkFlowWorkSpace.BASE_PATH + "updateprocess.xf";

//
XiorkFlowWorkSpace.STATUS_NULL = -1;
XiorkFlowWorkSpace.STATUS_SUCCESS = 0;
XiorkFlowWorkSpace.STATUS_FAIL = 1;
XiorkFlowWorkSpace.STATUS_FILE_EXIST = 3;
XiorkFlowWorkSpace.STATUS_FILE_NOT_FOUND = 5;
XiorkFlowWorkSpace.STATUS_XML_PARSER_ERROR = 7;
XiorkFlowWorkSpace.STATUS_IO_ERROR = 9;

//
XiorkFlowWorkSpace.ID = 1;

//
XiorkFlowWorkSpace.META_NODE_MOVED_STEP_TIME = 100;
XiorkFlowWorkSpace.META_NODE_MOVED_STEP = 1;

//
XiorkFlowWorkSpace.META_NODE_MAX = 20;

//
XiorkFlowWorkSpace.META_NODE_MIN_WIDTH = 80;
XiorkFlowWorkSpace.META_NODE_MIN_HEIGHT = 30;

//
/**
 * 建立工作空间
 */
XiorkFlowWorkSpace.build = function () {
 	//引入所需要的资源，资源加载顺序不能更改

	//name.xio.util
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/util/Message.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/util/Array.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/util/String.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/util/List.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/util/Observable.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/util/Observer.js");

	//name.xio.geom
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/geom/Point.js");

	//name.xio.html
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/html/Toolkit.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/html/Browser.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/html/Cursor.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/html/MouseEvent.js");

	//name.xio.xml
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xml/XMLDocument.js");

	//name.xio.ajax
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ajax/Ajax.js");

	//name.xio.ui.event
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/event/KeyListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/event/MouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/event/MouseWheelListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/event/ContextMenuListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/event/ListenerProxy.js");

	//name.xio.ui
    BuildLibrary.loadCSS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/ui.css");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/Dimension.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/Component.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/Button.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/ButtonModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/ToggleButton.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/ToggleButtonModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/ButtonGroup.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/ToolBar.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/Panel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/ScrollPanel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/Label.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/ui/Frame.js");

	//name.xio.geom.ui
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/geom/ui/GeometryCanvas.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/geom/ui/LineView.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/geom/ui/LineTextView.js");

    //name.xio.xiorkflow.meta.event
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/event/MetaNodeMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/event/MetaNodeTextMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/event/MetaNodeTextKeyListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/event/TransitionMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/event/MetaNodeResizeMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/event/TransitionTextMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/event/TransitionTextKeyListener.js");

    //name.xio.xiorkflow.meta
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/DragablePanel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/MetaModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/MetaNodeModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/MetaNode.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/StartNodeModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/StartNode.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/EndNodeModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/EndNode.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/NodeModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/Node.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/ForkNodeModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/ForkNode.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/JoinNodeModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/JoinNode.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/TransitionCompass.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/TransitionModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/meta/Transition.js");

    //name.xio.xiorkflow.event
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/WrapperMetaMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/WrapperSelectMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/MetaMoveMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/MetaMoveKeyListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/WrapperTransitionMouseListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/DeleteMetaActionListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/SaveActionListener.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/event/HelpActionListener.js");

    //name.xio.xiorkflow.process
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/process/AddProcess.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/process/GetProcess.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/process/UpdateProcess.js");

    //name.xio.xiorkflow
    BuildLibrary.loadCSS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/xiorkflow.css");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowToolBar.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/StateMonitor.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/TransitionMonitor.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowViewer.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowTableViewer.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowXMLViewer.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/StatusLabel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowModel.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowModelConverter.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlow.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowWrapper.js");
    BuildLibrary.loadJS(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "js/operation/xiorkflow/XiorkFlowViewPattern.js");
};

//
/**
 * 资源加载
 */
function BuildLibrary() {
}
BuildLibrary.loadJS = function (url, charset) {
    if (!charset) {
        charset = "UTF-8";
    }
    var charsetProperty = " charset=\"" + charset + "\" ";
    document.write("<script type=\"text/javascript\" src=\"" + url + "\" onerror=\"alert('Error loading ' + this.src);\"" + charsetProperty + "></script>");
};
BuildLibrary.loadCSS = function (url, charset) {
    if (!charset) {
        charset = "UTF-8";
    }
    var charsetProperty = " charset=\"" + charset + "\" ";
    document.write("<link href=\"" + url + "\" type=\"text/css\" rel=\"stylesheet\" onerror=\"alert('Error loading ' + this.src);\"" + charsetProperty + "/>");
};

