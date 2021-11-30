/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] arr = nextIntArray(N, br);
			sb.append(solve(arr)).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static long solve(int[] arr) {
		long dp[] = new long[arr.length];
		Arrays.fill(dp, (long) 1e18);
		dp[0] = 0;
		int min = arr[0];
		int minIndex = 0;
		for (int i = 1; i < arr.length; i++) {
			int j = minIndex;
			long f = i + 1 - j;
			long val = dp[j] + (f * arr[j]) - arr[i];
			dp[i] = Math.min(dp[i], val);
       
      if(arr[i] < min){
        min = arr[i];
				minIndex = i;
      }
		}
		return Math.max(0, dp[dp.length - 1]);
}

	private static int[] nextIntArray(int N, BufferedReader br) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		int arr[] = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		return arr;
	}

	private static class Pair implements Comparable<Pair> {
		int value, idx;

		Pair(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}

		@Override
		public int compareTo(Pair o) {
			return value - o.value;
		}
	}
}
