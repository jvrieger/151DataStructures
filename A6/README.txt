Julia Rieger
How to Compile:
How to Run:
No known bugs/limitations
Discussion:	Design of remove: I chose to create a private helper method so remove will be recursive.
		       	  	  I called this removeRec, which takes the parameters of a root node and
				  key E. The helper methods process four cases, 1) if root is null (returns
				  null because there is nothing to remove) 2) if root's data is more than
				  the key, in which the program will move to the root's left to recursively
				  call removeRec again, where the root's left is the new root. 3) if the root's
				  data is less than the key, in which the program will move to the root's right
				  to recursively call removeRec again, where the root's right is the new root,
				  and 4) if the root is null, it will move to different cases, 1) if the root's
				  left is null (returns root's right), 2) if the root's right is null (returns
				  root's left), and 3) if the root has two children, it will call a different
				  helper method called minKey(root), which will return the leftmost node in the
				  root's right subtree. minKey will replace this node with the previous root (that
				  is being removed) and reorder the right subtree to fit.

				  The general algorithm of this method is it finds the element to remove in a
				  binary search fashion, and if it is found in the tree it will go through
				  cases, if the key is a leaf, has one child, or has two children. Based on those
				  three cases it may call a seperate minKey method (for two children) to remove and
				  reorder the tree in a way that doesn't ruin the structure of it/delete nodes
				  you didn't want deleted.

		Extra Credit: My polls will process in date order no matter the order they're given on the commandline
