package dataAccess;

import java.util.ArrayList;
import java.util.Optional;

import entities.PlayerAverage;

public interface PlayerDAO {

	Optional<Integer> findPlayer(String playerName);

	void addGuessesToPlayer(int nGuess);

	ArrayList<PlayerAverage> getTopTenPlayers();

}