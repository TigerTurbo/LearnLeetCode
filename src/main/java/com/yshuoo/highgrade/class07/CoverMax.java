package com.yshuoo.highgrade.class07;

import java.util.*;

/**
 * @Author yangshuo
 * @Date 2021/5/26 20:54
 */
public class CoverMax {

    public static class Line{
        public int start;
        public int end;

        public Line(int s, int e){
            this.start = s;
            this.end = e;
        }

        public int getStart(){
            return start;
        }

        public int getEnd(){
            return end;
        }
    }

    public static int maxCover(int[][] m){
        Line[] lines = new Line[m.length];
        for (int i = 0; i < lines.length; i++){
            lines[i] = new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(lines,Comparator.comparing(Line::getStart));
        PriorityQueue<Line> heap = new PriorityQueue<>(Comparator.comparing(Line::getEnd));
        int max = 0;
        for (int i = 0; i < lines.length; i++){
            while (!heap.isEmpty() && heap.peek().end <= lines[i].start){
                heap.poll();
            }
            heap.add(lines[i]);
            max = Math.max(max,heap.size());
        }
        return max;

    }

    public static class Rectangle{
        public int up;
        public int down;
        public int left;
        public int right;

        public Rectangle(int up, int down, int left, int right){
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }

        public int getUp() {
            return up;
        }

        public int getDown() {
            return down;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }
    }

    public static int coverMax1(Rectangle[] resc){
        if (resc == null || resc.length == 0){
            return 0;
        }
        Arrays.sort(resc,Comparator.comparing(Rectangle::getDown));
        // 可能回对当前底边的公共区域，产生影响的矩阵
        TreeSet<Rectangle> leftOrdered = new TreeSet<>(Comparator.comparing(Rectangle::getLeft));
        int ans = 0;
        for (int i = 0; i < resc.length; i++){
            int curDown = resc[i].getDown();
            int index = i;
            // 已经排好了序，所以不用担心越过某个矩形
            while (resc[index].down == curDown){
                leftOrdered.add(resc[index]);
                index ++;
            }
            i = index;
            // 将顶小于等于当前底的数去掉
            removeLowerOnCurDown(leftOrdered,curDown);
            TreeSet<Rectangle> rightOrdered = new TreeSet<>(Comparator.comparing(Rectangle::getRight));
            for (Rectangle rec : leftOrdered){
                removeLowerOnCurLeft(rightOrdered,rec.getLeft());
                rightOrdered.add(rec);
                ans = Math.max(ans,rightOrdered.size());
            }

        }
        return ans;
    }

    public static void removeLowerOnCurDown(TreeSet<Rectangle> set, int curDown){
        Iterator<Rectangle> iterator = set.iterator();
        while (iterator.hasNext()){
            Rectangle next = iterator.next();
            if (next.up <= curDown){
                set.remove(next);
            }
        }
    }

    public static void removeLowerOnCurLeft(TreeSet<Rectangle> set, int curLeft){
        Iterator<Rectangle> iterator = set.iterator();
        while (iterator.hasNext()){
            Rectangle next = iterator.next();
            if (next.right <= curLeft){
                set.remove(next);
            }
        }
    }

}
