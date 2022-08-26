package nl.suriani.tenniskata.application.usecase.match.update.addpoint;

import nl.suriani.tenniskata.domain.value.MatchId;
import nl.suriani.tenniskata.domain.value.PlayerId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddPointUseCaseTest {

	@InjectMocks
	AddPointUseCase useCase;

	@Test
	void addPointEmptyMatch() {
		var matchId = new MatchId();
		var playerId = new PlayerId();
		var command = new AddPointCommand(matchId, playerId);
		var result = useCase;
		assertNotNull(result);
	}
}
