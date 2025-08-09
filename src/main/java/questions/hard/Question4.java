package questions.hard;

import java.io.*;

public class Question4 {

    public static void main(String[] args) {
        Child child = new Child(20, "abc", "pass");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("child.ser"));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("child.ser"))) {

            child.age = 15;
            child.id = 2;
            child.persisted = true;
            oos.writeObject(child);

            Child deserializedChild = (Child) ois.readObject();

            System.out.println(deserializedChild);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during the serialization");
        }
    }


    static class Parent {
        int id;

        public Parent() {
            this.id = 10;
            System.out.println("Parent constructor called, id = " + id);
        }
    }

    static class Child extends Parent implements Serializable {
        int age = 20;
        String name;
        transient String password;
        transient boolean persisted;

        public Child() {
            this.persisted = true;
        }

        public Child(int age, String name, String password) {
            this.age = age;
            this.name = name;
            this.password = password;
        }

        @Override
        public String toString() {
            return "age=" + age +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", persisted='" + persisted + '\'';
        }
    }

}