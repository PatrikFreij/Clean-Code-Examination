package Main;

import controller.Controller;
import dataAccess.ConnectionManager;
import dataAccess.PlayerDAO;
import dataAccess.PlayerDAOImpl;
import model.GameLogicService;
import model.MooGameLogic;
import model.AnimalGameLogic;
import view.SimpleWindow;
import view.SimpleWindowService;

public class Moo {

	public static void main(String[] args) {
		String windowMooName = "Moo";
		String windowAnimalName = "Guess the animal";
		SimpleWindowService viewWindow = new SimpleWindow(windowAnimalName);
		ConnectionManager cm = new ConnectionManager();
		PlayerDAO playerDAO = new PlayerDAOImpl(cm);
		GameLogicService gameMooLogic = new MooGameLogic();
		GameLogicService gameAnimalLogic = new AnimalGameLogic();
		Controller controller = new Controller(viewWindow, gameAnimalLogic, playerDAO);
		
		controller.executeGame();
	}
}