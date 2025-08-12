package questions.practical;

public class PracticalQuestions1 {

    public static void main(String[] args) {
        int x = Integer.parseInt("A", 16); //10
        byte x1 = (byte) (x - 9);
        System.out.println(x1); //1

        int x2 = Integer.parseInt("10", 2);
        System.out.println(x2); //2
    }
}

class BitWise {

    public static void main(String[] args) {
        int i = 5;
        int i2 = ~i;
        int i3 = i ^ i2;
        System.out.println(i2); //-6
        System.out.println(i3); //-1
    }
}
