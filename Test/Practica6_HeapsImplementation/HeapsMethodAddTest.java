package Practica6_HeapsImplementation;

import Practica6_HeapsImplementation.Parte1.Heap;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class HeapsMethodAddTest {

    @Test
    public void testInsertToMinHeap() {
        Heap minHeap = new Heap(10, true);

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals("[1]\n" + "[3][8]\n" + "[5][7]\n", minHeap.toString());
    }

    @Test
    public void testInsertToMaxHeap() {
        Heap maxHeap = new Heap(10, false);

        maxHeap.insert(10);
        maxHeap.insert(7);
        maxHeap.insert(12);
        maxHeap.insert(15);
        maxHeap.insert(9);

        assertEquals("[15]\n" + "[12][10]\n" + "[7][9]\n", maxHeap.toString());
    }

    @Test
    public void testInsertDuplicateValues() {
        Heap minHeap = new Heap(10, true);

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(5);
        minHeap.insert(1);

        assertEquals("[1]\n" + "[3][5]\n" + "[5]\n", minHeap.toString());
    }

    @Test
    public void testInsertSingleValue() {
        Heap maxHeap = new Heap(10, false);

        maxHeap.insert(10);

        assertEquals("[10]\n", maxHeap.toString());
    }
}
