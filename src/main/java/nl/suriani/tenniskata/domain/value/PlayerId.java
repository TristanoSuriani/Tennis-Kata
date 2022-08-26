package nl.suriani.tenniskata.domain.value;

import java.util.UUID;

public final class PlayerId extends ValueType<UUID> {
	public PlayerId(UUID value) {
		super(value);
	}

	public PlayerId() {
		this(UUID.randomUUID());
	}
}
