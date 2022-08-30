package nl.suriani.tenniskata.domain;

import nl.suriani.tenniskata.domain.enumeration.GamePoints;
import nl.suriani.tenniskata.shared.testdata.TestData;
import org.junit.jupiter.api.Test;

import static nl.suriani.tenniskata.domain.enumeration.GamePoints.GAME;
import static nl.suriani.tenniskata.domain.enumeration.GamePoints.LOVE;
import static org.junit.jupiter.api.Assertions.*;

class UpdateMatchTest {
	@Test
	void addUnitTest() {
		given(TestData.federerAndNadalVsVenusAndSerenaTwoSetsMatch)
				.when().team1ScoresAPoint()
				.and().team1ScoresAPoint()
				.and().team1ScoresAPoint()
				.and().team1ScoresAPoint()
				.then()
				.scoreLastGameIs(GAME, LOVE)
				.scoreLastSetIs(1, 0)
				.noTeamHasWonYet();

	}

	private Match whenTeam1ScoresAPoint(Match match) {
		return match.team1Scores();
	}

	private Given given(Match match) {
		return new Given(match);
	}

	static class Given {
		private Match match;

		Given(Match match) {
			this.match = match;
		}

		When when() {
			return new When(match);
		}
	}

	static class When {
		private Match match;

		When(Match match) {
			this.match = match;
		}

		When team1ScoresAPoint() {
			return new When(match.team1Scores());
		}

		When and() {
			return this;
		}

		Then then() {
			return new Then(match);
		}
	}

	static class Then {

		private Match match;

		Then(Match match) {
			this.match = match;
		}

		When when() {
			return new When(match);
		}

		Then scoreLastGameIs(GamePoints pointsTeam1, GamePoints pointsTeam2) {
			assertEquals(pointsTeam1, match.score().getLastSet().getLastGame().pointsTeam1());
			assertEquals(pointsTeam2, match.score().getLastSet().getLastGame().pointsTeam2());
			return this;
		}

		Then scoreLastSetIs(int pointsTeam1, int pointsTeam2) {
			assertEquals(pointsTeam1, match.score().getLastSet().pointsTeam1().value());
			assertEquals(pointsTeam2, match.score().getLastSet().pointsTeam2().value());
			return this;
		}

		Then noTeamHasWonYet() {
			assertFalse(match.isFinished());
			return this;
		}
	}
}
