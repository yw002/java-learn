package collection.list;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * LinkedList的时间复杂度
 * 在头尾增删查都是O(1)
 * 在其他地方是O(n)
 *
 * 线程不安全
 * 需要使用Collections.synchronizedList();
 */
public class TestLinkedList {
    public static void main(String[] args) {
        testLinkedList();
        // 创建一个线程安全的集合
        List<String> strings = Collections.synchronizedList(new LinkedList<>());
    }

    public static void testLinkedList() {
        LinkedList linkedList = new LinkedList();
    }
}
