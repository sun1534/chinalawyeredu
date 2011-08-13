
/**
 * <p>Title:  </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function StatusLabel(ui) {
    this.base = Label;
    this.base(ui);
    this.setClassName("NAME_XIO_UI_FONT NAME_XIO_UI_LABEL NAME_XIO_XIORKFLOW_STATUS_LABEL");
}
StatusLabel.prototype = new Label();
StatusLabel.prototype.toString = function () {
    return "[Component,Panel,Label,StatusLabel]";
};

//
StatusLabel.prototype.setText = function (text) {
    this.getUI().innerText = text;
};

