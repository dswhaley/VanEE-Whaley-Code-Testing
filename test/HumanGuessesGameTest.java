import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HumanGuessesGameTest {

    @Test
    void testInitialState() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        assertEquals(0, game.getNumGuesses());
        assertFalse(game.isDone());
    }

    @Test
    void testMakeGuessLow() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        GuessResult result = game.makeGuess(250);

        assertEquals(GuessResult.LOW, result);
        assertEquals(1, game.getNumGuesses());
        assertFalse(game.isDone());
    }

    @Test
    void testMakeGuessHigh() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        GuessResult result = game.makeGuess(750);

        assertEquals(GuessResult.HIGH, result);
        assertEquals(1, game.getNumGuesses());
        assertFalse(game.isDone());
    }

    @Test
    void testMakeGuessCorrect() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        GuessResult result = game.makeGuess(500);

        assertEquals(GuessResult.CORRECT, result);
        assertEquals(1, game.getNumGuesses());
        assertTrue(game.isDone());
    }

    @Test
    void testMultipleGuessesIncrementCounter() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        game.makeGuess(100);
        game.makeGuess(200);
        game.makeGuess(500);

        assertEquals(3, game.getNumGuesses());
        assertTrue(game.isDone());
    }
}