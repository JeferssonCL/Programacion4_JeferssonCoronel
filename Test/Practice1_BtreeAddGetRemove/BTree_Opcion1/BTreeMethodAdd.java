package Practice1_BtreeAddGetRemove.BTree_Opcion1;

import Practice1_BtreeAddGetRemove.BTree_Opcion1_Generics.BTree;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class BTreeMethodAdd {
    private BTree<Integer> bTree;

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
    public void testAddDescendingOrder() {
        bTree = new BTree<>(6);
        bTree.add(10);
        bTree.add(8);
        bTree.add(6);
        bTree.add(4);
        bTree.add(2);

        assertEquals("[2, 4, 6, 8, 10]\n", bTree.toString());
    }
}
