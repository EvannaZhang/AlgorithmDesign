package ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

import ad.Twodimension.InputReader;

public class FFT {
	static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
        
        public char[] nextCharArray() {
            return next().toCharArray();
        }
        
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }
        
        public BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }
        
    }
	public static void main(String[] args){
		int[] ans = new int[10];
		ArrayList<Integer> answ = new ArrayList<Integer>();
		
	
		InputReader in = new InputReader(System.in);
	    PrintWriter out = new PrintWriter(System.out);
	    int n = in.nextInt();
	    int p =in.nextInt();
	    int a[] = new int[n];
	    for(int i=0; i<n; i++){
	    	a[i] = in.nextInt();
	    }
	    for(int i=1; i<=p; i++){
	    	out.println(answer(a,i-1,p));
	    }
	    out.close();
	}
	
	public static int answer(int[] a, int s, int p){
		int need = s%p;
		int count = 0;
		for(int i=0; i<a.length; i++){
			for(int j=0; j<a.length; j++){
				int first = a[i]%p;
				int second = a[j]%p;
				long ans = first * second;
				if(ans%p==need){
					count = count+1;
				}
			}
		}
		
		
		return count;
	}
}
