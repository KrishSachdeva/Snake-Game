package view_controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.CustomFont;
import model.SnakeGame;

/**
 * A simple class to display the game and score
 *
 * @author Krish Sachdeva
 */

public class GameDisplay extends VBox {
    private Canvas gameCanvas;
    private GraphicsContext gc;
    private SnakeGame snakeGame;
    private Label scoreLabel;
    private CustomFont scoreFont;
    private Timeline countdown;
    
    private int countDownValue;
    
    /**
     * Initializes a new GameDisplay object to display the game
     *
     * @param width An integer representing the width of the window
     * @param height An integer representing the height of the window
     * @param snakeGame An instance of a game of Snake
     */
    public GameDisplay(int width, int height, SnakeGame snakeGame) {
        gameCanvas = new Canvas(width, height);
        this.gc = gameCanvas.getGraphicsContext2D();
        this.snakeGame = snakeGame;
        countDownValue = 3;
        
        initializeUI();
        createCountdown();
    }
    
    private void initializeUI() {
        scoreLabel = new Label();
        scoreFont = new CustomFont(20);
        scoreLabel.setFont(scoreFont.getCustomFont());
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(scoreLabel, gameCanvas);
        this.setSpacing(10);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    private void createCountdown() {
        countdown = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int currentNumber = countDownValue;
            if (currentNumber > 1) {
                if (currentNumber == 3)
                    scoreLabel.setText("set...");
                if (currentNumber == 2)
                    scoreLabel.setText("go!");
                countDownValue -= 1;
            } else {
                scoreLabel.setText("GO!");
                countdown.stop();  // Stop the countdown
                snakeGame.start();  // Start the game
            }
        }));
        countdown.setCycleCount(3);
    }
    
    public void showCountdown() {
        snakeGame.render();
        scoreLabel.setText("ready?");  // Set initial countdown value
        countdown.play();  // Start the countdown
    }
    
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
    
    public void setSnakeGame(SnakeGame sG) {
        snakeGame = sG;
    }
    
    public void updateScore() {
        scoreLabel.setText("Score: " + snakeGame.getScoreManager().getCurrentScore());
    }
}