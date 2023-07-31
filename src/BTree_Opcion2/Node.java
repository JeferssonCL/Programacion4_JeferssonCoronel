package BTree_Opcion2;

import java.util.Arrays;
import java.util.Objects;

public class Node implements Comparable<Node>{
    private int key;
    private TreeNode left;
    private TreeNode right;

    public Node(int data, int order) {
        this.key = data;
        this.left = new TreeNode(order);
        this.right = new TreeNode(order);
    }

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

    @Override
    public int compareTo(Node o) {
        return Integer.compare(key, o.getData());
    }
}
