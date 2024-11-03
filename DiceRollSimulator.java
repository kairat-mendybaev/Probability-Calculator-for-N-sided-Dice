import java.util.Scanner;

public class DiceRollSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of sides (N) on each die: ");
        int N = scanner.nextInt();
        System.out.print("Enter the number of dice (M): ");
        int M = scanner.nextInt();
        System.out.print("Enter the number of simulations (K): ");
        int K = scanner.nextInt();
        scanner.close();

        double[] probabilities = DiceUtils.calculateProbabilityDistribution(M, N, K);

        System.out.println("Probability distribution:");
        for (int i = 0; i < probabilities.length; i++) {
            System.out.printf("Sum %d: %.4f%%\n", i + M, probabilities[i] * 100);
        }
    }
}
