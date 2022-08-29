package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.Name;
import nl.suriani.tenniskata.domain.value.PlayerId;

public record PlayerDefinition(PlayerId id, Name name) {

	public PlayerDefinition {
		Guard.isNotNull(id);
		Guard.isNotNull(name);
	}

	public PlayerDefinition(Name name) {
		this(new PlayerId(), name);
	}
}
