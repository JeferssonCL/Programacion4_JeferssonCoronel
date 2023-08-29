package Practica9_IncrementalMatrix;

public class Comparison {

    public static void main(String[] args) throws InterruptedException {
        Program1 program1 = new Program1();
        Program2 program2 = new Program2();

        program1.run();
        program2.run();

        long minExecutionTime = Math.min(program1.getExecutionTime(), program2.getExecutionTime());

        System.out.println("Execution time using an array of Threads: " + program1.getExecutionTime() + " ns");
        System.out.println("Execution time using a pool of Threads: " + program2.getExecutionTime() + " ns");

        System.out.println(" -> Minimum execution time: " + minExecutionTime + " " +
                (minExecutionTime == program2.getExecutionTime() ? "ns (using a pool of Threads)." : "ns (using an array of Threads)."));
    }
}
