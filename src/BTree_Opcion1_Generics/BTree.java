package BTree_Opcion1_Generics;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * BTree is a generic class representing a B-tree data structure, where each node can have multiple keys
 * and child nodes. The B-tree is a balanced tree data structure that maintains the keys in sorted order,
 * ensuring efficient searching, insertion, and deletion operations.
 *
 * @param <T> The type of elements that the B-tree can hold. The elements must be comparable to maintain
 *            the sorted order of keys.
 */
public class BTree<T extends Comparable<T>> implements IBTree{
    private final int maxKeySize;
    private final int maxChildrenSize;
    private int size;
    private TreeNode<T> root = null;

    /**
     * Creates a new instance of the BTree class with the specified order, which determines the maximum
     * number of keys that a node can hold and the maximum number of child nodes that a node can have.
     * The order of the B-tree is calculated as (maxKeySize + 1).
     *
     * @param order The order of the B-tree, which determines the maximum number of keys and child nodes a node can have.
     */
    public BTree(int order) {
        this.maxKeySize = order - 1;
        this.maxChildrenSize = order;
        this.size = 0;
    }

    /**
     * Inserts a new value into the B-tree if it doesn't already exist in the tree.
     * If the root is null, it initializes the root node with the given value. Otherwise,
     * it calls the insertValueIntoTree method to addTreeNode the value into the appropriate node.
     *
     * @param valueToAdd The value to be inserted into the B-tree.
     */

    @Override
    public void addTreeNode(Object valueToAdd) {
        T value = (T) valueToAdd;
        if (searchNode(value) != null)
            return;

        if (root == null)
            initializeRootNode(value);
        else
            insertValueIntoTree(value, root);
        size++;
    }

    /**
     * Initializes the root node of the B-tree with the given value.
     *
     * @param value The value to be inserted into the root node.
     */
    private void initializeRootNode(T value) {
        root = new TreeNode<T>(null, maxKeySize, maxChildrenSize);
        root.insertKey(value);
    }

    /**
     * Inserts a new value into the B-tree by recursively traversing the tree.
     * If the node's number of children is zero, it directly inserts the value into the node's keys.
     * If the number of keys exceeds the maximum allowed size, it calls the split method to split the node.
     * Otherwise, it finds the appropriate child node to addTreeNode the value and recursively calls the method on that child node.
     *
     * @param value The value to be inserted into the B-tree.
     * @param treeNode  The current node being examined during the insertion process.
     */
    private void insertValueIntoTree(T value, TreeNode<T> treeNode) {
        int index = findInsertionIndex(value, treeNode);

        if (treeNode.getNumberOfChildren() == 0) {
            treeNode.insertKey(value);
            if (treeNode.getNumberOfKeys() > maxKeySize)
                split(treeNode);
        } else {
            TreeNode<T> child = treeNode.getChild(index);
            insertValueIntoTree(value, child);
        }
    }

    /**
     * Finds the index where the given value should be inserted in the node's keys array.
     * It performs a binary search to locate the correct index based on the value's ordering.
     *
     * @param value The value to be inserted into the node.
     * @param treeNode  The node in which the value will be inserted.
     * @return The index where the value should be inserted in the node's keys array.
     */
    private int findInsertionIndex(T value, TreeNode<T> treeNode) {
        int index = 0;
        int numberOfKeys = treeNode.getNumberOfKeys();

        while (index < numberOfKeys && value.compareTo(treeNode.getKey(index)) > 0) {
            index++;
        } return index;
    }

