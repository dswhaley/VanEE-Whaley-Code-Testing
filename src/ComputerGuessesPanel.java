import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and human's answers
 *
 * refactored this class
 */
public class ComputerGuessesPanel extends JPanel {

    private final ComputerGuessesGame game;
    private final JLabel guessMessage;

    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback) {
        this.game = new ComputerGuessesGame();
        this.guessMessage = new JLabel("I guess ___.");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addHeaderLabels();
        addControlButtons(cardsPanel, gameFinishedCallback);
        addVisibilityListener();
    }

    private void addHeaderLabels() {
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(guessMessage);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel prompt = new JLabel("Your number is...");
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(prompt);

        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void addControlButtons(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback) {
        // Lower Button
        createButton("Lower", e -> {
            game.updateLower();
            updateGuessDisplay();
        });

        // Equal Button
        createButton("Equal", e -> {
            // Report result before resetting UI
            GameResult result = new GameResult(false, game.getLastGuess(), game.getNumGuesses());
            gameFinishedCallback.accept(result);

            guessMessage.setText("I guess ___.");
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
        });

        // Higher Button
        createButton("Higher", e -> {
            game.updateHigher();
            updateGuessDisplay();
        });
    }

    private void createButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(listener);
        this.add(button);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void addVisibilityListener() {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                game.resetGame();
                updateGuessDisplay();
            }
        });
    }

    private void updateGuessDisplay() {
        guessMessage.setText("I guess " + game.getLastGuess() + ".");
    }
}
