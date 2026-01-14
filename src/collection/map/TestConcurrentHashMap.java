package collection.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程安全的hashMap
 * 1.7 采用分段数组+链表（了解即可）
 * <p>
 * 1.8 采用数组+链表/红黑树，使用CAS+Synchronized
 * - 采用CAS控制数组节点的添加
 * - 使用Synchronized锁定当前链表或红黑树首节点
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
    }
}
