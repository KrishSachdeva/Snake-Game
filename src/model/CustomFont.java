package model;

import javafx.scene.text.Font;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CustomFont {
    
    Font customFont;
    FileInputStream fontInputStream;
    
    /**
     * Creates a new custom font with the specified font size
     *
     * @param size An integer representing the font size
     */
    public CustomFont(int size) {
        try {
            fontInputStream = new FileInputStream("resources/Emulogic-zrEw.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        customFont = Font.loadFont(fontInputStream, size);
    }
    
    public void setFontSize(int size) {
        customFont = Font.loadFont(fontInputStream, size);
    }
    
    public Font getCustomFont() {
        return customFont;
    }
}
