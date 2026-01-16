package jvm;

/**
 * 编译后使用打印堆栈大小，局部变量的数量和方法的参数
 * javap -v Application.class
 * <p>
 * 程序计数器：
 * 线程私有的，每个线程一份，内部保存字节码的行号，用于记录正在执行的字节码指令的地址
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
