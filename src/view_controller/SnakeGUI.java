package view_controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SnakeAccount;
import model.SnakeAccountCollection;
import model.SnakeGame;

/**
 * A JavaFX GUI that represents a game of Snake
 *
 * @author Sameeka Maroli, Brendan Bamberg, Kayla Pierson, Vannela Chatla
 */

public class SnakeGUI extends Application {
    
    private static final int TILE_SIZE = 20;
    private int WINDOW_WIDTH = 600;
    private int WINDOW_HEIGHT = 600;
    private int ROWS = WINDOW_HEIGHT / TILE_SIZE;
    private int COLUMNS = WINDOW_WIDTH / TILE_SIZE;
    
    private SnakeGame snakeGame;
    private Scene currentScene;
    private SnakeAccount account;
    private LoginPane loginPane;
    private SettingsMenu settingsMenu;
    private PauseMenu pauseMenu;
    private MainMenu mainMenu;
    private GameDisplay gameDisplay;
    private Stage mainStage;
    
    private SnakeAccountCollection accountCollection;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Initialize the game and display it to a window
     *
     * @param primaryStage A Stage used to display the elements of the game
     */
    @Override
    public void start(Stage primaryStage) {
        accountCollection = new SnakeAccountCollection();
        // Display main menu, start game if that option is selected, show menus, etc.
        loginPane = new LoginPane(accountCollection, this, primaryStage);
        settingsMenu = new SettingsMenu(this);
        mainMenu = new MainMenu(this);
        pauseMenu = new PauseMenu(this, snakeGame);
        //Scene mainMenuScene = mainMenu.getScene();
        //primaryStage.setScene(mainMenuScene);
        currentScene = new Scene(loginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        getAccounts();
        
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(currentScene);
        
        mainStage = primaryStage;
        mainStage.show();
        mainStage.setResizable(false);
        
        setOnCloseRequest(mainStage);
    }
    
    /**
     * Starts the game with the current settings
     */
    public void startGame() {
        mainStage.close();
        
        gameDisplay = new GameDisplay(WINDOW_WIDTH, WINDOW_HEIGHT + 40, snakeGame);
        // Resize the stage and scene to show the full game
        snakeGame = new SnakeGame(WINDOW_WIDTH, WINDOW_HEIGHT, settingsMenu.getCurrentInterval(),
                settingsMenu.getNumPellets(), gameDisplay.getGraphicsContext(), this);
        gameDisplay.setSnakeGame(snakeGame);
        
        // Create root node to hold the Canvas
        if (mainMenu.getScene() != null) {
            mainMenu.getScene().setRoot(new VBox());
        }
        currentScene = new Scene(gameDisplay, WINDOW_WIDTH, WINDOW_HEIGHT + 40);
        
        currentScene.setOnKeyPressed(event -> snakeGame.handleKeyPress(event.getCode()));
        currentScene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent key) -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                if (currentScene.getRoot().equals(pauseMenu)) {
                    continueGame();
                } else {
                    snakeGame.stop();
                    currentScene.setRoot(pauseMenu);
                }
            }
        });
        
        // Re-initialize the Stage
        mainStage = new Stage();
        mainStage.setTitle("Snake Game");
        mainStage.setScene(currentScene);
        mainStage.setResizable(false);
        mainStage.show();
        setOnCloseRequest(mainStage);
        
        gameDisplay.showCountdown();
        //snakeGame.start();
    }
    
    /**
     * Continue the game and set the scene back to the game
     */
    public void continueGame() {
        // Create root node to hold the Canvas
        currentScene.setRoot(gameDisplay);
        currentScene.setOnKeyPressed(event -> snakeGame.handleKeyPress(event.getCode()));
        
        mainStage.setScene(currentScene);
        snakeGame.start();
    }
    
    // Methods to check the state of SnakeGame and display corresponding menus
    
    /**
     * Set the current scene's root to a given menu
     *
     * @param pane A pane object representing one of the menus
     */
    public void setSceneRoot(Pane pane) {
        currentScene.setRoot(pane);
    }
    
    /**
     * Sets the window size
     *
     * @param tileWidth  an int
     * @param tileHeight an int
     */
    public void setWindowSize(int tileWidth, int tileHeight) {
        WINDOW_WIDTH = tileWidth * TILE_SIZE;
        WINDOW_HEIGHT = tileHeight * TILE_SIZE;
    }
    
    /**
     * Asks if they want to save the data
     *
     * @param primaryStage the primary stage
     */
    private void saveAlert(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Shutdown Confirmation");
        alert.setHeaderText("Save Data?");
        alert.setContentText("Do you want to save data before exiting?");
        
        ButtonType saveButton = new ButtonType("Save");
        ButtonType closeButton = new ButtonType("Close Without Saving");
        
        alert.getButtonTypes().setAll(saveButton, closeButton);
        
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == saveButton) {
                saveDataAndClose(primaryStage);
            } else if (buttonType == closeButton) {
                primaryStage.close();
            }
        });
    }
    
    /**
     * Saves the data and closes the window
     *
     * @param primaryStage the primary stage
     */
    private void saveDataAndClose(Stage primaryStage) {
        if (snakeGame.getScoreManager().getCurrentScore() > accountCollection.getOverallHighScore()) {
            accountCollection.updateOverallHighScore(snakeGame.getScoreManager().getCurrentScore());
            System.out.println("overall high score is now " + accountCollection.getOverallHighScore());
        }
        
        accountCollection.writeState();
        primaryStage.close();
    }
    
    /**
     * Sets the close request if the user closes the window
     *
     * @param primaryStage the primary stage
     */
    private void setOnCloseRequest(Stage primaryStage) {
        if (snakeGame != null) {
            primaryStage.setOnCloseRequest(event -> {
                if (loginPane.getCurrentAccount() != null || snakeGame.getScoreManager().getCurrentScore() > accountCollection.getOverallHighScore()) {
                    
                    if (loginPane.getCurrentAccount() != null && snakeGame.getScoreManager().getCurrentScore() > loginPane.getCurrentAccount().getHighScore()) {
                        loginPane.getCurrentAccount().setHighScore(snakeGame.getScoreManager().getCurrentScore());
                    }
                    saveAlert(primaryStage);
                }
            });
        }
    }
    
    /**
     * Retrieves the current account collection state
     */
    private void getAccounts() {
        try {
            accountCollection.readState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves the account collection
     *
     * @return accountCollection a SnakeAccountCollection object
     */
    public SnakeAccountCollection getAccountCollection() {
        return accountCollection;
    }
    
    /**
     * Returns the settings menu
     *
     * @return settingsMenu
     */
    public SettingsMenu getSettingsMenu() {
        return settingsMenu;
    }
    
    /**
     * Returns the main menu
     *
     * @return mainMenu
     */
    public MainMenu getMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu(this);
        }
        return mainMenu;
    }
    
    /**
     * Returns the login pane
     *
     * @return loginPane
     */
    public LoginPane getLoginPane() {
        return loginPane;
    }
    
    /**
     * Returns the pause menu
     *
     * @return pauseMenu
     */
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
    
    /**
     * Returns the game display
     *
     * @return gameDisplay
     */
    public GameDisplay getGameDisplay() {
        return gameDisplay;
    }
}
