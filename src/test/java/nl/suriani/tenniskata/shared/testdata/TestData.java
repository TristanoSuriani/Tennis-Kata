package nl.suriani.tenniskata.shared.testdata;

import nl.suriani.tenniskata.domain.PlayerDefinition;
import nl.suriani.tenniskata.domain.TeamDefinition;
import nl.suriani.tenniskata.domain.value.Name;

public interface TestData {
	PlayerDefinition federer = player("R. Federer");
	PlayerDefinition nadal = player("R. Nadal");
	PlayerDefinition venusWilliams = player("V. Williams");
	PlayerDefinition serenaWilliams = player("S. Williams");

	TeamDefinition federerAndNadal = team(federer, nadal);
	TeamDefinition venusAndSerena = team(venusWilliams, serenaWilliams);
	TeamDefinition justFederer = team(federer);
	TeamDefinition justNadal = team(nadal);

	static TeamDefinition team(PlayerDefinition player1, PlayerDefinition player2) {
		return new TeamDefinition(player1, player2);
	}

	static TeamDefinition team(PlayerDefinition player) {
		return new TeamDefinition(player);
	}

	static PlayerDefinition player(String value) {
		return new PlayerDefinition(name(value));
	}

	static Name name(String value) {
		return new Name(value);
	}
}
