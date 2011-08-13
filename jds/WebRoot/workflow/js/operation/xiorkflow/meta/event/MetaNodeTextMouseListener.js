
//
/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function MetaNodeTextMouseListener(metaNode, wrapper) {
    this.metaNode = metaNode;
    this.wrapper = wrapper;
}
MetaNodeTextMouseListener.prototype = new MouseListener();
MetaNodeTextMouseListener.prototype.onDblClick = function (e) {
    var state = this.wrapper.getStateMonitor().getState();
    if (state != StateMonitor.SELECT) {
        return;
    }
    this.wrapper.getModel().clearSelectedMetaNodeModels();
    this.metaNode.startEdit();
//alert(this.metaNode.getModel().getText())


};

