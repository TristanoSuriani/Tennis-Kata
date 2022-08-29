package nl.suriani.tenniskata.application.usecase.match.create;

import nl.suriani.tenniskata.application.port.MatchRepository;
import nl.suriani.tenniskata.domain.Match;
import nl.suriani.tenniskata.domain.TeamDefinition;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.shared.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateMatchUseCaseTest {

	@Mock
	private MatchRepository matchRepository;

	@Captor
	private ArgumentCaptor<Match> matchArgumentCaptor;

	private static final TeamDefinition TEAM1 = TestData.federerAndNadal;
	private static final TeamDefinition TEAM2 = TestData.venusAndSerena;

	@InjectMocks
	private CreateMatchUseCase useCase;

	@Test
	void create() {
		var command = givenARequestToCreateTheMatch();

		whenAMatchIsCreated(command);

		thenTheAMatchBetweenTheTwoPlayerIsSavedAsConfirmed();
	}

	private CreateMatchCommand givenARequestToCreateTheMatch() {
		return new CreateMatchCommand(TEAM1, TEAM2, SetsToWin.THREE);
	}

	private void whenAMatchIsCreated(CreateMatchCommand command) {
		var result = useCase.create(command);
		assertNotNull(result);
		assertNotNull(result.matchId());
	}

	private void thenTheAMatchBetweenTheTwoPlayerIsSavedAsConfirmed() {
		verify(matchRepository).put(matchArgumentCaptor.capture());
		var match = matchArgumentCaptor.getValue();
		assertEquals(TEAM1, match.team1());
		assertEquals(TEAM2, match.team2());
	}
}
