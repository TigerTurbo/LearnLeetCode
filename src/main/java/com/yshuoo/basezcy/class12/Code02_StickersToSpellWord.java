package com.yshuoo.basezcy.class12;

import java.util.Arrays;
import java.util.HashMap;

public class Code02_StickersToSpellWord {

	// 伪代码
	public static int A (String rest, String[] arr){
		// 看看arr能不能覆盖rest
		return 0;
	}

	public static int minS (String rest, String[] arr){
		if (rest.equals("")){
			return 0;
		}
		// 边界条件需要看看贴纸里有没有rest的字符，没有的话跳过
		// 搞定rest的，第一张贴纸是什么
		int next = 0; // 已经使用了一张，所以初始值是1
		for (String first : arr){
			// rest 里的字符减去 first 里含有的字符 = nextRest 剩余未拼的字符
			// int cur = minS(nextRest,arr);
			// next = Math.min(next,cur);
		}
		return next + 1;
	}

	public static int minStickers1(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26]; // 保存贴纸字符数量，n是贴纸序号，26用来保存字符数量
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();
		dp.put("", 0);
		return process1(dp, map, target);
	}

	// dp 傻缓存，如果t已经算过了，直接返回dp中的值
	// t 剩余的目标
	// 0..N每一个字符串所含字符的词频统计
	// 返回-1 ，表示map无法组成rest
	public static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
		if (dp.containsKey(rest)) {
			return dp.get(rest);
		}
		// 以下就是正式递归过程
		int ans = Integer.MAX_VALUE; // 搞定rest使用贴纸的最少数量
		int n = map.length; // n 种贴纸
		int[] tmap = new int[26]; // 与顺序无关，转换为字频
		char[] target = rest.toCharArray();
		for (char c : target) {
			tmap[c - 'a']++;
		}
		// 枚举当前第一张贴纸是谁
		for (int i = 0; i < n; i++) {
			// 相当于当前贴纸至少含有一种target的字符
			// 因为搞定次序无关，可以先选择能搞定 target[0] 的贴纸
			if (map[i][target[0] - 'a'] == 0) {
				continue;
			}
			// 没搞定的字符都存在sb里
			StringBuilder sb = new StringBuilder();
			// j 每种贴纸的字符
			for (int j = 0; j < 26; j++) {
				if (tmap[j] > 0) { // j这个字符是target需要的
					for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
						sb.append((char) ('a' + j));
					}
				}
			}
			String s = sb.toString();
			int tmp = process1(dp, map, s);
			if (tmp != -1) {
				ans = Math.min(ans, 1 + tmp);
			}
		}
		// ans没有被设置过
		dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
		return dp.get(rest);
	}

	// 枚举每一张贴纸使用n张剩余的字符串情况
	public static int minStickers2(String[] stickers, String target) {
		int n = stickers.length;
		int[][] map = new int[n][26];
		for (int i = 0; i < n; i++) {
			char[] str = stickers[i].toCharArray();
			for (char c : str) {
				map[i][c - 'a']++;
			}
		}
		char[] str = target.toCharArray();
		int[] tmap = new int[26];
		for (char c : str) {
			tmap[c - 'a']++;
		}
		HashMap<String, Integer> dp = new HashMap<>();
		int ans = process2(map, 0, tmap, dp);
		return ans;
	}

	public static int process2(int[][] map, int i, int[] tmap, HashMap<String, Integer> dp) {
		StringBuilder keyBuilder = new StringBuilder();
		keyBuilder.append(i + "_");
		for (int asc = 0; asc < 26; asc++) {
			if (tmap[asc] != 0) {
				keyBuilder.append((char) (asc + 'a') + "_" + tmap[asc] + "_");
			}
		}
		String key = keyBuilder.toString();
		if (dp.containsKey(key)) {
			return dp.get(key);
		}
		boolean finish = true;
		for (int asc = 0; asc < 26; asc++) {
			if (tmap[asc] != 0) {
				finish = false;
				break;
			}
		}
		if (finish) {
			dp.put(key, 0);
			return 0;
		}
		if (i == map.length) {
			dp.put(key, -1);
			return -1;
		}
		int maxZhang = 0;
		for (int asc = 0; asc < 26; asc++) {
			if (map[i][asc] != 0 && tmap[asc] != 0) {
				maxZhang = Math.max(maxZhang, (tmap[asc] / map[i][asc]) + (tmap[asc] % map[i][asc] == 0 ? 0 : 1));
			}
		}
		int[] backup = Arrays.copyOf(tmap, tmap.length);
		int min = Integer.MAX_VALUE;
		int next = process2(map, i + 1, tmap, dp);
		tmap = Arrays.copyOf(backup, backup.length);
		if (next != -1) {
			min = next;
		}
		for (int zhang = 1; zhang <= maxZhang; zhang++) {
			for (int asc = 0; asc < 26; asc++) {
				tmap[asc] = Math.max(0, tmap[asc] - (map[i][asc] * zhang));
			}
			next = process2(map, i + 1, tmap, dp);
			tmap = Arrays.copyOf(backup, backup.length);
			if (next != -1) {
				min = Math.min(min, zhang + next);
			}
		}
		int ans = min == Integer.MAX_VALUE ? -1 : min;
		dp.put(key, ans);
		return ans;
	}


	public int minStickers(String[] stickers, String target) {
		int[][] stickerArray = new int[stickers.length][26];
		int index = 0;
		for (String s : stickers){
			char[] charArray = s.toCharArray();
			for (char c : charArray){
				stickerArray[index][c - 'a'] ++;
			}
			index ++;
		}
		HashMap<String, Integer> buildMap = new HashMap<>();
		buildMap.put("",0);
		return process(buildMap,stickerArray,target);

	}


	public int process(HashMap<String, Integer> buildMap, int[][] stickerArray, String rest){

		if (buildMap.containsKey(rest)){
			return buildMap.get(rest);
		}

		int ans = Integer.MAX_VALUE;
		int n = stickerArray.length;
		int[] targetArray = new int[26];
		char[] target = rest.toCharArray();

		for (char c : target){
			targetArray[c - 'a'] ++;
		}



		for (int i = 0; i < n; i++){

			if (stickerArray[i][target[0] - 'a'] == 0){
				continue;
			}

			StringBuilder sb = new StringBuilder();

			for (int j = 0; j < 26; j++){
				if (targetArray[j] > 0){

					for (int k = 0; k < Math.max(0, targetArray[j] - stickerArray[i][j]); k++){
						sb.append((char)('a' + j));
					}

				}

			}
			String s = sb.toString();
			int res = process(buildMap,stickerArray,s);
			if (res != -1){
				ans = Math.min(ans, res + 1);
			}
		}
		buildMap.put(rest,ans == Integer.MAX_VALUE ? -1 : ans);
		return buildMap.get(rest);

	}

}
