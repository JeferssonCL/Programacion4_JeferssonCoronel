package BTree_Opcion2;

public class BTree {

    private int order;
    private TreeNode root;

    public BTree(int order) {
        this.order = order;
        this.root = new TreeNode(order);
    }

    public void add(int value) {
        root.addKey(value);
    }

    @Override
    public String toString() {
        return "BTree{" +
                "order=" + order +
                ", root=" + root +
                '}';
    }
}
