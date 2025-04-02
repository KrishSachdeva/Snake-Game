package model;

/**
 * Represents a single Snake Account
 *
 * @author Kayla Pierson
 */

import java.io.Serializable;

public class SnakeAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private int highScore;

	public SnakeAccount(String username, String password) {
		this.username = username;
		this.password = password;
		this.highScore = 0;
	}

	public String getUsername() {
		return username;
	}

	public boolean login(String enteredPassword) {
		if (enteredPassword.equals(password)) {
			return true;
		}
		return false;
	}

	public void setHighScore(int score) {
		this.highScore = score;
	}

	public int getHighScore() {
		return this.highScore;
	}
}