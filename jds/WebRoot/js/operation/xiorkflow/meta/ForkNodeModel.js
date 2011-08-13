
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function ForkNodeModel() {
    this.base = MetaNodeModel;
    this.base();

    //
    this.FROMS_MAX = MetaNodeModel.NUM_NOT_LIMIT;
    this.setText("ForkNode");

    //
    this.setSize(new Dimension(80, 30));
}
ForkNodeModel.prototype = new MetaNodeModel();

//
ForkNodeModel.prototype.toString = function () {
	//分支
    return "[\u5206\u652f:" + this.getText() + "]";
};

//
ForkNodeModel.prototype.type = MetaNodeModel.TYPE_FORK_NODE;

