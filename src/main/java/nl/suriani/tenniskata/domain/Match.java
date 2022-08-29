package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.GamePoints;
import nl.suriani.tenniskata.domain.enumeration.MatchEventType;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.InstantId;
import nl.suriani.tenniskata.domain.value.MatchId;
import nl.suriani.tenniskata.domain.value.MatchPoints;
import nl.suriani.tenniskata.domain.value.SetPoints;
import nl.suriani.tenniskata.domain.value.TeamId;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record Match(MatchId matchId,
					TeamDefinition team1,
					TeamDefinition team2,
					List<MatchEvent> matchEvents,
					SetsToWin setsToWin) {

	public Match {
		Guard.isNotNull(team1);
		Guard.isNotNull(team2);
		Guard.isNotNull(matchEvents);
		Guard.isNotEmpty(matchEvents);
		Guard.isNotNull(setsToWin);

		matchEvents = List.copyOf(matchEvents);
	}

	public Match(TeamDefinition player1, TeamDefinition player2, SetsToWin setsToWin) {
		this(new MatchId(),
				player1,
				player2,
				List.of(MatchEvent.confirmed()),
				setsToWin);
	}

	public Match addPoint(TeamId team) {
		var eventsCopy = new ArrayList<>(matchEvents);
		var instant = getNextInstant();
		var pointPlayerEventType = getPointPlayerEventType(team);
		eventsCopy.add(new MatchEvent(instant, pointPlayerEventType));
		return new Match(matchId, team1, team2, eventsCopy, setsToWin);
	}

	private InstantId getNextInstant() {
		var max = matchEvents.stream()
				.map(MatchEvent::instantId)
				.max(Comparator.comparingInt(InstantId::value))
				.map(InstantId::value)
				.orElseThrow();

		return new InstantId(max + 1);
	}

	private MatchEventType getPointPlayerEventType(TeamId team) {
		if (team.equals(team1.id())) {
			return MatchEventType.POINT_TEAM1;
		}

		if (team.equals(team2.id())) {
			return MatchEventType.POINT_TEAM1;
		}

		throw new IllegalStateException();
	}


	public Score getScore() {
		return new Score(List.of(
				new Set(List.of(new Game(GamePoints.FIFTEEN, GamePoints.LOVE)),
						new SetPoints(0),
						new SetPoints(0))),
				new MatchPoints(0),
				new MatchPoints(0));
	}
}
