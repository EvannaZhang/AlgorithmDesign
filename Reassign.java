package ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class room{
	int num,max;
}

class MyComparator implements Comparator<room>{
	@Override
	public int compare(room a, room b){
	//	return (a.max==b.max)?(a.num>b.num):(a.max>b.max);
		if(a.max != b.max)
			if( a.max > b.max) return -1;
			else return 1;
		else
			if(a.num > b.num) return -1;	
			else return 1;
	}
}

public class Reassign {
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

        public int nextInt(){
            return Integer.parseInt(next());
        }

        public long nextLong(){
            return Long.parseLong(next());
        }
        
        public double nextDouble(){
            return Double.parseDouble(next());
        }
        
        public char[] nextCharArray(){
            return next().toCharArray();
        }
        
        public BigInteger nextBigInteger(){
            return new BigInteger(next());
        }
        
        public BigDecimal nextBigDecimal(){
            return new BigDecimal(next());
        }
        
    }
   
    public static void main(String[] args){
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int maxn = 102;
        room lab[] = new room[maxn];
        for(int i=0; i<maxn; i++){
        	lab[i] = new room();
        }
        int dp[][][] = new int[2][maxn][maxn*maxn];
        for(int i=0; i<=1; i++){
        	for(int j=0; j<maxn; j++){
               for(int k=0; k<maxn*maxn; k++){
                   if(j!=0)
                	   dp[i][j][k] = (int) -1e9;
                   else
                       dp[i][j][k] = 0;
                }
            }
        }
        int n = in.nextInt();
        int sum=0;
        int number=0;
        lab[0].max = Integer.MAX_VALUE;
        lab[0].num = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++){
        	lab[i].num = in.nextInt();
        	lab[i].max = in.nextInt();
        	sum = sum + lab[i].num;
        }
        int temp=sum;
        int pointer=1;
        
        while(sum>0){
           sum = sum - lab[pointer].max;
           number++;
           pointer++;
        }
        int capacity = 101*number - temp;
        
        Comparator cmp = new MyComparator();
		Arrays.sort(lab,cmp);
		
       for(int i=1; i<=n; i++){
           for(int j=1; j<=number; j++){
                for(int k=1; k<=capacity; k++){
                    int now = 101-lab[i].max;
                    if(now <= k){
                    	int tmp = dp[(i-1)%2][j-1][k-now] + lab[i].num;
                    	if(dp[(i-1)%2][j][k] > tmp){
                    		dp[i%2][j][k] = dp[(i-1)%2][j][k];	
                        }
                    	else{
                    		dp[i%2][j][k] = tmp;
                    	}
                    }
                    else{
                          dp[i%2][j][k] = dp[(i-1)%2][j][k];
                    }	
                }
           }
       }
       int result = temp - dp[n%2][number][capacity];
       out.println(result);
       out.close();
    }
	
}
