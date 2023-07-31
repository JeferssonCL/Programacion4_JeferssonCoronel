package BTree_Opcion2;

import java.util.*;
import java.util.stream.Collectors;

public class TreeNode {
    private Node[] keys;
    private int order;
    private int m;
    int count;

    public TreeNode(int order) {
        this.order = order;
        this.m = Math.round((float) order /2) - 1;
        this.keys = new Node[order - 1];
        this.count = 1;
    }

    public TreeNode(Node[] elements) {
        this.keys = elements;
    }

    public Node[] getKeys() {
        return keys;
    }

    public void setKeys(Node[] keys) {
        this.keys = keys;
    }

    public long size() {
        return Arrays.stream(keys).filter(Objects::nonNull).count();
    }

    public TreeNode[] divideTreeNode(Node[] arr, Node nodeToHead){
        List<Node> arregloMenores = new ArrayList<>();
        List<Node> arregloMayores = new ArrayList<>();

        for (Node node : arr) {
            if (node.getData() < nodeToHead.getData()) arregloMenores.add(node);
            else if (node.getData() > nodeToHead.getData()) arregloMayores.add(node);
        }

        Node[][] divisionArreglos = new Node[2][];
        divisionArreglos[0] = Arrays.copyOf(arregloMenores.toArray(new Node[0]), 3);
        divisionArreglos[1] = Arrays.copyOf(arregloMayores.toArray(new Node[0]), 3);
        return new TreeNode[] {new TreeNode(divisionArreglos[0]), new TreeNode(divisionArreglos[1])};
    }

    private void reorder(Node keyToAdd, Node[] keysChildren) {
        Node[] keysAux = new Node[order];
        keysAux[0] = keyToAdd;
        System.arraycopy(keysChildren, 0, keysAux, 1, keysChildren.length);
        Arrays.sort(keysAux, Comparator.nullsLast(Comparator.naturalOrder())); // 4 elemntos

        System.out.println(" ]] " + Arrays.toString(keysAux));
        Node node = keysAux[m];

        TreeNode[] treeNodes = divideTreeNode(keysAux, node);
        node.setLeft(treeNodes[0]);
        node.setRight(treeNodes[1]);

        System.out.println(" " + node);

        long size = Arrays.stream(keys).filter(Objects::nonNull).count();

        if(size == order - 1 && count == 1) {
            Node[] keysUpdate = new Node[order - 1];
            keysUpdate[0] = node;
            keys = keysUpdate;
            count++;
        }
        else if (size == order - 1 && count != 1) {
            keysAux[0] = keyToAdd;
            System.arraycopy(keys, 0, keysAux, 1, keys.length);
            Arrays.sort(keysAux, Comparator.nullsLast(Comparator.naturalOrder())); // 4 elemntos


            TreeNode[] treeNodes1 = divideTreeNode(keysAux, node);

            System.out.println(" a1:  " +node);
            System.out.println(" a2: " + Arrays.toString(treeNodes1));
        }
        else {
            addValueInArray(keys, node);
            deleteNode(node);
        }
    }


    private void deleteNode(Node node){
        for (int i = 1; i < keys.length; i++) {
            if (keys[i] != null) {
                Node nodePrev = keys[i - 1];
                if (keys[i].equals(node)) {
                    nodePrev.setRight(keys[i].getLeftNode());
                    break;
                }
            }
        }
    }

    private void addValueInArray(Node[] keys, Node keyToAdd) {
        if (Arrays.stream(keys).anyMatch(Objects::isNull)) {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] == null) {
                    keys[i] = keyToAdd;
                    break;
                }
            }
        }
        else {
            reorder(keyToAdd, keys);
        }
        Arrays.sort(keys, Comparator.nullsLast(Comparator.naturalOrder()));
    }

    public void addKey(int key) {
        Node keyToAdd = new Node(key, order);
        if (size() == order - 1 && count == 1) {
            reorder(keyToAdd, keys);
            count++;
        }
        else {
            int index = getIndexToAdd(keys, keyToAdd);
            if (keys[index] != null) {
                if (keys[index].hasChildren()) {
                    if (key < keys[index].getData())
                        addValueInArray(keys[index].getLeftNode().getKeys(), keyToAdd);
                    else
                        addValueInArray(keys[index].getRightNode().getKeys(), keyToAdd);
                }
                else {
                    addValueInArray(keys, keyToAdd);
                }
            }
            else {
                addValueInArray(keys, keyToAdd);
            }
        } Arrays.sort(keys, Comparator.nullsLast(Comparator.naturalOrder()));
    }

    private int getIndexToAdd(Node[] array, Node value) {
        int indexValorCercano = 0;
        if (array[0] != null) {
            int valorMasCercano = array[0].getData();

            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    if (Math.abs(array[i].getData() - value.getData()) < Math.abs(valorMasCercano - value.getData()))
                        indexValorCercano = i;
                }
            }
        }
        return indexValorCercano;
    }

    @Override
    public String toString() {
        return  Arrays.stream(keys)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}

