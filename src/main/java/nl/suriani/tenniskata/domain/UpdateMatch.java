package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.MatchEventType;

import java.util.function.BiFunction;

public class UpdateMatch implements BiFunction<Match, MatchEventType, Match> {

	@Override
	public Match apply(Match match, MatchEventType matchEventType) {
		return match;
	}
}
