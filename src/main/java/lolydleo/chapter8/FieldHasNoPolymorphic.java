package lolydleo.chapter8;

/**
 * 字段不参与多态
 * @author zzm
 * @learner lolydleo
 */
public class FieldHasNoPolymorphic {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Father, i have $" + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Son,  i have $" + money);
        }
    }

    public static void main(String[] args) {
        // 父类的引用指向子类的实例
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
    }
}

/**
 * 执行结果为
 * I am Son,  i have $0 // 子类还未构造
 * I am Son,  i have $4
 * This gay has $2
 *
 * Son类在创建的时候，首先隐式地调用了Father的构造函数，而Father构造函数中对showMeTheMoney的调用是一次虚方法调用
 *
 * */
