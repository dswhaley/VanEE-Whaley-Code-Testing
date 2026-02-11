import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StatsCalculatorTest {

    private StatsCalculator calculator;
    private GameStats stats;

    @BeforeEach
    void setUp() {
        stats = new GameStats() {
            private final int[] guesses = {0, 2, 1, 1, 3, 2, 0, 1, 0, 2};

            @Override
            public int numGames(int numGuesses) {
                if (numGuesses < 0) throw new IllegalArgumentException("Negative guesses");
                if (numGuesses >= guesses.length) return 0;
                return guesses[numGuesses];
            }

            @Override
            public int maxNumGuesses() {
                return guesses.length;
            }
        };

        calculator = new StatsCalculator(stats);
    }

    @Test
    void testNormalCase() {
        ArrayList<String> results = calculator.updateResults();

        assertEquals("3", results.get(0));
        assertEquals("2", results.get(1));
        assertEquals("6", results.get(2));
        assertEquals("3", results.get(3));
        assertEquals("3", results.get(4));
        assertEquals("2", results.get(5));
        assertEquals("1", results.get(6));
        assertEquals("0", results.get(7));
    }

    @Test
    void testEmptyStats() {
        stats = new GameStats() {
            @Override
            public int numGames(int numGuesses) { return 0; }
            @Override
            public int maxNumGuesses() { return 0; }
        };
        calculator = new StatsCalculator(stats);

        ArrayList<String> results = calculator.updateResults();

        for (String s : results) {
            assertEquals("0", s);
        }
    }

    @Test
    void testSingleValue() {
        stats = new GameStats() {
            private final int[] guesses = {0, 5, 0};

            @Override
            public int numGames(int numGuesses) {
                if (numGuesses < 0) throw new IllegalArgumentException();
                if (numGuesses >= guesses.length) return 0;
                return guesses[numGuesses];
            }

            @Override
            public int maxNumGuesses() {
                return guesses.length;
            }
        };
        calculator = new StatsCalculator(stats);

        ArrayList<String> results = calculator.updateResults();

        assertEquals("5", results.get(0));
        for (int i = 1; i < results.size(); i++) {
            assertEquals("0", results.get(i));
        }
    }

    @Test
    void testException() {
        stats = new GameStats() {
            @Override
            public int numGames(int numGuesses) {
                if (numGuesses < 0) throw new IllegalArgumentException("Negative guesses");
                return 0;
            }

            @Override
            public int maxNumGuesses() {
                return 3;
            }
        };

        assertThrows(IllegalArgumentException.class, () -> stats.numGames(-1));
    }
}
