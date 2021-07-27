package datastructure;

public class Test {
    public static void main(String[] args) {
        BalanceBinaryTree tree = new BalanceBinaryTree();
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.root = tree.root.lSpin();
        System.out.println(123);
    }
}
