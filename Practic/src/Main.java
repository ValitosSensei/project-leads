import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable task = () -> System.out.println("Задача 1: " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Задача 2: " + Thread.currentThread().getName());
        executor.submit(task);
        executor.submit(task2);
        executor.shutdown();
    }
}