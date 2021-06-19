package com.ibegu.dalaoadmin.utils;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Massive on 2016/12/26.
 */
public class TreeBuilder {

    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<TreeNode> bulid(List<TreeNode> treeNodes) {

        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (TreeNode treeNode : treeNodes) {

            if ("10".equals(treeNode.getParentId().toString())) {
                trees.add(treeNode);
            }

            for (TreeNode it : treeNodes) {
                if (it.getParentId() == treeNode.getBtId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<TreeNode> buildByRecursive(List<TreeNode> treeNodes) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodes) {
            if ("10".equals(treeNode.getParentId().toString())) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static TreeNode findChildren(TreeNode treeNode,List<TreeNode> treeNodes) {
        for (TreeNode it : treeNodes) {
            if(treeNode.getBtId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                //是否还有子节点，如果有的话继续往下遍历，如果没有则直接返回
                treeNode.getChildren().add(findChildren(it,treeNodes));

            }
        }
        return treeNode;
    }



    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(Long.valueOf(1),"广州",Long.valueOf(10));
        TreeNode treeNode2 = new TreeNode(Long.valueOf(2),"深圳",Long.valueOf(10));

        TreeNode treeNode3 = new TreeNode(Long.valueOf(3),"天河区",treeNode1);
        TreeNode treeNode4 = new TreeNode(Long.valueOf(4),"越秀区",treeNode1);
        TreeNode treeNode5 = new TreeNode(Long.valueOf(5),"黄埔区",treeNode1);
        TreeNode treeNode6 = new TreeNode(Long.valueOf(6),"石牌",treeNode3);
        TreeNode treeNode7 = new TreeNode(Long.valueOf(7),"百脑汇",treeNode6);


        TreeNode treeNode8 = new TreeNode(Long.valueOf(8),"南山区",treeNode2);
        TreeNode treeNode9 = new TreeNode(Long.valueOf(9),"宝安区",treeNode2);



        List<TreeNode> list = new ArrayList<TreeNode>();

        list.add(treeNode1);
        list.add(treeNode2);
        list.add(treeNode3);
        list.add(treeNode4);
        list.add(treeNode5);
        list.add(treeNode6);
        list.add(treeNode7);
        list.add(treeNode8);
        list.add(treeNode9);

        //第一种方式
        List<TreeNode> trees1 = TreeBuilder.bulid(list);
        System.out.println(trees1);

        //第二种方式
        List<TreeNode> trees2 = TreeBuilder.buildByRecursive(list);
        System.out.println(trees2);

    }

}

