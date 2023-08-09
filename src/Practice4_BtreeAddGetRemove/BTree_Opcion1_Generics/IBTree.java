package Practice4_BtreeAddGetRemove.BTree_Opcion1_Generics;

public interface IBTree<T> {
    void add(T value);
    T remove(T value);
    String toString();
}
