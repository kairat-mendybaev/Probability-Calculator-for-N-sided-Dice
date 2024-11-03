import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DiceUtilsTest {

    @Test
    void testSingleDieSingleRoll() {
        double[] probabilities = DiceUtils.calculateProbabilityDistribution(1, 6, 1);
        assertEquals(6, probabilities.length, "There should be six probabilities, one for each side of the die.");

        double sumProbabilities = 0;
        int countOnes = 0;
        for (double probability : probabilities) {
            sumProbabilities += probability;
            if (probability == 1.0) {
                countOnes++;
            }
        }

        assertEquals(1.0, sumProbabilities, 0.0001, "The sum of all probabilities should be 1.0");
        assertEquals(1, countOnes, "There should be exactly one outcome with a probability of 1.0");
    }


    @Test
    void testMultipleDice() {
        assertDoesNotThrow(() -> {
            double[] probabilities = DiceUtils.calculateProbabilityDistribution(2, 6, 1000);
            assertEquals(11, probabilities.length);
            double sumProbabilities = 0;
            for (double prob : probabilities) {
                sumProbabilities += prob;
            }
            assertEquals(1.0, sumProbabilities, 0.05, "The sum of probabilities should be close to 1");
        });
    }

    @Test
    void testLargeNumberOfSides() {
        assertDoesNotThrow(() -> {
            double[] probabilities = DiceUtils.calculateProbabilityDistribution(1, 20, 1000);
            assertEquals(20, probabilities.length);
            assertTrue(probabilities[0] > 0, "There should be a non-zero probability for each outcome");
        });
    }

    @Test
    void testZeroRolls() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            DiceUtils.calculateProbabilityDistribution(2, 6, 0);
        });
        assertTrue(exception.getMessage().contains("K must be greater than 0"), "Should throw IllegalArgumentException when K is zero");
    }

    @Test
    void testHighNumberOfRolls() {
        assertDoesNotThrow(() -> {
            double[] probabilities = DiceUtils.calculateProbabilityDistribution(2, 6, 10000);
            assertEquals(11, probabilities.length);
            double sumProbabilities = 0;
            for (double prob : probabilities) {
                sumProbabilities += prob;
            }
            assertEquals(1.0, sumProbabilities, 0.01, "The sum of probabilities should be very close to 1 with a large number of rolls");
        });
    }

    @Test
    void testInvalidNumberOfSides() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            DiceUtils.calculateProbabilityDistribution(1, 0, 1000);
        });
        assertTrue(exception.getMessage().contains("N must be greater than 0"), "Should throw IllegalArgumentException when the number of sides is zero");
    }
}
