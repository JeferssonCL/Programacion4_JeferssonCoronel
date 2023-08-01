package Practice1_BtreeAddGetRemove.BTree_Opcion1_Generics;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BTree class represents a B-tree data structure that maintains elements in sorted order.
 * It supports insertion and searching operations while ensuring B-tree properties are preserved.
 *
 * @param <T> The type of elements to be stored in the B-tree. Must implement Comparable<T> interface.
 */
public class BTree<T extends Comparable<T>> implements IBTree {
    private final int minKeySize;
    private final int maxKeySize;
    private final int maxChildrenSize;
    private TreeNode<T> root = null;

    public BTree(int order) {
        this.minKeySize = order / 2;
        this.maxKeySize = order - 1;
        this.maxChildrenSize = order;
    }

    /**
     * Adds a new value to the B-tree while maintaining the B-tree properties.
     * If the value is already present in the B-tree, it will not be added again.
     *
     * @param key The value to be added to the B-tree.
     */
    public void add(Object key) {
        T value = (T) key;
        if (searchNode(value) != null)
            return; // Value already exists, no need to add again.

        if (root == null) {
            root = new TreeNode<T>(null, maxKeySize, maxChildrenSize);
            root.insertKey(value);
        } else
            addRecursive(root, value);
    }

    /**
     * Recursive helper method for adding a new value to the B-tree.
     *
     * @param treeNode  The current node being examined during the insertion process.
     * @param value The value to be added to the B-tree.
     */
    private void addRecursive(TreeNode<T> treeNode, T value) {
        if (treeNode.numberOfChildren() == 0) {
            treeNode.insertKey(value);
            if (treeNode.numberOfKeys() <= maxKeySize) {
                return;
            }
            split(treeNode);
        } else {
            int childIndex = findChildIndex(treeNode, value);
            TreeNode<T> child = treeNode.getChild(childIndex);
            addRecursive(child, value);
        }
    }

    /**
     * Finds the appropriate child index for inserting a new value in the node.
     *
     * @param treeNode  The node in which the value is to be inserted.
     * @param value The value to be inserted.
     * @return The index of the child where the value should be inserted.
     */
    private int findChildIndex(TreeNode<T> treeNode, T value) {
        int numberOfKeys = treeNode.numberOfKeys();
        T lesser = treeNode.getKey(0);
        if (value.compareTo(lesser) <= 0) return 0;

        int last = numberOfKeys - 1;
        T greater = treeNode.getKey(last);
        if (value.compareTo(greater) > 0) return numberOfKeys;

        for (int i = 1; i < treeNode.numberOfKeys(); i++) {
            T prev = treeNode.getKey(i - 1);
            T next = treeNode.getKey(i);
            if (value.compareTo(prev) > 0 && value.compareTo(next) <= 0) return i;
        } return -1; // If the value doesn't fit in any existing key range, return -1 (should not happen).
    }

