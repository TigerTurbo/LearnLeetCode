package com.yshuoo.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author yangshuo
 * @Date 2020/6/29 21:49
 *
 */
public class UpdateMatrix {

    public int[][] updateMatrix(int[][] matrix) {

        if (matrix == null){
            return matrix;
        }

        int row = matrix.length;
        int clomn = matrix[0].length;


        for (int i = 0; i < row; i ++){
            for (int j = 0; j < clomn; j++){
                matrix[i][j] = matrix[i][j] == 0 ? 0 : 1000;
            }
        }

        for (int i = 0; i < row; i ++){
            for (int j = 0; j < clomn; j++){
                if (i-1 >=0){
                    matrix[i][j] = Math.min(matrix[i-1][j] + 1,matrix[i][j]);
                }
                if (j-1 >=0){
                    matrix[i][j] = Math.min(matrix[i][j-1] + 1,matrix[i][j]);
                }
            }
        }

        for (int i = row -1; i >= 0; i --){
            for (int j = clomn - 1; j >= 0; j--){
                if (i+1 < row){
                    matrix[i][j] = Math.min(matrix[i+1][j] + 1,matrix[i][j]);
                }
                if (j+1 < clomn){
                    matrix[i][j] = Math.min(matrix[i][j+1] + 1,matrix[i][j]);
                }
            }
        }


        return matrix;
    }

    public static void main(String[] args) {
        UpdateMatrix u = new UpdateMatrix();
        int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] ints = u.updateMatrix(matrix);
        for (int i = 0; i < ints.length; i++){
            System.out.println();
            for (int j = 0; j< ints[0].length; j++){
                System.out.print(ints[i][j] + " ");
            }
        }
    }

}
