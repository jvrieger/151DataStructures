public class Crash1
{
    static int[] a = { 10, 20, 30, 40, 50 };
    
    public static void main(String[] args) {
	
	for (int i = 0; i <= a.length; i++) {
	    System.out.println(a[i]);
	}
	
	System.out.println("Done printing the array!");
    }
}
