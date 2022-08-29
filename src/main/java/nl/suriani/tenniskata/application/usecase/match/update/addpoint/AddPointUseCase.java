package nl.suriani.tenniskata.application.usecase.match.update.addpoint;

import nl.suriani.tenniskata.application.port.MatchRepository;

public class AddPointUseCase {
	private final MatchRepository matchRepository;

	public AddPointUseCase(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public AddPointResult add(AddPointCommand command) {
		var matchId = command.matchId();
		var maybeMatch = matchRepository.get(matchId);

		if (maybeMatch.isEmpty()) {
			return AddPointResult.failed("Cannot find the match with id " + matchId.value());
		}

		var updatedMatch = maybeMatch.get()
				.addPoint(command.teamId());

		matchRepository.put(updatedMatch);
		return AddPointResult.success(updatedMatch.getScore());
	}
}
