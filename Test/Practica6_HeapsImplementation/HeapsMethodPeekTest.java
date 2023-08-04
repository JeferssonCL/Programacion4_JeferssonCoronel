package Practica6_HeapsImplementation;

import Practica6_HeapsImplementation.Parte1.Heap;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class HeapsMethodPeekTest {

    @Test
    public void testPeekInMinHeap() {
        Heap minHeap = new Heap(true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals(1, minHeap.peek());
    }

    @Test
    public void testPeekInMaxHeap() {
        Heap maxHeap = new Heap(false);
        maxHeap.insert(10);
        maxHeap.insert(7);
        maxHeap.insert(12);
        maxHeap.insert(15);
        maxHeap.insert(9);

        assertEquals(15, maxHeap.peek());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testPeekEmptyHeap() {
        Heap minHeap = new Heap(true);
        minHeap.peek(); // Should throw an exception as the heap is empty.
    }

    @Test
    public void testPeekDuplicateValues() {
        Heap minHeap = new Heap(true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(5);
        minHeap.insert(1);

        assertEquals(1, minHeap.peek()); // Should return the minimum value.
    }
}