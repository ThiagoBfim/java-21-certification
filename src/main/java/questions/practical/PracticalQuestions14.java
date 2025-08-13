package questions.practical;

import java.io.*;

public class PracticalQuestions14 {

    public static void main(String[] args) {
        System.out.println("PrintStream - Writes formatted representations of Java objects to binary stream");
        System.out.println("PrintWriter - Writes formatted representations of Java objects to character stream");
    }

}


class SerializePrimitive implements Serializable {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Parent child = new SerializePrimitive().new Child("Maria", 10);
        System.out.println(child); //Child{age=10, name='Jonh', salary='10000'}
        File file = new File("./child.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
             ObjectInputStream inputStreamReader = new ObjectInputStream(new FileInputStream(file))) {
            oos.writeObject(child);
            Child o = (Child) inputStreamReader.readObject();
            System.out.println(o); //Child{age=10, name='Jonh', salary='12000'}
        }
    }

    class Parent implements Serializable {
        final String name;

        Parent(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "Parent{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    class Child extends Parent {
        int age;
        transient int salary = 10000;

        Child(String name, int age) {
            super("Jonh");
            this.age = age;
        }

        private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
            System.out.println("READ OBJECT - ObjectInputStream");
            in.defaultReadObject();
            salary = 12000;
        }

        @Override
        public String toString() {
            return "Child{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", salary='" + salary + '\'' +
                    '}';
        }
    }
}

class SerializeRecord {

    public static void main(String[] args) {
        Test test = new Test("test", 10, "lastName"); //TEST
        System.out.println(test); //Test[name=test, age=10, lastName=lastName]
        File file = new File("./test.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
             ObjectInputStream inputStreamReader = new ObjectInputStream(new FileInputStream(file))) {
            oos.writeObject(test);


            Test o = (Test) inputStreamReader.readObject(); //TEST
            System.out.println(o); //Test[name=test, age=10, lastName=lastName]
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    class ABC {
        ABC(String name, int age, String lastName) throws Exception {
            throw new Exception("a");
        }
    }

    record Test(String name, int age,
                //            transient int id DOES NOT COMPILE
                String lastName
    ) implements Serializable {
        Test(String name, int age, String lastName) {
            this.age = age+1;
            this.lastName = lastName;
            this.name = name;
        }
    }

}

