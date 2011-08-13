package com.sxit.workflow.util;

import java.util.ArrayList;
import java.util.List;

public class NodeDoType {
  private int typeid;
  private String typename;

  private static List<NodeDoType> typelist = new ArrayList<NodeDoType> ();

  static {
    // 1 单人 2 循环 3 多人串行 4 多人并行
    typelist.add(new NodeDoType(1, "单人"));
    typelist.add(new NodeDoType(2, "循环"));
    typelist.add(new NodeDoType(3, "多人串行"));
    typelist.add(new NodeDoType(4, "多人并行"));
  }

  public NodeDoType() {

  }

  public NodeDoType(int typeid, String typename) {
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
