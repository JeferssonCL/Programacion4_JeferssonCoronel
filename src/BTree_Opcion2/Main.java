package BTree_Opcion2;

public class Main {
    public static void main(String[] args) {
        BTree bTree = new BTree(4);
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
        bTree.add(17);
        bTree.add(1);

        System.out.println(bTree);
    }
}