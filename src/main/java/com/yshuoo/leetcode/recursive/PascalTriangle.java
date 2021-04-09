package com.yshuoo.leetcode.recursive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author yangshuo
 * @Date 2020/7/4 9:35
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangleList = new ArrayList<>(numRows);
        if (numRows == 0){
            return triangleList;
        }
        gen(numRows-1,triangleList);
        return triangleList;
    }

    private void gen(int numRow, List<List<Integer>> triangleList){
        if (numRow == 0){
            List<Integer> list = new ArrayList<>(1);
            list.add(1);
            triangleList.add(list);
            return;
        }
        gen(numRow - 1, triangleList);
        List<Integer> beforeTriangle = triangleList.get(numRow - 1);
        List<Integer> triangle = new ArrayList<>(numRow + 1);
        for (int i = 0; i < numRow + 1; i++){
            if (i == 0 || i == numRow){
                triangle.add(1);
                continue;
            }
            Integer before = beforeTriangle.get(i - 1);
            Integer after = beforeTriangle.get(i);
            triangle.add(before + after);
        }
        triangleList.add(triangle);
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
        List<List<Integer>> generate = pascalTriangle.generate(9);
        for (int i = 0; i < generate.size(); i++){
            List<Integer> integers = generate.get(i);
            for (int j = 0; j < integers.size(); j++){
                System.out.print(integers.get(j) + " ");
            }
            System.out.println();
        }

    }

}
