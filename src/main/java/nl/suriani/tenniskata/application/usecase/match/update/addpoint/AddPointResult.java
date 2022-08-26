package nl.suriani.tenniskata.application.usecase.match.update.addpoint;

import nl.suriani.tenniskata.domain.Score;
import nl.suriani.tenniskata.domain.guard.Guard;
import nl.suriani.tenniskata.domain.value.MatchId;

import java.util.Optional;

public record AddPointResult(SuccessFailed successFailed,
							 Optional<Score> score,
							 Optional<String> note) {
	public AddPointResult {
		Guard.isNotNull(successFailed);
		Guard.isNotNull(score);
		Guard.isNotNull(note);
	}

	public static AddPointResult success(Score score) {
		return new AddPointResult(SuccessFailed.SUCCESS, Optional.of(score), Optional.empty());
	}

	public static AddPointResult failed(String note) {
		return new AddPointResult(SuccessFailed.FAILED, Optional.empty(), Optional.of(note));
	}

	public enum SuccessFailed {
		SUCCESS,
		FAILED
	}
}
