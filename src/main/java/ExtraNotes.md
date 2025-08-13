### TODO merge this notes:


### BitWise

        System.out.println(~5); //-6
        System.out.println(~6); //-7 
        System.out.println(~7); //-8
        System.out.println(~8); //-9
        System.out.println(~9); //-10
        System.out.println(~10); //-11
        System.out.println(1 ^ 1); //0
        System.out.println(2 ^ 1); //3 0010 001 = 0011
        System.out.println(3 ^ 1); //2 0011 001 = 0010
        System.out.println(4 ^ 1); //5 0100 001 = 0101
        System.out.println(1 ^ 2); //3
        System.out.println(2 ^ 2); //0
        System.out.println(3 ^ 2); //1
        System.out.println(4 ^ 2); //6
        System.out.println(5 ^ 2); //7
        System.out.println(2 >> 2); //0
        System.out.println(8 >> 2); //2 //shifting two places to the right -> 0b1000 to 0b0010
        System.out.println(-8 >> 2); //-2 //keeps the sign (arithmetic shift).
        System.out.println(8 >>> 2); //2
        System.out.println(-8 >>> 2); //1073741822
        int x = 2;
        System.out.println(x ^ ~x); //-1
        System.out.println(10 ^ -11);

### Enum

- Enum.A.ordinal() //starts with 0
- Enum does not have a public constructor, by default it is private.

