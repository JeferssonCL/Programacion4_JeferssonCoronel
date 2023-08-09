package Practice4_BtreeAddGetRemove.BTree_Opcion1_Generics;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A generic node used in a B-tree data structure.
 *
 * @param <T> the type of keys in the node, must implement Comparable interface.
 */
public class TreeNode<T extends Comparable<T>> implements IBTreeNode<T>{
    private final T[] keys;
    private int keysSize;
    private final TreeNode<T>[] children;
    private int childrenSize;
    private final Comparator <TreeNode<T>> comparator;
    protected TreeNode<T> parent;

    /**
     * Constructs a new Node.
     *
     * @param parent         the parent node.
     * @param maxKeySize     the maximum number of keys the node can hold.
     * @param maxChildrenSize the maximum number of children the node can have.
     */
    public TreeNode(TreeNode<T> parent, int maxKeySize, int maxChildrenSize) {
        this.parent = parent;
        this. comparator = Comparator.comparing((TreeNode<T> arg0) -> arg0.getKey(0));
        this.keys = (T[]) new Comparable[maxKeySize + 1];
        this.keysSize = 0;
        this.children = new TreeNode[maxChildrenSize + 1];
        this.childrenSize = 0;
    }

    /**
     * Gets the key at the specified index.
     *
     * @param index the index of the key to retrieve.
     * @return the key at the given index.
     */
    public T getKey(int index) {
        return keys[index];
    }

    /**
     * Finds the index of a given value in the keys array.
     *
     * @param value the value to find in the keys array.
     * @return the index of the value if found, otherwise -1.
     */
    public int indexOf(T value) {
        for (int i = 0; i < keysSize; i++) {
            if (keys[i].equals(value)) return i;
        } return -1;
    }

    /**
     * Inserts a new key into the node.
     *
     * @param value the value to insert.
     */
    public void insertKey(T value) {
        keys[keysSize++] = value;
        Arrays.sort(keys, 0, keysSize);
    }

    /**
     * Removes a specific key from the node.
     *
     * @param value the value to remove.
     * @return the removed value if found, otherwise null.
     */
    public T removeKey(T value) {
        T removed = null;
        boolean found = false;
        if (keysSize == 0) return null;

        for (int i = 0; i < keysSize; i++) {
            if (keys[i].equals(value)) {
                found = true;
                removed = keys[i];
            } else if (found) keys[i - 1] = keys[i];
        }
        if (found) {
            keysSize--;
            keys[keysSize] = null;
        } return removed;
    }

    /**
     * Removes the key at the specified index from the node.
     *
     * @param index the index of the key to be removed.
     * @return the removed key if successful, otherwise null.
     */
    public T removeKey(int index) {
        if (index >= keysSize) return null;

        T value = keys[index];
        for (int i = index + 1; i < keysSize; i++) keys[i - 1] = keys[i];

        keysSize--;
        keys[keysSize] = null;
        return value;
    }

    /**
     * Returns the number of keys currently in the node.
     *
     * @return the number of keys.
     */
    public int numberOfKeys() {
        return keysSize;
    }

    /**
     * Gets the child node at the specified index.
     *
     * @param index the index of the child node to retrieve.
     * @return the child node at the given index if it exists, otherwise null.
     */
    public TreeNode<T> getChild(int index) {
        if (index >= childrenSize) return null;
        return children[index];
    }

    /**
     * Finds the index of a given child node in the children array.
     *
     * @param child the child node to find.
     * @return the index of the child node if found, otherwise -1.
     */
    public int indexOf(TreeNode<T> child) {
        for (int i = 0; i < childrenSize; i++) {
            if (children[i].equals(child))
                return i;
        } return -1;
    }

    /**
     * Inserts a new child node into the node's children array.
     *
     * @param child the child node to insert.
     * @return true if the insertion was successful, otherwise false.
     */
    public boolean insertChild(TreeNode<T> child) {
        child.parent = this;
        children[childrenSize++] = child;
        Arrays.sort(children, 0, childrenSize, comparator);
        return true;
    }

