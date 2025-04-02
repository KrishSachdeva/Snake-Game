package tests;

/**
 * @author Vannela Chatla
 */

import javafx.embed.swing.JFXPanel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import model.Snake;
import model.SnakeGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeGameTest {
    
    private SnakeGame snakeGame;
    private GraphicsContext gc;
    Canvas canvas = new Canvas(800, 600);
    
    @BeforeEach
    public void setUp() {
        new JFXPanel();
        
        gc = canvas.getGraphicsContext2D();
        
        snakeGame = new SnakeGame(800, 600, 75_000_000, 5, gc, null);
    }
    
    @Test
    public void testSnakeGameInitialization() {
        assertNotNull(snakeGame.getBoard());
        assertNotNull(snakeGame.getSnake());
        assertNotNull(snakeGame.getScoreManager());
    }
    
    @Test
    public void testHandleKeyPress() {
        // Test handling key presses
        snakeGame.handleKeyPress(KeyCode.UP);
        assertEquals(Snake.Direction.UP, snakeGame.getSnake().getDirection());
        
        snakeGame.handleKeyPress(KeyCode.DOWN);
        assertNotEquals(Snake.Direction.DOWN, snakeGame.getSnake().getDirection());
        
        snakeGame.handleKeyPress(KeyCode.LEFT);
        assertEquals(Snake.Direction.LEFT, snakeGame.getSnake().getDirection());
        
        snakeGame.handleKeyPress(KeyCode.RIGHT);

    }
    
    
}

 
