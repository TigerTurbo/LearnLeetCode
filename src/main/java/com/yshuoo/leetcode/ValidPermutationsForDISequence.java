package com.yshuoo.leetcode;

/**
 * @Author yangshuo
 * @Date 2021/4/13 23:08
 */
public class ValidPermutationsForDISequence {

    public int numPermsDISequence(String S) {

        int mod = 1000000007;

        int n = S.length();

        char[] s = S.toCharArray();

        int[][] dp = new int[n+1][n+1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++){

            if (s[i-1] == 'D'){
                for (int j = i - 1; j >= 0; j--){
                    dp[i][j] = dp[i][j+1] + dp[i-1][j];
                    dp[i][j] %= mod;
                }
            }else{
                for (int j = 1; j <= i; j++){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
                    dp[i][j] %= mod;
                }
            }
        }

        int result = 0;

        for (int i = 0; i <= n; i++){
            result += dp[n][i];
            result %= mod;
        }

        return result;

    }

    public static void main(String[] args) {
        ValidPermutationsForDISequence v = new ValidPermutationsForDISequence();
        v.numPermsDISequence("DIDD");
    }

}
