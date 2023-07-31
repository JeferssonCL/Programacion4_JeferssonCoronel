package BTree_Opcion1;

public class Main {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<Integer>(5);

        bTree.addTreeNode(190);
        bTree.addTreeNode(57);
        bTree.addTreeNode(89);
        bTree.addTreeNode(90);
        bTree.addTreeNode(121);
        bTree.addTreeNode(170);
        bTree.addTreeNode(35);
        bTree.addTreeNode(48);
        bTree.addTreeNode(91);
        bTree.addTreeNode(22);
        bTree.addTreeNode(126);
        bTree.addTreeNode(132);
        bTree.addTreeNode(80);
        bTree.addTreeNode(200);

        System.out.println(bTree);
    }

}