    /**
     * Splits the given node to maintain the B-tree properties after insertion.
     *
     * @param treeNodeToSplit The node to be split.
     */
    private void split(TreeNode<T> treeNodeToSplit) {
        TreeNode<T> treeNode = treeNodeToSplit;
        int numberOfKeys = treeNode.numberOfKeys();
        int midIndex = numberOfKeys / 2;

        if (numberOfKeys % 2 == 0) midIndex--;
        T medianValue = treeNode.getKey(midIndex);

        TreeNode<T> left = new TreeNode<T>(null, maxKeySize, maxChildrenSize);
        for (int i = 0; i < midIndex; i++) left.insertKey(treeNode.getKey(i));

        if (treeNode.numberOfChildren() > 0) {
            for (int j = 0; j <= midIndex; j++) {
                TreeNode<T> c = treeNode.getChild(j);
                left.insertChild(c);
            }
        }
        TreeNode<T> right = new TreeNode<T>(null, maxKeySize, maxChildrenSize);
        for (int i = midIndex + 1; i < numberOfKeys; i++) right.insertKey(treeNode.getKey(i));

        if (treeNode.numberOfChildren() > 0) {
            for (int j = midIndex + 1; j < treeNode.numberOfChildren(); j++) {
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

            if (parent.numberOfKeys() > maxKeySize) split(parent);
        }
    }

    /**
     * Searches for a specific value in the B-tree.
     *
     * @param value The value to search for in the B-tree.
     * @return The node containing the value if found; otherwise, null.
     */
    private TreeNode<T> searchNode(T value) {
        TreeNode<T> treeNode = root;
        while (treeNode != null) {
            T lesser = treeNode.getKey(0);
            if (value.compareTo(lesser) < 0) {
                if (treeNode.numberOfChildren() > 0) treeNode = treeNode.getChild(0);
                else treeNode = null;
                continue;
            }

            int numberOfKeys = treeNode.numberOfKeys();
            int last = numberOfKeys - 1;
            T greater = treeNode.getKey(last);
            if (value.compareTo(greater) > 0) {
                if (treeNode.numberOfChildren() > numberOfKeys)
                    treeNode = treeNode.getChild(numberOfKeys);
                else treeNode = null;
                continue;
            }

            for (int i = 0; i < numberOfKeys; i++) {
                T currentValue = treeNode.getKey(i);
                if (currentValue.compareTo(value) == 0) return treeNode;

                int next = i + 1;
                if (next <= last) {
                    T nextValue = treeNode.getKey(next);
                    if (currentValue.compareTo(value) < 0 && nextValue.compareTo(value) > 0) {
                        if (next < treeNode.numberOfChildren()) {
                            treeNode = treeNode.getChild(next);
                            break;
                        } return null; }}}
        } return null;
    }

    /**
     * Returns the index of the next value greater than or equal to the given value.
     *
     * @param treeNode  The node containing keys to search through.
     * @param value The value to find the next index for.
     * @return The index of the next value greater than or equal to the given value.
     */
    private int getIndexOfNextValue(TreeNode<T> treeNode, T value) {
        for (int i = 0; i < treeNode.numberOfKeys(); i++) {
            T t = treeNode.getKey(i);
            if (t.compareTo(value) >= 0) return i;
        } return treeNode.numberOfKeys() - 1;
    }

    /**
     * Returns the index of the previous value less than or equal to the given value.
     *
     * @param treeNode  The node containing keys to search through.
     * @param value The value to find the previous index for.
     * @return The index of the previous value less than or equal to the given value.
     */
    private int getIndexOfPreviousValue(TreeNode<T> treeNode, T value) {
        for (int i = 1; i < treeNode.numberOfKeys(); i++) {
            T t = treeNode.getKey(i);
            if (t.compareTo(value) >= 0) return i - 1;
        }return treeNode.numberOfKeys() - 1;
    }

    /**
     * Combines the given node with its neighbors in the B-tree to maintain the B-tree properties.
     * This method is called during deletion when the number of keys in the node falls below the minimum required.
     *
     * @param treeNode The node to be combined with its neighbors.
     */
    private void combined(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.parent;
        int index = parent.indexOf(treeNode);
        int indexOfLeftNeighbor = index - 1;
        int indexOfRightNeighbor = index + 1;

        TreeNode<T> rightNeighbor = getRightNeighbor(parent, indexOfRightNeighbor);
        TreeNode<T> leftNeighbor = getLeftNeighbor(parent, indexOfLeftNeighbor);

        if (rightNeighbor != null && rightNeighbor.numberOfKeys() > minKeySize) {
            combineWithRightNeighbor(treeNode, parent, rightNeighbor);
        } else if (leftNeighbor != null && leftNeighbor.numberOfKeys() > minKeySize) {
            combineWithLeftNeighbor(treeNode, parent, leftNeighbor);
        } else if (rightNeighbor != null && parent.numberOfKeys() > 0) {
            combineWithRightNeighborAndParent(treeNode, parent, rightNeighbor);
        } else if (leftNeighbor != null && parent.numberOfKeys() > 0) {
            combineWithLeftNeighborAndParent(treeNode, parent, leftNeighbor);
        } else if (parent.numberOfKeys() == 0) {
            handleEmptyParent(treeNode);
        }
    }

    /**
     * Returns the right neighbor node of the specified parent node at the given index.
     *
     * @param parent             The parent node whose right neighbor is being retrieved.
     * @param indexOfRightNeighbor The index of the right neighbor within the parent's children.
     * @return The right neighbor node if it exists, otherwise null.
     */
    private TreeNode<T> getRightNeighbor(TreeNode<T> parent, int indexOfRightNeighbor) {
        if (indexOfRightNeighbor < parent.numberOfChildren()) {
            return parent.getChild(indexOfRightNeighbor);
        }
        return null;
    }

    /**
     * Returns the left neighbor node of the specified parent node at the given index.
     *
     * @param parent             The parent node whose left neighbor is being retrieved.
     * @param indexOfLeftNeighbor The index of the left neighbor within the parent's children.
     * @return The left neighbor node if it exists, otherwise null.
     */
    private TreeNode<T> getLeftNeighbor(TreeNode<T> parent, int indexOfLeftNeighbor) {
        if (indexOfLeftNeighbor >= 0) {
            return parent.getChild(indexOfLeftNeighbor);
        }
        return null;
    }

    /**
     * Combines the current node with its right neighbor node under the specified parent node.
     *
     * @param treeNode          The current node to combine with its right neighbor.
     * @param parent        The parent node containing the current node and the right neighbor.
     * @param rightNeighbor The right neighbor node to combine with the current node.
     */
    private void combineWithRightNeighbor(TreeNode<T> treeNode, TreeNode<T> parent, TreeNode<T> rightNeighbor) {
        T removeValue = rightNeighbor.getKey(0);
        int prev = getIndexOfPreviousValue(parent, removeValue);
        T parentValue = parent.removeKey(prev);
        T neighborValue = rightNeighbor.removeKey(0);
        treeNode.insertKey(parentValue);
        parent.insertKey(neighborValue);
        if (rightNeighbor.numberOfChildren() > 0) {
            treeNode.insertChild(rightNeighbor.removeChild(0));
        }
    }

    /**
     * Combines the current node with its left neighbor node under the specified parent node.
     *
     * @param treeNode         The current node to combine with its left neighbor.
     * @param parent       The parent node containing the current node and the left neighbor.
     * @param leftNeighbor The left neighbor node to combine with the current node.
     */
    private void combineWithLeftNeighbor(TreeNode<T> treeNode, TreeNode<T> parent, TreeNode<T> leftNeighbor) {
        T removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
        int prev = getIndexOfNextValue(parent, removeValue);
        T parentValue = parent.removeKey(prev);
        T neighborValue = leftNeighbor.removeKey(leftNeighbor.numberOfKeys() - 1);
        treeNode.insertKey(parentValue);
        parent.insertKey(neighborValue);
        if (leftNeighbor.numberOfChildren() > 0) {
            treeNode.insertChild(leftNeighbor.removeChild(leftNeighbor.numberOfChildren() - 1));
        }
    }

    /**
     * Combines the current node with its right neighbor node under the specified parent node.
     * The parent node is also adjusted accordingly after the merge.
     *
     * @param treeNode          The current node to combine with its right neighbor.
     * @param parent        The parent node containing the current node and the right neighbor.
     * @param rightNeighbor The right neighbor node to combine with the current node.
     */
    private void combineWithRightNeighborAndParent(TreeNode<T> treeNode, TreeNode<T> parent, TreeNode<T> rightNeighbor) {
        T removeValue = rightNeighbor.getKey(0);
        int prev = getIndexOfPreviousValue(parent, removeValue);
        T parentValue = parent.removeKey(prev);
        parent.removeChild(rightNeighbor);
        treeNode.insertKey(parentValue);

        for (int i = 0; i < rightNeighbor.getKeysSize(); i++) {
            T v = rightNeighbor.getKey(i);
            treeNode.insertKey(v);
        }

        for (int i = 0; i < rightNeighbor.getChildrenSize(); i++) {
            TreeNode<T> c = rightNeighbor.getChild(i);
            treeNode.insertChild(c);
        }

        if (parent.parent != null && parent.numberOfKeys() < minKeySize) {
            combined(parent);
        } else if (parent.numberOfKeys() == 0) {
            treeNode.parent = null;
            root = treeNode;
        }
    }

    /**
     * Combines the current node with its left neighbor node under the specified parent node.
     * The parent node is also adjusted accordingly after the merge.
     *
     * @param treeNode         The current node to combine with its left neighbor.
     * @param parent       The parent node containing the current node and the left neighbor.
     * @param leftNeighbor The left neighbor node to combine with the current node.
     */
    private void combineWithLeftNeighborAndParent(TreeNode<T> treeNode, TreeNode<T> parent, TreeNode<T> leftNeighbor) {
        T removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
        int prev = getIndexOfNextValue(parent, removeValue);
        T parentValue = parent.removeKey(prev);
        parent.removeChild(leftNeighbor);
        treeNode.insertKey(parentValue);

        for (int i = 0; i < leftNeighbor.getKeysSize(); i++) {
            T v = leftNeighbor.getKey(i);
            treeNode.insertKey(v);
        }

        for (int i = 0; i < leftNeighbor.getChildrenSize(); i++) {
            TreeNode<T> c = leftNeighbor.getChild(i);
            treeNode.insertChild(c);
        }

        if (parent.parent != null && parent.numberOfKeys() < minKeySize) {
            combined(parent);
        } else if (parent.numberOfKeys() == 0) {
            treeNode.parent = null;
            root = treeNode;
        }
    }

    /**
     * Handles the case when the parent node becomes empty after combining with a neighbor.
     * The node is detached from its parent and becomes the new root of the tree.
     *
     * @param treeNode The node that becomes the new root of the tree.
     */
    private void handleEmptyParent(TreeNode<T> treeNode) {
        treeNode.parent = null;
        root = treeNode;
    }

    /**
     * Returns the greatest node in the subtree rooted at the given node.
     * The greatest node is the rightmost node in the subtree.
     *
     * @param treeNodeToGet The root of the subtree to find the greatest node in.
     * @return The greatest node in the subtree rooted at the given node.
     */
    private TreeNode<T> getGreatestNode(TreeNode<T> treeNodeToGet) {
        TreeNode<T> treeNode = treeNodeToGet;
        while (treeNode.numberOfChildren() > 0) {
            treeNode = treeNode.getChild(treeNode.numberOfChildren() - 1);
        } return treeNode;
    }

    /**
     * Removes the greatest value from the specified node and returns it.
     *
     * @param treeNode The node from which the greatest value is to be removed.
     * @return The greatest value removed from the node, or null if the node is empty.
     */
    private T removeGreatestValue(TreeNode<T> treeNode) {
        T value = null;
        if (treeNode.numberOfKeys() > 0)
            value = treeNode.removeKey(treeNode.numberOfKeys() - 1);
        return value;
    }

    /**
     * Removes the first occurrence of the specified value from the BTree.
     * If the value is not found, returns null.
     *
     * @param key The value to be removed from the BTree.
     * @return The value that was removed, or null if the value was not found in the BTree.
     */
    public T remove(Object key) {
        T value = (T) key;
        T removed = null;
        TreeNode<T> treeNode = this.searchNode(value);
        removed = remove(value, treeNode);
        return removed;
    }

    /**
     * Removes the specified value from the given node in the BTree.
     *
     * @param value The value to be removed from the node.
     * @param treeNode The node from which the value is to be removed.
     * @return The value that was removed from the node, or null if the node is empty or the value is not found.
     */
    private T remove(T value, TreeNode<T> treeNode) {
        if (treeNode == null) return null;

        T removed = null;
        int index = treeNode.indexOf(value);
        removed = treeNode.removeKey(value);
        if (treeNode.numberOfChildren() == 0) {
            if (treeNode.parent != null && treeNode.numberOfKeys() < minKeySize)
                this.combined(treeNode);
            else if (treeNode.parent == null && treeNode.numberOfKeys() == 0)
                root = null;
        } else {
            TreeNode<T> lesser = treeNode.getChild(index);
            TreeNode<T> greatest = this.getGreatestNode(lesser);
            T replaceValue = this.removeGreatestValue(greatest);
            treeNode.insertKey(replaceValue);
            if (greatest.parent != null && greatest.numberOfKeys() < minKeySize) this.combined(greatest);
            if (greatest.numberOfChildren() > maxChildrenSize) this.split(greatest);
        } return removed;
    }

    /**
     * Returns the string representation of the BTree.
     *
     * @return The string representation of the BTree.
     */
    public String toString() {
        if (root == null) return "BTree is empty.";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode<T> currentTreeNode = queue.poll();
                sb.append("[");
                for (int j = 0; j < currentTreeNode.numberOfKeys(); j++) {
                    sb.append(currentTreeNode.getKey(j));
                    if (j != currentTreeNode.numberOfKeys() - 1) sb.append(", ");
                } sb.append("]");

                if (currentTreeNode.numberOfChildren() > 0) {
                    for (int j = 0; j <= currentTreeNode.numberOfChildren(); j++) {
                        TreeNode<T> child = currentTreeNode.getChild(j);
                        if (child != null) queue.offer(child);
                    }
                }
                if (!queue.isEmpty()) sb.append(", ");
            } sb.append("\n");
        } return sb.toString();
    }
}
