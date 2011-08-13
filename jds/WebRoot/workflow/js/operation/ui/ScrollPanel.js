
/**
 * <p>Title: ScrollPanel</p>
 * <p>Description: 卷轴面板</p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function ScrollPanel(ui) {
    this.base = Panel;
    this.base(ui);
    this.setClassName("NAME_XIO_UI_FONT NAME_XIO_UI_SCROLL_PANEL");
}
ScrollPanel.prototype = new Panel();
ScrollPanel.prototype.toString = function () {
    return "[Component,Panel,ScrollPanel]";
};

