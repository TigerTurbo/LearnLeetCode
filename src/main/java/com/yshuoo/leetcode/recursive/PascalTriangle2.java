package com.yshuoo.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangshuo
 * @Date 2020/7/4 10:20
 */
public class PascalTriangle2 {

    public List<Integer> getRow(int numRows) {
        List<List<Integer>> triangleList = new ArrayList<>(numRows);
        numRows += 1;
        gen(numRows-1,triangleList);
        return triangleList.get(numRows - 1);
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
}
