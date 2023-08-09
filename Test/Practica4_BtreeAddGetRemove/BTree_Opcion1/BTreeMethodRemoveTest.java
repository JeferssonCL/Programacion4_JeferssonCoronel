package Practica4_BtreeAddGetRemove.BTree_Opcion1;

import Practica4_BtreeAddGetRemove.BTree_Opcion1_Generics.BTree;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class BTreeMethodRemoveTest {
    private BTree<Integer> tree;

    @Test
    public void testRemoveFromSingleNodeTree() {
        tree = new BTree<>(3);
        tree.add(10);

        Integer removedValue = tree.remove(10);

        assertNull(tree.getTreeNode(10));
        assertEquals(Integer.valueOf(10), removedValue);
    }

    @Test
    public void testRemoveNonExistentValue() {
        tree = new BTree<>(3);
        tree.add(5);
        tree.add(10);
        tree.add(15);

        Integer removedValue = tree.remove(12);

        assertNull(tree.getTreeNode(12));
        assertNull(removedValue);
    }

    @Test
    public void testRemoveValueFromMultipleNodes() {
        tree = new BTree<>(3);
        tree.add(5);
        tree.add(10);
        tree.add(15);
        tree.add(20);
        tree.add(25);

        Integer removedValue = tree.remove(15);

        assertNull(tree.getTreeNode(15));
        assertEquals(Integer.valueOf(15), removedValue);
    }

    @Test
    public void testRemoveValueFromDeepTree() {
        tree = new BTree<>(3);
        for (int i = 1; i <= 100; i++) {
            tree.add(i);
        }

        Integer removedValue = tree.remove(50);

        assertNull(tree.getTreeNode(50));
        assertEquals(Integer.valueOf(50), removedValue);
    }

    @Test
    public void testRemoveValueFromLargeTree() {
        tree = new BTree<>(5);
        for (int i = 1; i <= 1000; i++) tree.add(i);

        Integer removedValue = tree.remove(999);

        assertNull(tree.getTreeNode(999));
        assertEquals(Integer.valueOf(999), removedValue);
    }

    @Test
    public void testRemoveSingleNodeTree() {
        BTree<Integer> tree = new BTree<>(3);
        tree.add(10);

        tree.remove(10);

        assertEquals(0, tree.getLevels().size());
    }

    @Test
    public void testRemoveExistingKeyInSingleNodeUsingLevelsToVerifyIt() {
        BTree<Integer> tree = new BTree<>(3);
        tree.add(10);

        tree.remove(10);

        assertEquals(0, tree.getLevels().size());
    }

    @Test
    public void testRemoveAllKeysUsingLevelsToVerifyIt() {
        BTree<Integer> tree = new BTree<>(4);
        for (int i = 1; i <= 100; i++) {
            tree.add(i);
        }

        for (int i = 1; i <= 100; i++) {
            tree.remove(i);
        }

        assertEquals(0, tree.getLevels().size());
    }
}
