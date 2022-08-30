package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.SetPoints;

import java.util.ArrayList;
import java.util.List;

public record Set(List<Game> games,
				  SetPoints pointsTeam1,
				  SetPoints pointsTeam2) {

	public Set {
		Guard.isNotNull(games);
		Guard.isNotNull(pointsTeam1);
		Guard.isNotNull(pointsTeam2);
		Guard.isNotEmpty(games);
	}

	public List<Game> getAllButLastGame() {
		return games.subList(0, games.size() - 1);
	}

	public Game getLastGame() {
		return games().stream()
				.reduce((prev, next) -> next)
				.orElseThrow();
	}

	public Set team1Scores() {
		var lastGame = getLastGame();
		var allButLastGame = getAllButLastGame();
		var lastGameUpdated = lastGame.team1Scores();
		var updatedListOfGames = new ArrayList<>(allButLastGame);
		updatedListOfGames.add(lastGameUpdated);

		if (lastGameUpdated.team1HasWon()) {
			return new Set(updatedListOfGames,
					new SetPoints(pointsTeam1.value() + 1),
					pointsTeam2);
		}

		return new Set(updatedListOfGames, pointsTeam1, pointsTeam2);
	}
}
