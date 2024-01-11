Julia Rieger

Discussion: 1.2 Determining Uniqueness:
	    In my PersonStopped class, I determine uniqueness through creating
	    instance variables and implementing Comparable. The attributes I
	    chose to determine unique personhood is: SEX, RACE, DATE OF BIRTH,
	    HAIR COLOR, EYE COLOR. My reasoning for these attributes is these
	    are descriptors often found on identification, which doesn't change
	    with new identification. My reasoning for not choosing attributes
	    such as height in ft and in, or weight, is because though those
	    descriptors can also be found on identification, they are much more
	    often updated with renewal of documentation. I concatonate these 5
	    attributes I chose into a unique String, which should only return
	    the same value (and therefore match Comparables) to another
	    PersonStopped object which represented the same person in a different
	    SQF occassion.

	    2 Complexity Analysis
	    The time in milliseconds of my 5 methods results are as follows:
	    
	    rows	0	50000	100000	150000
	    __________________________________________
	    allPairs	0	32163	118720	249936
	    hashLinear  4	35	44	52
	    hashDouble	3	41	51	59
	    builtinSort	1133	12312	16007	17712
	    quickSort	1934	12960	16577	18272

	    (NOTE: complexity.png contains all 5 method, but the 2 hash
	     methods are on top of each other due to such similar results
	     and both colors cannot be seen)

	    According to my time analysis, in practice, my hashLinear method
	    was most efficient of the 5.
	    
	    Hash methods: LinearHash was more efficient than DoubleHash by 7
	    	 	  milliseconds for this round of testing. Though double
			  hashing is thought to be more efficient in theory,
			  I hypothisize that since the capacity of my hash
			  table was so much larger than the data set (load factor
			  was about .16), there were not so many opportunities
			  for probing (on average inserting required around 1.1
			  probes, and max number of probes stayed from 6-8).
			  Because probing versus double hashing is where double
			  hashing takes the time superiority, there was not
			  as much opportunity for doubleHashing to prove itself
			  more efficient and the extra lines of code made it
			  even slightly less efficient than linear probing.
			  
	    Sorting methods: Java's builtinSort (mergeSort) was more efficient
	    	    	     than my implementation of quickSort. This was the
			     expected outcome for me because while mergeSort
			     is O(logn), quickSort is only expected to be
			     O(logn), and not gauranteed. This is because quickSort
			     can depend on luck for whether pivots chosen were
			     "good pivots" or not. Additionally, the Java builtin
			     sort has been through much more optimization than
			     my implementation of quickSort, and is expected to
			     be the more efficient method.
	   
