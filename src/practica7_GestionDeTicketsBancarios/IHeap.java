package practica7_GestionDeTicketsBancarios;

/**
 * The IHeap interface represents a heap data structure, which is a specialized tree-based
 * data structure that satisfies the heap property. A heap can be used to efficiently
 * retrieve and manipulate the minimum or maximum element from a collection.
 */
public interface IHeap <T extends Comparable<T>> {

     void moveElementToRight(int index);


     void updateValueAtIndex(int index, T newValue);

    /**
     * This method is used to get the quantity of elements in the heap.
     *
     * @return The number of elements in the heap.
     */
    int size();

    /**
     * Removes and returns the top element from the heap, maintaining the heap property.
     *
     * @return The top element of the heap.
     */
    T poll();

    /**
     * Retrieves the top element from the heap without removing it.
     *
     * @return The top element of the heap.
     */
    T peek();

    /**
     * Retrieves the element at the specified index in the heap.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     */
    T get(int index);

    /**
     * Searches for the specified value in the heap and returns its index.
     *
     * @param value The value to search for in the heap.
     * @return The index of the first occurrence of the value in the heap; otherwise, -1.
     */
    int search(T value);

    /**
     * Removes the first occurrence of the specified value from the heap, if present.
     *
     * @param value The value to be removed from the heap.
     */
    void remove(T value);

    /**
     * Inserts the specified value into the heap and maintains the heap property.
     *
     * @param value The value to be inserted into the heap.
     */
    void insert(T value);

    /**
     * Retrieves the underlying array representation of the heap.
     *
     * @return The array representation of the heap.
     */
    T[] getHeap();
}

