package nl.suriani.tenniskata.application.usecase.match.create;

import nl.suriani.tenniskata.domain.PlayerDefinition;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.guard.Guard;

public record CreateMatchCommand(PlayerDefinition player1,
								 PlayerDefinition player2,
								 SetsToWin setsToWin) {
	public CreateMatchCommand {
		Guard.isNotNull(player1);
		Guard.isNotNull(player2);
		Guard.isNotNull(setsToWin);
	}
}
