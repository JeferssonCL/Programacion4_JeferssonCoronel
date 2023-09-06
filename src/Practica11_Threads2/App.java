package Practica11_Threads2;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] inputArray = new int[n];
        int[] outputArray = new int[n];

        fillInputArray(inputArray, n);

        ExecutorService executor = Executors.newFixedThreadPool(n);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        submitTasks(completionService, inputArray, outputArray);

        waitForTasksToComplete(completionService, n);

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        printOutputArray(outputArray);
    }

    private static void fillInputArray(int[] inputArray, int n) {
        for (int i = 0; i < n; i++) {
            inputArray[i] = i + 1;
        }
    }

    private static void submitTasks(CompletionService<Integer> completionService, int[] inputArray, int[] outputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            final int index = i;
            completionService.submit(() -> {
                int result = SequenceTwoMultiplier.getInstance().doMultiplicationSequence(inputArray[index]);
                outputArray[index] = result;
                return result;
            });
        }
    }

    private static void waitForTasksToComplete(CompletionService<Integer> completionService, int n) throws InterruptedException, ExecutionException {
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
    }

    private static void printOutputArray(int[] outputArray) {
        System.out.print("Resultado: [");
        for (int i = 0; i < outputArray.length; i++) {
            System.out.print(outputArray[i]);
            if (i < outputArray.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }
}
