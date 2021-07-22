package lolydleo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author zzm
 * @learner lolydleo
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        // 获取一个unsafe对象
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        // 完成内存分配
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}

/**
 * Unsafe类是一个位于sun.misc包下的类，它提供了一些相对底层方法，能够让我们接触到一些更接近操作系统底层的资源，
 * 如系统的内存资源、cpu指令等。而通过这些方法，我们能够完成一些普通方法无法实现的功能，例如直接使用偏移地址操作对象、数组等等。
 * 但是在使用这些方法提供的便利的同时，也存在一些潜在的安全因素，例如对内存的错误操作可能会引起内存泄漏，严重时甚至可能引起jvm崩溃。
 * */

/**
 * 直接通过反射获取Unsafe实例进行内存分配
 * */
