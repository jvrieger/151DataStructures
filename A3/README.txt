Write-up
1. name, rank, number, totalRank, totalNumber, yearlyStats, rankSort
2. I keep the lists in sorted order by only inserting Names using the insertSorted() method
   which inserts a Name alphabetically into the list. When I update the info for a certain name
   I add it to the list in the same index which the original Name was.
3. I put the yearly statistics per name in the Name class in an ArrayList, and I store the totals
   in the Name class as private int instance variables.
4. The overall totals are stored in Main.java in the printOutput() method, the yearly totals are
   stored in the Main.java in the main method.
5. Total Rank is computed in a method in the linked list class

Julia Rieger
How to run: java Main <sex-flag> <Name> <filenames>
Known Bugs and Lmitations: slight variations with the rank, index, and percentages in my output vs expected output
