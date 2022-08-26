package nl.suriani.tenniskata.domain.value;

import java.util.UUID;

public final class MatchId extends ValueType<UUID> {
	public MatchId(UUID value) {
		super(value);
	}

	public MatchId() {
		this(UUID.randomUUID());
	}
}
