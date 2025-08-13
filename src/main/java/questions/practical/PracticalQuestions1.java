package questions.practical;

public class PracticalQuestions1 {

    public static void main(String[] args) {
        int x = Integer.parseInt("A", 16); //10
        byte x1 = (byte) (x - 9);
        System.out.println(x1); //1

        int x2 = Integer.parseInt("10", 2);
        System.out.println(x2); //2

        System.out.println(true ^ false);
        System.out.println(true ^ true);
        System.out.println(false ^ false);
    }
}

class BitWise {

    public static void main(String[] args) {
        int i = 5;
        int i2 = ~i;
        int i3 = i ^ i2;
        System.out.println(i2); //-6
        System.out.println(i3); //-1

        int a = 5;     // 0101
        int b = 3;     // 0011
        int result = a & b;  // 0101 0011 - 0001 = 1
        System.out.println(result); // 6
        int result2 = a | b;  //0101 0011 0111 = 7
        System.out.println(result2);
        System.out.println(0b0111);
    }
}
