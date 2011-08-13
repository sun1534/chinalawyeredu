
/**
 * <p>Title: ForkNode</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function ForkNode(model, wrapper) {
    this.base = MetaNode;
    var imageUrl = XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/fork.gif";
    this.base(model, imageUrl, wrapper);
}
ForkNode.prototype = new MetaNode();

