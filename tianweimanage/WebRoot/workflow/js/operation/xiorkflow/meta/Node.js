
/**
 * <p>Title: Node</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function Node(model, wrapper) {
    this.base = MetaNode;
    var imageUrl = XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/node.gif";
    this.base(model, imageUrl, wrapper);
}
Node.prototype = new MetaNode();

