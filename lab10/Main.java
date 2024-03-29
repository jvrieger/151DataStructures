public class Main {
    public static void main(String[] args) {
	LinkedBinaryTree<String> test = new LinkedBinaryTree<String>("M");
	System.out.println("\nLINKED BINARY TREE");
	test.insert("M");
	test.insert("N");
	test.insert("O");
	test.insert("L");
	test.insert("K");
	test.insert("Q");
	test.insert("P");
	test.insert("H");
	test.insert("I");
	test.insert("A");
	//System.out.println(test.remove("A"));
	//System.out.println(test.remove("B"));
	//System.out.println(test.remove("Z"));
	//System.out.println(test.getRootElement());
	System.out.println(test);

	AVLTree<String> tree = new AVLTree<String>("M");
	System.out.println("\n\nAVLTREE");
	tree.insert("N");
	tree.insert("O");
	tree.insert("L");
	tree.insert("K");
	tree.insert("Q");
	tree.insert("P");
	tree.insert("H");
	tree.insert("I");
	tree.insert("A");
	System.out.println(tree);
    }
}
