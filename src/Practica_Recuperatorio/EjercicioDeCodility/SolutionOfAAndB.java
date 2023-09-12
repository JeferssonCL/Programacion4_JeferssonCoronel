package Practica_Recuperatorio.EjercicioDeCodility;

public class SolutionOfAAndB {
    public String solution(int A, int B) {
        StringBuilder result = new StringBuilder();

        while (A > 0 || B > 0) {
            if (A > B) {
                if (result.length() >= 2 && result.substring(result.length() - 2).equals("aa")) {
                    result.append("b");
                    B--;
                } else {
                    result.append("a");
                    A--;
                }
            } else {
                if (result.length() >= 2 && result.substring(result.length() - 2).equals("bb")) {
                    result.append("a");
                    A--;
                } else {
                    result.append("b");
                    B--;
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        SolutionOfAAndB solutionOfAAndB = new SolutionOfAAndB();
        System.out.println(solutionOfAAndB.solution(5, 3));  // Output: "aabaabab"
        System.out.println(solutionOfAAndB.solution(3, 3));  // Output: "ababab" (or any other valid solution)
        System.out.println(solutionOfAAndB.solution(1, 4));  // Output: "bbabb"
    }
}
