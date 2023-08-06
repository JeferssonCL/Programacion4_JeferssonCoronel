package Practica6_HeapsImplementation.Parte2;

import java.util.PriorityQueue;

public class HeapExample {

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(5);
        heap.add(10);
        heap.add(7);
        heap.add(20);
        heap.add(15);

        System.out.print("Heap: ");
        while (!heap.isEmpty()) {
            System.out.print(heap.poll() + " ");
        }
    }
}
