package Practica4_BtreeAddGetRemove.BTree_Opcion1;

import Practica4_BtreeAddGetRemove.BTree_Opcion1_Generics.BTree;
import Practica4_BtreeAddGetRemove.BTree_Opcion1_Generics.TreeNode;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class BTreeMethodGetTest {

    private BTree<Integer> bTree;


    @Test
    public void testGetEmptyTree() {
        bTree = new BTree<>(3);

        TreeNode<Integer> result = bTree.getTreeNode(5);
        assertNull(result);
    }

    @Test
    public void testGetSingleNodeTree() {
        BTree<Integer> bTree = new BTree<>(3);
        bTree.add(10);

        TreeNode<Integer> result = bTree.getTreeNode(10);

        assertNotNull(result);
        assertEquals(1, result.numberOfKeys());
        assertEquals(Integer.valueOf(10), result.getKey(0));
    }

    @Test
    public void testGetNonExistentKey() {
        BTree<Integer> bTree = new BTree<>(3);
        bTree.add(5);
        bTree.add(10);
        bTree.add(15);

        TreeNode<Integer> result = bTree.getTreeNode(12);

        assertNull(result);
    }

    @Test
    public void testGetExistingKey() {
        BTree<Integer> bTree = new BTree<>(3);
        bTree.add(5);
        bTree.add(10);
        bTree.add(15);

        TreeNode<Integer> result = bTree.getTreeNode(10);

        assertNotNull(result);
        assertEquals(1, result.numberOfKeys());
        assertEquals(Integer.valueOf(10), result.getKey(0));
    }

    @Test
    public void testGetExistingKeyInMultipleNodes() {
        BTree<Integer> tree = new BTree<>(3);
        tree.add(5);
        tree.add(10);
        tree.add(15);
        tree.add(20);
        tree.add(25);

        TreeNode<Integer> result = tree.getTreeNode(15);

        assertNotNull(result);
        assertEquals(1, result.numberOfKeys());
        assertEquals(Integer.valueOf(15), result.getKey(0));
    }

    @Test
    public void testGetExistingKeyInDeepTree() {
        BTree<Integer> tree = new BTree<>(3);
        for (int i = 1; i <= 100; i++) {
            tree.add(i);
        }

        TreeNode<Integer> result = tree.getTreeNode(50);

        assertNotNull(result);
        assertEquals(1, result.numberOfKeys());
        assertEquals(Integer.valueOf(50), result.getKey(0));
    }

    @Test
    public void testGetExistingKeyWithDuplicateValues() {
        bTree = new BTree<>(4);
        bTree.add(5);
        bTree.add(10);
        bTree.add(15);
        bTree.add(15);
        bTree.add(20);

        TreeNode<Integer> result = bTree.getTreeNode(15);

        assertNotNull(result);
        assertEquals(2, result.numberOfKeys());
        assertEquals(Integer.valueOf(15), result.getKey(0));
    }
}
