package com.mikasa.core.Btree;

/**
 * Created by root2 on 16/8/22.
 */
public class BinaryTreeNode {

    public int value;
    public BinaryTreeNode leftNode;
    public BinaryTreeNode rightNode;

    public BinaryTreeNode(){

    }
    public BinaryTreeNode(int value){
        this.value = value ;
        this.leftNode = null;
        this.rightNode = null;
    }
}
