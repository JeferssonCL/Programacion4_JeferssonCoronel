package Practice3_BtreeAddGetRemove.BTree_Opcion2_Integer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a node in the B-tree.
 * Each node contains an array of keys and references to left and right child nodes.
 * The order of the node determines the maximum number of keys it can hold.
 */
public class TreeNode {
    private Node[] keys;
    private int order;
    private int m;
    int count;

    public TreeNode(int order) {
        this.order = order;
        this.m = Math.round((float) order /2) - 1;
        this.keys = new Node[order - 1];
        this.count = 1;
    }

    public TreeNode(Node[] elements) {
        this.keys = elements;
    }

    public Node[] getKeys() {
        return keys;
    }

    public long size() {
        return Arrays.stream(keys).filter(Objects::nonNull).count();
    }

    /**
     * Divides the given array of nodes into two parts based on the specified node.
     * Returns an array of TreeNode containing the keys less than the specified node's key
     * and the keys greater than the specified node's key.
     *
     * @param arr       the array of nodes to be divided
     * @param nodeToHead the node to be used as the pivot for division
     * @return an array of TreeNode containing the divided nodes
     */
    public TreeNode[] divideTreeNode(Node[] arr, Node nodeToHead){
        List<Node> minorArray = new ArrayList<>();
        List<Node> majorArrays = new ArrayList<>();

        for (Node node : arr) {
            if (node.getData() < nodeToHead.getData()) minorArray.add(node);
            else if (node.getData() > nodeToHead.getData()) majorArrays.add(node);
        }

        Node[][] elementsOnTreeNode = new Node[2][];
        elementsOnTreeNode[0] = Arrays.copyOf(minorArray.toArray(new Node[0]), 3);
        elementsOnTreeNode[1] = Arrays.copyOf(majorArrays.toArray(new Node[0]), 3);
        return new TreeNode[] {new TreeNode(elementsOnTreeNode[0]), new TreeNode(elementsOnTreeNode[1])};
    }

    /**
     * Helper method used to reorder the keys in the node based on the presence of children.
     * If the node has children, it calls the {@link #reorderWithChildren(Node, Node[])} method.
     * Otherwise, it calls the {@link #reorderWithoutChildren(Node)} method.
     *
     * @param keyToAdd     the node to be added to the B-tree
     * @param keysChildren the array of child nodes to be added, or null if the node has no children
     */
    // Main method that calls the helper methods based on the condition
    private void reorder(Node keyToAdd, Node[] keysChildren) {
        if (keysChildren != null)
            reorderWithChildren(keyToAdd, keysChildren);
        else
            reorderWithoutChildren(keyToAdd);
    }

    /**
     * Helper method to reorder the keys when the node has children.
     * It inserts the given node and its child nodes in the appropriate positions in the array of keys.
     * If the size of the node reaches order - 1, it promotes the middle key to the parent node.
     * Otherwise, it adds the node to the array and updates the children accordingly.
     *
     * @param keyToAdd     the node to be added to the B-tree
     * @param keysChildren the array of child nodes to be added
     */
    // Method to sort with children
    private void reorderWithChildren(Node keyToAdd, Node[] keysChildren) {
        Node[] keysAux = new Node[order];
        keysAux[0] = keyToAdd;
        System.arraycopy(keysChildren, 0, keysAux, 1, keysChildren.length);
        Arrays.sort(keysAux, Comparator.nullsLast(Comparator.naturalOrder()));
        Node node = keysAux[m];

        TreeNode[] treeNodes = divideTreeNode(keysAux, node);
        node.setLeft(treeNodes[0]);
        node.setRight(treeNodes[1]);

        long size = Arrays.stream(keys).filter(Objects::nonNull).count();

        if (size == order - 1) {
            Node[] keysAuxFather = new Node[order];
            keysAuxFather[0] = keyToAdd;
            System.arraycopy(keys, 0, keysAuxFather, 1, keys.length);
            Arrays.sort(keysAuxFather, Comparator.nullsLast(Comparator.naturalOrder()));
            Node nodeFather = keysAuxFather[m];
            TreeNode[] treeNodes1 = divideTreeNode(keysAuxFather, nodeFather);
            nodeFather.setLeft(treeNodes1[0]);
            nodeFather.setRight(treeNodes1[1]);
            Node[] keysUpdate = new Node[order - 1];
            keysUpdate[0] = nodeFather;
            keys = keysUpdate;
        } else {
            addValueInArray(keys, node);
            deleteNode(node);
        }
    }

    /**
     * Helper method to reorder the keys when the node has no children.
     * It inserts the given node in the appropriate position in the array of keys.
     * If the size of the node reaches order - 1, it promotes the middle key to the parent node.
     * Otherwise, it adds the node to the array and increments the node count.
     *
     * @param keyToAdd the node to be added to the B-tree
     */
    // Method to order without children
    private void reorderWithoutChildren(Node keyToAdd) {
        Node[] keysAux = new Node[order];
        keysAux[0] = keyToAdd;
        System.arraycopy(keys, 0, keysAux, 1, keys.length);
        Arrays.sort(keysAux, Comparator.nullsLast(Comparator.naturalOrder()));
        Node node = keysAux[m];

        TreeNode[] treeNodes = divideTreeNode(keysAux, node);
        node.setLeft(treeNodes[0]);
        node.setRight(treeNodes[1]);

        long size = Arrays.stream(keys).filter(Objects::nonNull).count();

        if (size == order - 1) {
            Node[] keysUpdate = new Node[order - 1];
            keysUpdate[0] = node;
            keys = keysUpdate;
            count++;
        } else {
            addValueInArray(keys, node);
            deleteNode(node);
        }
    }