> Unlike a regular java class, you cannot access a non-final static field from an enum's constructor. ( JLS 8.9.2 )

    enum Pets {
        DOG("D"), CAT("C"), FISH("F");
        private static final String prefix = "I am ";
        private static String prefixNotFinal = "I am ";
        String name;

        Pets(String s) {
            name = s + prefix;
        //   var x = prefixNotFinal; //DOES NOT COMPILE
            var y = prefix;
        }


1. Every enum has a static valueOf(String ) method, which takes the exact name of the enum constant as String and returns the enum constant. 
Here, AccountType.valueOf("FD"), will return AccountType.FD enum. 
But if the argument does not match any enum name, a java.lang.IllegalArgumentException is thrown.
2. Every enum has an instance method named ordinal(), which returns the index of the enum constant in the order the constants are declared in the enum. The indexing starts with 0.
3. Every enum implicitly extends Enum class and the Enum class has a toString() method which returns the name of the enum constant as a String. 
So, for example, AccountType.FD will return "FD". However, in this case, the toString method has been overridden to return the name in lowercase.


        enum Type {
            DAY,
            WEEK,
            MONTH;

            @Override
            public String toString() {
                return super.toString().toLowerCase();
            }
        }

        System.out.println(Type.DAY.ordinal());
        System.out.println(Type.DAY.name());
        System.out.println(Type.valueOf(Type.DAY.name()));
        //System.out.println(Type.valueOf(Type.DAY.toString())); .IllegalArgumentException: No enum constant null.day

### Interface

Interface cannot have private variables

    interface Size {   
    //        private final int STEP = 10; //DOES NOT COMPILE
    //        private static final int STEP = 10; //DOES NOT COMPILE
    }

```java
class Interfaces {

    interface House {
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

        public static void main(String[] args) {
            Office office = new Interfaces().new WTH();
            //office.getAddress(); //DOES NOT COMPILE -> Trying to access static method
            //Java requires a static interface method to be invoked using the interface name instead of a reference variable
            ((WTH)office).getAddress();
        }
    }

}
```

### Stream

The method filtering receives a predicate and filter the elements.

.collect(Collectors.filtering(p, Collectors.mapping(Book::title, Collectors.toSet())));


#### Reduce

List<String> items = Arrays.asList("Candy", "Gum");

        var result = items.parallelStream()
                .reduce(items.parallelStream().reduce("", (a, b) -> a + b), //Return CandyGum as identity
                        (a, b) -> a + b);

        System.out.println(result); //CandyGumCandyCandyGumGum

### Record

Records does not allow extends

       public static sealed abstract class Person21 permits Student2 { //does not compile
       }
    
       static record Student2(int id) extends Person21 { //DOES NOT COMPILE, extends not allowed
       }

### Date

        LocalDateTime now = LocalDateTime.now();
        System.out.println(Duration.between(now, now.plusHours(1))); //PT1H
        System.out.println(Duration.between(
                OffsetTime.of(now.toLocalTime(), ZoneOffset.UTC),
                OffsetTime.of(now.toLocalTime(), ZoneOffset.of("+01:00"))
                )); //PT-1H

         System.out.println(Duration.between(
                OffsetTime.of(now.toLocalTime(), ZoneOffset.of("-03:00")),
                OffsetTime.of(now.toLocalTime(), ZoneOffset.of("+01:00"))
                )); //PT-4H

        System.out.println(Duration.between(
            LocalDateTime.of(2022, Month.MARCH, 13,2,0),
            LocalDateTime.of(2022, Month.MARCH, 13,3,0)
        )); //PT1H

        System.out.println(Duration.between(
                ZonedDateTime.of(LocalDateTime.of(2022, Month.MARCH, 13,2,0),
                        ZoneId.of("US/Eastern")),//-5
                ZonedDateTime.of(LocalDateTime.of(2022, Month.MARCH, 13,3,0),
                        ZoneId.of("US/Eastern"))//-4
        )); //PT0S

        System.out.println(Duration.between(
                ZonedDateTime.of(LocalDateTime.of(2022, Month.NOVEMBER, 6,1,0),
                        ZoneId.of("US/Eastern")),//-4
                ZonedDateTime.of(LocalDateTime.of(2022, Month.NOVEMBER, 6,2,0),
                        ZoneId.of("US/Eastern"))//-5
        )); //PT2H

        System.out.println(Duration.between(
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-04:00")),
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-05:00"))
        )); //PT1H

March +1 ->    -05:00 to -04:00

    System.out.println(Duration.between(
        OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-04:00")),
        OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-05:00"))
    )); //PT1H


November -1 -> -04:00 to -05:00

        System.out.println(Duration.between(
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-05:00")),
                OffsetTime.of(date.toLocalTime(), ZoneOffset.of("-04:00"))
        )); //PT-1H

### Optional - Reduce

        OptionalInt reduce = IntStream.rangeClosed(0,5).reduce((a1, b) -> a1 + b);
        System.out.println(reduce.getAsInt()); //15
        int reduceInt = IntStream.rangeClosed(0,5).reduce(0, (a1, b) -> a1 + b);
        System.out.println(reduceInt); //15

### Casting

        C  c = null;
        A a = new B();
        A a2 = new A();
        B b2 = (B)a;
        //B b3 = (B)a2; //compile - Casting exception

### Modules

Module with Service Loader:
- DOES NOT requires the module that contains the implementation
- uses the Service Interface module

> The module that uses a service should not even be aware of the module that provides the service.

    module main {
        uses org.bomfim.service.AnimalService;
        requires sub.module; //Module with AnimalServiceImpl NOT REQUIRED, NOT RECOMMENDED
        requires task; //Module with AnimalService
    }

If b.jar is not a modular application, these commands will have the following output:
- jdeps --module-path lib\a.jar; lib\b.jar
  //Not work, b.jar is not a modular, so we cannot use --module-path
- jdeps --class-path lib\a.jar; lib\b.jar
  Ideally, since a.jar is a module, it should be put on the module-path. However, if b.jar does not refer to classes from a.jar, this will work and will do the analysis for b.jar.


> In bottom-up migration all classes and jars get moved to the module-path.

> If a request is made from an automatic module to load a type whose package is not defined in any known module then the module system will attempt to load it from the classpath.

> If a package is defined in both a named module and the unnamed module then the package in the unnamed module is ignored.

> If a package is available in a jar on the classpath as well as in another jar on the module-path, the one on the classpath is ignored.

The jdeps tool is used to find out all dependencies of a class file or a jar file. It inspects the given class file (or
all class files inside a jar files) and finds out all the required modules and packages that are referred to by this
class or jar file.

Example:

    jdeps --module-path out out\moduleA\test\A.class

### JImage

Creation of a JImage does not require an application to be modular.

> The JImage tool can list, extract, and verify the contents of a JImage file.

> The JImage format is used to store runtime classes and resources of modules.

>  The JImage file replaces rt.jar as of Java 9.

JImage is a tool and file format in Java used for storing the modular runtime image.

It's essentially an archive that contains compiled Java class files (.class files), 
resource files (.properties, .txt, etc.), and other necessary components for the Java runtime environment. 
It is not used to store native libraries (.dll, .so, .dylib).

Example:

    # List all modules in the runtime image
    jimage list $JAVA_HOME/lib/modules | head
    
    # Extract all contents to ./extracted/
    jimage extract --dir extracted $JAVA_HOME/lib/modules
    
    # Verify the image
    jimage verify $JAVA_HOME/lib/modules

### IO

When you're using Files.lines(Path path) (from java.nio.file.Files) to read a file line-by-line as a stream, here's what happens if the file is corrupted mid-read.


| Situation                   | Outcome                                                                  |
|-----------------------------|--------------------------------------------------------------------------|
| File is corrupted on line 2 | `UncheckedIOException` is thrown during stream traversal                 |
| File encoding is malformed  | `MalformedInputException` wrapped in `UncheckedIOException`              |
| File deleted mid-stream     | `NoSuchFileException` or `IOException` wrapped in `UncheckedIOException` |


### Thread

When you execute the program, the JVM creates and starts the main thread to execute the main method. This thread is in
RUNNABLE state and may be scheduled to execute any time. A sleeping thread is in TIMED_WAITING state. Therefore, the "A"
thread is put in TIMED_WAITING state as soon as it calls Thread.sleep(10000).

Note that when **another** thread interrupts the sleeping thread, the sleeping thread transitions to RUNNABLE state and
comes out of the sleep method with an InterruptedException. The thread that calls interrupt remains in RUNNABLE state.

#### Virtual Thread

> A Virtual thread executes code on top of a platform thread.

        Thread.Builder.OfVirtual ofVirtual = Thread.ofVirtual();
        Thread t = Thread.ofVirtual().unstarted(new Runnable() {
            @Override
            public void run() {
                
            }
        });

- Virtual Threads are daemon threads, does NOT prevent the JVM from exiting when the program finishes

### System.Console

System.Console does not throw exception, but it can be null.

    char[] password = console.readPassword("Enter password: "); //does not encrypt the password

Return as char[], but does not encrypt the password.

### Serializable

Primitive types are serializable.

Constructor of the class for an object being deserialized is never invoked except for record classes.

If an object is being deserialized, that means the class of that object implements Serializable. Therefore, its constructor will never be called. 
However, constructor for the super class may be invoked if the super class does not implement serializable interface.

While deserializing a record, a new record object is created by invoking the record class’s canonical constructor, passing values deserialized from the stream as arguments to the canonical constructor. 
This is secure because it means the record class can validate the values before assigning them to fields, just like when an ordinary Java program creates a record object via new.

### IO

When you create a FileOutputStream without specifying the append mode (which can be true or false), it overwrites the existing file.

#### Glob Pattern

There are questions in the exam that expect you to know basic syntax for the glob pattern. The following description from JavaDoc API is sufficient:

String representation of the path is matched using a limited pattern language that resembles regular expressions but with a simpler syntax.

For example:
1. *.java : Matches a path that represents a file name ending in .java
2. *.* : Matches file names containing a dot
3. *.{java,class} : Matches file names ending with .java or .class
4. foo.? : Matches file names starting with foo. and a single character extension

Example:

```java

public static void findFiles() throws Exception {
    Path dir = Paths.get(".");
    try {
        DirectoryStream<Path> ds = Files.newDirectoryStream(dir, "*.{gif,jpeg}");
        for(Path p : ds){
            System.out.println(p);
        }
    } catch (Exception e){
        
    }
}


```