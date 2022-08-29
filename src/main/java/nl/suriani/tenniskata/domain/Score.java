package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.GamePoints;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.MatchPoints;
import nl.suriani.tenniskata.domain.value.SetPoints;

import java.util.List;

public record Score(List<Set> sets,
					MatchPoints team1,
					MatchPoints team2) {

	public Score {
		Guard.isNotNull(sets);
		Guard.isNotNull(team1);
		Guard.isNotNull(team2);
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
}
