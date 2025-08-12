package questions.practical;

import java.util.ArrayList;
import java.util.List;

public class PracticalQuestions9 {

    public static void main(String[] args) {

    }
}

class Generics {
    public static void main(String[] args) {

    }

    List<Number> test(List<? super Number> numbers) {
        return List.of();
    }

    class B extends Generics {

        //        @Override //OVERLOAD
        List<Number> test(ArrayList<Number> numbers) {
            return super.test(numbers);
        }

        @Override
        ArrayList<Number> test(List<? super Number> numbers) {
            return new ArrayList<>();
        }
    }
}
