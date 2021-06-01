package com.yshuoo.basezcy.class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {

	public static ArrayList<String> permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process(chs, 0, res);
		return res;
	}

	/**
	 * str[0...i-1]已经做好决定
	 * str[i...]都有机会来到i位置
	 * i 是终止位置，str当前的样子，就是一种结果 -> ans
	 * @param str
	 * @param i
	 * @param res
	 */
	public static void process(char[] str, int i, ArrayList<String> res) {
		if (i == str.length) {
			res.add(String.valueOf(str));
		}
		// i 还没到终止位置，i往后的所有位置，都可以来到i位置
		for (int j = i; j < str.length; j++) { // j 就是 i后面的字符都有机会
			swap(str, i, j);
			process(str, i + 1, res);
			// 一定要恢复现场
			swap(str, i, j);
		}
	}

	public static ArrayList<String> permutationNoRepeat(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		char[] chs = str.toCharArray();
		process2(chs, 0, res);
		return res;
	}


	public static void process2(char[] str, int i, ArrayList<String> res) {
		if (i == str.length) {
			res.add(String.valueOf(str));
		}
		// 限制都是小写字母，有其他的可以换成HashMap
		boolean[] visit = new boolean[26]; // visit[0 1 .. 25]
		for (int j = i; j < str.length; j++) {
			if (!visit[str[j] - 'a']) {
				visit[str[j] - 'a'] = true;
				swap(str, i, j);
				process2(str, i + 1, res);
				swap(str, i, j);
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void main(String[] args) {
		String s = "aac";
		List<String> ans1 = permutation(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutationNoRepeat(s);
		for (String str : ans2) {
			System.out.println(str);
		}

	}

}
