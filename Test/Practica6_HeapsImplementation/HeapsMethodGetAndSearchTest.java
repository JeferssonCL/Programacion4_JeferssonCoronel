package Practica6_HeapsImplementation;

import Practica6_HeapsImplementation.Parte1.Heap;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

public class HeapsMethodGetAndSearchTest {

    @Test
    public void testSearchInMinHeap() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);

        assertEquals(0, minHeap.search(1)); // return the index
        assertEquals(1, minHeap.get(0)); // return the value using index

        assertEquals(1, minHeap.search(3));
        assertEquals(3, minHeap.get(1));

        assertEquals(4, minHeap.search(7));
        assertEquals(7, minHeap.get(4));
    }

    @Test
    public void testSearchExistingElement() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(20);

        int result = maxHeap.search(30);
        assertEquals(1, result);
        assertEquals(30, maxHeap.get(result));
    }

    @Test
    public void testSearchNonExistingElement() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(30);
        minHeap.insert(40);

        int result = minHeap.search(25);
        assertEquals(-1, result);
    }

    @Test
    public void testSearchDuplicateElements() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(50);
        maxHeap.insert(70);

        int result = maxHeap.search(70);
        assertEquals(0, result); // The first occurrence of 70 is at index 1.
        assertEquals(70, maxHeap.get(result));
    }

    @Test
    public void testSearchInMaxHeap() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(10);
        maxHeap.insert(7);
        maxHeap.insert(12);
        maxHeap.insert(15);
        maxHeap.insert(9);
        /*
           15(0)
         /       \
        12(1)     10(2)
       /    \
      7(3)   9(4)  */

        assertEquals(2, maxHeap.search(10));
        assertEquals(10, maxHeap.get(2));

        assertEquals(3, maxHeap.search(7));
        assertEquals(7, maxHeap.get(3));

        assertEquals(1, maxHeap.search(12));
        assertEquals(12, maxHeap.get(1));
    }

    @Test
    public void testSearchEmptyHeap() {
        Heap minHeap = new Heap(10, true);
        assertEquals(-1, minHeap.search(5)); // Empty heap
    }

    @Test
    public void testSearchForDuplicateValues() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(5);
        minHeap.insert(1);

        assertEquals(2, minHeap.search(5)); // Returns the index of the first occurrence
        assertEquals(5, minHeap.get(2));
    }
}