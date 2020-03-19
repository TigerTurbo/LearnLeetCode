package com.yshuoo.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yangshuo
 * @date 2020/3/18 13:36
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
 * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *  假设最小公式值m=ƒ(n)
 * //那么n的值满足下列公式 ∑(A[i] * A[i]) = n
 * //令 k 为满足最小值 m 的时候，最大的平方数  。 令  d + k * k; = n ;  d >= 0;
 *    // 注意：一定要是满足m最小的时候的k值,一味的取最大平方数,就是贪心算法了
 * //得出 f(d) + f(k*k) = f(n);
 * //显然 f(k*k) = 1; 则  f(d) + 1 = f(n); 因为 d = n - k*k;
 * //则可以推出ƒ(n - k * k) + 1 = ƒ(n) ;  且 k * k <= n;
 */
public class NumSquares {

    public int numSquares(int n) {
        if (Math.pow(Math.floor(Math.sqrt(n)), 2) == n) return 1;
        while ((n & 3) == 0) {
            n >>= 2;
        }
        if ((n & 7) == 7) {
            return 4;
        }
        for (int y, x = 1; x * x < n; x++) {
            y = (int) Math.floor(Math.sqrt(n - x * x));
            if (x * x + y * y == n) return 2;
        }
        return 3;
    }

    public static void main(String[] args) {
        NumSquares numSquares = new NumSquares();
        int i = numSquares.numSquares(12);
        int i1 = numSquares.numSquares(13);
        System.out.println(i);
        System.out.println(i1);
    }

}