    /**
     * Deletes the specified node from the B-tree.
     * It removes the node from the array of keys and updates the children accordingly.
     *
     * @param node the node to be deleted from the B-tree
     */
    private void deleteNode(Node node){
        for (int i = 1; i < keys.length; i++) {
            if (keys[i] != null) {
                Node nodePrev = keys[i - 1];
                if (keys[i].equals(node)) {
                    nodePrev.setRight(keys[i].getLeftNode());
                    break;
                }
            }
        }
    }

    /**
     * Adds the given node to the array of keys in the appropriate position.
     * If there is an empty slot in the array, it inserts the node in that slot.
     * Otherwise, it calls the {@link #reorder(Node, Node[])} method to rearrange the keys.
     * Finally, it sorts the keys in ascending order.
     *
     * @param keys      the array of keys to which the node is added
     * @param keyToAdd  the node to be added to the B-tree
     */
    private void addValueInArray(Node[] keys, Node keyToAdd) {
        if (Arrays.stream(keys).anyMatch(Objects::isNull)) {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] == null) {
                    keys[i] = keyToAdd;
                    break; }
            }
        } else reorder(keyToAdd, keys);
        Arrays.sort(keys, Comparator.nullsLast(Comparator.naturalOrder()));
    }

    /**
     * Adds a key to the B-tree.
     * It inserts the key to the appropriate position in the node's array of keys.
     * If the node's size reaches order - 1, and it is the root node (count == 1), it promotes the middle key to the parent node.
     * Otherwise, it adds the key to the array and updates the children accordingly.
     * Finally, it sorts the keys in ascending order.
     *
     * @param key the key to be added to the B-tree
     */
    public void addKey(int key) {
        Node keyToAdd = new Node(key, order);
        if (size() == order - 1 && count == 1) {
            reorder(keyToAdd, null);
            count++;
        }
        else {
            int index = getIndexToAdd(keys, keyToAdd);
            if (keys[index] != null) addValueToChildNode(keys[index], keyToAdd);
            else addValueInArray(keys, keyToAdd);
        } Arrays.sort(keys, Comparator.nullsLast(Comparator.naturalOrder()));
    }

    /**
     * Helper method to add a key to a child node.
     * If the parent node has children, it determines the appropriate child node based on the key's value.
     * Then, it calls the {@link #addValueInArray(Node[], Node)} method to add the key to the child node's array of keys.
     * If the parent node has no children, it directly adds the key to the parent node's array of keys.
     *
     * @param parentNode the parent node whose child node is updated with the key
     * @param keyToAdd   the key to be added to the child node
     */
    private void addValueToChildNode(Node parentNode, Node keyToAdd) {
        if (parentNode.hasChildren()) {
            TreeNode childNode = (keyToAdd.getData() < parentNode.getData()) ? parentNode.getLeftNode() : parentNode.getRightNode();
            addValueInArray(childNode.getKeys(), keyToAdd);
        } else addValueInArray(keys, keyToAdd);
    }

    /**
     * Determines the index where the given key should be added to the array based on its value.
     * It finds the index that corresponds to the closest key to the given key's value in the array.
     * The search is performed using the absolute difference of key values.
     *
     * @param array the array of keys
     * @param value the key whose index is to be determined
     * @return the index in the array where the key should be added
     */
    private int getIndexToAdd(Node[] array, Node value) {
        int indexNearValue = 0;
        if (array[0] != null) {
            int nearValue = array[0].getData();

            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    if (Math.abs(array[i].getData() - value.getData()) < Math.abs(nearValue - value.getData()))
                        indexNearValue = i; } }
        } return indexNearValue;
    }

    /**
     * Finds the node in the B-tree that contains the given key.
     * It creates a node with the specified key value and recursively searches the B-tree to find the corresponding node.
     *
     * @param key the key to search for in the B-tree
     * @return the TreeNode containing the specified key, or null if the key is not found in the B-tree
     */
    public TreeNode getTreeNode(int key) {
        Node nodeToFind = new Node(key, order);
        return findNode(this, nodeToFind);
    }

    /**
     * Helper method used by {@link #getTreeNode(int)} to recursively search for the node containing the given key.
     * It traverses the B-tree starting from the current node and looks for the node containing the specified key.
     * If the current node's keys array contains the key, it returns the current node.
     * Otherwise, it recursively searches in the left or right child node based on the key's value.
     *
     * @param currentNode the current node being examined during the search
     * @param nodeToFind  the node with the key value to find in the B-tree
     * @return the TreeNode containing the specified key, or null if the key is not found in the B-tree
     */
    private TreeNode findNode(TreeNode currentNode, Node nodeToFind) {
        Node[] nodes = currentNode.getKeys();

        for (Node node : nodes) {
            if (node != null) {
                if (node.compareTo(nodeToFind) == 0) return currentNode;
                else if (node.compareTo(nodeToFind) > 0) return findNode(node.getLeftNode(), nodeToFind);
                else return findNode(node.getRightNode(), nodeToFind);
            }
        } return null; // Node not found
    }

    @Override
    public String toString() {
        return  Arrays.stream(keys)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}

