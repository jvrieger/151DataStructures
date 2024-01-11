public class Main {
    public static void main(String[] args) {
	LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();
	tree.insert(6);
	tree.insert(4);
	tree.insert(2);
	tree.insert(7);
	tree.insert(3);
	tree.insert(9);
	tree.insert(8);
	tree.insert(5);
	System.out.println("\nTree currently: ");
	tree.printInOrder();
	System.out.println();
       	System.out.println();


	System.out.println("Tree contains 4: " + tree.contains(4));
	System.out.println("Tree contains 1: " + tree.contains(1));

	System.out.println("Size: " + tree.size());
	System.out.println("Height: " + tree.height());
	System.out.println();
    }
}
