package base.package1;

public class Father2 {
    public static void main(String[] args) {
        Father father = new Father();
        // 同包下可以访问 default、protected、public修饰的成员
        System.out.println(father.testDefault);
        System.out.println(father.testProtected);
        System.out.println(father.testPublic);
    }
}
