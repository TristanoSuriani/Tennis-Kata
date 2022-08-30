package nl.suriani.tenniskata.domain;


import nl.suriani.tenniskata.domain.enumeration.GamePoints;
import nl.suriani.tenniskata.domain.guard.Guard;

import static nl.suriani.tenniskata.domain.enumeration.GamePoints.*;

public record Game(GamePoints pointsTeam1, GamePoints pointsTeam2) {

	public Game {
		Guard.isNotNull(pointsTeam1);
		Guard.isNotNull(pointsTeam2);
	}

	public Game team1Scores() {
		if (pointsTeam2 == GAME) {
			throw new IllegalStateException("Cannot mutate score");
		}
		return switch (pointsTeam1()) {
			case LOVE -> new Game(FIFTEEN, pointsTeam2);
			case FIFTEEN -> new Game(THIRTY, pointsTeam2);
			case THIRTY -> new Game(FORTY, pointsTeam2);
			case FORTY -> pointsTeam2 == ADVANTAGE_IN ?
					new Game(FORTY, FORTY) :
					new Game(GAME, pointsTeam2);
			case ADVANTAGE_IN -> new Game(GAME, pointsTeam2);
			case GAME -> throw new IllegalStateException("Cannot mutate score");
		};
	}

	public Game team2Scores() {
		if (pointsTeam1 == GAME) {
			throw new IllegalStateException("Cannot mutate score");
		}
		return switch (pointsTeam1()) {
			case LOVE -> new Game(pointsTeam1, FIFTEEN);
			case FIFTEEN -> new Game(pointsTeam1, THIRTY);
			case THIRTY -> new Game(pointsTeam1, FORTY);
			case FORTY -> pointsTeam2 == ADVANTAGE_IN ?
					new Game(FORTY, FORTY) :
					new Game(pointsTeam1, GAME);
			case ADVANTAGE_IN -> new Game(pointsTeam1, GAME);
			case GAME -> throw new IllegalStateException("Cannot mutate score");
		};
	}

	public boolean isDeuce() {
		return pointsTeam1 == FORTY && pointsTeam2 == FORTY;
	}

	public boolean team1HasWon() {
		return pointsTeam1 == GAME;
	}

	public boolean team2HasWon() {
		return pointsTeam2 == GAME;
	}

}
