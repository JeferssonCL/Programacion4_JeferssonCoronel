package BTree;

import BTree_Opcion1.BTree;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class BTreeMethodAdd {
    private BTree<Integer> bTree;

    @Test
    public void testAddSingleNode() {
        bTree = new BTree<>(2);
        bTree.addTreeNode(10);
        assertEquals(1, bTree.getSize());
        assertEquals("[10]\n", bTree.toString());
    }

    @Test
    public void testAddMultipleNodes() {
        bTree = new BTree<>(5);
        bTree.addTreeNode(10);
        bTree.addTreeNode(5);

        assertEquals(2, bTree.getSize());
        assertEquals("[5, 10]\n", bTree.toString());
    }

    @Test
    public void testAddEmptyTree() {
        bTree = new BTree<>(3);
        assertEquals(0, bTree.getSize());
        assertEquals("BTree is empty.", bTree.toString());
    }
    @Test
    public void testAddDescendingOrder() {
        bTree = new BTree<>(6);
        bTree.addTreeNode(10);
        bTree.addTreeNode(8);
        bTree.addTreeNode(6);
        bTree.addTreeNode(4);
        bTree.addTreeNode(2);
        assertEquals(5, bTree.getSize());
        assertEquals("[2, 4, 6, 8, 10]\n", bTree.toString());
    }
}
