package ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.*;


public class River {
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
	   public static void main(String[] args) 
	    { 
	        InputReader in = new InputReader(System.in);
	        PrintWriter out = new PrintWriter(System.out);
	        int cases = in.nextInt();
	        for(int i=0; i<cases; i++){
	        	int man = in.nextInt();
	        	int big = in.nextInt();
	        	int small = in.nextInt();
	        	int answer = 0;
	        	if(big%2==0){
	        		if(man>0){
	        			answer = answer + man;
	        			if(small>man*2){
	        				small = small - man*2;
	        				answer = answer + (int)Math.ceil((double)small/3);
	        				if(big>0)
	        					answer = answer + big/2;
	        			}else{
	        				man = man - (int)Math.ceil((double)small/2);
	        				if(man>0)
	        					big = big - man;
	        				if(big>0)
	        					answer = answer +(int)Math.ceil((double)big/2);
	        			}
	        		}else{
	        			if(small>0)
	        				answer = (int) (answer + Math.ceil((double)small/3));
	        			if(big>0)
	        				answer = answer +(int)Math.ceil((double)big/2);
	        		}
	        	}else{ //big odd
	        		answer++;
	        		big = big-1;
	        		man = man-1;
	        		if(man>0){
	        			answer = answer + man;
	        			if(small>man*2){
	        				small = small - man*2;
	        				answer = answer + (int)Math.ceil((double)small/3);
	        				if(big>0)
	        					answer = answer + big/2;
	        			}else{
	        				man = man - (int)Math.ceil((double)small/2);
	        				if(man>0)
	        					big = big - man;
	        				if(big>0)
	        					answer = answer +(int)Math.ceil((double)big/2);
	        			}
	        		}else{
	        			if(small>0)
	        				answer = (int) (answer + Math.ceil((double)small/3));
	        			if(big>0)
	        				answer = answer +(int)Math.ceil((double)big/2);
	        		}
	        	}
	        	out.println(answer);
	  
	        	
	        }
	        out.close();
	    }
}
