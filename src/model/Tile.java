package model;

/**
 * Represents a tile on the board of a game of Snake
 *
 * @author Vannela Chatla
 */

public class Tile {
    private int x;
    private int y;
    private int tileX, tileY;
    private static int TILE_SIZE = 20;
    
    /**
     * Create a new Tile object
     *
     * @param x The x-coordinate of the specified square
     * @param y The y-coordinate of the specified square
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        tileX = Math.floorDiv(x, TILE_SIZE);
        tileY = Math.floorDiv(y, TILE_SIZE);
    }
    
    /**
     * Get the x-coordinate of the current square
     *
     * @return An integer representing the x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get the y-coordinate of the current square
     *
     * @return An integer representing the y-coordinate
     */
    public int getY() {
        return y;
    }
    
    public int getTileX() {
    	return tileX;
    }
    
    public int getTileY() {
    	return tileY;
    }
    
    /**
     * Set the x-coordinate to a new square
     *
     * @param x An integer representing the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
        tileX = Math.floorDiv(x, TILE_SIZE);
    }
    
    /**
     * Set the y-coordinate to a new square
     *
     * @param y An integer representing the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
        tileY = Math.floorDiv(y, TILE_SIZE);
    }
    
    /**
     * Determine if this Tile object is equal to another Tile object
     *
     * @param obj Another object to be compared with this Point object
     * @return True if the other object is both a Point object and has the same x- and y-coordinates, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tile other = (Tile) obj;
        return tileX == other.tileX && tileY == other.tileY;
    }
    
    @Override
    public String toString() {
        return "(" + tileX + ", " + tileY + ")";
    }
    
    /**
     * Calculate a hash code for this Tile object
     *
     * @return An integer that represents the hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
