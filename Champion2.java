package ad;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Champion2 {
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
	 public static int partition(int[][] array,int lo,int hi){
	        int key = array[lo][2];
	        int key2 = array[lo][0];
	        int key3 = array[lo][1];
	        while(lo<hi){
	            while(array[hi][2]>=key&&hi>lo){
	                hi--;
	            }
	            array[lo][2]=array[hi][2];
	            array[lo][0]=array[hi][0];
	            array[lo][1]=array[hi][1];
	            while(array[lo][2]<=key&&hi>lo){
	                lo++;
	            }
	            array[hi][2]=array[lo][2];
	            array[hi][0]=array[lo][0];
	            array[hi][1]=array[lo][1];
	        }
	        array[hi][2]=key;
	        array[hi][0]=key2;
	        array[hi][1]=key3;
	        return hi;
	    }
	    
	    public static void sort(int[][] array,int lo ,int hi){
	        if(lo>=hi){
	            return ;
	        }
	        int index=partition(array,lo,hi);
	        sort(array,lo,index-1);
	        sort(array,index+1,hi); 
	    }
	    
	    public static void main(String[] args) 
	    { 
	        InputReader in = new InputReader(System.in);
	        PrintWriter out = new PrintWriter(System.out);
	        
	        int number = in.nextInt();
	        int edge[][] = new int [number*(number+1)/2][3];
	        int count = -1;
	        for(int i=1; i<=number;i++){ //from 1 to number
	        	int input = in.nextInt();
	        	count++;
	        	edge[count][0] = i-1; //0
	        	edge[count][1] = i;
	        	edge[count][2] = input;

	        	for(int j=0; j<number-i; j++){ //from 0 to number-i-1
	        		int bian = in.nextInt();
	        		count++;
	        		edge[count][0] = i-1; //i
	        		edge[count][1] = i+j+1;
	        		edge[count][2] = bian;
	        	}
	        }//edge stored
	        sort(edge,0,number*(number+1)/2-1);
	       
	        int father[] = new int[number+1];
	        for(int i=0; i<number+1;i++)
	        	father[i] = -1;
	        long answer = 0;
	        int countedge = 1;
	        father[edge[0][0]] = edge[0][0];
	        father[edge[0][1]] = edge[0][0];
	        answer = edge[0][2];
	        for(int i=1; i<number*(number+1)/2; i++){
	        	if(father[edge[i][0]]<0 && father[edge[i][1]]<0){
	        		answer = answer + edge[i][2];
	        		father[edge[i][0]] = edge[i][0];
	        		father[edge[i][1]] = edge[i][0];
	        		countedge++;
	        		if(countedge>number-1)
	        			break;
	        	}
	        	else if(father[edge[i][0]]<0 && father[edge[i][1]]>=0){
	        		father[edge[i][0]] = father[edge[i][1]];
	        		countedge++;
	        		answer = answer + edge[i][2];
	        		if(countedge>number-1)
	        			break;
	        	}
	        	else if(father[edge[i][0]]>=0 && father[edge[i][1]]<0){
	        		father[edge[i][1]] = father[edge[i][0]];
	        		countedge++;
	        		answer = answer + edge[i][2];
	        		if(countedge>number-1)
	        			break;
	        	}
	        	else if(father[edge[i][0]]>=0&&father[edge[i][1]]>=0){
	        		if(father[edge[i][0]]!=father[edge[i][1]]){
	        			int a = find(edge[i][0],father);
	        			int b = find(edge[i][1],father);
	        			if(a!=b){
	        				countedge++;
	        				answer = answer + edge[i][2];
	        				father[b] = a;
	        				if(countedge>number-1)
	        					break;
	        			}
	        		}
	        	}
	        }
	        out.println(answer);
	        
	        out.close();
	    }
	    public static int find(int a, int[] test){
	    	int temp = test[a];
	    	if(a==temp)
	    		return temp;
	    	else
	    		return find(temp, test);
	    }
}
