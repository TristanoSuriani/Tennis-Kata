package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.MatchId;

import java.util.List;

public record Match(MatchId matchId,
					PlayerDefinition player1,
					PlayerDefinition player2,
					List<MatchEvent> matchEvents,
					SetsToWin setsToWin) {

	public Match {
		Guard.isNotNull(player1);
		Guard.isNotNull(player2);
		Guard.isNotNull(matchEvents);
		Guard.isNotEmpty(matchEvents);
		Guard.isNotNull(setsToWin);

		matchEvents = List.copyOf(matchEvents);
	}
}
