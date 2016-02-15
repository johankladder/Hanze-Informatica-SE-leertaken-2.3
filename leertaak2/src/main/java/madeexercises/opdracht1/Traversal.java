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

    // TODO: Create example-tree to perform test on
    private TreeNode initBinaryTree() {
        return null;
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
