
/**
 * <p>Title:JoinNode</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function JoinNode(model, wrapper) {
    this.base = MetaNode;
    var imageUrl = XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/join.gif";
    this.base(model, imageUrl, wrapper);
}
JoinNode.prototype = new MetaNode();

