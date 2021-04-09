package com.yshuoo.leetcode;

import java.util.*;

/**
 * @Author yangshuo
 * @Date 2021/4/9 16:19
 */
public class DisplayTableOfFoodOrdersInARestaurant {

    public static List<List<String>> displayTable(List<List<String>> orders) {
        // 用于保存最终列表需要的表头，先把菜名存起来，然后再头部插入“Table”关键字
        List<String> titleList = new ArrayList<>();
        // 以桌号为key，value为以菜名为key，数量为value的hashMap
        TreeMap<Integer,HashMap<String, Integer>> allMap = new TreeMap<>();
        if (orders == null || orders.size() == 0){
            return null;
        }
        // 遍历统计桌号对应的菜品有哪些，以及菜品对应的数量有多少
        for (int i = 0; i < orders.size(); i ++){
            List<String> details = orders.get(i);
            int num = Integer.parseInt(details.get(1));
            String name = details.get(2);
            HashMap<String, Integer> foodMap = allMap.getOrDefault(num, new HashMap<>());
            int foodNum = foodMap.getOrDefault(name,0);
            foodNum ++;
            foodMap.put(name, foodNum);
            allMap.putIfAbsent(num,foodMap);
            if (!titleList.contains(name)){
                titleList.add(name);
            }
        }
        // 对菜名列表进行字典序排序
        Collections.sort(titleList);
        // 头部插入关键字Table
        titleList.add(0,"Table");
        // 用于保存最终的结果
        List<List<String>> lastList = new ArrayList<>();
        lastList.add(titleList);
        // 开始遍历整个map
        Iterator iter = allMap.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            int key = (int)entry.getKey();
            HashMap<String, Integer> valueMap = (HashMap<String, Integer>)entry.getValue();
            List<String> list = new ArrayList();
            list.add(String.valueOf(key));
            // 对整个菜品的list进行遍历，存入具体的数量
            for (int i = 1; i < titleList.size(); i++){
                String title= titleList.get(i);
                int count = valueMap.getOrDefault(title,0);
                list.add(String.valueOf(count));
            }
            lastList.add(list);
        }
        return lastList;
    }

    public static void main(String[] args) {
        List<List<String>> orders = new ArrayList<>();
        List<String> order1 = new ArrayList<>();
        order1.add("David");
        order1.add("3");
        order1.add("Ceviche");
        orders.add(order1);
        List<String> order2 = new ArrayList<>();
        order2.add("Corina");
        order2.add("10");
        order2.add("Beef Burrito");
        orders.add(order2);
        List<String> order3 = new ArrayList<>();
        order3.add("David");
        order3.add("3");
        order3.add("Fried Chicken");
        orders.add(order3);
        List<String> order4 = new ArrayList<>();
        order4.add("Carla");
        order4.add("5");
        order4.add("Water");
        orders.add(order4);
        List<String> order5 = new ArrayList<>();
        order5.add("Carla");
        order5.add("5");
        order5.add("Ceviche");
        orders.add(order5);
        List<String> order6 = new ArrayList<>();
        order6.add("Carla");
        order6.add("3");
        order6.add("Ceviche");
        orders.add(order6);
        List<List<String>> lists = displayTable(orders);
        System.out.println(lists);
    }

}
