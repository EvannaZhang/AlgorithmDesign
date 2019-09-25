package ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class contest{
	ArrayList<Integer> distance = new ArrayList<Integer>();
	boolean check = false;
	ArrayList<Integer> next = new ArrayList<Integer>();
}
class pairnode{
	int distance;
	int number;
}
public class Champion {
	public static class MinComparator implements Comparator<pairnode>{
		public int compare(pairnode first, pairnode second){
			if(second.distance < first.distance)
				return 1;
			else
				return -1;
		}
	}
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
	        InputReader in = new InputReader(System.in);
	        PrintWriter out = new PrintWriter(System.out);
	        int number = in.nextInt();
	        int min = Integer.MAX_VALUE;
	        int begin1 = 0, begin2 = 0;
	        contest all[] = new contest[number+1];
	        for(int i=0; i<=number; i++)
	        	all[i] = new contest();
	        for(int i=1; i<=number;i++){
	        	int dis = in.nextInt();
	        	if(dis<min){
	        		min = dis;
	        		begin1 = 0;
	        		begin2 = 1;
	        	}
	        	all[i-1].next.add(i);
	        	all[i-1].distance.add(dis);
	        	all[i].next.add(i-1);
	        	all[i].distance.add(dis);
	        	for(int j=0; j<number-i; j++){
	        		int distance = in.nextInt();
	        		if(distance<min){
	        			min = distance;
		        		begin1 = i+j;
		        		begin2 = i+j+1;
		        	}
	        		all[i-1].next.add(i+j+1);
	        		all[i-1].distance.add(distance);
	        		all[i+j+1].next.add(i-1);
	        		all[i+j+1].distance.add(distance);
	        	}
	        }
        	
	        PriorityQueue<pairnode> prim = new PriorityQueue<pairnode>(new MinComparator());
	        	all[begin1].check = true;
	        	all[begin2].check = true;
	        	int answer = min;
	        	for(int i=0; i<all[begin1].next.size();i++){
	        		int now = all[begin1].next.get(i);
	        		if(all[now].check==false){
	        			pairnode test = new pairnode();
	        			test.distance = all[begin1].distance.get(i);
	        			test.number = now;
	        			prim.add(test);
	        		}
	        	}
	        	
	        	for(int i=0; i<all[begin2].next.size();i++){
	        		int now = all[begin2].next.get(i);
	        		if(all[now].check==false){
	        			pairnode test = new pairnode();
	        			test.distance = all[begin1].distance.get(i);
	        			test.number = now;
	        			prim.add(test);
	        		}
	        	}
	        	while(!prim.isEmpty()){
	        		pairnode top = prim.poll();
	        		if(all[top.number].check==false){
	        		 	answer = answer +top.distance;
	        			all[top.number].check = true;
	        			for(int i=0; i<all[top.number].next.size();i++){
	        				int now = all[top.number].next.get(i);
	        				if(all[now].check==false){
	        					pairnode test = new pairnode();
	        					test.number = now;
	        					test.distance = all[top.number].distance.get(i);
	        					prim.add(test);
	        				}
	        				
	        			}
	        		}
	        	}
	       out.println(answer);
	       out.close();
	    }
}
