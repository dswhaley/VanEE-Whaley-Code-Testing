import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComputerGuessesGameTest {

    private ComputerGuessesGame game;

    @BeforeEach
    void setUp() {
        game = new ComputerGuessesGame();
        game.resetGame();
    }

    @Test
    void testInitialState() {
        assertEquals(501, game.getLastGuess());
        assertEquals(0, game.getNumGuesses());
    }

    @Test
    void testUpdateLower() {
        game.updateLower();
        assertEquals(251, game.getLastGuess());
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void testUpdateHigher() {
        game.updateHigher();
        assertEquals(751, game.getLastGuess());
        assertEquals(1, game.getNumGuesses());
    }

    @Test
    void testResetGame() {
        game.updateHigher();
        game.updateHigher();
        game.resetGame();
        assertEquals(501, game.getLastGuess());
        assertEquals(0, game.getNumGuesses());
    }

    @Test
    void testStuckAtOne() {
        for (int i = 0; i < 15; i++) {
            game.updateLower();
        }
        assertEquals(1, game.getLastGuess());
        game.updateLower();
        assertEquals(1, game.getLastGuess());
    }

    @Test
    void testStuckAtOneThousand() {
        for (int i = 0; i < 15; i++) {
            game.updateHigher();
        }
        assertEquals(1000, game.getLastGuess());
        game.updateLower();
        assertEquals(1000, game.getLastGuess());
    }

    @Test
    void testBinarySearchProgression() {
        game.updateHigher();
        assertEquals(751, game.getLastGuess());
        game.updateLower();
        assertEquals(626, game.getLastGuess());
        assertEquals(2, game.getNumGuesses());
    }

    @Test
    void testGuessCountOnImmediateWin() {
        assertEquals(0, game.getNumGuesses());
    }
}