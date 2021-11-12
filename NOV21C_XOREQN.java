import java.io.*;
import java.util.*;

import javax.swing.text.Segment;

public class Main
{
    static PrintWriter out = new PrintWriter((System.out));
    static Kioken sc = new Kioken();

    public static void main(String[] args)
    {
      int t = 1;
      t = sc.nextInt();
      while (t-- > 0)
      {
          solve();
      }
      out.close();
    }

    public static void solve()
    {
      int n = sc.nextInt();
      long[] arr = new long[n];
      for(int i = 0; i < n; i++){
        arr[i] = sc.nextLong();
      }

      // int[] bits = new int[32];
      // for(int i = 0; i < n; i++){
      //   calculateBit(arr[i], bits);
      // }

      int index = 0;
      long x = 0;
      long x1 = 0L;
      boolean add = true;
      while(index < 64){
        if(add){
          for(int i = 0; i < n; i++){
            // out.println(" xx " + x1);
            arr[i] += x1;
          }
        }
       

        long newXor = 0L;
        for(int i = 0; i < n; i++){
          newXor = newXor^arr[i];
        }

        if(newXor == 0){
           out.println(x);
           return;
        }
        String s = Long.toBinaryString(newXor);
        // out.println("ss " + s);
        char c;
        if(index >= s.length()){
          c = '0';
        }else{
          c = s.charAt(s.length() - 1 - index);
        }

        // out.println(s + " and" + c + " - " + newXor);
        if(c == '1'){
          // odd no. of 1s and even no. of 0s
          // out.println(" -- " + (1 << index));
          x += leftShift(index);
          x1 = leftShift(index);
          add = true;
        }else add = false;
        index++;
      }

      out.println(-1);
      return;

    }

    static void calculateBit(int val, int[] bit){

      int index = 0;
      while(val > 0){
        if((val & 1) == 1){
          bit[index]++;
        }
        index++;
        val = val >> 1;
      }
      return;
    }
    public static long leftShift(long a){
        return (long)Math.pow(2, a);
    }

    public static int lower_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0, e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) <= k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        return Math.abs(s) - 1;
    }

    public static int upper_bound(ArrayList<Integer> ar, int k)
    {
        int s = 0;
        int e = ar.size();
        while (s != e)
        {
            int mid = s + e >> 1;
            if (ar.get(mid) < k)
            {
                s = mid + 1;
            }
            else
            {
                e = mid;
            }
        }
        if (s == ar.size())
        {
            return -1;
        }
        return s;
    }

    public static int[] z_function(String s) {
        int n = (int) s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r)
                z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i]))
                ++z[i];
            if (i + z[i] - 1 > r){
                l = i; r = i + z[i] - 1;
            }
        }
        return z;
    }

    static class Kioken
    {
        // FileInputStream br = new FileInputStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        public String next()
        {
            while (!st.hasMoreTokens())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt()
        {
            return Integer.parseInt(next());
        }

        public long nextLong()
        {
            return Long.parseLong(next());
        }

        public double nextDouble()
        {
            return Double.parseDouble(next());
        }

        public String nextLine()
        {
            try
            {
                return br.readLine();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        public boolean hasNext()
        {
            String next = null;
            try
            {
                next = br.readLine();
            }
            catch (Exception e)
            {
            }
            if (next == null || next.length() == 0)
            {
                return false;
            }
            st = new StringTokenizer(next);
            return true;
        }
    }


    static class FenwickTree{
        int[] arr;
        int N = 0;
       
        FenwickTree(int n, int[] aa) {
            // index in FT is always 1 more than in array
            N = n + 1;
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = 0;
            }

            for (int i = 0; i < aa.length; i++) {
                update(i, aa[i]);
            }
        }

        void update(int idx, int val) {
            idx++;
            while (idx < N) {
                // Add 'val' to current node of BIT Tree
                arr[idx] += val;
                // Update index to that of parent
                // in update View
                idx += idx & (-idx);
            }
        }

        int sum(int index) {
            int sum = 0; // Initialize result

            // index in BITree[] is 1 more than
            // the index in arr[]
            index = index + 1;

            // Traverse ancestors of BITree[index]
            while (index > 0) {
                // Add current element of BITree
                // to sum
                sum += arr[index];

                // Move index to parent node in
                // getSum View
                index -= index & (-index);
            }
            return sum;
        }

        // just like prefix sum
        int rangeSum(int l, int r){
            return sum(r) - sum(l-1);
        }

        // binary lifting
        // return the lowerBound index for target sum
        int getLBSumIndex(int sum, int target) {
            int curr = 0, prevSum = 0;
            for (int i = N; i >= 0; i--) {
                if (curr + (1 << i) < N) {
                    if (arr[curr + (1 << i)] + prevSum < target) {
                        curr = curr + (1 << i);
                        prevSum = arr[curr];
                    }
                }
            }
            return (curr + 1);
        }

        void print() {
            out.println(Arrays.toString(arr));
        }
    }

    static class SegmentTree{
        int[] arr = new int[4*100000];
        int[] givenArr;
        // HINT: This can be updated with ques.
        int build(int index, int l, int r){
            if(l == r){
                return arr[index] = givenArr[l];
            }
            int mid = (l+r)/2;
           return arr[index] = build(2*index+1, l, mid) + build(2*index+2, mid+1, r);
        }
        SegmentTree(int[] nums) {
            givenArr = nums;
            build(0, 0, nums.length - 1);
        }

        // HINT: This can be updated with ques.
        void update(int index, int l, int r, int diff, int i) {
            if(i>=arr.length){return ;}
            if(index>=l && index<=r){
                arr[i] = arr[i]+diff;
            }
            if(index<l || index>r){
                return;
            }
            int mid = (l+r)/2;
            update(index,l,mid, diff, 2*i+1);
            update(index,mid+1, r, diff, 2*i+2);
            return;
        }

        void update(int index, int val){
            int diff = val - givenArr[index];
            givenArr[index] = val;
            update(index, 0, givenArr.length-1, diff, 0);
        }

        int query(int left, int right, int l, int r, int i) {
            // not overlapping
            if (r < left || l > right) {
                return 0;
            }

            // total - overlapping
            if (l >= left && r <= right) {
                return arr[i];
            }

            // partial overlapping
            int mid = (l + r) / 2;
            int le = query(left, right, l, mid, 2 * i + 1);
            int ri = query(left, right, mid + 1, r, 2 * i + 2);
            return le + ri;
        }
        // HINT: for max sum, can be changed according to ques.
        int query(int l , int r){
            return query(l, r, 0, givenArr.length-1, 0);
        }
    }
}