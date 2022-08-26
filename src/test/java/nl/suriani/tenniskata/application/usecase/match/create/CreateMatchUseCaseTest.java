package nl.suriani.tenniskata.application.usecase.match.create;

import nl.suriani.tenniskata.application.port.MatchRepository;
import nl.suriani.tenniskata.domain.Match;
import nl.suriani.tenniskata.domain.PlayerDefinition;
import nl.suriani.tenniskata.domain.enumeration.SetsToWin;
import nl.suriani.tenniskata.domain.value.Name;
import nl.suriani.tenniskata.domain.value.PlayerId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateMatchUseCaseTest {


	@Spy
	private MatchRepository matchRepository;

	@Captor
	private ArgumentCaptor<Match> matchArgumentCaptor;

	private static final PlayerDefinition PLAYER1 = new PlayerDefinition(new PlayerId(UUID.randomUUID()), new Name("Federer"));
	private static final PlayerDefinition PLAYER2 = new PlayerDefinition(new PlayerId(UUID.randomUUID()), new Name("Nadal"));

	@InjectMocks
	private CreateMatchUseCase useCase;

	@Test
	void create() {
		var command = givenARequestToCreateTheMatch();

		whenAMatchIsCreated(command);

		thenTheAMatchBetweenTheTwoPlayerIsSavedAsConfirmed();
	}

	private CreateMatchCommand givenARequestToCreateTheMatch() {
		return new CreateMatchCommand(PLAYER1, PLAYER2, SetsToWin.THREE);
	}

	private void whenAMatchIsCreated(CreateMatchCommand command) {
		var result = useCase.create(command);
		assertNotNull(result);
		assertNotNull(result.matchId());
	}

	private void thenTheAMatchBetweenTheTwoPlayerIsSavedAsConfirmed() {
		verify(matchRepository).put(matchArgumentCaptor.capture());
		var match = matchArgumentCaptor.getValue();
		assertEquals(PLAYER1, match.player1());
		assertEquals(PLAYER2, match.player2());
	}
}
