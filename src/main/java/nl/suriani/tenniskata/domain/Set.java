package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.SetPoints;

import java.util.List;

public record Set(List<Game> games,
				  SetPoints player1,
				  SetPoints player2) {
	public Set {
		Guard.isNotNull(games);
		Guard.isNotNull(player1);
		Guard.isNotNull(player2);
		Guard.isNotEmpty(games);
	}
}
