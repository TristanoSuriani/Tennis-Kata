package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.MatchPoints;

import java.util.List;

public record Score(List<Set> sets,
					MatchPoints player1,
					MatchPoints player2) {
	public Score {
		Guard.isNotNull(sets);
		Guard.isNotNull(player1);
		Guard.isNotNull(player2);
		Guard.isNotEmpty(sets);

		sets = List.copyOf(sets);
	}
}
