package nl.suriani.tenniskata.application.usecase.match.update.addpoint;

import nl.suriani.tenniskata.application.port.MatchRepository;
import nl.suriani.tenniskata.domain.Match;
import nl.suriani.tenniskata.domain.TeamDefinition;
import nl.suriani.tenniskata.domain.enumeration.MatchEventType;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.value.MatchId;
import nl.suriani.tenniskata.shared.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddPointUseCaseTest {

	@Spy
	private MatchRepository matchRepository;

	@InjectMocks
	private AddPointUseCase useCase;

	private static final TeamDefinition TEAM1 = TestData.federerAndNadal;
	private static final TeamDefinition TEAM2 = TestData.venusAndSerena;
	private static final Match MATCH = new Match(TEAM1, TEAM2, SetsToWin.TWO);
	private static final MatchId MATCH_ID = MATCH.matchId();

	@Test
	void addPoint_butNoMatch() {
		whenCannotFindMatchWithGivenId();
		thenFail("Cannot find the match with id " + MATCH_ID.value());
	}

	@Test
	void addFirstPoint_toMatch_player1() {
		whenAMatchIsFound();
		thenAddPointToTeam(TEAM1);

		var expectedEvents = List.of(MatchEventType.MATCH_CONFIRMED,
				MatchEventType.POINT_TEAM1);


	}

	private void whenAMatchIsFound() {
		Mockito.when(matchRepository.get(MATCH_ID))
				.thenReturn(Optional.of(MATCH));
	}

	private void thenAddPointToTeam(TeamDefinition team) {
		var command = addPointTo(team);
		useCase.add(command);
	}

	private void whenCannotFindMatchWithGivenId() {
		Mockito.when(matchRepository.get(MATCH_ID))
				.thenReturn(Optional.empty());
	}

	private AddPointCommand givenAddPointRequest() {
		return new AddPointCommand(MATCH_ID, TestData.justFederer.id());
	}

	private void thenFail(String note) {
		var command = addPointTo(TEAM1);
		var result = useCase.add(command);
		assertTrue(result.score().isEmpty());
		assertTrue(result.note().isPresent());
		assertEquals(AddPointResult.SuccessFailed.FAILED, result.successFailed());
		assertEquals(note, result.note().get());
	}

	private AddPointCommand addPointTo(TeamDefinition team) {
		return new AddPointCommand(MATCH_ID, team.id());
	}
}
