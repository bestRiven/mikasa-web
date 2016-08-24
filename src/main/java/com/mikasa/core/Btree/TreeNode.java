package com.mikasa.core.Btree;

/**
 * Created by root2 on 16/8/22.
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(){}

    public TreeNode(int val) {
        this.val = val;
        this.left=null;
        this.right=null;
    }
}
