package com.sxit.system.model;

import java.util.ArrayList;
import java.util.List;

public class DepartmentType {
  private int typeid;
  private String typename;

  private static List<DepartmentType> typelist = new ArrayList<DepartmentType> ();

  static {
    typelist.add(new DepartmentType(0, "公司"));
    typelist.add(new DepartmentType(1, "一级部门"));
    typelist.add(new DepartmentType(2, "二级部门"));
    typelist.add(new DepartmentType(3, "三级部门"));
  }

  public DepartmentType() {

  }

  public DepartmentType(int typeid, String typename) {
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
