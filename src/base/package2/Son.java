package base.package2;

import base.package1.Father;

public class Son extends Father {
    public static void main(String[] args) {
        Son son = new Son();
        // 不同包的子类无法访问default，但可以访问protected和public
//        System.out.println(son.testDefault);
        System.out.println(son.testProtected);
        System.out.println(son.testPublic);
    }
}
