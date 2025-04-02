package view_controller;

/**
 * LoginPane snake class
 *
 * @author Kayla Pierson
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CustomFont;
import model.SnakeAccount;
import model.SnakeAccountCollection;

public class LoginPane extends VBox {
	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginButton;
	// private Button logoutButton;
	private Button createAccountButton;
	private Button skipButton1;
	private Button skipButton2;
	private Label statusLabel;
	private Label welcomeLabel;
	private Label usernameLabel;
	private Label passwordLabel;
	private SnakeAccountCollection accountCollection;
	private SnakeAccount currentAccount;
	private SnakeGUI snakeGUI;
	private Stage stage;
	private Background background;
	private MainMenu mainMenu;

	private CustomFont headerFont, optionsFont, labelFont;

	public LoginPane(SnakeAccountCollection accountCollection, SnakeGUI snakeGUI, Stage stage) {
		this.snakeGUI = snakeGUI;
		this.stage = stage;
		this.accountCollection = accountCollection;

		headerFont = new CustomFont(40);
		optionsFont = new CustomFont(14);
		labelFont = new CustomFont(12);
		background = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
		initializeComponents();
		layoutComponents();
	}

	/**
	 * Switches the GUI to the main menu
	 *
	 */
	private void switchToMainMenu() {
		snakeGUI.getMainMenu().initializeComponents(snakeGUI);
		snakeGUI.getMainMenu().layoutComponents();
		snakeGUI.setSceneRoot(snakeGUI.getMainMenu());
	}

	/**
	 * Initializes the components of the LoginPane
	 *
	 */
	private void initializeComponents() {
		welcomeLabel = new Label("Snake");
		setFont(welcomeLabel, headerFont);

		loginButton = new Button("Login");
		createAccountButton = new Button("Create new account");
		setFont(loginButton);
		setFont(createAccountButton);
		loginButton.setStyle("-fx-border-color: white; -fx-border-width: 2px;");
		createAccountButton.setStyle("-fx-border-color: white; -fx-border-width: 2px;");

		usernameLabel = new Label("username: ");
		passwordLabel = new Label("password: ");
		setFont(usernameLabel, labelFont);
		setFont(passwordLabel, labelFont);

		usernameField = new TextField();
		passwordField = new PasswordField();

		statusLabel = new Label("");
		setFont(statusLabel, labelFont);

		skipButton1 = new Button("skip");
		setFont(skipButton1);
		skipButton2 = new Button("\u23e9");
		skipButton2.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-text-fill: white;");

		loginButton.setOnAction(e -> login());
		createAccountButton.setOnAction(e -> addNewAccount());
		skipButton1.setOnAction(e -> signInAsGuest());
		skipButton2.setOnAction(e -> signInAsGuest());
	}

	
	/**
	 * Layouts the LoginPane components
	 *
	 */
	@SuppressWarnings("unchecked")
	private void layoutComponents() {
		HBox loginOptions = new HBox();
		loginOptions.getChildren().addAll(loginButton, createAccountButton);
		loginOptions.setAlignment(Pos.CENTER);
		loginOptions.setSpacing(35);

		HBox usernameArea = new HBox();
		usernameArea.getChildren().addAll(usernameLabel, usernameField);
		usernameArea.setAlignment(Pos.CENTER);

		HBox passwordArea = new HBox();
		passwordArea.getChildren().addAll(passwordLabel, passwordField);
		passwordArea.setAlignment(Pos.CENTER);

		HBox skipArea = new HBox();
		skipArea.getChildren().addAll(skipButton1, skipButton2);
		skipArea.setAlignment(Pos.CENTER_RIGHT);

		this.setBackground(background);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(welcomeLabel, loginOptions, usernameArea, passwordArea, statusLabel, skipArea);
		this.setSpacing(60);

	}

	/**
	 * Logins a user after they click the login button
	 */
	private void login() {
	    String username = usernameField.getText();
	    String password = passwordField.getText();
	    
	    if (username.isBlank() || password.isBlank()) {
	    	statusLabel.setText("Must login first");
	    	return;
	    }

	    for (SnakeAccount account : accountCollection.getAccounts()) {
	        if (account.getUsername().equals(username)) {
	            if (account.login(password)) {
	                usernameField.clear();
	                passwordField.clear();
	                currentAccount = account;
	                switchToMainMenu();
	                return;
	            } else {
	                statusLabel.setText("Invalid password");
	                return;
	            }
	        }
	    }
	    statusLabel.setText("Invalid username");
	}

	/**
	 * Adds a new account to the account collection
	 *
	 */
	private void addNewAccount() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		

		for (SnakeAccount account : accountCollection.getAccounts()) {
			if (account.getUsername().equals(username)) {
				statusLabel.setText("username taken");
				return;
			}
		}
		
		if (!username.isEmpty() && !password.isEmpty()) {
			SnakeAccount newAccount = new SnakeAccount(username, password);
			accountCollection.addAccount(newAccount);
			statusLabel.setText("Account created");
	
			usernameField.clear();
			passwordField.clear();
		}
		else
			statusLabel.setText("Must add username/password");
	}

	/**
	 * Retrieves the current logged in snake account
	 *
	 * @return currentAccount a SnakeAccount 
	 */
	public SnakeAccount getCurrentAccount() {
		return currentAccount;
	}

	/**
	 * Sets the font to a Label
	 *
	 * @param label The Label to change the font of
	 * @param font A CustomFont
	 */
	private void setFont(Label label, CustomFont font) {
		label.setFont(font.getCustomFont());
		label.setTextFill(Color.WHITE);
		label.setBackground(background);
	}

	/**
	 * Sets the font to a Button
	 *
	 * @param button The Button to change the font of
	 */
	private void setFont(Button button) {
		button.setFont(optionsFont.getCustomFont());
		button.setTextFill(Color.WHITE);
		button.setBackground(background);
	}

	/**
	 * Signs the user into the guest account
	 *
	 */
	private void signInAsGuest() {
		currentAccount = accountCollection.getAccount("Guest");
		switchToMainMenu();
	}

}