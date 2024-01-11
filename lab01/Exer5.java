	public class Exer5 {
       	      
       	      public static boolean isPrime(int x) {

		     if(x <= 1) {
		         return false;
		     }

	      	     for(int i = 2; i <= x/2; i++) {
		     	     if(x%i == 0) {
			     	    return false;
			     }
		     }
		     return true;
   	      }

	      public static double average(int[] arr) {
	      
		double total = 0.0;
		for(int i = 0; i < arr.length; i++) {
			total += arr[i];

		}
		return total/arr.length;
	      }

	      public static int count(String str1, String str2) {

	      	     int count = 0;
		     for (int i = 0; i < str1.length(); i++) {
		     	 if(str1.indexOf(str2, i) != -1) {
			 	count ++;
			 }
		     }
		     return count;
	      }

	      public static void main(String[] args) {

	      	     System.out.println(isPrime(1));
		     System.out.println(isPrime(17));
		     System.out.println(isPrime(20));
                     int[] intArray1 = new int[]{10,20,30,40,50,60,70,80};
                     int[] intArray2 = new int[]{2,4};
		     System.out.println(average(intArray1));
		     System.out.println(average(intArray2));
		     System.out.println(count("aaaa", "aa"));
	     	     System.out.println(count("catdog", "cat"));
		     System.out.println(count("meowmeowmeowmeowmeow", "meow"));

	      }
	}
