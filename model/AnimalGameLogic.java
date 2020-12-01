package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimalGameLogic implements GameLogicService {
	
	List<String> animals = new ArrayList<>();
	Random randomIndex = new Random();
	String animalGoal;
	String correctAnswer = "BBBB,";
	String wrongAnswer = "C";
	
	@Override
	public String generateGoal() {
		addAnimals();
		return animalGoal = animals.get(randomIndex.nextInt(animals.size()));
	}

	@Override
	public String checkCorrectGoal(String goal, String guess) {
		if(goal.equalsIgnoreCase(guess)) return correctAnswer;
		return wrongAnswer;
	}
	
	public void addAnimals() {
		animals.add("bear");
		animals.add("orca");
		animals.add("roan");
		animals.add("goat");
		animals.add("wolf");
		animals.add("oxen");
	}

}
