package lolydleo;
/**
 * 虚拟机栈和本地方法栈测试
 * HotSpot不区分虚拟机栈和本地方法栈
 * 1. 如果线程请求的栈深度大于虚拟机允许，StackOverflowError
 * 2. 虚拟机栈内存允许动态扩展但内存不够，OutOfMemoryError
 *
 * VM Args：-Xss128k
 *
 * @author zzm
 * @learner lolydleo
 */
public class JavaVMStackSOF_1 {

    private int stackLength = 1;

    // 不断进行递归调用
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF_1 oom = new JavaVMStackSOF_1();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}

