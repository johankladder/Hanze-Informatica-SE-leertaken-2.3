package opdracht2;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class TreeNode extends DefaultMutableTreeNode {

    private String name;

    public TreeNode(String name) {
        this.name = name;
    }

    public TreeNode() {

    }

    public String getName() {
        return name;
    }
}
