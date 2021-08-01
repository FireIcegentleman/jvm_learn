package lolydleo.chapter8;

public class StackProcessing {
    public int calc(){
        int a = 100 ;
        int b = 200 ;
        int c = 300 ;
        return (a + b) * c ;
    }
}
/**
 * 先运行指令: javac JavapTest.java 生成对应的class文件, 之后再进行javap -c操作
 * */

/**
 *     Code:
 *       stack=2, locals=4, args_size=1
 *          0: bipush        100
 *          2: istore_1
 *          3: sipush        200
 *          6: istore_2
 *          7: sipush        300
 *         10: istore_3
 *         11: iload_1
 *         12: iload_2
 *         13: iadd
 *         14: iload_3
 *         15: imul
 *         16: ireturn
 *         */
