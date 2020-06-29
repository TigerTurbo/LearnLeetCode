package com.yshuoo.leetcode.queue;

import java.util.*;

/**
 * @author yangshuo
 * @date 2020/3/16 10:05
 *
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：
 *
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。
 * 每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，
 * 这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 *
 *示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 *
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *
 * 提示：
 *
 *     死亡列表 deadends 的长度范围为 [1, 500]。
 *     目标数字 target 不会在 deadends 之中。
 *     每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 *
 */
public class OpenLock {


    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")){
            return 0;
        }
        int startArray[] = new int[10000];
        int endArray[] = new int[10000];
        // 将字符串转成数字
        for (int i = 0; i < deadends.length; i++){
            int dead = Integer.valueOf(deadends[i]);
            if (dead == 0){
                return -1;
            }
            startArray[dead] = -1;
            endArray[dead] = -1;
        }
        Queue<Integer> startQueue = new LinkedList<Integer>();
        Queue<Integer> endQueue = new LinkedList<Integer>();
        int targetKey = Integer.valueOf(target);
        startQueue.add(0);
        endQueue.add(targetKey);
        startArray[0] = 1; // 为了防止旋转一次就碰撞的情况，这里设置开头和目标的值都是1
        endArray[targetKey] = 1;
        while (!startQueue.isEmpty() || !endQueue.isEmpty()){
            // 0000开始的队列
            int bfs = bfs(startQueue, startArray, endArray);
            if (bfs != -1){
                return bfs;
            }
            // target开始的队列
            bfs = bfs(endQueue,endArray,startArray);
            if (bfs != -1){
                return bfs;
            }
        }
        return -1;
    }

    private int bfs(Queue<Integer> queue,int[] array, int[] valueArray) {
        if (!queue.isEmpty()) {
            int num = queue.poll();
            if (array[num] == -1) {
                return -1;
            }
            // 如果对应的两个遍历数组报存的值都不为0也不是deadend，说明发生了碰撞
            if (array[num] != 0 && array[num] != -1 && valueArray[num]!=0 && valueArray[num] != -1) {
                return array[num] + valueArray[num] - 2; // 因为预先设置了1，所以最后要减2才是真正的步数
            }
            addKey(num, queue, array);
        }
        return -1;
    }

    private void addKey(int key, Queue<Integer> keyQueue,int[] alreadyArray){
        // 将每个位数都上下旋转
        int thousand = key / 1000;
        int hundred = key % 1000 / 100;
        int ten = key % 100 / 10;
        int digits = key % 10;
        // 操作密码
        int thousand1 =  add(thousand);
        int thousand2 = subtraction(thousand);
        int hundred1 = add(hundred);
        int hundred2 = subtraction(hundred);
        int ten1 = add(ten);
        int ten2 = subtraction(ten);
        int digits1 = add(digits);
        int digits2 = subtraction(digits);
        int value = thousand * 1000 + hundred * 100 + ten * 10 + digits;
        if (thousand1 != -1){
            judgeRepeat(thousand1, hundred, ten, digits, value, keyQueue, alreadyArray);
        }

        if (thousand2 != -1){
            judgeRepeat(thousand2,hundred,ten,digits,value,keyQueue,alreadyArray);
        }

        if (hundred1 != -1){
            judgeRepeat(thousand,hundred1,ten,digits,value,keyQueue,alreadyArray);
        }

        if (hundred2 != -1){
            judgeRepeat(thousand,hundred2,ten,digits,value,keyQueue,alreadyArray);
        }

        if (ten1 != -1){
           judgeRepeat(thousand,hundred,ten1,digits,value,keyQueue,alreadyArray);
        }

        if (ten2 != -1){
            judgeRepeat(thousand,hundred,ten2,digits,value,keyQueue,alreadyArray);
        }

        if (digits1 != -1){
            judgeRepeat(thousand, hundred, ten, digits1, value, keyQueue, alreadyArray);
        }

        if (digits2 != -1){
            judgeRepeat(thousand,hundred,ten,digits2,value,keyQueue,alreadyArray);
        }
    }


    private void judgeRepeat(int thousand, int hundred, int ten,
                                int digits, int value,
                                Queue<Integer> keyQueue, int[]alreadyArray){

        int num = thousand * 1000 + hundred * 100 + ten * 10 + digits;
        if (alreadyArray[num] == 0){
            // 等于0说明没有遍历过，放入队列
            alreadyArray[num] = alreadyArray[value] + 1;
            keyQueue.add(num);
        }
    }

    private int add(int num){
        int high = num + 1;
        if (high == 10){
            high = 0;
        }
        return high;
    }

    private int subtraction(int num){
        int low = num - 1;
        if (low == -1){
            low = 9;
        }
        return low;
    }

    public static void main(String[] args) {
        OpenLock openLock = new OpenLock();
        String [] abs = {"0000"};
        String target = "8888";
        int step = openLock.openLock(abs, target);
        System.out.println(step);
    }
}
