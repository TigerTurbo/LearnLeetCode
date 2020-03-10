package com.yshuoo.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 *
 *     -1 表示墙或是障碍物
 *     0 表示一扇门
 *     INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 *
 * 你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
 *
 * 示例：
 *
 * 给定二维网格：
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 *
 * 运行完你的函数后，该网格应该变成：
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 * @author yangshuo
 * @date 2020/3/6 15:31
 */
public class WallsAndGates {

    private static int[][] SETP_ARRAY = {{0,1},{0,-1},{1,0},{-1,0}};

    public void wallsAndGates(int[][] rooms) {

        int row = rooms.length;
        if (row == 0){
            return;
        }
        int column = rooms[0].length;
        Queue queue = new LinkedList();
        // 1、先遍历一遍把所有的门节点找出，并入队
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j ++){
                int value = rooms[i][j];
                if (value == 0){
                    queue.add(new Point(i,j));
                }
            }
        }
        // 2、按照门节点做多源BFS，这样每次只要空房间出队就是到多个门的最短距离
        while (!queue.isEmpty()){
            Point point = (Point)queue.poll();
            int x = point.x;
            int y = point.y;
            for (int[] step : SETP_ARRAY){
                int pointX = step[0] + x;
                int pointY = step[1] + y;
                if (pointX < 0 || pointY < 0 || pointX >= row || pointY >= column
                        || rooms[pointX][pointY] != Integer.MAX_VALUE){
                    continue;
                }
                rooms[pointX][pointY] = rooms[x][y] + 1;
                queue.add(new Point(pointX,pointY));
            }
        }
    }

    class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) {
        int[][] rooms = {
            {Integer.MAX_VALUE,-1,0,Integer.MAX_VALUE},
            {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1},
            {Integer.MAX_VALUE,-1,Integer.MAX_VALUE,-1},
            {0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE}
        };
        WallsAndGates wallsAndGates = new WallsAndGates();
        wallsAndGates.wallsAndGates(rooms);
        for (int i = 0; i < rooms.length; i++){
            for (int j = 0; j < rooms[i].length; j++){
                System.out.print(rooms[i][j] + " ");
            }
            System.out.println();
        }
    }

}
