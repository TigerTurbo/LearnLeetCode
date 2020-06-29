package com.yshuoo.leetcode.stack;

import java.util.Stack;

/**
 * @author yangshuo
 * @date 2020/3/20 14:23
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 *
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class IsValid {

    private static char[] small =  {'(',')'};
    private static char[] middle =  {'[',']'};
    private static char[] large = {'{','}'};
    private static int left = 0;
    private static int right = 1;

    public boolean isValid(String s) {

        if (s.equals("")){
            return true;
        }
        if (s.length() <= 1){
            return false;
        }
        // 转换字符数组
        char[] charArray = s.toCharArray();
        // 首位是右半边返回false
        if (charArray[0] != small[left] && charArray[0] != middle[left] && charArray[0] != large[left]){
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (char c : charArray){
            if (stack.isEmpty()){
                stack.add(c);
            }else{
                // 如果是左半边则入栈
                if (c == small[left] || c == middle[left] || c == large[left]){
                    stack.add(c);
                }else{
                    // 如果是右半边，判断栈顶元素和当前元素是否成对
                    if (stack.peek() == small[left]){
                        // 成对出栈
                        if (c == small[right]){
                            stack.pop();
                        }else{
                            // 不成对直接返回false
                            return false;
                        }
                    }else if (stack.peek() == middle[left]){
                        if (c == middle[right]){
                            stack.pop();
                        }else{
                            return false;
                        }
                    }else{
                        if (c == large[right]){
                            stack.pop();
                        }else{
                            return false;
                        }
                    }
                }
            }
        }
        // 遍历完之后，去看栈中还有没有元素，有的话说明括号没有成对出现，例如((()(),这个情况栈里还剩下((
        if (stack.isEmpty()){
            return true;
        }else{
            return false;
        }

    }

    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        System.out.println(isValid.isValid("()"));
        System.out.println(isValid.isValid("()[]{}"));
        System.out.println(isValid.isValid("(]"));
        System.out.println(isValid.isValid("([)]"));
        System.out.println(isValid.isValid("{[]}"));
    }
}
