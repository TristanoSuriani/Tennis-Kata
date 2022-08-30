package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.GamePoints;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.MatchPoints;
import nl.suriani.tenniskata.domain.value.SetPoints;

import java.util.ArrayList;
import java.util.List;

public record Score(List<Set> sets,
					MatchPoints pointsTeam1,
					MatchPoints pointsTeam2) {

	public Score {
		Guard.isNotNull(sets);
		Guard.isNotNull(pointsTeam1);
		Guard.isNotNull(pointsTeam2);
		Guard.isNotEmpty(sets);

		sets = List.copyOf(sets);
	}

	public static Score initialScore() {
		return new Score(List.of(new Set(List.of(
					new Game(GamePoints.LOVE, GamePoints.LOVE)),
					new SetPoints(0),
					new SetPoints(0))),
				new MatchPoints(0),
				new MatchPoints(0));
	}

	public Score team1Scores() {
		/*
			not complete. it only calculates game and set points at this moment.
		 */
		final var lastSet = getLastSet();
		final var allButLastSet = getAllButLastSet();
		final var lastSetUpdated = lastSet.team1Scores();

		final var updatedListOfSets = new ArrayList<>(allButLastSet);
		updatedListOfSets.add(lastSetUpdated);

		return new Score(updatedListOfSets, pointsTeam1, pointsTeam2);
	}



	public Set getLastSet() {
		return sets.stream()
				.reduce((prev, next) -> next)
				.orElseThrow();
	}

	public List<Set> getAllButLastSet() {
		return sets.subList(0, sets.size() - 1);
	}
}
