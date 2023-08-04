package Practica6_HeapsImplementation;

import Practica6_HeapsImplementation.Parte1.Heap;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class HeapsMethodPollTest {

    @Test
    public void testPollInMinHeap() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals(1, minHeap.poll());
        assertEquals(3, minHeap.poll());
        assertEquals(5, minHeap.poll());
        assertEquals(7, minHeap.poll());
        assertEquals(8, minHeap.poll());
    }

    @Test
    public void testPollInMaxHeap() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(10);
        maxHeap.insert(7);
        maxHeap.insert(12);
        maxHeap.insert(15);
        maxHeap.insert(9);

        assertEquals(15, maxHeap.poll());
        assertEquals(12, maxHeap.poll());
        assertEquals(10, maxHeap.poll());
        assertEquals(9, maxHeap.poll());
        assertEquals(7, maxHeap.poll());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testPollEmptyHeap() {
        Heap minHeap = new Heap(10, true);
        minHeap.poll(); // Should throw an exception as the heap is empty.
    }

    @Test
    public void testPollDuplicateValues() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(5);
        minHeap.insert(1);

        assertEquals(1, minHeap.poll()); // Should return the minimum value.
        assertEquals(3, minHeap.poll()); // After removing the minimum, the next minimum is 3.
    }
}
