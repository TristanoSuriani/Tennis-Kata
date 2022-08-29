package nl.suriani.tenniskata.application.usecase.match.update.addpoint;

import nl.suriani.tenniskata.domain.PlayerDefinition;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.MatchId;
import nl.suriani.tenniskata.domain.value.PlayerId;
import nl.suriani.tenniskata.domain.value.TeamId;

public record AddPointCommand(MatchId matchId, TeamId teamId) {
	public AddPointCommand {
		Guard.isNotNull(matchId);
		Guard.isNotNull(teamId);
	}
}
