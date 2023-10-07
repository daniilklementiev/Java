package step.learning.async;

import java.sql.SQLOutput;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TaskDemo {
    private final ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public void run() {
        long t = System.nanoTime();
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    System.err.println("supplier" + e.getMessage());
                }
                return "Supply or smth";
            }
        };
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.printf("%.1f Accepted: '%s' %n", (System.nanoTime() - t) / 1e6, s);
            }
        };

        Function<String, String> addQuestion = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + "?";
            }
        };


        Future<String> task1 = CompletableFuture.supplyAsync(supplier, threadPool);
        Future<?> task2 = CompletableFuture.supplyAsync(supplier, threadPool).thenAccept(consumer);
        Future<String> task3 = CompletableFuture.supplyAsync(supplier, threadPool).thenApply(addQuestion).thenApply(addQuestion).thenApply(String::toUpperCase);
        try {
            String res = task1.get();
            System.out.printf("%.1f: %s %n", (System.nanoTime() - t) / 1e6, res);
            res = task3.get();
            System.out.printf("%.1f: %s %n", (System.nanoTime() - t) / 1e6, res);
        } catch (ExecutionException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
        threadPool.shutdown();
        try {
            boolean isDone = threadPool.awaitTermination(300, TimeUnit.MILLISECONDS);
            if(!isDone){
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%.1f: Main finishes %n", (System.nanoTime() - t) / 1e6);
    }

    public void run2() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Callable finishes";
            }
        };
        Future<String> task = threadPool.submit(callable);
        System.out.println(task.toString());
        long t = System.nanoTime();
        Future<String> task2 = threadPool.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return "Task 2 finishes";
        });
        try {
            System.out.printf("%.1f Waiting for result t2 %n", (System.nanoTime() - t) / 1e6);
            String res = task2.get(); // ~await task
            System.out.println(res);
            System.out.printf("%.1f Waiting for result t1 %n", (System.nanoTime() - t) / 1e6);
            res = task.get(); // ~await task
            System.out.printf("%.1f result %n", (System.nanoTime() - t) / 1e6);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }
        System.out.printf("%.1f shutdown %n", (System.nanoTime() - t) / 1e6);
        threadPool.shutdown();
    }

    public void run1() {
        long t = System.nanoTime();
        threadPool.submit(() -> {
            System.out.printf("%.1f Task starts %n", (System.nanoTime() - t) / 1e6);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.printf("%.1f Task ends %n", (System.nanoTime() - t) / 1e6);
        });
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.printf("%.1f shutdown %n", (System.nanoTime() - t) / 1e6);
        threadPool.shutdownNow();
        System.out.printf("%.1f Main ends %n", (System.nanoTime() - t) / 1e6);
    }
}
