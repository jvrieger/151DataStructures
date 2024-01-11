Julia Rieger

known bugs/limitations: Program cannot remove more than one candidate with the -r flag
      			ArrayHeap toString() is not always accurate


Discussion: Design of peekTopN: I copied the heap to a new arraylist which
	    	      		had a time complexity of O(n) because I had
				to iterate through every element in the heap.
				I then used Collections.sort to sort this
				ArrayList so the candidates with the highest
				poll results would be at the top. This has
				a time complexity of O(nlogn) according
				to javadoc. I then created another arraylist,
				this one just to hold the top n candidates,
				and copied the top n candidates to this
				list, which has a complexity of O(k), where
				k is the top k elements of the dataset n.
				In total this method has a time complexity
				of O(nlogn + n + k).
			
