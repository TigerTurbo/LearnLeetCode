package com.yshuoo.leetcode.stack;

import java.util.Stack;

/**
 * @author yangshuo
 * @date 2020/3/23 14:11
 *
 * 逆波兰表达式求值
 *
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 *     整数除法只保留整数部分。
 *     给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 *
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 *
 * 示例 3：
 *
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {

        // 将栈的结构用数组代替
        int [] stack = new int[tokens.length/ 2 + 1];
        int index = 0;
        for (String s : tokens){
            // 每次做运算，将之前已经参加过运算的index -1 和 index -2 的值覆盖掉，这里计算完毕覆盖index-2的值
            switch (s){
                case "+":
                    stack[index - 2] += stack[index-1];
                    index --;
                    break;
                case "-":
                    stack[index - 2] -= stack[index-1];
                    index --;
                    break;
                case "*":
                    stack[index - 2] *= stack[index-1];
                    index --;
                    break;
                case "/":
                    stack[index - 2] /= stack[index-1];
                    index --;
                    break;
                default:
                    // 这里由于index++的性质，所以覆盖的实际上是上一步index-- 后的值
                    stack[index ++] = Integer.parseInt(s);
            }
        }

        // 最后栈顶的元素肯定是最后的计算结果
        return stack[0];
    }

    public static void main(String[] args) {
        EvalRPN evalRPN = new EvalRPN();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN.evalRPN(tokens));
        String[] tokens4 = {"4", "3", "-"};
        System.out.println(evalRPN.evalRPN(tokens4));
        String[] tokens1 = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN.evalRPN(tokens1));
        String[] tokens2 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN.evalRPN(tokens2));
        String[] tokens3 = {"2", "1", "+", "3", "5", "+", "*"};
        System.out.println(evalRPN.evalRPN(tokens3));
    }

}
