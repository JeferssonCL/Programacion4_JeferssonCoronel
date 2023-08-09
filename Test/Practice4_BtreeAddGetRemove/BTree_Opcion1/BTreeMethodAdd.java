package Practice4_BtreeAddGetRemove.BTree_Opcion1;

import Practice4_BtreeAddGetRemove.BTree_Opcion1_Generics.BTree;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class BTreeMethodAdd {
    private BTree<Integer> bTree;

    @Test
    public void testBTreeAdditionWithIntegersOrder4() {
        bTree = new BTree<>(4);

        // Add elements in ascending order
        bTree.add(1);
        bTree.add(2);
        bTree.add(3);

        // Verify elements are inserted and sorted correctly
        assertEquals("[1, 2, 3]\n", bTree.toString());
    }

    @Test
    public void testBTreeAdditionWithDuplicates() {
        bTree = new BTree<>(4);

        // Add duplicate elements
        bTree.add(1);
        bTree.add(2);
        bTree.add(1);
        bTree.add(2);
        bTree.add(3);
        bTree.add(3);

        // Verify duplicate elements are not added
        assertEquals("[1, 2, 3]\n", bTree.toString());
    }

    @Test
    public void testBTreeAdditionWithNegativeValues() {
        bTree = new BTree<>(5);

        // Add elements with negative values
        bTree.add(-10);
        bTree.add(5);
        bTree.add(-5);
        bTree.add(0);

        // Verify elements are inserted and sorted correctly
        assertEquals("[-10, -5, 0, 5]\n", bTree.toString());
    }

    @Test
    public void testBTreeAdditionWithLargeBTree() {
        bTree = new BTree<>(12);

        // Add elements in a large B-tree
        bTree.add(10);
        bTree.add(20);
        bTree.add(5);
        bTree.add(15);
        bTree.add(30);
        bTree.add(25);
        bTree.add(35);
        bTree.add(8);
        bTree.add(7);
        bTree.add(22);
        bTree.add(18);

        // Verify elements are inserted and sorted correctly
        assertEquals("[5, 7, 8, 10, 15, 18, 20, 22, 25, 30, 35]\n", bTree.toString());
    }

    @Test
    public void testAddSingleNode() {
        bTree = new BTree<>(2);
        bTree.add(10);

        assertEquals("[10]\n", bTree.toString());
    }

    @Test
    public void testAddMultipleNodes() {
        bTree = new BTree<>(5);
        bTree.add(10);
        bTree.add(5);

        assertEquals("[5, 10]\n", bTree.toString());
    }

    @Test
    public void testAddEmptyTree() {
        bTree = new BTree<>(3);

        assertEquals("BTree is empty.", bTree.toString());
    }

    @Test
    public void testBTreeAdditionWithMultipleElementsSorted() {
        bTree = new BTree<>(4);
        bTree.add(10);
        bTree.add(20);
        bTree.add(30);
        assertEquals("[10, 20, 30]\n", bTree.toString());
    }

    @Test
    public void testBTreeAdditionWithMultipleElementsUnsorted() {
        bTree = new BTree<>(4);
        bTree.add(30);
        bTree.add(10);
        bTree.add(20);
        assertEquals("[10, 20, 30]\n", bTree.toString());
    }

    @Test
    public void testBTreeAdditionWithRepeatedElements() {
        bTree = new BTree<>(3);
        bTree.add(10);
        bTree.add(20);
        bTree.add(20);
        bTree.add(10);
        assertEquals("[10, 20]\n", bTree.toString());
    }

    @Test
    public void testBTreeAdditionWithLargeNumbers() {
        bTree = new BTree<>(5);
        bTree.add(1000);
        bTree.add(10000);
        bTree.add(100);
        bTree.add(100000);
        assertEquals("[100, 1000, 10000, 100000]\n", bTree.toString());
    }

    @Test
    public void testBTreeWith1Level() {
        bTree = new BTree<>(4);
        bTree.add(7);
        bTree.add(12);
        bTree.add(15);

        assertEquals(bTree.getLevels().size(), 1);
    }

    @Test
    public void testBTreeWith2Levels() {
        bTree = new BTree<>(4);
        bTree.add(7);
        bTree.add(12);
        bTree.add(15);
        bTree.add(50);
        bTree.add(22);
        bTree.add(46);

        assertEquals(bTree.getLevels().size(), 2);
    }

    @Test
    public void testBTreeWith3Levels() {
        bTree = new BTree<>(4);
        bTree.add(7);
        bTree.add(12);
        bTree.add(15);
        bTree.add(50);
        bTree.add(22);
        bTree.add(46);
        bTree.add(23);
        bTree.add(39);
        bTree.add(26);
        bTree.add(8);
        bTree.add(1);
        bTree.add(2);
        bTree.add(18);
        bTree.add(78);
        bTree.add(32);
        bTree.add(29);

        assertEquals(bTree.getLevels().size(), 3);
    }
}
