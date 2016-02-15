package madeexercises.opdracht1;

/**
 * Created by johankladder on 15-2-16.
 */
public class Traversal {

    TreeNode rootNode; // Root-node of the created binary-tree

    public static void main(String[] args) {
        Traversal traversal = new Traversal();
        traversal.inOrderTraversal();
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
        TreeNode root = new TreeNode(50);
        TreeNode r1 = new TreeNode(20);
        TreeNode r2 = new TreeNode(10);
        TreeNode r3 = new TreeNode(25);
        TreeNode r4 = new TreeNode(5);

        TreeNode r5 = new TreeNode(70);
        TreeNode r6 = new TreeNode(40);
        TreeNode r7 = new TreeNode(85);
        TreeNode r8 = new TreeNode(30);

        // Relations (Parent and child):
        root.left = r1;
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;

        root.right = r5;
        r5.left = r6;
        r5.right = r7;
        r7.left = r8;

        return root;
    }

    // TODO: Create in order traversal on created binary tree
    public boolean inOrderTraversal() {
        return false;
    }

    // TODO: Create pre order traversal on created binary tree
    public boolean preOrderTraversal() {
        return false;
    }
}
