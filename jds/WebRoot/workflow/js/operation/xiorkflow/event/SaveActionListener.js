
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function SaveActionListener(xiorkFlow) {
    this.xiorkFlow = xiorkFlow;
}
SaveActionListener.prototype.actionPerformed = function (obj) {
    var wrapper = this.xiorkFlow.getWrapper();
    var toolbar = this.xiorkFlow.getToolBar();
    var name = wrapper.getModel().getName();
    if (!name) {
    	//请输入您将工作流程图保存成的名字？
        name = prompt("\u8bf7\u8f93\u5165\u5de5\u4f5c\u6d41\u7a0b\u56fe\u7684\u540d\u5b57\uff1f", "");
        if (name != null && name != "") {
            var addProcess = new AddProcess(wrapper, toolbar, this.xiorkFlow.getProcessList());
            addProcess.addProcess(name);
        }
    } else {
        var updateProcess = new UpdateProcess(wrapper, toolbar);
        updateProcess.updateProcess();
    }
};

