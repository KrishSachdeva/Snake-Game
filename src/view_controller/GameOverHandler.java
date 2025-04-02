package view_controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.CustomFont;
import model.SnakeGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A Scene that shows the Game Over state of a game of Snake
 *
 * @author Krish Sachdeva
 */

public class GameOverHandler extends VBox {
    
    private SnakeGame theGame;
    private SnakeGUI snakeGUI;
    private Label gameOverLabel, scoreLabel, highScoreLabel;
    private Button restart, quit;
    private Button leaderboardButton;
    
    /**
     * Creates a new GameOverHandler object
     *
     * @param theGame A SnakeGame object that has information about the current game state
     */
    public GameOverHandler(SnakeGame theGame) {
        this.theGame = theGame;
        intializeComponents();
        layoutGUI();

    }



 /**
     * Initializes all components of the Scene with custom fonts
     */
    private void intializeComponents() {
        gameOverLabel = new Label("GAME OVER");
        scoreLabel = new Label("Score: " + theGame.getScoreManager().getCurrentScore());
        highScoreLabel = new Label("High Score: " + theGame.getScoreManager().getHighScore());
        restart = new Button("Restart");
        quit = new Button("Quit");
        
        leaderboardButton = new Button("View Leaderboard"); 
        leaderboardButton.setFont(new CustomFont(15).getCustomFont());
        leaderboardButton.setOnAction(e -> {
            LeaderBoard lb = new LeaderBoard(snakeGUI.getAccountCollection());
            lb.display(new Stage());
        });

        
        // Create a new custom font for the Game Over text
        FileInputStream fontInputStream;
        try {
            fontInputStream = new FileInputStream("resources/Emulogic-zrEw.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font gameOverFont = Font.loadFont(fontInputStream, 36);
        
        // Create a new custom font for the other text
        try {
            fontInputStream = new FileInputStream("resources/Emulogic-zrEw.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        Font labelFont = Font.loadFont(fontInputStream, 15);
        
        gameOverLabel.setFont(gameOverFont);
        scoreLabel.setFont(labelFont);
        highScoreLabel.setFont(labelFont);
        restart.setFont(labelFont);
        quit.setFont(labelFont);
        
        restart.setOnAction(event -> {
        
        });
        quit.setOnAction(event -> {
        
        });
        
        try {
            fontInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Creates the layout of the Scene
     */
    private void layoutGUI() {
        this.getChildren().addAll(gameOverLabel, scoreLabel, highScoreLabel, restart, quit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        
        gameOverLabel.setAlignment(Pos.CENTER);
        scoreLabel.setAlignment(Pos.CENTER);
        highScoreLabel.setAlignment(Pos.CENTER);
        restart.setAlignment(Pos.CENTER);
        quit.setAlignment(Pos.CENTER);
    }
}