package nl.suriani.tenniskata.application.usecase.match.update.addpoint;

import nl.suriani.tenniskata.application.port.MatchRepository;
import nl.suriani.tenniskata.domain.Match;
import nl.suriani.tenniskata.domain.PlayerDefinition;
import nl.suriani.tenniskata.domain.Score;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.value.MatchId;
import nl.suriani.tenniskata.domain.value.Name;
import nl.suriani.tenniskata.domain.value.PlayerId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddPointUseCaseTest {

	@Spy
	private MatchRepository matchRepository;

	@InjectMocks
	private AddPointUseCase useCase;

	private final MatchId MATCH_ID = new MatchId();
	private final PlayerId PLAYER_1 = new PlayerId();
	private final PlayerId PLAYER_2 = new PlayerId();
	private final AddPointCommand COMMAND = new AddPointCommand(MATCH_ID, PLAYER_1);

	@Test
	void addPoint_butNoMatch() {
		whenCannotFindMatchWithGivenId();
		thenFail("Cannot find the match with id " + MATCH_ID.value());
	}

	@Test
	void addFirstPoint_toMatch_player1() {
		var match = givenBrandNewMatch();
		var score = whenAMatchIsFound(match);
		assertEquals(0, score.player1().value());
		assertEquals(0, score.player2().value());
		assertEquals(0, score.sets().get(0).player1().value());
		assertEquals(0, score.sets().get(0).player2().value());
		assertEquals("15", score.sets().get(0).games().get(0).player1().display());
		assertEquals("0", score.sets().get(0).games().get(0).player2().display());
	}

	private Score whenAMatchIsFound(Match match) {
		Mockito.when(matchRepository.get(COMMAND.matchId()))
				.thenReturn(Optional.of(match));
		return match.getScore();
	}

	private Match givenBrandNewMatch() {
		return new Match(new PlayerDefinition(PLAYER_1, new Name("Federer")),
				new PlayerDefinition(PLAYER_2, new Name("Nadal")),
				SetsToWin.TWO);
	}

	private void whenCannotFindMatchWithGivenId() {
		Mockito.when(matchRepository.get(COMMAND.matchId()))
				.thenReturn(Optional.empty());
	}

	private AddPointCommand givenAddPointRequest() {
		return new AddPointCommand(MATCH_ID, PLAYER_1);
	}

	private void thenFail(String note) {
		var result = useCase.add(COMMAND);
		assertTrue(result.score().isEmpty());
		assertTrue(result.note().isPresent());
		assertEquals(AddPointResult.SuccessFailed.FAILED, result.successFailed());
		assertEquals(note, result.note().get());
	}
}
