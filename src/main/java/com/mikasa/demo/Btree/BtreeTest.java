package com.mikasa.demo.Btree;

/**
 * Created by root2 on 16/8/22.
 */
public class BtreeTest {

    /**
                 1
                / \
               2   3
              / \   \
             4  5   6
     */
    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);

        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.right = r6;

//      System.out.println(getNodeNumRec(r1));
//      System.out.println(getNodeNum(r1));
//      System.out.println(getDepthRec(r1));
//      System.out.println(getDepth(r1));

//      preorderTraversalRec(r1);
//      System.out.println();
//      preorderTraversal(r1);
//      System.out.println();
//      inorderTraversalRec(r1);
//      System.out.println();
//      inorderTraversal(r1);
//      System.out.println();
//      postorderTraversalRec(r1);
//      System.out.println();
//      postorderTraversal(r1);
//      System.out.println();
//      levelTraversal(r1);
//      System.out.println();
//      levelTraversalRec(r1);
//      System.out.println();

//      TreeNode tmp = convertBSTRec(r1);
//      while(true){
//          if(tmp == null){
//              break;
//          }
//          System.out.print(tmp.val + " ");
//          if(tmp.right == null){
//              break;
//          }
//          tmp = tmp.right;
//      }
//      System.out.println();
//      while(true){
//          if(tmp == null){
//              break;
//          }
//          System.out.print(tmp.val + " ");
//          if(tmp.left == null){
//              break;
//          }
//          tmp = tmp.left;
//      }


//      TreeNode tmp = convertBST2DLL(r1);
//      while(true){
//          if(tmp == null){
//              break;
//          }
//          System.out.print(tmp.val + " ");
//          if(tmp.right == null){
//              break;
//          }
//          tmp = tmp.right;
//      }

//      System.out.println(getNodeNumKthLevelRec(r1, 2));
//      System.out.println(getNodeNumKthLevel(r1, 2));

//      System.out.println(getNodeNumLeafRec(r1));
//      System.out.println(getNodeNumLeaf(r1));

//      System.out.println(isSame(r1, r1));
//      inorderTraversal(r1);
//      System.out.println();
//      mirror(r1);
//      TreeNode mirrorRoot = mirrorCopy(r1);
//      inorderTraversal(mirrorRoot);

        System.out.println(BinaryTreeUtil.isCompleteBinaryTree(r1));
        System.out.println(BinaryTreeUtil.isCompleteBinaryTreeRec(r1));

    }
}
