
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function Panel(ui) {
    this.base = Component;
    if (ui) {
        this.base(ui);
    } else {
        this.base(Toolkit.newLayer());
    }
}
Panel.prototype = new Component();
Panel.prototype.toString = function () {
    return "[Component,Panel]";
};

