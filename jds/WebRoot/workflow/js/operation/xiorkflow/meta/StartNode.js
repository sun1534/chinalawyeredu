
/**
 * <p>Title: StartNode</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function StartNode(model, wrapper) {
    this.base = MetaNode;
    var imageUrl = XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/start.gif";
    this.base(model, imageUrl, wrapper);
}
StartNode.prototype = new MetaNode();

