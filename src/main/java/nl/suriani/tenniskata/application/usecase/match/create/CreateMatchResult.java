package nl.suriani.tenniskata.application.usecase.match.create;

import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.MatchId;

public record CreateMatchResult(MatchId matchId) {
	public CreateMatchResult {
		Guard.isNotNull(matchId);
	}
}
