
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function EndNodeModel() {
    this.base = MetaNodeModel;
    this.base();

    //
    this.FROMS_MAX = 0;
    this.setText("EndNode");

    //
    this.setSize(new Dimension(80, 30));
}
EndNodeModel.prototype = new MetaNodeModel();

//
EndNodeModel.prototype.toString = function () {
	//结束
    return "[\u7ed3\u675f:" + this.getText() + "]";
};

//
EndNodeModel.prototype.type = MetaNodeModel.TYPE_END_NODE;

