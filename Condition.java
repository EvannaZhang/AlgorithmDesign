package ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Condition {
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
        
        int n = in.nextInt();
        int[] score = new int[n+1];
        long[][][] dp = new long[n+1][104][2];
        int mod = 1000000007;
        for(int i=1; i<=n; i++){
        	score[i] = in.nextInt();
        }
        if(score[1] == -1){
            for(int i = 1; i <= 100; ++i) 
                dp[1][i][1] = 1;
        }
        else{
            dp[1][score[1]][1] = 1;
        }
        
        for(int i = 2; i <= n; ++i) {
            if(score[i] == -1) {
                long count = 0;
                for(int j = 1; j <= 100; ++j) {
                    dp[i][j][1] = count;
                    count = count + dp[i-1][j][1] + dp[i-1][j][0];
                    count = count % mod;
                }
                count = 0;
                for(int j = 100; j >= 1; --j) {
                	count = count + dp[i-1][j][0];
                	count = count % mod;
                	long temp = dp[i-1][j][1] + count;
                	temp = temp % mod;
                    dp[i][j][0] = temp;
                }
            }else{
                for(int j = 1; j < score[i]; ++j){
                	long temp = dp[i][score[i]][1] + dp[i-1][j][1] + dp[i-1][j][0];
                	temp = temp % mod;
                    dp[i][score[i]][1] = temp;
                }
                for(int j = 100; j >= score[i]; --j){
                	long temp = dp[i][score[i]][0] + dp[i-1][j][0];
                	temp = temp % mod;
                    dp[i][score[i]][0] = temp;
                }
                
                long temp = dp[i][score[i]][0] + dp[i-1][score[i]][1];
                temp = temp % mod;
                dp[i][score[i]][0] = temp;
            }
        }
        
        long result = 0;
        if(score[n] == -1){
            for(int i = 1; i <= 100; ++i) {
            	long temp = result + dp[n][i][0];
            	temp = temp % mod;
                result = temp;
            }
        }
        else{
            result = dp[n][score[n]][0];
        }
        out.println(result);
        
        out.close();
    }
    
}
