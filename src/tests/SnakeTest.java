package tests;

import javafx.scene.paint.Color;
import model.Snake;
import model.Snake.Direction;
import model.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {

	private Snake snake;
 
	@BeforeEach
	public void setUp() {
		snake = new Snake(300, 300, Color.GREEN);
	}

	@Test
	public void testSnakeCreation() {
		assertNotNull(snake);
		assertEquals(1, snake.size());
		assertNotNull(snake.getHead());
	}

	@Test
	public void testSnakeMove() {
		snake.move();
		assertEquals(1, snake.size());
		assertNotNull(snake.getHead());
	}

	@Test
	public void testSnakeGrow() {
		snake.grow();
		assertEquals(2, snake.size());
		assertNotNull(snake.getHead());
	}

	@Test
	public void testSnakeSetDirection() {
		snake.setDirection(Snake.Direction.LEFT);
		assertEquals(Snake.Direction.RIGHT, snake.getDirection());
        snake.setDirection(Snake.Direction.UP);
		assertEquals(Snake.Direction.UP, snake.getDirection());
	}
    
    @Test
    public void testSnakeMove1() {
        snake.move();
        assertEquals(1, snake.size());
        assertNotNull(snake.getHead());
    }

    @Test
    public void testSnakeGrow1() {
        snake.grow();
        assertEquals(2, snake.size());
        assertNotNull(snake.getHead());
    }

    @Test
    public void testSnakeSetDirection1() {
        snake.setDirection(Snake.Direction.UP);
        assertEquals(Snake.Direction.UP, snake.getDirection());
    }

    @Test
    public void testCannotReverseDirection() {
        snake.setDirection(Direction.RIGHT);
        snake.setDirection(Direction.LEFT);
        assertEquals(Direction.RIGHT, snake.getDirection(), "Snake should not be able to reverse direction.");
    }
    
    @Test
    public void testHeadPositionAfterMove() {
        snake.setDirection(Direction.RIGHT);
        snake.move();
        Tile head = snake.getHead();
        assertEquals(16, head.getTileX());
        assertEquals(15, head.getTileY());
    }
    
    @Test
    public void testCollisionWithSelf() {
        snake.grow();
        snake.grow();
        snake.grow();
        snake.grow();
        snake.move();
        snake.setDirection(Direction.UP);
        snake.move();
        snake.setDirection(Direction.LEFT);
        snake.move();
        snake.setDirection(Direction.DOWN);
        snake.move();
        assertTrue(snake.hasCollidedWithSelf(), "Snake should detect collision with itself.");
    }
}
