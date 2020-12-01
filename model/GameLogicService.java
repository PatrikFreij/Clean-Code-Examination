package model;

public interface GameLogicService {

	String generateGoal();

	String checkCorrectGoal(String goal, String guess);

}