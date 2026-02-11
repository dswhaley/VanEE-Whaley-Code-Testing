import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HumanGuessesGameTest {

    @Test
    void testInitialState() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        assertEquals(0, game.getNumGuesses(), "Initial guess count should be 0.");
        assertFalse(game.isDone(), "Game should not be done at start.");
    }

    @Test
    void testMakeGuessLow() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        GuessResult result = game.makeGuess(250);

        assertEquals(GuessResult.LOW, result);
        assertEquals(1, game.getNumGuesses());
        assertFalse(game.isDone(), "Game should not be done on a low guess.");
    }

    @Test
    void testMakeGuessHigh() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        GuessResult result = game.makeGuess(750);

        assertEquals(GuessResult.HIGH, result);
        assertEquals(1, game.getNumGuesses());
        assertFalse(game.isDone(), "Game should not be done on a high guess.");
    }

    @Test
    void testMakeGuessCorrect() {
        // This test verifies Observability (isDone) and Controllability (target injection)
        HumanGuessesGame game = new HumanGuessesGame(500);
        GuessResult result = game.makeGuess(500);

        assertEquals(GuessResult.CORRECT, result);
        assertEquals(1, game.getNumGuesses());
        assertTrue(game.isDone(), "isDone() should be true after a correct guess.");
    }

    @Test
    void testMultipleGuessesIncrementCounter() {
        HumanGuessesGame game = new HumanGuessesGame(500);
        game.makeGuess(100);
        game.makeGuess(200);
        game.makeGuess(500);

        assertEquals(3, game.getNumGuesses(), "Guess counter should increment correctly.");
        assertTrue(game.isDone());
    }
}