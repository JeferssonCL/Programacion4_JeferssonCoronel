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
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

