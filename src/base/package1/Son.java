package base.package1;

public class Son extends Father {
    public static void main(String[] args) {
        Son son = new Son();
        // 同包的本类和子类都可以访问：default、protected、public
        System.out.println(son.testDefault);
        System.out.println(son.testProtected);
        System.out.println(son.testPublic);
    }
}
