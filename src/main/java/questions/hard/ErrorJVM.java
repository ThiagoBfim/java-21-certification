package questions.hard;

public class ErrorJVM {

    enum Date {
        HOUR("hour"), DAY("day");

        private String day;
        Date(String day) {

        }

        public String getDay() {
            return day;
        }
    }


    public static void main(String[] args) {
       Date date = Date.HOUR;

        System.out.println(date instanceof Enum);
        System.out.println(date instanceof Date);

        class A { }
        System.out.println(A.class.getCanonicalName());

//       date.getDay().toLowerCase(); //because the return value of "org.bomfim.practical.ErrorJVM$Date.getDay()" is null
        Class<Date> enumClass = Date.class;
        enumClass.getName();
        enum Type {
            DAY,
            WEEK,
            MONTH;
        }

        System.out.println(Type.valueOf(Type.DAY.name()));
        System.out.println(Type.valueOf("Banana"));
//        System.out.println(Type.valueOf(Type.DAY.toString())); //.IllegalArgumentException: No enum constant null.day


        enum Date2 {
            HOUR("hour"), DAY("day");

            private String day;
            Date2(String day) {

            }

            public String getDay() {
                return day;
            }

            @Override
            public String toString() {
                return Date2.HOUR.day.toLowerCase();
            }
        }
        Date2 date2 = Date2.DAY;
        date2.toString();
    }
}
enum Type {
    DAY,
    WEEK,
    MONTH;
}

class Main {
        public static void main(String[] args) {
            class A {}
            System.out.println(A.class.getCanonicalName()); // Prints: null
            System.out.println(Main.class.getCanonicalName()); // Prints: null
        }
}