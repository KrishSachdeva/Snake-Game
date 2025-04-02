package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * Represents the collection of Snake Accounts
 *
 * @author Krish Sachdeva
 */

public class SnakeAccountCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SnakeAccount> accounts;
	public int overallHighScore;

	public SnakeAccountCollection() {
		accounts = new ArrayList<>();
		addAccount(new SnakeAccount("Guest", ""));
		readOverallHighScore();
	}

	/**
	 * Retrieves the list of snake accounts
	 *
	 * @return accounts A list of SnakeAccounts
	 */
	public List<SnakeAccount> getAccounts() {
		return accounts;
	}

	/**
	 * Gets a SnakeAccount given its username
	 *
	 * @return account A single SnakeAccount
	 */
	public SnakeAccount getAccount(String username) {
		for (SnakeAccount account : accounts) {
			if (account.getUsername().equals(username)) {
				return account;
			}
		}
		return null;
	}

	/**
	 * Adds an account to the snake account collection
	 *
	 * @return A boolean showing whether it was successful or not
	 */
	public boolean addAccount(SnakeAccount account) {
		for (SnakeAccount existingAccount : accounts) {
			if (existingAccount.getUsername().equals(account.getUsername())) {
				showAlert("Username already taken!");
				return false;
			}
		}
		accounts.add(account);
		return true;
	}

	/**
	 * Returns the overall high score 
	 *
	 * @return overallHighScore an int 
	 */
	public int getOverallHighScore() {
		return overallHighScore;
	}

	/**
	 * Updates the overall high score 
	 *
	 * @param newHighScore an int which is the new overall high score
	 */
	public void updateOverallHighScore(int newHighScore) {
		overallHighScore = newHighScore;
	}

	/**
	 * Writes the current state of the account collection to the ser file
	 *
	 */
	public void writeState() {
		try (FileOutputStream fileOut = new FileOutputStream("jukebox_accounts.ser");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
			objectOut.writeObject(accounts);
			writeOverallHighScore();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the current state of the account collection from ser file
	 *
	 */
	public void readState() {
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("jukebox_accounts.ser"))) {
			for (SnakeAccount account : (List<SnakeAccount>) inFile.readObject()) {
				if (!account.getUsername().isEmpty())
	                accounts.add(account);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the overall high score from ser file
	 *
	 */
	private void readOverallHighScore() {
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("overall_high_score.ser"))) {
			overallHighScore = (int) inFile.readObject();
		} catch (IOException | ClassNotFoundException e) {
			overallHighScore = 0; // If unable to read, initialize to 0
		}
	}

	/**
	 * Writes the overall high score to the ser file
	 *
	 */
	private void writeOverallHighScore() {
		try (ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("overall_high_score.ser"))) {
			outFile.writeObject(overallHighScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows alert according to the given message
	 *
	 * @param message A string showing the alert message
	 */
	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Alert");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}