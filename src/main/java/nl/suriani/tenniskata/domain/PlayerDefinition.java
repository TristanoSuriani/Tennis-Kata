package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.value.Name;
import nl.suriani.tenniskata.domain.value.PlayerId;

public record PlayerDefinition(PlayerId playerId, Name name) {
}
