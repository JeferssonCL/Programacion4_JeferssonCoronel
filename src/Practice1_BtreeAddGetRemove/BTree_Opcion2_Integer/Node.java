package Practice1_BtreeAddGetRemove.BTree_Opcion2_Integer;

import java.util.Arrays;
import java.util.Objects;

/**
 * A node representing a key-value pair in a B-tree.
 * The node contains a single key and references to left and right child nodes.
 * It implements the Comparable interface to support comparisons based on the key value.
 */
public class Node implements Comparable<Node>{
    private int key;
    private TreeNode left;
    private TreeNode right;

    /**
     * Creates a new Node with the specified key and order for the child TreeNodes.
     *
     * @param data  The key value for this Node.
     * @param order The order of the child TreeNodes (maximum number of keys in a child).
     */
    public Node(int data, int order) {
        this.key = data;
        this.left = new TreeNode(order);
        this.right = new TreeNode(order);
    }

    /**
     * Checks whether the Node has both left and right children.
     *
     * @return true if the Node has both left and right children; false otherwise.
     */
    public boolean hasChildren() {
        return !Arrays.stream(left.getKeys()).allMatch(Objects::isNull) &&
        !Arrays.stream(right.getKeys()).allMatch(Objects::isNull);
    }

    /**
     * This method get the data from the node
     *
     * @return data
     */
    public int getData() {
        return key;
    }

    /**
     * This method changes the node data to a new one.
     *
     * @param data new data
     */
    public void setData(Object data) {
        this.key = (Integer) data;
    }

    /**
     * This method returns the data of the left node.
     *
     * @return data of the left node
     */
    public TreeNode getLeftNode() {
        return this.left;
    }

    /**
     * This method returns the data of the right node.
     *
     * @return data of the right node
     */
    public TreeNode getRightNode() {
        return this.right;
    }

    /**
     * This method changes the data from the left node to a new one.
     *
     * @param left new data of the left node
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * This method changes the data from the right node to a new one.
     *
     * @param right new data of the right node
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return key +"\t---->\t"+
                "left:" + left + "\t\t"+
                "right:" + right ;
    }

    /**
     * Compares this Node with the specified Node based on their key values.
     *
     * @param o The Node to be compared with this Node.
     * @return a negative integer, zero, or a positive integer if this Node is less than,
     *         equal to, or greater than the specified Node, respectively.
     */
    @Override
    public int compareTo(Node o) {
        return Integer.compare(key, o.getData());
    }
}
