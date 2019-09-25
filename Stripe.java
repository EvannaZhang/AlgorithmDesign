package ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Stripe {
	static boolean answer = true;
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
	   public static void main(String[] args) { 
	        InputReader in = new InputReader(System.in);
	        PrintWriter out = new PrintWriter(System.out);
	        int cases = in.nextInt();
	        for(int i=0; i<cases; i++){
	        	answer = true;
	        	int number = in.nextInt();
	        	int color[] = new int[number];
	        	for(int j=0; j<number; j++){
	        		color[j] = in.nextInt();
	        	}
	        	check(color,number);
	        	if(answer==true)
	        		find(color,0,number-1);
	        	
	        	if(answer==true)
	        		out.println("Yes");
	        	else
	        		out.println("No");
	        }
	        
	        out.close();
	    }
	   public static void check(int[] color, int length){
		   if(length>1){
			   int last = color[0];
			   for(int i=1; i<length; i++){
				   if(color[i]==last){
					   answer = false;
					   break;
				   }else{
					   last = color[i];
				   }
			   }
		   }
	   }
	   public static void find(int[] color,int begin, int end){
		   	if(end-begin<=0)
			   return;
		   	HashMap<Integer, Integer> test = new HashMap<Integer,Integer>();
       		for(int j=begin; j<=end; j++){
       			int nowcolor = color[j];
       			if(test.get(nowcolor)==null){
       				test.put(nowcolor, 1);
       			}
       			else{
       				int temp = test.get(nowcolor);
       				++temp;
       				test.remove(nowcolor);
       				test.put(nowcolor, temp);
       			}
       		}
       		int last = begin;
       		int pointcnt = 0;
       		for(int j=begin; j<=end; j++){
       			int now = color[j];
       			int value = test.get(now);
       			if(value==1){
       				pointcnt++;
       				if(j>last+1){
       					find(color,last,j-1);
       				}
       				last = j+1;
       			}
       		}
       		if(pointcnt>0&&last<end)
       			find(color,last,end);
       		if(pointcnt==0){
       			answer = false;
       			return;
       		}
       		
	   }
}