package lolydleo.chapter8;

/**
 * 方法静态分派演示
 * @author zzm
 * @learn lolydleo
 */
public class StaticDispatch {

    // 变量的静态类型，或者叫外观类型
    // 仅仅在使用时发生变化，变量本身的静态类型不会被改变，最终静态类型是在编译期可知的。
    static abstract class Human {
    }

    // 变量的实际类型，或者叫运行时类型
    // 变化结果只有在运行期才可以确定，必须等程序运行到这行才能知道
    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        // 两个静态类型相同，实际类型不同的变量
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        // 虚拟机重载时使用的是静态类型而不是实际类型
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}

/**
 * 执行结果是
 * hello,guy!
 * hello,guy!
 * */
