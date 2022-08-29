package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.TeamId;

import java.util.Optional;

public record TeamDefinition(TeamId id,
							 PlayerDefinition player1,
							 Optional<PlayerDefinition> player2) {

	public TeamDefinition {
		Guard.isNotNull(id);
		Guard.isNotNull(player1);
		Guard.isNotNull(player2);
	}

	public TeamDefinition(PlayerDefinition player1, PlayerDefinition player2) {
		this(new TeamId(), player1, Optional.of(player2));
	}

	public TeamDefinition(PlayerDefinition player) {
		this(new TeamId(), player, Optional.empty());
	}
}
