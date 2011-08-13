
/**
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) xio.name 2006</p>
 * @author xio
 */
function XMLDocument() {
}
XMLDocument.newDomcument = function () {
    if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLDOM");
    } else {
        //alert("Your browser cannot handle this script");
    }
    //TODO firefox ...
};

