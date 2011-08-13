
/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function TransitionMonitor() {
    this.reset();
}

//
TransitionMonitor.prototype.reset = function () {
    this.setFromMetaNodeModel(null);
    this.setToMetaNodeModel(null);
};

//
TransitionMonitor.prototype.setFromMetaNodeModel = function (metaNodeModel) {
    this.fromMetaNodeModel = metaNodeModel;
};
TransitionMonitor.prototype.getFromMetaNodeModel = function () {
    return this.fromMetaNodeModel;
};

//
TransitionMonitor.prototype.setToMetaNodeModel = function (metaNodeModel) {
    this.toMetaNodeModel = metaNodeModel;
};
TransitionMonitor.prototype.getToMetaNodeModel = function () {
    return this.toMetaNodeModel;
};

//
TransitionMonitor.prototype.isEnable = function () {
    return (this.fromMetaNodeModel && this.toMetaNodeModel);
};

