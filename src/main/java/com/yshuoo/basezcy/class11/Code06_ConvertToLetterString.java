package com.yshuoo.basezcy.class11;

public class Code06_ConvertToLetterString {

	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// str[0 .. i-1]已经转化完了，固定了
	// i之前的位置，如何转化已经做过决定了, 不用再关心
	// i... 有多少种转化的结果
	public static int process(char[] str, int i) {
		// str.length - 1位置完成了转化，返回一种转换方式
		if (i == str.length) { // base case
			return 1;
		}
		// i没有到终止位置，题目限制0无法转化
		if (str[i] == '0') {
			return 0;
		}
		// str[i]字符不是‘0’
		if (str[i] == '1') {
			int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
			if (i + 1 < str.length) {
				res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
			}
			return res;
		}
		if (str[i] == '2') {
			int res = process(str, i + 1); // i自己作为单独的部分，后续有多少种方法
			// (i和i+1)作为单独的部分并且没有超过26，后续有多少种方法
			if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
				res += process(str, i + 2); // (i和i+1)作为单独的部分，后续有多少种方法
			}
			return res;
		}
		// str[i] == '3' ~ '9' 只有一种选择，30已经大于所有转换码了
		return process(str, i + 1);
	}

	public static int dpWays(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			if (str[i] == '0') {
				dp[i] = 0;
			} else if (str[i] == '1') {
				dp[i] = dp[i + 1];
				if (i + 1 < N) {
					dp[i] += dp[i + 2];
				}
			} else if (str[i] == '2') {
				dp[i] = dp[i + 1]; 
				if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
					dp[i] += dp[i + 2];
				}
			} else {
				dp[i] = dp[i + 1];
			}
		}
		return dp[0];
	}

	public static void main(String[] args) {
		System.out.println(number("11111"));
	}

}
