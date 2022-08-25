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
}
