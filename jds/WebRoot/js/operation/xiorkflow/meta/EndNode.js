
/**
 * <p>Title: EndNode</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function EndNode(model, wrapper) {
    this.base = MetaNode;
    var imageUrl = XiorkFlowWorkSpace.XIORK_FLOW_PATH + "images/xiorkflow/end.gif";
    this.base(model, imageUrl, wrapper);
}
EndNode.prototype = new MetaNode();

