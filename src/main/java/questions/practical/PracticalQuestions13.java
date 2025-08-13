package questions.practical;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PracticalQuestions13{

    public static void main(String[] args) {
        Thread.Builder.OfVirtual ofVirtual = Thread.ofVirtual();
        Thread unstarted = ofVirtual.unstarted(() -> {});
        unstarted.run(); //sync - void
        unstarted.start(); //async - void
        Executors.newSingleThreadExecutor().execute(() -> {}); //Runnable - void
//        Executors.newSingleThreadExecutor().execute(() -> 5); //Compile error
        Executors.newSingleThreadExecutor().submit(() -> {}); //Runnable - Future
        Executors.newSingleThreadExecutor().submit(() -> 5); //Callable
    }
}


class Threads {
    static ArrayList al = new ArrayList();

    public static void main(String[] args) {
        Runnable r = () -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (al) {
                    al.add(i);
                }
            }
        };
        Thread t1 = new Thread(r, "T1");
        Thread t2 = new Thread(r, "T2");

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future f1 = es.submit(t1);
        Future f2 = es.submit(t2);
        es.shutdown();
        try {
            f1.get();
            f2.get();
            System.out.println(al);
            System.out.println(al.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ScheduledFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        threadPool();
    }

    private static void threadPool() throws InterruptedException, ExecutionException {
        try (ExecutorService executorService = Executors.newScheduledThreadPool(1)) {
//            ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> 10, 5, TimeUnit.MILLISECONDS); //DOES NOT COMPILE
        }
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> 10, 5, TimeUnit.MILLISECONDS);
        System.out.println(schedule.get());
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Schedule2"), 5, 5, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Schedule3"), 5, 5, TimeUnit.MILLISECONDS);
        scheduledExecutorService.schedule(() -> System.out.println("Schedule1"), 2, TimeUnit.MILLISECONDS);
        Thread.sleep(10);
        System.out.println("End");
        //scheduledExecutorService.scheduleWithFixedDelay = Creates and executes Runnable task after given initial delay and subsequently with given delay between termination of one execution and commencement of next
        List<Runnable> runnables = scheduledExecutorService.shutdownNow();
        System.out.println(runnables.size());
        scheduledExecutorService.shutdown();
    }
}

class ThreadExample {

    static class A implements Runnable {
        Thread thread;
        public void run() {
            System.out.println("Starting loop");
            try {
                System.out.println(thread.getState());
                Thread.sleep(10_000); // Line 1 - sleep for 10 seconds
            } catch (Exception e) {
                System.out.println("Error: " + thread.getState());
                e.printStackTrace();
            }
            System.out.println("Ending loop");
        }

        public void setThread(Thread t) {
            thread = t;
        }
    }

    public static void main(String[] args) throws Exception {
        A a = new A();
        Thread t = Thread.ofVirtual().start(a);
        a.setThread(t);
        System.out.println(t.getState());
        Thread.sleep(100);      // Give the virtual thread time to start
        System.out.println(t.getState());
        t.interrupt();           // Interrupt the virtual thread
        System.out.println(t.getState());
        t.join();               // Line 2 - Wait for the virtual thread to complete
        System.out.println("ending thread");
    }
}