package Practica11_Threads2;

import java.util.Random;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int n = 10;

        int[] inputArray = new int[n];
        int[] outputArray = new int[n];

        for (int i = 0; i < n; i++) {
            inputArray[i] = i + 1;
        }

        ExecutorService executor = Executors.newFixedThreadPool(n);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < n; i++) {
            final int index = i;
            completionService.submit(() -> {
                int result = SequenceTwoMultiplier.getInstance().doMultiplicationSequence(inputArray[index]);
                outputArray[index] = result;
                return result;
            });
        }

        Random random = new Random();
        int completedTasks = 0;

        while (completedTasks < n) {
            Future<Integer> completedTask = completionService.take();
            int result = completedTask.get();
            completedTasks++;

            int sleepTime = random.nextInt(5) + 1;
            Thread.sleep(sleepTime * 1000);

            double progress = (double) completedTasks / n * 100;
            System.out.println("Progreso: " + progress + "% - Hilo " + completedTasks + " terminado - Resultado: " + result);
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        System.out.print("Resultado: [");
        for (int i = 0; i < n; i++) {
            System.out.print(outputArray[i]);
            if (i < n - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }
}
