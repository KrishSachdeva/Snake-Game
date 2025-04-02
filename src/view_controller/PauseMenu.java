package view_controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.CustomFont;
import model.SnakeGame;

/**
 * A scene that shows the Pause Menu
 *
 * @author Brendan Bamberg
 */

public class PauseMenu extends VBox {

    private Label pauseLabel, confirmLabel;
    private Button continueGame, quitToMenu, confirm, back;
    
    private SnakeGame theGame;
    private SnakeGUI snakeGUI;
    private CustomFont labelFont, labelFontTwo, buttonFont;
    private Background background;
    
    /**
     * Initializes a new PauseMenu to be displayed during a game of Snake
     *
     * @param snakeGUI The main GUI that shows the game
     * @param theGame A SnakeGame object representing the current game
     */
    public PauseMenu(SnakeGUI snakeGUI, SnakeGame theGame) {
        labelFont = new CustomFont(40);
        labelFontTwo = new CustomFont(24);
        buttonFont = new CustomFont(12);
        background = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        this.theGame = theGame;
        this.snakeGUI = snakeGUI;
        
        initializeComponents();
        layoutGUI();
    }
    
    /**
     * Initializes the components of the PauseMenu and adds listeners to some components
     */
    private void initializeComponents() {
        pauseLabel = new Label("Pause");
        confirmLabel = new Label("Are you sure?");
        setFont(pauseLabel);
        confirmLabel.setFont(labelFontTwo.getCustomFont());
        confirmLabel.setTextFill(Color.WHITE);
        confirmLabel.setBackground(background);
        
        continueGame = new Button("Continue");
        quitToMenu = new Button("Main Menu");
        confirm = new Button("Yes");
        back = new Button("No");
        setFont(continueGame);
        setFont(quitToMenu);
        setFont(confirm);
        setFont(back);
        
        continueGame.setOnAction(event-> {
            snakeGUI.continueGame();
        });
        
        quitToMenu.setOnAction(event -> {
            switchLayout(1);
        });

        confirm.setOnAction(event -> {
            snakeGUI.setSceneRoot(snakeGUI.getMainMenu());
            switchLayout(0);
        });

        back.setOnAction(event -> {
            switchLayout(0);
        });
    }
    
    /**
     * Lays out the components of the PauseMeny
     */
    private void layoutGUI() {
        this.getChildren().addAll(pauseLabel, spacingButton(), continueGame, quitToMenu);
        this.setAlignment(Pos.CENTER);
        this.setBackground(background);
        this.setSpacing(20);
    }
    
    /**
     * Switches between different layouts to show either the main pause menu or the confirmation to quit
     *
     * @param layout An integer representing which layout should be shown
     */
    private void switchLayout(int layout) {
        this.getChildren().clear();
        if (layout == 0) {
            this.getChildren().addAll(pauseLabel, spacingButton(), continueGame, quitToMenu);
            this.setAlignment(Pos.CENTER);
            this.setBackground(background);
            this.setSpacing(20);
        } else if (layout == 1) {
            HBox buttonBox = new HBox();
            buttonBox.getChildren().addAll(confirm, spacingButton(), back);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.setSpacing(20);

            this.getChildren().addAll(confirmLabel, buttonBox);
            this.setAlignment(Pos.CENTER);
            this.setBackground(background);
            this.setSpacing(20);
        }
    }
    
    /**
     * A function that returns an empty button used to space components of the menu
     *
     * @return An empty Button object
     */
    private Button spacingButton() {
        Button spacingButton = new Button();
        setFont(spacingButton);
        spacingButton.setMinWidth(60);
        return spacingButton;
    }
    
    /**
     * Sets the font style of a label to a custom font and background
     *
     * @param label The desired Label to be adjusted
     */
    private void setFont(Label label) {
        label.setFont(labelFont.getCustomFont());
        label.setTextFill(Color.WHITE);
        label.setBackground(background);
    }
    
    /**
     * Sets the font style of a button to a custom font and background
     *
     * @param button The desired Button to be adjusted
     */
    private void setFont(Button button) {
        button.setFont(buttonFont.getCustomFont());
        button.setTextFill(Color.WHITE);
        button.setBackground(background);
    }
    
}
