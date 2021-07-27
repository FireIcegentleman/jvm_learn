package lolydleo.chapter4;

/**
 * 遇到一个问题，在VM Option中的类名要如何限定？
 * */
public class Bar {
    int a = 1 ;
    static int b = 2 ;

    public int sum(int c){
        return a + b + c ;
    }

    public static void main(String[] args) {
        new Bar().sum(3) ;
    }
}
