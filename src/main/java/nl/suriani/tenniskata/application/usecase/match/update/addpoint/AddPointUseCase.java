package nl.suriani.tenniskata.application.usecase.match.update.addpoint;

import nl.suriani.tenniskata.application.port.MatchRepository;

public class AddPointUseCase {
	private final MatchRepository matchRepository;

	public AddPointUseCase(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

}
