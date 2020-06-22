package com.yshuoo.leetcode.stack;

/**
 * @Author yangshuo
 * @Date 2020/6/18 22:54
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，
 * 你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 *
 * 提示：
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class FindTargetSumWays {

    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int c : nums){
            sum += c;
        }
        if (sum < S){
            return 0;
        }

        int dp[][] = new int[nums.length][sum*2+1];
        if (nums[0] == 0){
            dp[0][sum] = 2;
        }else{
            dp[0][sum - nums[0]] = 1;
            dp[0][sum + nums[0]] = 1;
        }

        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < sum*2+1; j++){
                int l = nums[j] + sum < sum*2+1 ? nums[j] + sum : 0;
                int r = nums[j] - sum >=0 ? nums[j] - sum : 0;
                dp[i][j] = dp[i-1][l] + dp[i-1][r];
            }
        }

        return dp[nums.length - 1][sum + S];
    }


}
