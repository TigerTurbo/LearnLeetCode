package com.yshuoo.leetcode.queue;

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

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length <= 0){
            return 0;
        }

        NumLand numLand = new NumLand(grid);

        int row = grid.length;
        int column = grid[0].length;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (grid[i][j] == '1'){
                    grid[i][j] = '0';
                    if (i - 1 >= 0 && grid[i-1][j] == '1'){
                        numLand.union(i * column + j, (i-1) * column + j);
                    }
                    if (j - 1 >= 0 && grid[i][j-1] == '1'){
                        numLand.union(i * column + j, i * column + j - 1);
                    }
                    if (i + 1 < row && grid[i+1][j] == '1'){
                        numLand.union(i * column + j, (i+1) * column + j);
                    }
                    if (j + 1 < column && grid[i][j+1] == '1'){
                        numLand.union(i * column + j, i * column + j + 1);
                    }
                }
            }
        }

        return numLand.getCount();

    }

    class NumLand {

        private int[] parent;
        private int[] rank;
        private int count;

        public NumLand(char[][] grid){
            int row = grid.length;
            int column = grid[0].length;
            parent = new int[row * column];
            rank = new int[row * column];
            for (int i = 0; i < row; i++){
                for (int j = 0; j < column; j++){
                    if (grid[i][j] == '1'){
                        parent[i * column + j] = i * column + j;
                        count ++;
                    }
                }
            }
        }

        public void union(int start, int end){
            int rankX = find(start);
            int rankY = find(end);
            if (rankX != rankY){
                if (rank[rankX] > rank[rankY]){
                    parent[rankY] = rankX;
                }else if (rank[rankX] < rank[rankY]){
                    parent[rankX] = rankY;
                }else{
                    parent[rankX] = rankY;
                    rank[rankY] += 1;
                }
                count --;
            }
        }

        private int find(int i){
            // 路径压缩，让每个元素都指向他的根节点
            if (parent[i] != i){
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public int getCount(){
            return count;
        }

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
        char[][] grid2 = {{'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}};
        NumIslands numIslands = new NumIslands();
        int num = numIslands.numIslands(grid);
        int value = numIslands.numIslands(grid1);
        int value2 = numIslands.numIslands(grid2);
        System.out.println(num);
        System.out.println(value);
        System.out.println(value2);
    }
}
