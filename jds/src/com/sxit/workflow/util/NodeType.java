package com.sxit.workflow.util;


import java.util.ArrayList;
import java.util.List;

public class NodeType {
  private int typeid;
  private String typename;

  private static List<NodeType> typelist = new ArrayList<NodeType> ();

  static {
    typelist.add(new NodeType(1, "开始节点"));
    typelist.add(new NodeType(2, "普通节点"));
    typelist.add(new NodeType(3, "分支节点"));
    typelist.add(new NodeType(4, "汇聚节点"));
    typelist.add(new NodeType(5, "结束节点"));
  }

  public NodeType() {

  }

  public NodeType(int typeid, String typename) {
    this.typeid = typeid;
    this.typename = typename;
  }

  public static List getTypelist() {
    return typelist;
  }
  public int getTypeid () {
   return typeid;
  }
  public String getTypename () {
   return typename;
  }
}
