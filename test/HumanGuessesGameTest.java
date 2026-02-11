import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class HumanGuessesGameTest {
    private HumanGuessesGame game;

    @BeforeEach
    void setUp() {
        // use package-private constructor for determinism
        game = new HumanGuessesGame(500);
    }

    @Test
    void testInitialState() {
        assertEquals(0, game.getNumGuesses(), "Should start with zero guesses.");
        assertFalse(game.isDone(), "Game should not be done initially.");
    }

    @Test
    void testGuessLow() {
        GuessResult result = game.makeGuess(1);
        assertEquals(GuessResult.LOW, result);
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void testGuessHigh() {
        GuessResult result = game.makeGuess(1000);
        assertEquals(GuessResult.HIGH, result);
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void testGuessCorrect() {
        GuessResult result = game.makeGuess(500);
        assertEquals(GuessResult.CORRECT, result);
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void testMultipleGuessesIncrement() {
        game.makeGuess(1);
        game.makeGuess(1000);
        game.makeGuess(500);
        assertEquals(3, game.getNumGuesses());
    }
}
