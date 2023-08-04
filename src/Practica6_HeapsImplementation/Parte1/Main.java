package Practica6_HeapsImplementation.Parte1;

public class Main {

    public static void main(String[] args) {
        Heap maxHeap = new Heap(10, false);

        maxHeap.insert(500);
        maxHeap.insert(10);
        maxHeap.insert(62);
        maxHeap.insert(74);
        maxHeap.insert(10);
        maxHeap.insert(-6);
        maxHeap.insert(9);
        maxHeap.insert(-60);
        maxHeap.insert(-80);
        maxHeap.insert(8);

        System.out.println(maxHeap);
        System.out.println();
        System.out.println();
        maxHeap.remove(74);
        System.out.println(maxHeap);
        System.out.println();
        System.out.println();
        maxHeap.remove(-60);
        System.out.println(maxHeap);
        System.out.println();
        System.out.println();
        maxHeap.remove(500);
        System.out.println(maxHeap);
        System.out.println();
        System.out.println();
    }
}
