
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function XiorkFlowToolBar(xiorkFlow) {
    this.base = ToolBar;
    this.base();

    //
    this.xiorkFlow = xiorkFlow;

    //
    this.addSeparator();

    //
    this.saveButton = new Button(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/save.gif", "\u4fdd\u5b58");
    //保存
    this.saveButton.setToolTipText("\u4fdd\u5b58");
    this.saveButton.addActionListener(new SaveActionListener(this.xiorkFlow));
    this.add(this.saveButton);

    //
    this.nodeButtonGroup = new ButtonGroup();

    //
    this.addSeparator();

    //
    this.selectButton = new ToggleButton(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/select.gif", "", true);
    //选择
    this.selectButton.setToolTipText("\u9009\u62e9");
    this.add(this.selectButton);
    this.nodeButtonGroup.add(this.selectButton);
    this.selectButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_SELECT;

    //
    this.addSeparator();

    //
    this.startButton = new ToggleButton(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/start.gif");
    //开始节点
    this.startButton.setToolTipText("\u5f00\u59cb\u8282\u70b9");
    this.add(this.startButton);
    this.nodeButtonGroup.add(this.startButton);
    this.startButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_START_NODE;

    //
    this.endButton = new ToggleButton(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/end.gif", "");
    //结束节点
    this.endButton.setToolTipText("\u7ed3\u675f\u8282\u70b9");
    this.add(this.endButton);
    this.nodeButtonGroup.add(this.endButton);
    this.endButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_END_NODE;

    //
    this.addSeparator();

    //
    this.nodeButton = new ToggleButton(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/node.gif");
    //任务节点
    this.nodeButton.setToolTipText("\u4efb\u52a1\u8282\u70b9");
    this.add(this.nodeButton);
    this.nodeButtonGroup.add(this.nodeButton);
    this.nodeButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_NODE;

    //
    this.forkButton = new ToggleButton(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/fork.gif");
    //分支节点
    this.forkButton.setToolTipText("\u5206\u652f\u8282\u70b9");
    this.add(this.forkButton);
    this.nodeButtonGroup.add(this.forkButton);
    this.forkButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_FORK_NODE;

    //
    this.joinButton = new ToggleButton(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/join.gif");
    //汇聚节点
    this.joinButton.setToolTipText("\u6c47\u805a\u8282\u70b9");
    this.add(this.joinButton);
    this.nodeButtonGroup.add(this.joinButton);
    this.joinButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_JOIN_NODE;

    //
    this.addSeparator();

    //
    this.transitionButton = new ToggleButton(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/transition.gif");
    //连接
    this.transitionButton.setToolTipText("\u8fde\u63a5");
    this.add(this.transitionButton);
    this.nodeButtonGroup.add(this.transitionButton);
    this.transitionButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_TRANSITION;

    //
    this.addSeparator();

    //
    this.deleteButton = new Button(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/delete.gif");
    //删除
    this.deleteButton.setToolTipText("\u5220\u9664");
    this.deleteButton.addActionListener(new DeleteMetaActionListener(this.xiorkFlow));
    this.add(this.deleteButton);

    //
    this.addSeparator();

    //
    this.viewerPatternButtonGroup = new ButtonGroup();

    //design
    var designButton = new ToggleButton("", "\u8bbe\u8ba1", true);
    //设计视图
    designButton.setToolTipText("\u8bbe\u8ba1\u89c6\u56fe");
    this.add(designButton);
    this.viewerPatternButtonGroup.add(designButton);
    designButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_DESIGN;

    //table
    var tableButton = new ToggleButton("", "\u8868\u683c", true);
    //表格视图
    tableButton.setToolTipText("\u8868\u683c\u89c6\u56fe");
    this.add(tableButton);
    this.viewerPatternButtonGroup.add(tableButton);
    tableButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_TABLE;

    //混合显示
    var mixButton = new ToggleButton("", "\u6df7\u5408\u663e\u793a", true);
    //混合视图
    mixButton.setToolTipText("\u6df7\u5408\u89c6\u56fe");
    this.add(mixButton);
    this.viewerPatternButtonGroup.add(mixButton);
    mixButton.getModel().name = XiorkFlowToolBar.BUTTON_NAME_MIX;

    //
    this.addSeparator();

    //
    var helpButton = new Button(XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/help.gif", "\u5e2e\u52a9");
    helpButton.addActionListener(new HelpActionListener());
    //帮助
    helpButton.setToolTipText("\u5e2e\u52a9");
    this.add(helpButton);
}
XiorkFlowToolBar.prototype = new ToolBar();
XiorkFlowToolBar.prototype.getNodeButtonGroup = function () {
    return this.nodeButtonGroup;
};
XiorkFlowToolBar.prototype.setDesignButtonEnable = function (b) {
    var buttons = this.nodeButtonGroup.getButtons();
    for (var i = 0; i < buttons.size(); i++) {
        buttons.get(i).getModel().setEnabled(b);
    }
    this.deleteButton.getModel().setEnabled(b);
};
XiorkFlowToolBar.prototype.setButtonEnable = function (b) {
    var buttons = this.nodeButtonGroup.getButtons();
    for (var i = 0; i < buttons.size(); i++) {
        buttons.get(i).getModel().setEnabled(b);
    }
    var viewPatternbuttons = this.viewerPatternButtonGroup.getButtons();
    for (var i = 0; i < viewPatternbuttons.size(); i++) {
        viewPatternbuttons.get(i).getModel().setEnabled(b);
    }
    this.deleteButton.getModel().setEnabled(b);
    this.saveButton.getModel().setEnabled(b);
};
XiorkFlowToolBar.prototype.getViewerPatternButtonGroup = function () {
    return this.viewerPatternButtonGroup;
};

//
XiorkFlowToolBar.prototype.update = function (observable, arg) {
    if (arg instanceof Array) {
        if (arg.size() == 2) {
            var property = arg[0];
            var state = arg[1];
            if (property == StateMonitor.OPERATION_STATE_RESET) {
                switch (state) {
                  case StateMonitor.SELECT:
                    this.selectButton.getModel().setPressed(true);
                    break;
                  case StateMonitor.NODE:
                    this.nodeButton.getModel().setPressed(true);
                    break;
                  case StateMonitor.FORK_NODE:
                    this.forkNodeButton.getModel().setPressed(true);
                    break;
                  case StateMonitor.JOIN_NODE:
                    this.joinNode.getModel().setPressed(true);
                    break;
                  case StateMonitor.START_NODE:
                    this.startNodeButton.getModel().setPressed(true);
                    break;
                  case StateMonitor.END_NODE:
                    this.endNodeButton.getModel().setPressed(true);
                    break;
                  case StateMonitor.TRANSITION:
                    this.transitionButton.getModel().setPressed(true);
                    break;
                  default:
                    break;
                }
            }
        }
    }
};

//
XiorkFlowToolBar.BUTTON_NAME_SELECT = "BUTTON_NAME_SELECT";
XiorkFlowToolBar.BUTTON_NAME_START_NODE = "BUTTON_NAME_START_NODE";
XiorkFlowToolBar.BUTTON_NAME_END_NODE = "BUTTON_NAME_END_NODE";
XiorkFlowToolBar.BUTTON_NAME_FORK_NODE = "BUTTON_NAME_FORK_NODE";
XiorkFlowToolBar.BUTTON_NAME_JOIN_NODE = "BUTTON_NAME_JOIN_NODE";
XiorkFlowToolBar.BUTTON_NAME_NODE = "BUTTON_NAME_NODE";
XiorkFlowToolBar.BUTTON_NAME_TRANSITION = "BUTTON_NAME_TRANSITION";

//
XiorkFlowToolBar.BUTTON_NAME_DESIGN = "BUTTON_NAME_DESIGN";
XiorkFlowToolBar.BUTTON_NAME_TABLE = "BUTTON_NAME_TABLE";
XiorkFlowToolBar.BUTTON_NAME_MIX = "BUTTON_NAME_MIX";

