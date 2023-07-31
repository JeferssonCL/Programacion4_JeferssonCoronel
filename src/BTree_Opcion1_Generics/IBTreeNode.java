package BTree_Opcion1_Generics;

public interface IBTreeNode<T extends Comparable<T>> {

    T getKey(int index);
    void insertKey(T value);
    int getNumberOfKeys();
    TreeNode<T> getChild(int index);
    void removeChild(TreeNode<T> child);
    int getNumberOfChildren();
}
