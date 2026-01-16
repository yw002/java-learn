package jvm;

/**
 * 类的加载阶段
 * 加载
 * 链接（验证，准备，解析）
 * 初始化
 * 在类的初始化阶段，对类的静态变量、静态代码块执行初始化操作
 */
public class InitializationDemo {
    public static void main(String[] args) {
        // 1.首次访问这个类的静态变量或静态方法时
//        System.out.println(Animal.age);
        // 2.子类初始化，如果父类还没初始化，会引发父类先初始化
//        System.out.println(Cat.die);
        // 3.子类访问父类的静态变量，只触发父类初始化
        System.out.println(Cat.age);
    }
}

class Animal{
    static int age = 2;
    static {
        System.out.println("Animal的静态代码块");
    }
}

class Cat extends Animal{
    static boolean die = false;
    static {
        System.out.println("Cat 静态代码块 1");
    }

    static {
        System.out.println("Cat 静态代码块 2");
    }
}