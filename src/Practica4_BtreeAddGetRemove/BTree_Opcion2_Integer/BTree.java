package Practica4_BtreeAddGetRemove.BTree_Opcion2_Integer;

/**
 * A B-tree implementation that supports insertion of integer keys.
 * B-trees are balanced search trees designed for efficient retrieval
 * and storage of large amounts of data. This implementation uses
 * TreeNode to store the keys and maintain the structure of the tree.
 * The B-tree is constructed with a specified order, which determines
 * the maximum number of keys a node can hold.
 */
public class BTree {

    private final int order;
    private final TreeNode root;

    public BTree(int order) {
        this.order = order;
        this.root = new TreeNode(order);
    }

    /**
     * Inserts the given integer value into the B-tree.
     *
     * @param value the integer value to be inserted into the B-tree
     */
    public void add(int value) {
        root.addKey(value);
    }

    /**
     * Searches for a node in the B-tree containing the specified key.
     * Returns the TreeNode containing the key if found, or null if the key is not present in the tree.
     *
     * @param key the integer key to be searched in the B-tree
     * @return the TreeNode containing the key, or null if not found
     */
    public TreeNode getTreeNode(int key) {
        return root.getTreeNode(key);
    }

    /**
     * Returns a string representation of the B-tree.
     *
     * @return a string representing the B-tree's order and its root node
     */
    @Override
    public String toString() {
        return "BTree{" +
                "order=" + order +
                ", root=" + root +
                '}';
    }
}
