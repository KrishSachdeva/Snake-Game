package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CustomFont;
import model.SnakeAccount;
import model.SnakeAccountCollection;
/*
 * @author Vennela Chatla
 */
public class MainMenu extends VBox {

    private static final int BUTTON_FONT_SIZE = 20;

    private Button startGameButton;
    private Button settingsButton;
    private Label welcomeLabel;
    private Label highScoreLabel; 
    private CustomFont buttonFont;
    private Button leaderBoardButton;


    public MainMenu(SnakeGUI snakeGUI) {
        buttonFont = new CustomFont(BUTTON_FONT_SIZE);
    }

    
	/**
	 * Initialize the components to the Main Menu
	 *
	 * @param snakeGUI the main GUI that shows the game
	 */
    public void initializeComponents(SnakeGUI snakeGUI) {
        SnakeAccount account = snakeGUI.getLoginPane().getCurrentAccount();
        startGameButton = new Button("Start Game");
        startGameButton.setOnAction(event -> startGame(snakeGUI));
        startGameButton.setFont(buttonFont.getCustomFont());
        startGameButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        startGameButton.setTextFill(Color.WHITE);

        settingsButton = new Button("Settings");
        settingsButton.setOnAction(event -> openSettings(snakeGUI));
        settingsButton.setFont(buttonFont.getCustomFont());
        settingsButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        settingsButton.setTextFill(Color.WHITE);

        welcomeLabel = new Label("Welcome, " + account.getUsername() + "!");
        welcomeLabel.setFont(buttonFont.getCustomFont());
        welcomeLabel.setTextFill(Color.WHITE);
        
        
        highScoreLabel = new Label("Overall High Score: " + account.getHighScore());
        highScoreLabel.setFont(buttonFont.getCustomFont());
        highScoreLabel.setTextFill(Color.WHITE);
        
        
        leaderBoardButton = new Button("View Leaderboard");
        leaderBoardButton.setFont(buttonFont.getCustomFont());
        leaderBoardButton.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        leaderBoardButton.setTextFill(Color.WHITE);
        leaderBoardButton.setOnAction(e -> {
            LeaderBoard lb = new LeaderBoard(snakeGUI.getAccountCollection());
            lb.display(new Stage());
        });
    }

	/**
	 * Display the components to the Main Menu
	 *
	 */
    public void layoutComponents() {
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setSpacing(20);
        
        this.getChildren().addAll(welcomeLabel, highScoreLabel, startGameButton, settingsButton, leaderBoardButton); // Include leaderboard button

    }

	/**
	 * Starts the game
	 *
	 * @param snakeGUI the main GUI that shows the game
	 */
    private void startGame(SnakeGUI snakeGUI) {
        snakeGUI.startGame();
    }

	/**
	 * Opens the settings menu
	 *
	 * @param snakeGUI the main GUI that shows the game
	 */
    private void openSettings(SnakeGUI snakeGUI) {
        snakeGUI.setSceneRoot(snakeGUI.getSettingsMenu());
    }
}