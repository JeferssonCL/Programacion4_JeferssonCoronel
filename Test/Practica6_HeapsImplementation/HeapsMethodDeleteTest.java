package Practica6_HeapsImplementation;

import Practica6_HeapsImplementation.Parte1.Heap;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class HeapsMethodDeleteTest {

    @Test
    public void testRemoveElementExistsInMinHeap() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
        minHeap.insert(7);
        assertEquals("[1]\n" + "[3][8]\n" + "[5][7]\n", minHeap.toString());

        minHeap.remove(5);
        assertEquals("[1]\n" + "[3][8]\n" + "[7]\n", minHeap.toString());
    }

    @Test
    public void testRemoveRootElementFromMinHeap() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        assertEquals("[3]\n" + "[5][8]\n", minHeap.toString());

        minHeap.remove(5);
        assertEquals("[3]\n" + "[8]\n", minHeap.toString());
    }

    @Test
    public void testRemoveLastElementFromMinHeap() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        assertEquals("[5]\n", minHeap.toString());


        minHeap.remove(5);
        assertEquals("MinHeap is empty.", minHeap.toString());
    }

    @Test
    public void testRemoveElementNotPresentInMinHeap() {
        Heap minHeap = new Heap(10, true);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        assertEquals("[3]\n" + "[5][8]\n", minHeap.toString());

        minHeap.remove(10);
        assertEquals("[3]\n" + "[5][8]\n", minHeap.toString());
    }

    @Test
    public void testRemoveElementExistsInMaxHeap() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);
        maxHeap.insert(7);
        assertEquals("[8]\n" + "[7][5]\n" + "[1][3]\n", maxHeap.toString());

        maxHeap.remove(5);
        assertEquals("[8]\n" + "[7][3]\n" +  "[1]\n", maxHeap.toString());
    }

    @Test
    public void testRemoveRootElementFromMaxHeap() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        assertEquals("[8]\n" + "[3][5]\n", maxHeap.toString());

        maxHeap.remove(8);
        assertEquals("[5]\n" + "[3]\n", maxHeap.toString());
    }

    @Test
    public void testRemoveLastElementFromMaxHeap() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(5);
        assertEquals("[5]\n", maxHeap.toString());

        maxHeap.remove(5);
        assertEquals("MaxHeap is empty.", maxHeap.toString());
    }

    @Test
    public void testRemoveElementNotPresentInMaxHeap() {
        Heap maxHeap = new Heap(10, false);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        assertEquals("[8]\n" + "[3][5]\n", maxHeap.toString());

        maxHeap.remove(10);
        assertEquals("[8]\n" + "[3][5]\n", maxHeap.toString());
    }
}
