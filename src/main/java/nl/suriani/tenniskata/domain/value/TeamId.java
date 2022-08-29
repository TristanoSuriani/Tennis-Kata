package nl.suriani.tenniskata.domain.value;

import java.util.UUID;

public final class TeamId extends ValueType<UUID> {

	public TeamId(UUID value) {
		super(value);
	}

	public TeamId() {
		this(UUID.randomUUID());
	}
}
