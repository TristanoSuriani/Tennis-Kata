package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.GamePoints;
import nl.suriani.tenniskata.domain.guard.Guard;

public record Points(GamePoints player1, GamePoints player2) {
	public Points {
		Guard.isNotNull(player1);
		Guard.isNotNull(player2);
	}
}
