import java.util.Random;

public class DiceUtils {

    private static final Random random = new Random();

    public static int rollDice(int M, int N) {
        int sum = 0;
        for (int i = 0; i < M; i++) {
            sum += random.nextInt(N) + 1;
        }
        return sum;
    }

    public static int[] simulateRolls(int M, int N, int K) {
        if (K <= 0) {
            throw new IllegalArgumentException("K must be greater than 0.");
        }
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0.");
        }
        int[] results = new int[K];
        for (int i = 0; i < K; i++) {
            results[i] = rollDice(M, N);
        }
        return results;
    }

    public static double[] calculateProbabilityDistribution(int M, int N, int K) {
        int[] rollResults = simulateRolls(M, N, K);
        int maxSum = M * N;
        int minSum = M;
        double[] probabilities = new double[maxSum - minSum + 1];

        // Count results
        for (int result : rollResults) {
            probabilities[result - minSum]++;
        }

        // Convert counts to probabilities
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= K;
        }

        return probabilities;
    }
}
