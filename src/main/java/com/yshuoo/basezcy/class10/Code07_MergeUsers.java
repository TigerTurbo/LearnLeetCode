package com.yshuoo.basezcy.class10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author yangshuo
 * @Date 2021/5/15 14:23
 */
public class Code07_MergeUsers {

    public static class UnionSet<V> {
        // V -> 节点
        public HashMap<V, Code01_UnionFind.Node<V>> nodes = new HashMap<>();
        // 子 -> 父
        public HashMap<Code01_UnionFind.Node<V>, Code01_UnionFind.Node<V>> parents = new HashMap<>();
        // 只有一个点，它是代表点，才会有size记录
        public HashMap<Code01_UnionFind.Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> values) {
            for (V value : values) {
                Code01_UnionFind.Node<V> node = new Code01_UnionFind.Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 从点cur开始，一直往上找，找到不能再往上找的代表点，返回
        public Code01_UnionFind.Node<V> findFather(Code01_UnionFind.Node<V> cur) {
            Stack<Code01_UnionFind.Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur头节点,扁平化，为了减少遍历链
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Code01_UnionFind.Node<V> aHead = findFather(nodes.get(a));
            Code01_UnionFind.Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize >= bSetSize) {
                    parents.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                    sizeMap.remove(bHead);
                } else {
                    parents.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

        public int getSetNum(){
            return sizeMap.size();
        }
    }

    public static class User{
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    /**
     * 返回合并之后用户的数量
     * @param users
     * @return
     */
    public static int mergeUsers(List<User> users){
        // 也可以用下标来代表user
        UnionSet<User> unionFind = new UnionSet<>(users);
        HashMap<String,User> mapA = new HashMap<>();
        HashMap<String,User> mapB = new HashMap<>();
        HashMap<String,User> mapC = new HashMap<>();
        for (User user : users){
            if (mapA.containsKey(user.a)){
                unionFind.union(user,mapA.get(user.a));
            }else{
                mapA.put(user.a,user);
            }
            if (mapB.containsKey(user.b)){
                unionFind.union(user,mapB.get(user.b));
            }else{
                mapB.put(user.b,user);
            }
            if (mapC.containsKey(user.c)){
                unionFind.union(user,mapC.get(user.c));
            }else{
                mapC.put(user.c,user);
            }
        }

        // 向并查集询问，合并之后，还有多少集合？
        return unionFind.getSetNum();
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User u = new User("1","2","3");
        list.add(u);
        User u1 = new User("1","4","5");
        list.add(u1);
        User u2 = new User("7","8","9");
        list.add(u2);
        int i = mergeUsers(list);
        System.out.println(i);
    }

}
