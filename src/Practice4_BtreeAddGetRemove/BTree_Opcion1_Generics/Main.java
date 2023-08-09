package Practice4_BtreeAddGetRemove.BTree_Opcion1_Generics;

public class Main {

    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<>(4);
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

        System.out.println(bTree);
        System.out.println(bTree.getLevels().size());
        bTree.remove(18);
        System.out.println(bTree);
        System.out.println(bTree.getTreeNode(12));
    }
}
