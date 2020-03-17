package com.yshuoo.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yangshuo
 * @date 2020/3/10 15:24
 *
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或
 * 垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 */
public class NumIslands {

    private static int[][] STEP = {{0,1},{0,-1},{1,0},{-1,0}};

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int num = 0;
        if (row <= 0){
            return 0;
        }
        int column = grid[0].length;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (grid[i][j] != 49){
                    continue;
                }
                Queue queue = new LinkedList();
                int[] point = {i,j};
                queue.add(point);
                while (!queue.isEmpty()){
                    int[] points = (int[])queue.poll();
                    // grid[points[0]][points[1]] = '2'; 在这里才设置已经遍历过，会导致很多循环，执行超时
                    for (int[] step : STEP){
                        int x = step[0] + points[0];
                        int y = step[1] + points[1];
                        if (x < 0 || x >= row || y < 0 || y >= column || grid[x][y] != 49){
                            continue;
                        }
                        int[] p = {x,y};
                        queue.add(p);
                        grid[x][y] = '2';
                    }
                }
                num ++;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},
                        {'1','1','0','1','0'},
                        {'1','1','0','0','0'},
                        {'0','0','0','0','0'}};
        char[][] grid1 = {{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        NumIslands numIslands = new NumIslands();
        int num = numIslands.numIslands(grid);
        int value = numIslands.numIslands(grid1);
        System.out.println(num);
        System.out.println(value);
    }
}
