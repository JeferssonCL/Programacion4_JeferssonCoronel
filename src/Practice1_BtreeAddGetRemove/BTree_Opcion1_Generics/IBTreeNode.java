package Practice1_BtreeAddGetRemove.BTree_Opcion1_Generics;

public interface IBTreeNode<T extends Comparable<T>> {

    T getKey(int index);
    int indexOf(T value);
    void insertKey(T value);
    T removeKey(T value);
    T removeKey(int index);
    int numberOfKeys();
    TreeNode<T> getChild(int index);
    int indexOf(TreeNode<T> child);
    boolean insertChild(TreeNode<T> child);
    boolean removeChild(TreeNode<T> child);
    TreeNode<T> removeChild(int index);
}
