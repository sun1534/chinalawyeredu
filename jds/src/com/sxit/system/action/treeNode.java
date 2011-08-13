package com.sxit.system.action;

import java.util.Set;
import java.util.HashSet;


/**
 *
 * @author zrb
 */
public class treeNode {
              private String id;
              private String name;
              private boolean leaf;
              private Set <treeNode> children;
              public String getId() {
                      return id;
              }
              public void setId(String id) {
                      this.id = id;
              }
              public boolean isLeaf() {
                      return leaf;
              }
              public void setLeaf(boolean leaf) {
                      this.leaf = leaf;
              }
              public String getName() {
                      return name;
              }
              public void setName(String name) {
                      this.name = name;
              }
              public Set getChildren() {
                      return children;
              }
              public void setChildren(Set children) {
                      this.children = children;
              }
}
