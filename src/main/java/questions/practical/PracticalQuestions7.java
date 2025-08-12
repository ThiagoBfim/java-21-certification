package questions.practical;

public class PracticalQuestions7 {

    enum Type {
        A, B, C
    }

    public static void main(String[] args) {
        System.out.println(Type.A.ordinal()); //0
        System.out.println(Type.A.name()); //"A"
    }
}

class OverrideInterfaces {

    interface A {

        public default void foo(){
            System.out.println("A.foo");
        }

    }
    interface B extends A{

        void foo(); //COMPILES

    }
    interface C extends A{

//        static void foo(){}; //DOES NOT COMPILE

        static void bar(){

        }

    }

}

class Interfaces {

    interface House {
        //        private static final String name = "abc"; //DOES NOT COMPILE, Interface cannot have a private variable
        public static final String name = "abc";

        private void method() {
        }

        public default String getAddress() {
            return "127.0.0.1";
        }
    }

    interface Office {
        public static String getAddress() {
            return "127.0.0.2";
        }
    }

    class WTH implements House, Office {

        static int x = 10;

        void printX(){
            System.out.println(this.x);
        }

    }

    public static void main(String[] args) {
        Office office = new Interfaces().new WTH();
        //office.getAddress(); //DOES NOT COMPILE -> Trying to access static method
        //Java requires a static interface method to be invoked using the interface name instead of a reference variable
        ((WTH) office).getAddress();
        Office.getAddress();

        System.out.println(((WTH) office).x); //10
        ((WTH) office).printX(); //10
    }

}

class OverrideTest {

    public static void main(String[] args) {
        OverrideTest override = new OverrideTest();
        override.print(1, 2); //int, float 3.0
        override.print(1, 2.0); //double, double 3.0
//        override.printWrapper(1, 2.0); //DOES NOT COMPILE
        override.printWrapper(1.0, 2.0); // Double ... : [Ljava.lang.Double;@548c4f57
        override.printWrapper(1, 2.0F); // Integer, Float : 3.0
    }

    public void print(int x, float y) {
        System.out.println("int, float: " + (x + y));
    }

    public void print(double x, double y) {
        System.out.println("double, double: " + (x + y));
    }

    public void printWrapper(Double... values) {
        System.out.println("Double ... : " + values);
    }

    public void printWrapper(Integer x, Float f) {
        System.out.println("Integer, Float : " + (x + f));
    }

}