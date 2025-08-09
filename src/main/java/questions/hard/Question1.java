package questions.hard;

public class Question1 {

    //Question with sealed, interfaces,

    sealed interface Animal {
//        private int age = 0; // LINE 1

        void sound();
    }

    final class Dog implements Animal {
        public void sound() {
            System.out.println("Woof");
        }
    }

    record Cat(String name) implements Animal { // LINE 2
        public void sound() {
            System.out.println("Meow " + name);
        }
    }

    non-sealed class Bird implements Animal { // LINE 3
        String type = "Parrot";

        public void sound() {
            System.out.println("Chirp");
        }
    }

    record Lion(int age) implements Animal {

//        String name = "Lion"; //LINE 4

        @Override
        public void sound() {
            System.out.println("Roar");
        }
    }

    final class Sparrow extends Bird {
        // inherits sound()
    }

    public static void main(String[] args) {
        Animal a = new Cat("Kitty");

//        String result = switch (a) { // LINE 5
//            case Cat(var n) -> "Cat name: " + n; // LINE 6
//            case Dog d -> "Dog says woof";
//            case Sparrow s -> "Tiny bird";
//            case Bird bird -> "Bird";
//            case Lion(var age) when age > 1 -> "Lion age: " + age; //LINE 7
//            case Lion(var age) when age <= 1 -> "Lion age: " + age; //Line 8
//            case null -> "null";
//        };
//        System.out.println(result);
    }
}

/**
 * Answer: LINE 1, LINE 4, LINE 5
 * B) LINE 2 DOES NOT COMPILE
 * C) LINE 3 DOES NOT COMPILE
 * D) LINE 4 DOES NOT COMPILE
 * E) LINE 5 DOES NOT COMPILE
 * F) LINE 6 DOES NOT COMPILE
 * G) LINE 7 DOES NOT COMPILE
 * H) LINE 8 DOES NOT COMPILE
 */