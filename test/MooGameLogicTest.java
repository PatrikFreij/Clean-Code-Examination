package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GameLogicMock;
import model.GameLogicService;

class MooGameLogicTest {
	
	GameLogicService gl;
	String secretNumber;
	String setSecretNumber;
	String allBulls;

	@BeforeEach
	void setUp() throws Exception {
		gl = new GameLogicMock();
		secretNumber = gl.generateGoal();
		allBulls = "BBBB,";
		setSecretNumber = "1234";
	}
	
	@Test
	void testRandomNumberGenerator() {
		String[] randomNumberGenerator = {"1234", "1337", "4290", "2667", "1607", "2356", "3467", "9695", "2035", "4367"};
		String guess = "2667";
		String answer;
		int numberOfGuesses = 0;
		Random random = new Random();
		for (int i = 0; i <= randomNumberGenerator.length; i = random.nextInt(10)) {
			answer = randomNumberGenerator[i];
			System.out.println(answer);
			if(answer.equals(guess)) {
				System.out.println("Guesses: " + numberOfGuesses);
				assertEquals(guess, answer);
				break;
			} else {
				System.out.println("wrong guess");
			}
			numberOfGuesses++;
		}
	}

	@Test
	void correctGuessTest() {
		String[] randomNumberGenerator = {"1234", "1337", "4290", "2667", "1607", "2356", "3467", "9695", "2035", "4367"};
		String guess = "2667";
		String answer;
		String checkAnswer;
		int numberOfGuesses = 0;
		Random random = new Random();
		for (int i = 0; i <= randomNumberGenerator.length; i = random.nextInt(10)) {
			answer = randomNumberGenerator[i];
			System.out.println(answer);
			checkAnswer = gl.checkCorrectGoal(answer, guess);
			System.out.println(checkAnswer);
			if(checkAnswer.contains("BBBB,CC")) {
				checkAnswer = checkAnswer.substring(0, checkAnswer.length() - 2);
				if(allBulls.equals(checkAnswer)) {
					System.out.println("Guesses: " + numberOfGuesses);
					assertEquals(guess, answer);
					break;
				}
			} else {
				System.out.println("Wrong guess");
			}
			numberOfGuesses++;
		}
	}
	
	@Test
	void checkGoalTest() {
		String expected = secretNumber;
		assertEquals(expected, secretNumber);
	}
	
	@Test
	void checkCorrectGuess() {
		String allCorrect = "BBBB,";
		String checkAnswer = gl.checkCorrectGoal(secretNumber, secretNumber);
		assertEquals(checkAnswer, allCorrect);
	}
	
	@Test
	void checkClassNotNull() {
		assertNotNull(gl);
	}
	
	@Test
	void checkCorrectRandomGenerator() {
		String goal = gl.generateGoal();
		assertNotEquals("123", goal);
	}
	
	@Test
	void checkAmountOfNumbers() {
		String fourNumbers = gl.generateGoal();
		assertTrue(() -> fourNumbers.length() == 4);
	}
	

}
