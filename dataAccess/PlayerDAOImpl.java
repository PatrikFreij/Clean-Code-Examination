package dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import entities.PlayerAverage;

public class PlayerDAOImpl implements PlayerDAO {
	
	private Statement stmt;
	private Statement getAllPlayers;
	private Statement getPlayerbyId;
	private ResultSet resultPlayerName;
	private ResultSet resultAllPlayers;
	private ResultSet resultPlayerWithId;
	private Connection connection;
	private String playerName;
	private int allPlayerId;
	private Optional<Integer> playerId = Optional.empty();
	private int numberOfGames;
	private int totalGuesses;
	
	public PlayerDAOImpl(ConnectionManager connectionManager) {
		connection = connectionManager.connectDB();
	}
	
	@Override
	public Optional<Integer> findPlayer(String playerName) {
		try {
			stmt = connection.createStatement();
			resultPlayerName = stmt.executeQuery("select id,name from players where name = '" + playerName + "'");
			if (resultPlayerName.next()) {
				playerId = Optional.of(resultPlayerName.getInt("id"));
				return playerId;
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed login");
		}
	}
	
	@Override
	public void addGuessesToPlayer(int nGuess) {
		try {
			stmt.executeUpdate("INSERT INTO results " + 
					"(result, player) VALUES (" + nGuess + ", " + playerId.get() + ")" );
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed adding guesses to player");
		}
	}
	
	@Override
	public ArrayList<PlayerAverage> getTopTenPlayers() {
		ArrayList<PlayerAverage> topList = new ArrayList<>();
		try {
			getAllPlayers = connection.createStatement();
			getPlayerbyId = connection.createStatement();
			resultAllPlayers = getAllPlayers.executeQuery("select * from players");
			while(resultAllPlayers.next()) {
				allPlayerId = resultAllPlayers.getInt("id");
				playerName = resultAllPlayers.getString("name");
				resultPlayerWithId = getPlayerbyId.executeQuery("select * from results where player = " + allPlayerId );
				numberOfGames = 0;
				totalGuesses = 0;
				while (resultPlayerWithId.next()) {
					numberOfGames++;
					totalGuesses += resultPlayerWithId.getInt("result");
				}
				if (numberOfGames > 0) {
					topList.add(new PlayerAverage(playerName, (double)totalGuesses/numberOfGames));
				}
			}
			return topList;
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get top ten players");
		}
	}
	
}