
import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main( String args[])
	{
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		while( t-->0)
		{
			int n = scan.nextInt();
			int[] a= new int[n];
			for( int i=0; i<n; i++)
			{
				a[i] = scan.nextInt();
			}
			Arrays.sort(a);
			int c = 0;
			for( int i=0; i<n; i++)
			{
				if(a[i]==a[0]) c++;
				else break;
			}
			System.out.println((long)(a[0])*(long)n +(long)( n-c));
		}
	}
}