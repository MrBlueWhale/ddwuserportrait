package com.ibegu.dalaoadmin.utils;


import java.util.List;

/**
 * Created by Massive on 2016/12/26.
 */
public class TreeNode {

    private Long btId;

    private Long parentId;

    private String name;

    private List<TreeNode> children;

    public TreeNode(Long btId, String name, Long parentId) {
        this.btId = btId;
        this.parentId = parentId;
        this.name = name;
    }
    public TreeNode(Long btId, String name, TreeNode parent) {
        this.btId = btId;
        this.parentId = parent.getBtId();
        this.name = name;
    }

    public Long getBtId() {
        return btId;
    }

    public void setBtId(Long btId) {
        this.btId = btId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + btId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

}
