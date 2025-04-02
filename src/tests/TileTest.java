package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Tile;

public class TileTest {

    @Test
    public void testEquals() {
        Tile tile1 = new Tile(5, 5);
        Tile tile2 = new Tile(5, 5);
        Tile tile3 = new Tile(9, 9);

        assertTrue(tile1.equals(tile2));
    }

    @Test
    public void testHashCode() {
        Tile tile1 = new Tile(5, 5);
        Tile tile2 = new Tile(5, 5);
        Tile tile3 = new Tile(10, 10);

        assertEquals(tile1.hashCode(), tile2.hashCode());
        assertNotEquals(tile1.hashCode(), tile3.hashCode());
    }
}
