package com.yshuoo.leetcode;

/**
 * @Author yangshuo
 * @Date 2020/8/3 22:09
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int target = 0;
        char[] cArray1 = num1.toCharArray();
        char[] cArray2 = num2.toCharArray();
        int index1 = cArray1.length - 1;
        int index2 = cArray2.length - 1;
        while (index1 >= 0 || index2 >= 0){
            int number1 = index1 >= 0 ? cArray1[index1--] - 48 : 0;
            int number2 = index2 >= 0 ? cArray2[index2--] - 48 : 0;
            int sum = number1 + number2 + target;
            target = sum / 10;
            sb.insert(0,sum % 10);
        }
        if (target == 1){
            sb.insert(0,1);
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        addStrings.addStrings("9","999");
    }
}
