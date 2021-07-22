package lolydleo;

public class RuntimeConstantPoolOOM_2 {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
/**
 * JDK6得到两个false，因为需要把首次遇到的字符串实例复制到永久代的字符串常量池中，而StringBuilder创建在堆上，引用不一致
 * JDK7则没有这个问题，不过java字符串并非在此处第一次出现
 * */