    /**
     * Splits the given node into two nodes and redistributes the keys and children accordingly.
     * This method is called when a node's number of keys exceeds the maximum allowed size.
     *
     * @param treeNodeToSplit The node that needs to be split.
     */
    private void split(TreeNode<T> treeNodeToSplit) {
        TreeNode<T> treeNode = treeNodeToSplit;
        int numberOfKeys = treeNode.getNumberOfKeys();
        int midIndex = numberOfKeys / 2;

        if (numberOfKeys % 2 == 0) midIndex--;

        T medianValue = treeNode.getKey(midIndex);
        TreeNode<T> left = new TreeNode<T>(null, maxKeySize, maxChildrenSize);

        for (int i = 0; i < midIndex; i++) left.insertKey(treeNode.getKey(i));

        if (treeNode.getNumberOfChildren() > 0) {
            for (int j = 0; j <= midIndex; j++) {
                TreeNode<T> c = treeNode.getChild(j);
                left.insertChild(c);
            }
        }

        TreeNode<T> right = new TreeNode<T>(null, maxKeySize, maxChildrenSize);
        for (int i = midIndex + 1; i < numberOfKeys; i++) right.insertKey(treeNode.getKey(i));

        if (treeNode.getNumberOfChildren() > 0) {
            for (int j = midIndex + 1; j < treeNode.getNumberOfChildren(); j++) {
                TreeNode<T> c = treeNode.getChild(j);
                right.insertChild(c);
            }
        }

        if (treeNode.parent == null) {
            TreeNode<T> newRoot = new TreeNode<T>(null, maxKeySize, maxChildrenSize);
            newRoot.insertKey(medianValue);
            treeNode.parent = newRoot;
            root = newRoot;
            treeNode = root;
            treeNode.insertChild(left);
            treeNode.insertChild(right);
        } else {
            TreeNode<T> parent = treeNode.parent;
            parent.insertKey(medianValue);
            parent.removeChild(treeNode);
            parent.insertChild(left);
            parent.insertChild(right);

            if (parent.getNumberOfKeys() > maxKeySize) split(parent);
        }
    }

    /**
     * Searches for a node containing the given value in the BTree.
     *
     * @param value The value to search for in the BTree.
     * @return The node containing the value, or null if the value is not found in the BTree.
     */
    private TreeNode<T> searchNode(T value) {
        return searchNodeRecursive(root, value);
    }

    /**
     * Recursively searches for a node containing the given value in the specified node and its children.
     *
     * @param treeNode The current node being searched.
     * @param value The value to search for.
     * @return The node containing the value, or null if the value is not found in the node or its children.
     */
    private TreeNode<T> searchNodeRecursive(TreeNode<T> treeNode, T value) {
        if (treeNode == null) return null;

        T lesser = treeNode.getKey(0);
        T greater = treeNode.getKey(treeNode.getNumberOfKeys() - 1);

        if (value.compareTo(lesser) < 0)
            return searchNodeRecursive(treeNode.getChild(0), value);

        if (value.compareTo(greater) > 0) {
            int lastChildIndex = treeNode.getNumberOfChildren() - 1;
            return searchNodeRecursive(treeNode.getChild(lastChildIndex), value);
        } return searchInNodeKeys(treeNode, value);
    }

    /**
     * Searches for the value within the keys of the given node.
     * If the value is not found in the current node, it may recursively search in its children.
     *
     * @param treeNode The node in which to search for the value.
     * @param value The value to search for.
     * @return The node containing the value, or null if the value is not found in the node or its children.
     */
    private TreeNode<T> searchInNodeKeys(TreeNode<T> treeNode, T value) {
        int numberOfKeys = treeNode.getNumberOfKeys();

        for (int i = 0; i < numberOfKeys; i++) {
            T currentValue = treeNode.getKey(i);
            int next = i + 1;

            if (currentValue.compareTo(value) == 0) return treeNode;

            if (next < numberOfKeys) {
                T nextValue = treeNode.getKey(next);
                if (currentValue.compareTo(value) < 0 && nextValue.compareTo(value) > 0)
                    return searchNodeRecursive(treeNode.getChild(next), value); }
        } return null;
    }

    /**
     * Returns a string representation of the BTree.
     *
     * @return A string representing the BTree structure with its keys and children.
     *         If the BTree is empty, it returns "BTree is empty.".
     */
    @Override
    public String toString() {
        if (root == null) return "BTree is empty.";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode<T> currentTreeNode = queue.poll();
                appendNodeToStringBuilder(sb, Objects.requireNonNull(currentTreeNode));

                for (int j = 0; j <= currentTreeNode.getNumberOfChildren(); j++) {
                    TreeNode<T> child = currentTreeNode.getChild(j);
                    if (child != null)
                        queue.offer(child); }
                if (!queue.isEmpty())
                    sb.append(", ");
            } sb.append("\n");
        } return sb.toString();
    }

    /**
     * Appends the keys of a given node to the provided StringBuilder.
     *
     * @param sb The StringBuilder to which the node's keys will be appended.
     * @param treeNode The node whose keys will be appended to the StringBuilder.
     */
    private void appendNodeToStringBuilder(StringBuilder sb, TreeNode<T> treeNode) {
        sb.append("[");
        for (int j = 0; j < treeNode.getNumberOfKeys(); j++) {
            sb.append(treeNode.getKey(j));
            if (j != treeNode.getNumberOfKeys() - 1)
                sb.append(", ");
        } sb.append("]");
    }

    public int getSize() {
        return size;
    }
}