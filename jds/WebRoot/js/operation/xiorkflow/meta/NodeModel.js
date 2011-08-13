
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function NodeModel() {
    this.base = MetaNodeModel;
    this.base();

    //
    this.setText("Node");

    //
    this.setSize(new Dimension(80, 30));
}
NodeModel.prototype = new MetaNodeModel();

//
NodeModel.prototype.toString = function () {
	//节点
    return "[\u8282\u70b9:" + this.getText() + "]";
};

//
NodeModel.prototype.type = MetaNodeModel.TYPE_NODE;

