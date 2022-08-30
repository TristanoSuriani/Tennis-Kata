package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.MatchEventType;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.InstantId;
import nl.suriani.tenniskata.domain.value.MatchId;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record Match(MatchId matchId,
					TeamDefinition team1,
					TeamDefinition team2,
					List<MatchEvent> matchEvents,
					SetsToWin setsToWin,
					Score score) {

	public Match {
		Guard.isNotNull(team1);
		Guard.isNotNull(team2);
		Guard.isNotNull(matchEvents);
		Guard.isNotEmpty(matchEvents);
		Guard.isNotNull(setsToWin);
		Guard.isNotNull(score);

		matchEvents = List.copyOf(matchEvents);
	}

	public Match(TeamDefinition player1, TeamDefinition player2, SetsToWin setsToWin) {
		this(new MatchId(),
				player1,
				player2,
				List.of(MatchEvent.confirmed()),
				setsToWin,
				Score.initialScore());
	}

	public boolean isFinished() {
		return team1Won() || team2Won();
	}

	public boolean team1Won() {
		return getLastEvent().type() == MatchEventType.TEAM1_WON;
	}

	public boolean team2Won() {
		return getLastEvent().type() == MatchEventType.TEAM2_WON;
	}


	public Match team1Scores() {
		var instant = getNextInstant();
		var eventsCopy = new ArrayList<>(matchEvents);

		eventsCopy.add(new MatchEvent(instant, MatchEventType.POINT_TEAM1));
		var updatedScore = score.team1Scores();
		var lastSet = score.getLastSet();
		var lastGame = lastSet.getLastGame();
		if (lastGame.isDeuce()) {
			eventsCopy.add(new MatchEvent(instant, MatchEventType.DEUCE));
		}
		// not complete, let's stop here for now
		return new Match(matchId, team1, team2, eventsCopy, setsToWin, updatedScore);
	}

	private InstantId getNextInstant() {
		var max = matchEvents.stream()
				.map(MatchEvent::instantId)
				.max(Comparator.comparingInt(InstantId::value))
				.map(InstantId::value)
				.orElseThrow();

		return new InstantId(max + 1);
	}

	private MatchEvent getLastEvent() {
		return matchEvents.stream()
				.reduce((prev, next) -> next)
				.orElseThrow();
	}
}
