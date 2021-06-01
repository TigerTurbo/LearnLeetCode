package com.yshuoo;

/**
 * @Author yangshuo
 * @Date 2021/5/27 17:41
 */
public class BitArray {

    public static void main(String[] args) {
        // 3200 bit 做出 bit[0,3199]
        int[] arr = new int[100];
        // arr[0] -> 32 bit int 0(int)
        // arr[1] -> 32 bit int 1(int)
        System.out.println(453/32); // 14
        System.out.println(453%32); // 5
        // arr[14] 上的第 5 位
        // status是453在arr上的状态
        int status = (arr[453/32] >> (453%32)) & 1;
        // 453 设置为1
        arr[453/32] = arr[453/32] | (1<<(453%32));

        // 能放1万个数，能表示32 0000 bit
        // matrix[0] -> 3200 bit
        int[][] matrix = new int[100][100];

    }

}
