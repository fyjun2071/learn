package learn.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树
 */
public class BinaryTree<T> {

    /**
     * 先序创建二叉树(递归)
     *
     * @param treeData
     * @return
     */
    public TreeNode<T> createBinaryTree(LinkedList<T> treeData) {
        TreeNode<T> root = null;
        T data = treeData.removeFirst();
        if (data != null) {
            root = new TreeNode<T>(data, null, null);
            root.left = createBinaryTree(treeData);
            root.right = createBinaryTree(treeData);
        }

        return root;
    }

    /**
     * 先序遍历(递归)
     *
     * @param root
     */
    public void preOrderTraversal(TreeNode<T> root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    /**
     * 中序遍历(递归)
     *
     * @param root
     */
    public void inOrderTraversal(TreeNode<T> root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    /**
     * 后序遍历(递归)
     *
     * @param root
     */
    public void postOrderTraversal(TreeNode<T> root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    /**
     * 先序遍历二叉树（非递归）
     * 思路：对于任意节点T，访问这个节点并压入栈中，然后访问节点的左子树，
     * 遍历完左子树后，取出栈顶的节点T，再先序遍历T的右子树
     */
    public void preOrderTraversalbyLoop(TreeNode<T> root) {
        TreeNode<T> p = root;//p为当前节点
        Stack<TreeNode<T>> stack = new Stack<>();
        //栈不为空时，或者p不为空时循环
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                //当前节点不为空。访问并压入栈中。并将当前节点赋值为左儿子
                stack.push(p);
                System.out.print(p.data + " ");
                p = p.left;
            } else {
                //当前节点为空：
                //  1、当p指向的左儿子时，此时栈顶元素必然是它的父节点
                //  2、当p指向的右儿子时，此时栈顶元素必然是它的爷爷节点
                //取出栈顶元素，赋值为right
                p = stack.pop();
                p = p.right;
            }
        }
    }

    /**
     * 中序遍历二叉树（非递归）
     * 思路：先将T入栈，遍历左子树；遍历完左子树返回时，栈顶元素应为T，
     * 出栈，访问T->data，再中序遍历T的右子树。
     */
    public void inOrderTraversalbyLoop(TreeNode<T> root) {
        TreeNode<T> p = root;//p为当前节点
        Stack<TreeNode<T>> stack = new Stack<>();
        //栈不为空时，或者p不为空时循环
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                //当前节点不为空。访问并压入栈中。并将当前节点赋值为左儿子
                stack.push(p);
                p = p.left;
            } else {
                //当前节点为空：
                //  1、当p指向的左儿子时，此时栈顶元素必然是它的父节点
                //  2、当p指向的右儿子时，此时栈顶元素必然是它的爷爷节点
                //取出栈顶元素，赋值为right
                p = stack.pop();
                System.out.print(p.data + " ");
                p = p.right;
            }
        }
    }

    /**
     * 后序遍历二叉树（非递归）
     */
    public void postOrderTraversalbyLoop(TreeNode<T> root) {
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> p = root;
        TreeNode<T> prev = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                TreeNode<T> temp = stack.peek().right;
                if (temp == null || temp == prev) {
                    p = stack.pop();
                    System.out.print(p.data + " ");
                    prev = p;
                    p = null;
                } else {
                    p = temp;
                }
            }
        }
    }

    /**
     * 层次遍历二叉树（非递归）
     */
    public void layerUnrecursive(TreeNode<T> root) {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        TreeNode<T> p;
        queue.push(root);
        while (!queue.isEmpty()) {
            p = queue.removeFirst();
            System.out.print(p.data + " ");
            if (p.left != null) {
                queue.add(p.left);
            }

            if (p.right != null) {
                queue.add(p.right);
            }

        }
    }

}

/**
 * 节点
 */
class TreeNode<T> {

    /**
     * 数据
     */
    public T data;

    /**
     * 左子节点
     */
    public TreeNode<T> left;

    /**
     * 右子节点
     */
    public TreeNode<T> right;

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
