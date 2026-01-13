package collection.list;

import java.util.*;

/**
 * ArrayList的时间复杂度
 * 在尾部插入和删除是O(1)，无需挪动元素
 * 在其他地方增删查都是O(n)
 * 给定下标查询是O(1)
 *
 * 线程不安全
 * 需要使用 Collections.synchronizedList();
 */
public class TestArrayList {
    public static void main(String[] args) {
        // 测试ArrayList扩容
        testGrow();
        // 测试数组转List
        testArray2List();
        // 测试List转数组
        testList2Array();
        // 线程安全的创建方法
        Collection<String> strings = Collections.synchronizedList(new ArrayList<String>());
    }

    public static void testGrow() {
        // ArrayList的无参构造函数
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 第一次扩容，扩容为默认值10
        list.add(1);
        for (int i = 2; i <= 10; i++) {
            list.add(i);
        }
        // 添加第11个数据就会触发grow扩容，int newCapacity = oldCapacity + (oldCapacity >> 1);
        list.add(11);
    }

    /**
     * 测试数组转List
     */
    public static void testArray2List() {
        System.out.println("\n测试数组转List\n");
        String[] str = {"aaa", "bbb", "ccc"};
        // 源码只创建了List的引用，并没有新创建对象
        List<String> list = Arrays.asList(str);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("========修改原数组元素后，List也被更改了========");
        str[0] = "changed";
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 测试List转数组
     */
    public static void testList2Array() {
        System.out.println("\n测试List转数组\n");
        ArrayList<String> list = new ArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        String[] array = list.toArray(new String[list.size()]);
        for (String s : array) {
            System.out.println(s);
        }
        System.out.println("=========修改原List元素后，数组不受影响===========");
        list.add("XXX");
        for (String s : array) {
            System.out.println(s);
        }
    }
}
