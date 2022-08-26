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

	public Match(PlayerDefinition player1, PlayerDefinition player2, SetsToWin setsToWin) {
		this(new MatchId(),
				player1,
				player2,
				List.of(MatchEvent.confirmed()),
				setsToWin);
	}

	public Match addPoint(PlayerDefinition player) {
		var eventsCopy = new ArrayList<>(matchEvents);
		var instant = getNextInstant();
		var pointPlayerEventType = getPointPlayerEventType(player);
		eventsCopy.add(new MatchEvent(instant, pointPlayerEventType));
		return new Match(matchId, player1, player2, eventsCopy, setsToWin);
	}

	private InstantId getNextInstant() {
		var max = matchEvents.stream()
				.map(MatchEvent::instantId)
				.max(Comparator.comparingInt(InstantId::value))
				.map(InstantId::value)
				.orElseThrow();

		return new InstantId(max + 1);
	}

	private MatchEventType getPointPlayerEventType(PlayerDefinition player) {
		if (player.equals(player1)) {
			return MatchEventType.POINT_PLAYER1;
		}

		if (player.equals(player2)) {
			return MatchEventType.POINT_PLAYER2;
		}

		throw new IllegalStateException();
	}


}
