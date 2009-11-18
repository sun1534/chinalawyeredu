
/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function UpdateProcess(wrapper, toolbar) {
    this.base = Ajax;
    this.base();
    this.wrapper = wrapper;
    this.toolbar = toolbar;
}
UpdateProcess.prototype = new Ajax();
UpdateProcess.prototype.setButtonEnable = function (b) {
    if (this.toolbar) {
        this.toolbar.setButtonEnable(b);
    }
};
UpdateProcess.prototype.updateProcess = function () {
    var model = this.wrapper.getModel();
    var name = model.getName();
    if (!name) {
        this.name = null;
    	//工作流程图名字为空！
        alert("\u5de5\u4f5c\u6d41\u7a0b\u56fe\u540d\u5b57\u4e3a\u7a7a\uff01");
        return false;
    }

    //
    this.setButtonEnable(false);

    //
    var doc = XiorkFlowModelConverter.convertModelToXML(model);
    if (!doc) {
    	//将工作流程图转化成xml时出错！
        alert("\u5c06\u5de5\u4f5c\u6d41\u7a0b\u56fe\u8f6c\u5316\u6210xml\u65f6\u51fa\u9519\uff01");
        this.setButtonEnable(true);
        return false;
    }

    //
    var url = XiorkFlowWorkSpace.URL_UPDATE_PROCESS;
    var method = "POST";
    var params = "name=" + name;
    params += "&xml=" + doc.xml;

    //
    this.loadXMLHttpRequest(url, method, params);
};
UpdateProcess.prototype.onReadyStateChange = function (httpRequest) {
    if (httpRequest.readyState == 4) {
        if (httpRequest.status == 200) {
            this.processXMLHttpRequest(httpRequest);
        } else {
        	//处理过程出现错误！
            alert("\u5904\u7406\u8fc7\u7a0b\u51fa\u73b0\u9519\u8bef\uff01");
            this.setButtonEnable(true);
        }
    }
};
UpdateProcess.prototype.processXMLHttpRequest = function (httpRequest) {
    var doc = httpRequest.responseXML;
    if (!doc) {
    	//操作结束，未知服务器处理结果！
        alert("\u64cd\u4f5c\u7ed3\u675f\uff0c\u672a\u77e5\u670d\u52a1\u5668\u5904\u7406\u7ed3\u679c\uff01");
        this.setButtonEnable(true);
        return false;
    }

    //
    var responseNode = doc.getElementsByTagName("Response")[0];
    var statusValue = eval(responseNode.getAttribute("status"));
    var alertStr = "";
    switch (statusValue) {
      case XiorkFlowWorkSpace.STATUS_SUCCESS:
      	//更新成功。
        alertStr = "\u66f4\u65b0\u6210\u529f\u3002";
        this.wrapper.getModel().setName(this.name);
        this.wrapper.setChanged(false);
        break;
      case XiorkFlowWorkSpace.STATUS_FAIL:
      	//更新失败。
        alertStr = "\u66f4\u65b0\u5931\u8d25\u3002";
        break;
      case XiorkFlowWorkSpace.STATUS_XML_PARSER_ERROR:
      	//更新失败，xml解析出错。
        alertStr = "\u66f4\u65b0\u5931\u8d25\uff0cxml\u89e3\u6790\u51fa\u9519\u3002";
        break;
      case XiorkFlowWorkSpace.STATUS_FILE_NOT_FOUND:
      	//更新失败，文件未找到。系统自动转成添加模式。
        document.title = "\u589e\u52a0";
        alertStr = "\u66f4\u65b0\u5931\u8d25\uff0c\u6587\u4ef6\u672a\u627e\u5230\u3002\u7cfb\u7edf\u81ea\u52a8\u8f6c\u6210\u6dfb\u52a0\u6a21\u5f0f\u3002";
        break;
      case XiorkFlowWorkSpace.STATUS_IO_ERROR:
      	//更新失败，IO错误。
        alertStr = "\u66f4\u65b0\u5931\u8d25\uff0cIO\u9519\u8bef\u3002";
        break;
      default:
        //更新失败，未知错误。
        alertStr = "\u66f4\u65b0\u5931\u8d25\uff0c\u672a\u77e5\u9519\u8bef\u3002";
        break;
    }
    this.setButtonEnable(true);
    alert(alertStr);
};

