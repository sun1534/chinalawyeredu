package com.uu800.webbase.util;
import java.util.*;

public class TreeInfo 
{
    private String nodeId = "-1";
    private String parentId = "-1";
    private String nodeName = null;
    private String url = null;
    private String target = null;

    public TreeInfo (){
    	
    }
    
    public TreeInfo (int nodeid,int parentid,String nodename,String url,String target){
    	this.nodeId= String.valueOf(nodeid);
    	this.parentId= String.valueOf(parentid);
    	this.nodeName = nodename;
    	this.url=url;
    	this.target=target;    	
    }
    
    public TreeInfo (String nodeid,String parentid,String nodename,String url,String target){
    	this.nodeId= nodeid;
    	this.parentId= parentid;
    	this.nodeName = nodename;
    	this.url=url;
    	this.target=target;    	
    }

    public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    /** *//**
     * @生成创建tree的脚本
     * @param treeInfoList
     * @return
     */
    public static String createTreeInfo(List<TreeInfo> treeInfoList) {
        StringBuffer contents = new StringBuffer();
        contents.append("<script type='text/javascript'>\n");
        contents.append("<!--\n");      
        
        contents.append("d = new dTree('d');\n");
      //  contents.append("d.add(0,-1,'ROOT');\n");        

        TreeInfo treeInfo = null;
        for (int i = 0; i < treeInfoList.size(); i++) {
            treeInfo = treeInfoList.get(i);
            contents.append(createOneItem(treeInfo));
        }

        contents.append("document.write(d);\n");
        //contents.append("d.openAll();\n");      
        
        contents.append("-->\n");
        contents.append("\n</script>");
        return contents.toString();
    }

    /** *//**
     * @生成创建一个条目的脚本
     * @param treeInfo
     * @return
     */
    public static String createOneItem(TreeInfo treeInfo) {
        StringBuffer item = new StringBuffer();
        item.append("d.add(");
        // 第一个参数，表示当前节点的ID
        item.append(treeInfo.getNodeId() + ",");
        // 第二个参数，表示当前节点的父节点的ID,根节点的值为 -1
        item.append(treeInfo.getParentId() + ",");
        // 第三个参数，节点要显示的文字
        item.append("'" + treeInfo.getNodeName() + "',");
        // 第四个参数，节点的Url
        item.append("'" + treeInfo.getUrl() + "',");
        // 第五个参数，鼠标移至该节点时节点的Title
        item.append("'" + "',");
        // 第六个参数，节点的target
        item.append("'"+treeInfo.target+"',");
        // 第七个参数，用做节点的图标,节点没有指定图标时使用默认值
        item.append("'',");
        // 第八个参数，用做节点打开的图标,节点没有指定图标时使用默认值
        item.append("'',");
        // 第九个参数，判断节点是否打开
        item.append(false);

        item.append(");\n");
        return item.toString();
    }

}
