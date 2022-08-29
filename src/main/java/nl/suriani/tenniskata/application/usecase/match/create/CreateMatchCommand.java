package nl.suriani.tenniskata.application.usecase.match.create;

import nl.suriani.tenniskata.domain.TeamDefinition;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.guard.Guard;

public record CreateMatchCommand(TeamDefinition team1,
								 TeamDefinition team2,
								 SetsToWin setsToWin) {
	public CreateMatchCommand {
		Guard.isNotNull(team1);
		Guard.isNotNull(team2);
		Guard.isNotNull(setsToWin);
	}
}
