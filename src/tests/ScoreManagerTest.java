package tests;

/**
 * A JavaFX GUI that represents a game of Snake
 *
 * @author Krish Sachdeva
 */

import model.ScoreManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreManagerTest {

    private ScoreManager scoreManager;

    @BeforeEach
    public void setUp() {
        scoreManager = new ScoreManager();
    }

    @Test
    public void testInitialization() {
        assertEquals(0, scoreManager.getCurrentScore(), "Current score should be initialized to 0.");
    }

    @Test
    public void testUpdateScore() {
        scoreManager.updateScore(50);
        assertEquals(50, scoreManager.getCurrentScore(), "Score should be updated by 50 points.");
    }

    @Test
    public void testResetScore() {
        scoreManager.updateScore(100); // First update the score
        scoreManager.resetScore(); // Then reset it
        assertEquals(0, scoreManager.getCurrentScore(), "Score should reset to 0.");
    }

    @Test
    public void testSaveScore() {
        scoreManager.updateScore(200);
        scoreManager.saveScore();
        assertEquals(200, scoreManager.getHighScore(), "High score should be updated to current score.");
    }
}
