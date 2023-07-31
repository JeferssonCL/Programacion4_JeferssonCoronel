package BTree_Opcion1_Generics;

/**
 * IBTree is an interface that defines the contract for a B-tree data structure. A B-tree is a self-balancing tree data
 * structure that maintains sorted data and allows for efficient insertion, deletion, and search operations.
 *
 * @param <T> The type of elements that the B-tree can hold.
 */
public interface IBTree<T> {

    void addTreeNode(T value);
    String toString();
}
