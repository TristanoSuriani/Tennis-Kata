package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.MatchEventType;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.InstantId;

import java.time.LocalDateTime;

public record MatchEvent(LocalDateTime timestamp, InstantId instantId, MatchEventType type) {

	public MatchEvent {
		Guard.isNotNull(timestamp);
		Guard.isNotNull(instantId);
		Guard.isNotNull(type);
	}

	public MatchEvent(InstantId instantId, MatchEventType type) {
		this(LocalDateTime.now(), instantId, type);
	}

	public static MatchEvent confirmed() {
		return new MatchEvent(new InstantId(0), MatchEventType.MATCH_CONFIRMED);
	}
}
