package nl.suriani.tenniskata.application.port;

import nl.suriani.tenniskata.domain.Match;
import nl.suriani.tenniskata.domain.value.MatchId;

import java.util.Optional;

public interface MatchRepository {
	void put(Match match);

	Optional<Match> get(MatchId matchId);
}
