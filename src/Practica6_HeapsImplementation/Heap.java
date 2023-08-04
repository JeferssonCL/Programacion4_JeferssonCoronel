package Practica6_HeapsImplementation;

import java.util.LinkedList;
import java.util.Queue;


/**
 * The Heap class represents a binary heap data structure. It can be used as either
 * a MinHeap or a MaxHeap based on the value of the isMinHeap parameter provided
 * during initialization.
 */
public class Heap {
    private final int[] heap;
    private int size;
    private final int capacity;
    private final boolean isMinHeap;

    /**
     * Creates a new Heap with the given capacity and heap type.
     *
     * @param capacity   The maximum number of elements that the heap can hold.
     * @param isMinHeap  If true, the heap will be a MinHeap; if false, it will be a MaxHeap.
     */
    public Heap(int capacity, boolean isMinHeap) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
        this.isMinHeap = isMinHeap;
    }

    /**
     * Inserts a new value into the heap.
     *
     * @param value The value to be inserted.
     * @throws IllegalStateException if the heap is full and cannot insert more elements.
     */
    public void insert(int value) {
        if (size == capacity)
            throw new IllegalStateException("Heap is full, cannot insert.");

        heap[size] = value;
        size++;

        reOrderUp(size - 1, isMinHeap);
    }

    /**
     * Removes the first occurrence of the specified value from the heap, if present.
     *
     * @param value The value to be removed.
     */
    public void remove(int value) {
        int indexToRemove = search(value);
        if (indexToRemove == -1)
            return;

        heap[indexToRemove] = heap[size - 1];
        size--;

        reOrderDown(indexToRemove, isMinHeap);
    }

    /**
     * Reorders the heap starting from the specified index downwards to maintain the heap property.
     *
     * @param index The index from which the reordering should start.
     * @param isMinHeap A boolean flag indicating whether the heap is a MinHeap (true) or MaxHeap (false).
     */
    private void reOrderDown(int index, boolean isMinHeap) {
        int currentIdx = index;

        while (true) {
            int childIdx;
            if (isMinHeap) childIdx = findChild(currentIdx, false);
            else childIdx = findChild(currentIdx, true);

            if (childIdx != currentIdx) {
                swap(currentIdx, childIdx);
                currentIdx = childIdx;
            } else break;
        }
    }

    /**
     * Reorders the heap starting from the specified index upwards to maintain the heap property.
     *
     * @param index The index from which the reordering should start.
     * @param isMinHeap A boolean flag indicating whether the heap is a MinHeap (true) or MaxHeap (false).
     */
    private void reOrderUp(int index, boolean isMinHeap) {
        int currentIdx = index;
        int parentIdx = (currentIdx - 1) / 2;

        while (currentIdx > 0) {
            boolean shouldSwap;
            if (isMinHeap) shouldSwap = heap[currentIdx] < heap[parentIdx];
            else shouldSwap = heap[currentIdx] > heap[parentIdx];

            if (shouldSwap) {
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = (currentIdx - 1) / 2;
            } else break;
        }
    }

    /**
     * Finds the index of the child node (either the largest or the smallest) based on the specified flag.
     *
     * @param index The index of the parent node.
     * @param findLargest A boolean flag indicating whether to find the largest child (true) or the smallest child (false).
     * @return The index of the child node based on the specified flag.
     */
    private int findChild(int index, boolean findLargest) {
        int leftChildIdx = leftChild(index);
        int rightChildIdx = rightChild(index);
        int targetIdx = index;

        if (findLargest) {
            if (leftChildIdx < size && heap[leftChildIdx] > heap[targetIdx])
                targetIdx = leftChildIdx;

            if (rightChildIdx < size && heap[rightChildIdx] > heap[targetIdx])
                targetIdx = rightChildIdx;
        } else {
            if (leftChildIdx < size && heap[leftChildIdx] < heap[targetIdx])
                targetIdx = leftChildIdx;

            if (rightChildIdx < size && heap[rightChildIdx] < heap[targetIdx])
                targetIdx = rightChildIdx;
        } return targetIdx;
    }

    /**
     * Returns the index of the first occurrence of the specified value in the heap, if present.
     *
     * @param value The value to be searched for.
     * @return The index of the value in the heap, or -1 if the value is not present.
     */
    public int search(int value) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == value)
                return i;
        } return -1;
    }

    /**
     * Swaps the elements at the specified indices in the heap array.
     *
     * @param i The index of the first element to be swapped.
     * @param j The index of the second element to be swapped.
     */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Returns the index of the left child for a given parent index in the heap array.
     *
     * @param index The index of the parent node.
     * @return The index of the left child node.
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child for a given parent index in the heap array.
     *
     * @param index The index of the parent node.
     * @return The index of the right child node.
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * Adds the index of the left child node of the given parent index to the specified queue,
     * if the left child exists within the valid range of indices in the heap.
     *
     * @param queue The queue to which the left child index will be added.
     * @param index The index of the parent node.
     */
    private void addLeftChildToQueue(Queue<Integer> queue, int index) {
        int leftChildIndex = leftChild(index);
        if (leftChildIndex < size) queue.offer(leftChildIndex);
    }

    /**
     * Adds the index of the right child node of the given parent index to the specified queue,
     * if the right child exists within the valid range of indices in the heap.
     *
     * @param queue The queue to which the right child index will be added.
     * @param index The index of the parent node.
     */
    private void addRightChildToQueue(Queue<Integer> queue, int index) {
        int rightChildIndex = rightChild(index);
        if (rightChildIndex < size) queue.offer(rightChildIndex);
    }

    /**
     * Returns a string representation of the Heap. The string displays the elements of the heap
     * arranged in a level-order traversal.
     *
     * @return A string representation of the Heap in level-order traversal format.
     *         If the heap is empty, returns "MinHeap is empty." for MinHeap or "MaxHeap is empty." for MaxHeap.
     */
    @Override
    public String toString() {
        if (size == 0) return isMinHeap ? "MinHeap is empty." : "MaxHeap is empty.";

        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            StringBuilder levelSb = new StringBuilder();

            for (int i = 0; i < levelSize; i++) {
                int index = queue.poll();
                levelSb.append("[").append(heap[index]).append("]");

                addLeftChildToQueue(queue, index);
                addRightChildToQueue(queue, index);
            }
            sb.append(levelSb);
            sb.append("\n");
        } return sb.toString();
    }
}