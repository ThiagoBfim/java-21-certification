package questions.hard;

public class Question5 {

    public static void main(String[] args) {
        int a = 12;   // 1100 in binary
        int b = 5;    // 0101 in binary

        int result1 = a & b;
        int result2 = a | b;
        int result3 = a ^ b;
        int result4 = ~a;
        int result5 = a ^ result4;

        System.out.println(result1 + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
    }

}
