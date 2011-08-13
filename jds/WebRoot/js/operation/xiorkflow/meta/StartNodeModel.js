
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function StartNodeModel() {
    this.base = MetaNodeModel;
    this.base();

    //
    this.TOS_MAX = 0;
    this.setText("StartNode");

    //
    this.setSize(new Dimension(80, 30));
}
StartNodeModel.prototype = new MetaNodeModel();

//
StartNodeModel.prototype.toString = function () {
	//开始
    return "[\u5f00\u59cb:" + this.getText() + "]";
};

//
StartNodeModel.prototype.type = MetaNodeModel.TYPE_START_NODE;

