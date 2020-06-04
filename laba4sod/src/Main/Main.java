package Main;

import java.util.Random;
import java.util.Scanner;

class Node {
    Node parent;
    boolean used = false;
    private int key;
    Node left;
    Node right;
    Node (int key) {
        this.key = key;
    }

    int getKey() {
        return this.key;
    }

}

class BinaryTree {
    private Node root;
    void add(int key) {
        Node node = new Node(key);
        if (root == null) { root = node; return; }
        Node current = root;
        int val = current.getKey();
        while (true) {
            if (val < 0 && Math.abs(val)%2==1) {
                if (key < 0 && Math.abs(key)%2==1) {
                    if (key >= val) {
                        if (current.left == null) {
                            current.left = node;
                            node.parent = current;
                            break;
                        } else {
                            current = current.left;
                            val = current.getKey();
                        }
                    } else {
                        if (current.right == null) {
                            current.right = node;
                            node.parent = current;
                            break;
                        } else {
                            current = current.right;
                            val = current.getKey();
                        }
                    }
                } else {
                    if (current.right == null) {
                        current.right = node;
                        node.parent = current;
                        break;
                    } else {
                        current = current.right;
                        val = current.getKey();
                    }
                }
            } else {
                if (key < 0 && Math.abs(key)%2==1) {
                    if (current.left == null) {
                        current.left = node;
                        node.parent = current;
                        break;
                    } else {
                        current = current.left;
                        val = current.getKey();
                    }
                } else {
                    if (key >= val) {
                        if (current.right == null) {
                            current.right = node;
                            node.parent = current;
                            break;
                        } else {
                            current = current.right;
                            val = current.getKey();
                        }
                    } else {
                        if (current.left == null) {
                            current.left = node;
                            node.parent = current;
                            break;
                        } else {
                            current = current.left;
                            val = current.getKey();
                        }
                    }
                }
            }
        }
    }

    String printbinarytree() {
        StringBuilder srt = new StringBuilder();
        Node current = root;
        int height = 1;
        int maxHeight = 1;
        while (current != null) {
            if (current.left != null && !current.left.used) {
                current = current.left;
                height++;
                if (maxHeight < height) maxHeight = height;
            } else if (!current.used) {
                srt.append(current.getKey()).append(" ");
                current.used = true;
            } else if (current.right != null && !current.right.used) {
                current = current.right;
                height++; if (maxHeight < height) maxHeight = height;
            } else {
                if (current.right != null) {
                    current.right.used = false;
                }
                if (current.left != null) {
                    current.left.used = false;
                }
                current = current.parent;
                height--;
            }
        }
        if (root.left != null) {
            root.left.used = false;
        }
        if (root.right != null) {
            root.right.used= false;
        }
        root.used = false;
        srt.append("\n Высота дерева: ").append(maxHeight);
        return srt.toString();
    }
}



public class Main {

    private BinaryTree bt;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        bt = new BinaryTree();
        Scanner sc = new Scanner(System.in);
        int element = 50;
        long start = System.currentTimeMillis();
        addbt(element);
        long finish = System.currentTimeMillis() - start;
        System.out.println("Время: " + finish+ " мс.");
        System.out.println(bt.printbinarytree());
    }

    private void addbt(int element) {
        for (int i = 0; i < element; i++) {
            int value = (int) ((Math.random()*50)-25);
            bt.add(value);
        }
    }
}
