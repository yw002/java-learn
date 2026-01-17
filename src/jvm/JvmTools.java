package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * jps
 * jstack
 * jmap工具（mac没有权限）
 * - 查看堆内存：jmap -heap [pid]
 * - 生成dump文件：jmap -dump:format=b,file=/xx/xx/heap.hprof [pid]
 * jstat工具：
 * - 总结垃圾回收统计：jstat -gcutil [pid]
 * - 垃圾回收统计：   jstat -gc [pid]
 * jconsole可视化工具
 * visualvm工具
 * <p>
 * 使用jvm参数获取dump文件，内存溢出后程序会直接中断，jmap只能打印正在运行的程序，所以通过参数生成dump文件
 * 参数1：-XX:+HeapDumpOnOutOfMemoryError
 * 参数2：-XX:HeapDumpPath=/Users/yw/app/dumps/
 */
public class JvmTools {
    public static void main(String[] args) {
        testHeap();
//        testHeapOverflow();
    }

    static void testHeap() {
        new Thread(() -> {
            while (true) {

            }
        }, "t1").start();
        new Thread(() -> {
            while (true) {

            }
        }, "t2").start();
        new Thread(() -> {
            while (true) {

            }
        }, "t3").start();
    }

    /**
     * 演示内存溢出：需要限制堆空间大小参数 -Xmx8m
     */
    static void testHeapOverflow() {
        List<String> list = new ArrayList<String>();
        while (true) {
            list.add("hello");
        }
    }
}
