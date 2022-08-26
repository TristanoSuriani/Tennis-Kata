package nl.suriani.tenniskata.domain;


import nl.suriani.tenniskata.domain.enumeration.GamePoints;
import nl.suriani.tenniskata.domain.guard.Guard;

import java.util.List;

public record Game(GamePoints player1, GamePoints player2) {
	public Game {
		Guard.isNotNull(player1);
		Guard.isNotNull(player2);
	}
}
