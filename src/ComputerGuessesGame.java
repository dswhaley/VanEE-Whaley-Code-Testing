public class ComputerGuessesGame {
    private int numGuesses;
    private int lastGuess;
    private int upperBound;
    private int lowerBound;

    public ComputerGuessesGame() {
        resetGame();
    }

    public void resetGame() {
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        lastGuess = (lowerBound + upperBound + 1) / 2;
    }

    public void updateLower() {
        upperBound = Math.min(upperBound, lastGuess);
        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
    }

    public void updateHigher() {
        lowerBound = Math.max(lowerBound, lastGuess + 1);
        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
    }

    public int getLastGuess() {
        return lastGuess;
    }

    public int getNumGuesses() {
        return numGuesses;
    }
}