package Practica6_HeapsImplementation.Parte2;

import java.util.TreeSet;

public class BTreeExample {

    public static void main(String[] args) {
        TreeSet<Integer> bTree = new TreeSet<>();

        bTree.add(5);
        bTree.add(10);
        bTree.add(7);
        bTree.add(20);
        bTree.add(15);

        System.out.println("BTree: " + bTree);
    }
}
