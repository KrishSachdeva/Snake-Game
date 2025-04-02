package tests;

/**
 *
 * @author Krish Sachdeva
 */


import model.FoodPellet;
import model.Snake;
import model.Tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FoodPelletTest {
    private final int boardWidth = 30;
    private final int boardHeight = 20;

    @Test
    public void testFoodPelletCreationAndSpawn() {
        FoodPellet foodPellet = new FoodPellet(boardWidth, boardHeight);
        Tile currentTile = foodPellet.getCurrentTile();
        assertNotNull(currentTile, "Current tile should not be null after spawning.");
        assertTrue(currentTile.getTileX() >= 0 && currentTile.getTileX() < boardWidth,
                   "Tile X should be within board width bounds after spawning.");
        assertTrue(currentTile.getTileY() >= 0 && currentTile.getTileY() < boardHeight,
                   "Tile Y should be within board height bounds after spawning.");
    }

    @Test
    public void testDetectCollision() {
        FoodPellet foodPellet = new FoodPellet(boardWidth, boardHeight);
        Tile head = foodPellet.getCurrentTile();
        assertTrue(foodPellet.detectCollision(head),
                   "Should detect a collision when  head and pellet is on same tile.");
        assertTrue(foodPellet.isEaten(),
                   "Eaten after a collision.");
    }


    @Test
    public void testRespawnPellet() {
        FoodPellet foodPellet = new FoodPellet(boardWidth, boardHeight);
        Tile originalTile = new Tile(foodPellet.getCurrentTile().getTileX(), foodPellet.getCurrentTile().getTileY());
        foodPellet.respawn(boardWidth, boardHeight, false, new Snake());
        Tile newTile = foodPellet.getCurrentTile();
        assertFalse(foodPellet.isEaten(),
                    "Pellet should not be marked as eaten after respawning.");
        assertNotEquals(originalTile, newTile,
                        "Pellet should spawn at a new location after respawning.");
    }
}
