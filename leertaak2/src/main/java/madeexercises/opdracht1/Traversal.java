package madeexercises.opdracht1;

import java.util.Stack;

// TODO: JavaDoc

public class Traversal {

    TreeNode rootNode; // Root-node of the created binary-tree

    public static void main(String[] args) {
        Traversal traversal = new Traversal();
        traversal.inOrderTraversal();
        System.out.println("\n");
        traversal.preOrderTraversal();
    }

    public Traversal() {
        rootNode = initBinaryTree();
    }

    /*
    Creates a binary tree based on the structure mentioned in the
    resources. Will return the rootNode if the tree when done
    initialising.
     */
    private TreeNode initBinaryTree() {
        // Nodes: // For visual, check resource!
        TreeNode root = new TreeNode(40);
        TreeNode r20 = new TreeNode(20);
        TreeNode r10 = new TreeNode(10);
        TreeNode r30 = new TreeNode(30);


        TreeNode r50 = new TreeNode(50);
        TreeNode r60 = new TreeNode(60);
        TreeNode r70 = new TreeNode(70);

        // Relations (Parent and child):
        root.left = r20;
        r20.left = r10;
        r20.right = r30;

        root.right = r60;
        r60.left = r50;
        r60.right = r70;

        return root;
    }

    public void inOrderTraversal() {
        System.out.println("in order traversal:");

        TreeNode current = rootNode;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (current != null || !stack.empty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.right;
            }
        }

    }

    public void preOrderTraversal() {
        System.out.println("pre order traversal:");

        TreeNode current = rootNode;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(current);
        do {
            if (current != null) {
                TreeNode found = stack.pop();
                System.out.print(found.data + " ");

                if (found.right != null) {
                    stack.push(found.right);
                }
                if (found.left != null) {
                    stack.push(found.left);
                }

            } else {
                stack.push(current);
            }

        } while (current != null && !stack.isEmpty());
    }
}
