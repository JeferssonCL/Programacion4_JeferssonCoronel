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


    public Heap(int capacity, boolean isMinHeap) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
        this.isMinHeap = isMinHeap;
    }

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


    private int leftChild(int index) {
        return 2 * index + 1;
    }


    private int rightChild(int index) {
        return 2 * index + 2;
    }


    private void addLeftChildToQueue(Queue<Integer> queue, int index) {
        int leftChildIndex = leftChild(index);
        if (leftChildIndex < size) queue.offer(leftChildIndex);
    }

    private void addRightChildToQueue(Queue<Integer> queue, int index) {
        int rightChildIndex = rightChild(index);
        if (rightChildIndex < size) queue.offer(rightChildIndex);
    }

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

