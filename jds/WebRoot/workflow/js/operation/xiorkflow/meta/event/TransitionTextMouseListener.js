
//
/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function TransitionTextMouseListener(transition, wrapper) {
    this.transition = transition;
    this.wrapper = wrapper;
}
TransitionTextMouseListener.prototype = new MouseListener();
TransitionTextMouseListener.prototype.onDblClick = function (e) {
    var state = this.wrapper.getStateMonitor().getState();
    if (state != StateMonitor.SELECT) {
        return;
    }
    this.wrapper.getModel().clearSelectedMetaNodeModels();
    this.transition.startEdit();
};

