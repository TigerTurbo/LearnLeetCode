package com.yshuoo.leetcode.queue;

import java.util.*;

/**
 * @Author yangshuo
 * @Date 2020/6/30 22:02
 *
 */
public class CanVisitAllRooms {

    private int roomNum;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.isEmpty()){
            return false;
        }

        roomNum = rooms.size();

        if(roomNum == 1){
            return true;
        }

        boolean[] already = new boolean[roomNum];

        dfs(rooms,already,0);

        if (roomNum == 0){
            return true;
        }

        return false;
    }

    private void dfs(List<List<Integer>> rooms, boolean[] already, int key){
        if (already[key]){
            return;
        }
        roomNum --;
        already[key] = true;
        if (roomNum == 0){
            return;
        }
        List<Integer> integers = rooms.get(key);
        if (integers.size() <= 0){
            return;
        }
        for (int room : rooms.get(key)){
            dfs(rooms, already, room);
        }
    }

    public static void main(String[] args) {
        CanVisitAllRooms c = new CanVisitAllRooms();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        List<Integer> list3 = new ArrayList<>();
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(list);
        rooms.add(list1);
        rooms.add(list2);
        rooms.add(list3);
        System.out.println(c.canVisitAllRooms(rooms));
    }

}
