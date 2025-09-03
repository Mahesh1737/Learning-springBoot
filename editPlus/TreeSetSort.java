import java.util.*;
import java.io.*;

class TreeSetSort 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		TreeSet<> t = new TreeSet<>();
		System.out.println("Enter the number of ele you want to add = ");
		int n = sc.nextInt();
		System.out.println("Enter the numbers = ");
		for (int i=0;i<n ;i++ ){
			t.add(sc.nextInt());
		}
		
		System.out.println(" "+t);

	}
}
