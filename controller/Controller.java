package controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JOptionPane;

import dataAccess.PlayerDAO;
import entities.PlayerAverage;
import model.GameLogicService;
import view.SimpleWindowService;

public class Controller {
	
	SimpleWindowService viewWindow;
	GameLogicService gameLogic;
	PlayerDAO playerDAO;
	int windowMessage = JOptionPane.YES_OPTION;
	PlayerAverage playerAverage;
	Optional<Integer> playerId = Optional.empty();
	
	public Controller(SimpleWindowService viewWindow, GameLogicService gameLogic, PlayerDAO playerDAO) {
		this.viewWindow = viewWindow;
		this.gameLogic = gameLogic;
		this.playerDAO = playerDAO;
	}
	
	public void executeGame() {
		login();
		playGame();
	}
	
	public void login() {
		viewWindow.addString("Enter your user name:\n");
		String playerName = viewWindow.getString();
		playerId = playerDAO.findPlayer(playerName);
		if(! playerId.isPresent()) {
			viewWindow.addString("User not in database, please register with admin");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException("Failed in finding user");
			}
			viewWindow.exit();
		}
	}
	
	public void playGame() {
		while (windowMessage == JOptionPane.YES_OPTION) {
			String goal = gameLogic.generateGoal();
			viewWindow.clear();
			viewWindow.addString("New game:\n");
			
			viewWindow.addString("For practice, the goal is: " + goal + "\n");//comment out or remove next line to play real games!
			
			String guess = viewWindow.getString();
			viewWindow.addString(guess +"\n");
			int numberOfGuesses = 1;
			String correctGuessesDisplayed = gameLogic.checkCorrectGoal(goal, guess);
			viewWindow.addString(correctGuessesDisplayed + "\n");
			while ( ! correctGuessesDisplayed.equals("BBBB,")) {
				numberOfGuesses++;
				guess = viewWindow.getString();
				viewWindow.addString(guess +"\n");
				correctGuessesDisplayed = gameLogic.checkCorrectGoal(goal, guess);
				viewWindow.addString(correctGuessesDisplayed + "\n");
			}
			playerDAO.addGuessesToPlayer(numberOfGuesses);
			showTopTen();
			windowMessage = JOptionPane.showConfirmDialog(null, "Correct, it took " + numberOfGuesses
					+ " guesses\nContinue?");
		}
		viewWindow.exit();
	}
	
	public void showTopTen() {
		ArrayList<PlayerAverage> topPlayerList = new ArrayList<>();
		topPlayerList = playerDAO.getTopTenPlayers();
		viewWindow.addString("Top Ten List\n    Player     Average\n");
		int position = 1;
		topPlayerList.sort((p1,p2)->(Double.compare(p1.getAverage(), p2.getAverage())));
		for (PlayerAverage player : topPlayerList) {
			viewWindow.addString(String.format("%3d %-10s%5.2f%n", position, player.getName(), player.getAverage()));
			if (position++ == 10) break;
		}
	}

}
