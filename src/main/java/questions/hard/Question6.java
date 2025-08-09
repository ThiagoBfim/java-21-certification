package questions.hard;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Question6 {

    private static volatile int counter = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> increment());
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Final counter = " + counter);
    }

    private static void increment() {
        counter++;
    }

}
