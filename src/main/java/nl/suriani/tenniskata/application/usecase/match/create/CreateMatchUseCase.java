package nl.suriani.tenniskata.application.usecase.match.create;

import nl.suriani.tenniskata.application.port.MatchRepository;
import nl.suriani.tenniskata.domain.Match;

public class CreateMatchUseCase {
	private final MatchRepository matchRepository;

	public CreateMatchUseCase(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public CreateMatchResult create(CreateMatchCommand createMatchCommand) {
		var match = new Match(createMatchCommand.player1(),
				createMatchCommand.player2(),
				createMatchCommand.setsToWin());

		matchRepository.put(match);
		return new CreateMatchResult(match.matchId());
	}
}
