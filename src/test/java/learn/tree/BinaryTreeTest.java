package learn.tree;

import org.junit.Test;

import java.util.LinkedList;

public class BinaryTreeTest {

    @Test
    public void recursive() {

        BinaryTree<String> binaryTree = new BinaryTree<>();

        //输入ABDH##I##E##CF#J##G##（#用null代替）
        LinkedList<String> tree = new LinkedList<>();
        tree.add("A");
        tree.add("B");
        tree.add("D");
        tree.add("H");
        tree.add(null);
        tree.add(null);
        tree.add("I");
        tree.add(null);
        tree.add(null);
        tree.add("E");
        tree.add(null);
        tree.add(null);
        tree.add("C");
        tree.add("F");
        tree.add(null);
        tree.add("J");
        tree.add(null);
        tree.add(null);
        tree.add("G");
        tree.add(null);
        tree.add(null);

        TreeNode<String> root = binaryTree.createBinaryTree(tree);

        //先序遍历（递归）
        System.out.println("先序遍历（递归）------------------------------");
        binaryTree.preRecursive(root);
        System.out.println();

        //中序遍历（递归）
        System.out.println("中序遍历（递归）------------------------------");
        binaryTree.midRecursive(root);
        System.out.println();

        //后序遍历（递归）
        System.out.println("后序遍历（递归）------------------------------");
        binaryTree.bacRecursive(root);
        System.out.println();


        //先序遍历（非递归）
        System.out.println("先序遍历（非递归）------------------------------");
        binaryTree.preUnrecursive(root);
        System.out.println();

        //中序遍历（非递归）
        System.out.println("中序遍历（非递归）------------------------------");
        binaryTree.midUnrecursive(root);
        System.out.println();

        //后序遍历（非递归）
        System.out.println("后序遍历（非递归）------------------------------");
        binaryTree.bacUnrecursive(root);
        System.out.println();

        //层次遍历（非递归）
        System.out.println("层次遍历（非递归）------------------------------");
        binaryTree.layerUnrecursive(root);
        System.out.println();

    }

}
