package BTree_Opcion1_Generics;

import java.util.Arrays;
import java.util.Comparator;

/**
 * TreeNode is a generic class representing a node in a B-tree data structure. Each node can hold multiple keys
 * and child nodes. The keys are stored in a sorted array, and the child nodes are stored in a sorted array based
 * on the first key of each child node. The class also supports operations for inserting and removing keys and children.
 *
 * @param <T> The type of elements that the TreeNode can hold. The elements must be comparable to maintain
 *            the sorted order of keys and child nodes.
 */
public class TreeNode<T extends Comparable<T>> implements IBTreeNode<T>{
    private final T[] keys;
    private int keysSize;
    private final TreeNode<T>[] children;
    private int childrenSize;
    private final Comparator <TreeNode<T>> comparator;
    protected TreeNode<T> parent;


    /**
     * Creates a new instance of the TreeNode class with the specified parent node, maximum key size, and maximum
     * children size. The parent node is used to keep track of the node's position in the B-tree. The maximum key size
     * determines the capacity of the keys array, and the maximum children size determines the capacity of the children array.
     *
     * @param parent        The parent node of this TreeNode. If this is the root node, the parent should be null.
     * @param maxKeySize    The maximum number of keys that this node can hold.
     * @param maxChildrenSize The maximum number of child nodes that this node can have.
     */
    public TreeNode(TreeNode<T> parent, int maxKeySize, int maxChildrenSize) {
        this.parent = parent;
        this.keys = (T[]) new Comparable[maxKeySize + 1];
        this.keysSize = 0;
        this.children = new TreeNode[maxChildrenSize + 1];
        this.childrenSize = 0;
        this.comparator = (TreeNode<T> arg0, TreeNode<T> arg1) -> arg0.getKey(0).compareTo(arg1.getKey(0));
    }

    /**
     * Retrieves the key at the specified index in the node's keys array.
     *
     * @param index The index of the key to retrieve.
     * @return The key at the specified index.
     */
    @Override
    public T getKey(int index) {
        return keys[index];
    }

    /**
     * Inserts a new key into the node's keys array and ensures that the array remains sorted.
     *
     * @param value The value to be inserted as a new key.
     */
    @Override
    public void insertKey(T value) {
        keys[keysSize++] = value;
        Arrays.sort(keys, 0, keysSize);
    }

    /**
     * Returns the number of keys currently stored in the node.
     *
     * @return The number of keys in the node.
     */
    @Override
    public int getNumberOfKeys() {
        return keysSize;
    }

    /**
     * Retrieves the child node at the specified index in the node's children array.
     *
     * @param index The index of the child node to retrieve.
     * @return The child node at the specified index, or null if the index is out of range.
     */
    @Override
    public TreeNode<T> getChild(int index) {
        if (index >= childrenSize) return null;
        return children[index];
    }

    /**
     * Inserts a new child node into the node's children array and ensures that the array remains sorted based on
     * the first key of each child node.
     *
     * @param child The child node to be inserted.
     */
    public void insertChild(TreeNode<T> child) {
        child.parent = this;
        children[childrenSize++] = child;
        Arrays.sort(children, 0, childrenSize, comparator);
    }

    /**
     * Removes the specified child node from the node's children array.
     *
     * @param child The child node to be removed.
     */
    @Override
    public void removeChild(TreeNode<T> child) {
        boolean found = false;
        if (childrenSize == 0) return;

        for (int i = 0; i < childrenSize; i++) {
            if (children[i].equals(child))
                found = true;
            else if (found)
                children[i - 1] = children[i];
        }
        if (found) {
            childrenSize--;
            children[childrenSize] = null;
        }
    }

    /**
     * Returns the number of child nodes currently stored in the node.
     *
     * @return The number of child nodes in the node.
     */
    @Override
    public int getNumberOfChildren() {
        return childrenSize;
    }
}