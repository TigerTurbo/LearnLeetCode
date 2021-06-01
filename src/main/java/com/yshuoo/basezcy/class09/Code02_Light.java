package com.yshuoo.basezcy.class09;

import java.util.HashSet;

public class Code02_Light {

	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}

	/**
	 * str[index ...] 位置，自由选择放灯还是不放
	 * str[0...index-1] 位置，已经做完决定了，那些放了灯的位置，放在lights里
	 * 要求选出能照亮所有点的方案，并且在这些方案中，返回最少需要几个灯
	 * @param str
	 * @param index
	 * @param lights
	 * @return
	 */
	public static int process(char[] str, int index, HashSet<Integer> lights) {
		if (index == str.length) { // 结束的时候
			for (int i = 0; i < str.length; i++) { // 能不能照亮所有位置
				if (str[i] != 'X') {
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		} else {
			// 不放
			int no = process(str, index + 1, lights);
			int yes = Integer.MAX_VALUE;
			// 是点才能放
			if (str[index] == '.') {
				lights.add(index);
				yes = process(str, index + 1, lights);
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}

	public static int minLight2(String road) {
		char[] str = road.toCharArray();
		int index = 0;
		int light = 0;
		while (index < str.length) {
			if (str[index] == 'X') {
				index++;
			} else {
				// 必然要放一个灯
				light++;
				if (index + 1 == str.length) {
					break;
				} else {
					// 下一个位置是X，就跳到下下一个位置
					if (str[index + 1] == 'X') {
						index = index + 2;
					} else {
						// 不是X，有两种情况，iii,iix 那么也是需要一个灯就行，只是iii放在中间，iix放在前面和中间都行
						// 所以要走三步，i不会被之前位影响
						index = index + 3;
					}
				}
			}
		}
		return light;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLight1(test);
			int ans2 = minLight2(test);
			if (ans1 != ans2) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
