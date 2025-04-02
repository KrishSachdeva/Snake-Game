package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a Food Pellet to be eaten by a player in a game of Snake
 *
 * @author Kayla Pierson, Brendan Bamberg
 */

public class FoodPellet {
    private static int TILE_SIZE = 20;
    private static int POWER_UP_CHANCE = 10;
    
    private Color color;
    private Tile currentTile;
    private GraphicsContext gc;  // This is used only for drawing
    private Color[] pelletColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.CYAN, Color.BLUE, Color.PINK, Color.PURPLE};
    
    private boolean isEaten = false;
    private boolean isWhite;
    private boolean isPowerUp;
    
    /**
     * Create a new FoodPellet object for a given board size
     *
     * @param boardWidth  An integer representing the board width
     * @param boardHeight An integer representing the board height
     * @param gc          A GraphicsContext used to draw the FoodPellet
     */
    public FoodPellet(int boardWidth, int boardHeight, boolean isWhite, GraphicsContext gc) {
        this.gc = gc;
        this.isWhite = isWhite;
        isEaten = false;
        isPowerUp = false;
        spawn(boardWidth, boardHeight, true);
    }
    
    public FoodPellet(int boardWidth, int boardHeight) {
        spawn(boardWidth, boardHeight, true);
    }
    
    /**
     * Spawn the FoodPellet at a new location on the board
     *
     * @param boardWidth  An integer representing the width of the board
     * @param boardHeight An integer representing the height of the board
     * @param canPowerUp A boolean representing whether the FoodPellet can be a power up
     */
    public void spawn(int boardWidth, int boardHeight, boolean canPowerUp) {
        Random random = new Random();
        int index;
        
        // One in ten chance of being a power up
        if (canPowerUp) {
            int determinePowerUp = random.nextInt(POWER_UP_CHANCE);
            isPowerUp = determinePowerUp == POWER_UP_CHANCE - 1;
        } else {
            isPowerUp = false;
        }
        
        if (isWhite) {
            this.color = Color.WHITE;
        } else {
            index = random.nextInt(pelletColors.length); // corrected to use the length of the array
            this.color = pelletColors[index];
        }
        if (isPowerUp) {
            this.color = Color.GOLD;
        }
        
        int x = random.nextInt(boardWidth - (3 * TILE_SIZE));
        int y = random.nextInt(boardHeight - (3 * TILE_SIZE));
        
        currentTile = new Tile(x + TILE_SIZE, y + TILE_SIZE);
    }
    
    /**
     * Draw the FoodPellet at the current location on the board
     */
    public void draw() {
        gc.setFill(color);
        gc.fillRect(currentTile.getTileX() * TILE_SIZE, currentTile.getTileY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    /**
     * Respawn the FoodPellet and set its status to no longer be eaten
     *
     * @param boardWidth  An integer representing the width of the board
     * @param boardHeight An integer representing the height of the board
     * @param canPowerUp A boolean representing whether the FoodPellet can be a power up
     * @param snake A Snake object representing the player
     */
    public void respawn(int boardWidth, int boardHeight, boolean canPowerUp, Snake snake) {
        Tile oldTile = new Tile(currentTile.getTileX(), currentTile.getTileY());
        do {
            spawn(boardWidth, boardHeight, canPowerUp);
        } while (oldTile.equals(currentTile) || snake.getBody().contains(currentTile));
        isEaten = false;
    }
    
    
    /**
     * Detect collision with the head of the Snake
     *
     * @param head A Tile object representing the head of the Snake
     * @return True if the head of the Snake has collided, False otherwise
     */
    public boolean detectCollision(Tile head) {
        if (head.equals(currentTile)) {
            isEaten = true;
            return true;
        }
        return false;
    }
    
    /**
     * Get whether the FoodPellet is eaten
     *
     * @return A boolean representing the FoodPellet's eaten status
     */
    public boolean isEaten() {
        return isEaten;
    }
    
    /**
     * Get whether the FoodPellet is a power up
     *
     * @return A boolean representing the FoodPellet's power up status
     */
    public boolean isPowerUp() {
        return isPowerUp;
    }
    
    /**
     * Get the current tile of the FoodPellet
     *
     * @return A Tile object representing the tile location on the board
     */
    public Tile getCurrentTile() {
        return currentTile;
    }
}