    /**
     * Removes a specific child node from the node's children array.
     *
     * @param child the child node to remove.
     * @return true if the child node was found and removed, otherwise false.
     */
    public boolean removeChild(TreeNode<T> child) {
        boolean found = false;
        if (childrenSize == 0) return found;

        for (int i = 0; i < childrenSize; i++) {
            if (children[i].equals(child)) found = true;
            else if (found) children[i - 1] = children[i];
        }
        if (found) {
            childrenSize--;
            children[childrenSize] = null;
        } return found;
    }

    /**
     * Removes the child node at the specified index from the node's children array.
     *
     * @param index the index of the child node to remove.
     * @return the removed child node if successful, otherwise null.
     */
    public TreeNode<T> removeChild(int index) {
        if (index >= childrenSize) return null;

        TreeNode<T> value = children[index];
        children[index] = null;
        for (int i = index + 1; i < childrenSize; i++) children[i - 1] = children[i];

        childrenSize--;
        children[childrenSize] = null;
        return value;
    }

    /**
     * Returns a string representation of the current node and its information.
     * The string contains details about the keys of the node and, if applicable,
     * the keys of its parent node and information about its children.
     *
     * @return A string representation of the node and its information.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        appendKeys(builder);

        if (parent != null) appendParentKeys(builder);

        if (children != null) appendChildrenInfo(builder);
        return builder.toString();
    }

    /**
     * Helper method to append the keys of the current node to the given StringBuilder.
     *
     * @param builder The StringBuilder to which the keys will be appended.
     */
    private void appendKeys(StringBuilder builder) {
        builder.append("keys=[");
        for (int i = 0; i < numberOfKeys(); i++) {
            T value = getKey(i);
            builder.append(value);
            if (i < numberOfKeys() - 1) builder.append(", ");
        } builder.append("]\n");
    }

    /**
     * Helper method to append the keys of the parent node to the given StringBuilder.
     *
     * @param builder The StringBuilder to which the parent node's keys will be appended.
     */
    private void appendParentKeys(StringBuilder builder) {
        builder.append("parent=[");
        for (int i = 0; i < parent.numberOfKeys(); i++) {
            T value = parent.getKey(i);
            builder.append(value);
            if (i < parent.numberOfKeys() - 1) builder.append(", ");
        } builder.append("]\n");
    }

    /**
     * Helper method to append information about the number of keys and children of the node
     * to the given StringBuilder.
     *
     * @param builder The StringBuilder to which the information will be appended.
     */
    private void appendChildrenInfo(StringBuilder builder) {
        builder.append("keySize=").append(numberOfKeys()).append(" children=").append(numberOfChildren()).append("\n");
    }

    /**
     * Returns the number of children nodes that the current node has.
     *
     * @return The number of children nodes.
     */
    public int numberOfChildren() {
        return childrenSize;
    }

    /**
     * Returns an array containing the keys of the current node.
     *
     * @return An array of keys.
     */
    public T[] getKeys() {
        return keys;
    }

    /**
     * Returns the number of keys present in the current node.
     *
     * @return The number of keys.
     */
    public int getKeysSize() {
        return keysSize;
    }

    /**
     * Returns an array containing the children nodes of the current node.
     *
     * @return An array of children nodes.
     */
    public TreeNode<T>[] getChildren() {
        return children;
    }

    /**
     * Returns the number of children nodes that the current node has.
     *
     * @return The number of children nodes.
     */
    public int getChildrenSize() {
        return childrenSize;
    }

    /**
     * Returns the comparator used for comparing nodes.
     *
     * @return The comparator for nodes.
     */
    public Comparator<TreeNode<T>> getComparator() {
        return comparator;
    }

    /**
     * Returns the parent node of the current node.
     *
     * @return The parent node.
     */
    public TreeNode<T> getParent() {
        return parent;
    }
}
