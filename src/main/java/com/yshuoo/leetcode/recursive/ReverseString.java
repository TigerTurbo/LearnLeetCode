package com.yshuoo.leetcode.recursive;

/**
 * @Author yangshuo
 * @Date 2020/7/1 22:05
 */
public class ReverseString {

    public void reverseString(char[] s) {
        int size = (s.length-1) / 2;
        char a;
        for (int i = 0; i <= size; i++){
            a = s[i];
            s[i] = s[s.length - i -1];
            s[s.length - i -1] = a;
        }
    }

    public static void main(String[] args) {
        ReverseString r = new ReverseString();
        char[] s = new char[]{'h','e','l','4','o',','};
        r.reverseString(s);
        for (char c : s){
            System.out.println(c);
        }
        printReverse(s);

    }

    private static void printReverse(char [] str) {
        helper(0, str);
    }

    private static void helper(int index, char [] str) {
        if (str == null || index >= str.length) {
            return;
        }
        helper(index + 1, str);
        System.out.print(str[index]);
    }

}
