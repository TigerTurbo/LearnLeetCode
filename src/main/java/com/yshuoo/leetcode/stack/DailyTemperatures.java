package com.yshuoo.leetcode.stack;

/**
 * @author yangshuo
 * @date 2020/3/20 15:50
 *
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {

        if (T == null || T.length <= 0){
            return null;
        }
        int length = T.length;
        int[] days = new int[length];
        if (T.length == 1){
            return days;
        }

        // 从后往前遍历
        for (int i = length -2; i >= 0; i--){
            /**
             * 每次加上后一个的位置值的原因是
             *
             * 如果current大于after，就去看after的第一个大于它的数字在什么位置，是不是比当前大
             * 因为是第一个所以保证了中间不会出现比current还大的
             * 比current还大的，那肯定比after大，比after大的数字出现在中间，只能说明after的时候就已经出错了
             */
            for (int j = i + 1; j < length; j += days[j]){
                int current = T[i];
                int after = T[j];
                // 如果后面的大于当前，说明找到了第一次比他大的位置
                if (after > current){
                    days[i] = j - i;
                    break;
                }
                // 如果等于0说明，后面不会有比current更大的了
                if (days[j] == 0){
                    break;
                }
            }
        }
        return days;

    }

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int [] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = dailyTemperatures.dailyTemperatures(T);
        for (int i : ints){
            System.out.println(i);
        }
    }
}
