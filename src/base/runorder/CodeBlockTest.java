package base.runorder;

// 父类
class Parent {
    // 父类静态代码块
    static {
        System.out.println("1. 父类静态代码块执行");
    }
    // 父类普通代码块
    {
        System.out.println("2. 父类普通代码块执行");
    }
    // 父类构造方法
    public Parent() {
        System.out.println("3. 父类构造方法执行");
    }
}

// 子类
class Child extends Parent {
    // 子类静态代码块
    static {
        System.out.println("4. 子类静态代码块执行");
    }
    // 子类普通代码块
    {
        System.out.println("5. 子类普通代码块执行");
    }
    // 子类构造方法
    public Child() {
        System.out.println("6. 子类构造方法执行");
    }
}

// 测试类
public class CodeBlockTest {
    public static void main(String[] args) {
        System.out.println("===== 创建第一个子类对象 =====");
        Child c1 = new Child();
        System.out.println("\n===== 创建第二个子类对象 =====");
        Child c2 = new Child();
    }
}